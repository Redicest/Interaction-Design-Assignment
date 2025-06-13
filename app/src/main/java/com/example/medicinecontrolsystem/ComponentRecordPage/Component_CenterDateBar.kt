package com.example.medicinecontrolsystem.ComponentRecordPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Calendar
import com.example.medicinecontrolsystem.data.data_Date
import com.example.medicinecontrolsystem.customFunctions.TimeViewModel

@Composable
fun CenterDateBarList(
    timeViewModel: TimeViewModel,
    modifier: Modifier = Modifier,
    baseUnit: Dp // 添加基础单位参数
) {
    // 获取当前日期
    val currentDate by remember { derivedStateOf { timeViewModel.getCurrentDate() } }
    val (currentYear, currentMonth, currentDay) = currentDate

    // 生成日期列表
    val dateList = remember(currentYear, currentMonth, currentDay) {
        generateDateList(currentYear, currentMonth, currentDay)
    }

    // 添加选中状态管理
    var selectedIndex by remember { mutableIntStateOf(0) }

    val listState = rememberLazyListState()

    // 自动滚动到当前日期位置
    LaunchedEffect(Unit) {
        listState.scrollToItem(0)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-baseUnit * 0.125f)), // 上移覆盖背景条
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(horizontal = baseUnit * 0.4f, vertical = baseUnit * 0.2f)
        ) {
            itemsIndexed(dateList) { index, item ->
                CenterDateBarItem(
                    dateItem = item,
                    isSelected = (index == selectedIndex),
                    onClick = { selectedIndex = index },
                    baseUnit = baseUnit, // 传递基础单位
                    modifier = Modifier.padding(horizontal = baseUnit * 0.25f)
                )
            }
        }
    }
}

@Composable
fun CenterDateBarItem(
    dateItem: data_Date,
    isSelected: Boolean,
    onClick: () -> Unit,
    baseUnit: Dp, // 添加基础单位参数
    modifier: Modifier = Modifier
) {
    // 定义渐变画笔（选中状态使用）
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xFFF2F7FB), Color(0xFFD9F0FF))
    )

    val backgroundColor = if (isSelected) gradientBrush else Color(0xFFEEEEEE)
    val textColor = if (isSelected) Color.Black else Color(0xFF555555)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier
                .size(width = baseUnit * 5f, height = baseUnit * 7f) // 相对尺寸
                .clickable { onClick() },
            shape = RoundedCornerShape(baseUnit * 0.4f) // 相对圆角
        ) {
            // 使用 when 表达式处理不同类型的背景
            when (backgroundColor) {
                is Brush -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(brush = backgroundColor as Brush),
                        contentAlignment = Alignment.Center
                    ) {
                        DateTextContent(dateItem, textColor, baseUnit) // 传递基础单位
                    }
                }

                is Color -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = backgroundColor as Color),
                        contentAlignment = Alignment.Center
                    ) {
                        DateTextContent(dateItem, textColor, baseUnit) // 传递基础单位
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(baseUnit * 0.45f))

        // 下方的条 - 选中时蓝色且变粗，未选中时透明（覆盖在背景条上）
        Box(
            modifier = Modifier
                .width(baseUnit * 4f) // 相对宽度
                .height(if (isSelected) baseUnit * 0.25f else baseUnit * 0.125f) // 相对高度
                .background(
                    if (isSelected) Color(0xFF2196F3) else Color.Transparent, // 未选中时透明
                    RoundedCornerShape(baseUnit * 0.5f) // 相对圆角
                )
                .clip(RoundedCornerShape(baseUnit * 0.5f))
        )
    }
}

// 提取文本内容为单独函数
@Composable
private fun DateTextContent(dateItem: data_Date, textColor: Color, baseUnit: Dp) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = dateItem.dayOfWeek,
            color = textColor,
            fontSize = (baseUnit.value * 1.3).sp, // 相对字体大小
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(baseUnit * 0.2f))

        Text(
            text = dateItem.dayOfMonth,
            color = textColor,
            fontSize = (baseUnit.value * 1.3).sp, // 相对字体大小
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

private fun generateDateList(
    currentYear: Int,
    currentMonth: Int,
    currentDay: Int
): List<data_Date> {
    val calendar = Calendar.getInstance().apply {
        set(currentYear, currentMonth - 1, currentDay)
    }

    val dateList = mutableListOf<data_Date>()
    val weekDays = listOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")

    for (i in 0 until 7) {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        // 移除 isSelected 属性（由外部控制）
        dateList.add(
            data_Date(
                dayOfWeek = weekDays[dayOfWeek - 1], // 直接使用预定义列表
                dayOfMonth = day.toString(),
                year = year,
                month = month,
                day = day
            )
        )

        calendar.add(Calendar.DAY_OF_MONTH, 1)
    }

    return dateList
}

@Composable
fun IndicatorBar(
    itemCount: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(itemCount) { index ->
            val isSelected = index == selectedIndex
            val height = if (isSelected) 4.dp else 2.dp
            val color = if (isSelected) Color.Blue else Color.Gray

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(height)
                    .padding(horizontal = 10.dp)
                    .background(color, RoundedCornerShape(50))
            )
        }
    }
}