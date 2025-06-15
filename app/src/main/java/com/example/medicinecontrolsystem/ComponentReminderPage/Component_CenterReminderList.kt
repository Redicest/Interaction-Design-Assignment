package com.example.medicinecontrolsystem.ComponentReminderPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import com.example.medicinecontrolsystem.customFunctions.TimeViewModel
import com.example.medicinecontrolsystem.data.data_Patient
import com.example.medicinecontrolsystem.data.patients
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

@Composable
fun CenterReminderList(
    modifier: Modifier = Modifier,
    baseUnit: Dp,
    viewModel: TimeViewModel
) {
    // 1. 过滤有效提醒（日期和时间都不为"null"）
    val validPatients = patients.filter {
        it.reminderDate != "null" && it.reminderTime != "null"
    }

    // 2. 按提醒日期分组
    val groupedPatients = validPatients.groupBy { it.reminderDate }

    // 3. 获取当前日期（用于计算剩余时间）
    val (currentYear, currentMonth, currentDay) = viewModel.getCurrentDate()
    val currentDateString = "$currentYear.$currentMonth.$currentDay"

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(baseUnit * 2f)
    ) {
        groupedPatients.forEach { (date, patientsInGroup) ->
            // 日期标题项
            item {
                DateHeader(
                    date = date,
                    baseUnit = baseUnit,
                    isToday = date == currentDateString
                )
            }

            // 该日期下的所有患者提醒
            items(patientsInGroup) { patient ->
                CenterReminderItem(
                    patient = patient,
                    baseUnit = baseUnit,
                    currentDate = currentDateString,
                    currentTime = viewModel.formattedTime.value
                )
            }
        }
    }
}

@Composable
fun DateHeader(
    date: String,
    baseUnit: Dp,
    isToday: Boolean = false
) {
    // 解析日期显示格式：2025.6.1 → 6月1日
    val displayDate = try {
        val parts = date.split(".")
        "${parts[1].toInt()}月${parts[2].toInt()}日"
    } catch (e: Exception) {
        date
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = baseUnit * 1.5f, )
    ) {
        Text(
            text = if (isToday) "今天 ($displayDate)" else displayDate,
            color = if (isToday) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = (baseUnit.value * 1.8).sp,
            modifier = Modifier.padding(start = baseUnit)
        )
    }
}

@Composable
fun CenterReminderItem(
    patient: data_Patient,
    modifier: Modifier = Modifier,
    baseUnit: Dp,
    currentDate: String,
    currentTime: String
) {
    // 计算剩余时间
    val timeLeft = calculateTimeLeft(
        reminderDate = patient.reminderDate,
        reminderTime = patient.reminderTime,
        currentDate = currentDate,
        currentTime = currentTime
    )

    // 开关状态
    var isReminderActive by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = baseUnit * 0.5f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = baseUnit * 1.2f)
                .height(baseUnit * 7f),
            shape = RoundedCornerShape(baseUnit),
            elevation = CardDefaults.cardElevation(defaultElevation = baseUnit * 0.4f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFFFFFFF))
                    .padding(horizontal = baseUnit * 1.3f, vertical = baseUnit * 1.3f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    // 提醒时间
                    Text(
                        text = patient.reminderTime,
                        fontWeight = FontWeight.W600,
                        fontSize = (baseUnit.value * 1.8).sp
                    )

                    // 患者姓名
                    Text(
                        text = "王小花",
                        fontSize = (baseUnit.value * 1.5).sp,
                        modifier = Modifier.padding(top = baseUnit * 0.3f)
                    )
                }

                // 剩余时间
                Text(
                    text = timeLeft,
                    color = if (timeLeft.startsWith("-")) Color.Red else Color(0xFF03A9F4),
                    fontWeight = FontWeight.Bold,
                    fontSize = (baseUnit.value * 1.6).sp
                )

                // 提醒开关
                Switch(
                    checked = isReminderActive,
                    onCheckedChange = { isReminderActive = it },
                    colors = SwitchDefaults.colors(
                        checkedBorderColor = Color(0xFFFFFFFF),
                        checkedTrackColor = Color(0xFF03A9F4)
                    )
                )
            }
        }
    }
}

// 计算剩余时间
private fun calculateTimeLeft(
    reminderDate: String,
    reminderTime: String,
    currentDate: String,
    currentTime: String
): String {
    return try {
        val sdf = SimpleDateFormat("yyyy.M.d HH:mm", Locale.getDefault())

        // 当前日期时间
        val currentDateTime = sdf.parse("$currentDate $currentTime")

        // 提醒日期时间
        val reminderDateTime = sdf.parse("$reminderDate $reminderTime")

        // 计算时间差（分钟）
        val diffMinutes = ((reminderDateTime.time - currentDateTime.time) / (1000 * 60)).toInt()

        when {
            diffMinutes > 0 -> {
                when {
                    diffMinutes >= 60 -> "${diffMinutes / 60}小时${diffMinutes % 60}分钟后"
                    else -> "${diffMinutes}分钟后"
                }
            }
            diffMinutes < 0 -> {
                val absMin = diffMinutes.absoluteValue
                when {
                    absMin >= 60 -> "已过${absMin / 60}小时"
                    else -> "已过${absMin}分钟"
                }
            }
            else -> "立即"
        }
    } catch (e: Exception) {
        "时间错误"
    }
}

@Preview(
    device = Devices.PIXEL_3A,
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CenterReminderListPreview() {
    // 创建模拟的 TimeViewModel
    val mockViewModel = object : TimeViewModel() {
        override fun getCurrentDate() = Triple(2025, 6, 1)
    }

    // 获取屏幕尺寸
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val baseUnit = min(screenHeight, screenWidth) / 40f

    CenterReminderList(
        baseUnit = baseUnit,
        viewModel = mockViewModel
    )
}