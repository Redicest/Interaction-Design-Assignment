package com.example.medicinecontrolsystem.ComponentMainPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicinecontrolsystem.R
import com.example.medicinecontrolsystem.customFunctions.TimeBarViewModel

@Composable
fun TimeBar(
    modifier: Modifier = Modifier,
    timeBarViewModel: TimeBarViewModel
) {
    // 使用 StateFlow 收集状态
    val selectedIndex by timeBarViewModel.selectedIndex.collectAsState()

    // 或者使用可变状态（两种方式都可以）
    // val selectedIndex = viewModel.altSelectedIndex

    // 定义时间标签列表
    val timeLabels = listOf(
        stringResource(R.string.time_morning),
        stringResource(R.string.time_noon),
        stringResource(R.string.time_evening),
        stringResource(R.string.time_beforeSleep)
    )
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        // 添加细的灰色条
        Column(
            modifier = modifier.fillMaxWidth()
        ){
            Spacer(modifier = Modifier.height(24.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color(0xFFE0E0E0))//0xFFE0E0E0
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                timeLabels.forEachIndexed { index, label ->
                    TimeBarItem(
                        text = label,
                        isSelected = selectedIndex == index,
                        onClick = { timeBarViewModel.setSelectedIndex(index) }
                    )
                }
            }
        }
    }
}

@Composable
fun TimeBarItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
    ) {
        // 文本 - 选中时黑色，未选中时灰色
        Text(
            text = text,
            color = if (isSelected) Color.Black else Color(0xFF888888),
            fontSize = 14.sp,
            fontWeight = if (isSelected) MaterialTheme.typography.titleLarge.fontWeight
            else MaterialTheme.typography.bodyMedium.fontWeight
        )

        if(isSelected){
            Spacer(modifier = Modifier.height(2.dp))
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }


        // 下方的条 - 选中时蓝色且变粗，未选中时灰色
        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .width(40.dp)
                .height(if (isSelected) 5.dp else 2.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isSelected) Color(0xFF2196F3) else Color(0x00E0E0E0)),

            )
        }

    }
}

@Composable
fun TimeBarP(
    modifier: Modifier = Modifier,
) {

    // 或者使用可变状态（两种方式都可以）
    // val selectedIndex = viewModel.altSelectedIndex

    // 定义时间标签列表
    val timeLabels = listOf(
        stringResource(R.string.time_morning),
        stringResource(R.string.time_noon),
        stringResource(R.string.time_evening),
        stringResource(R.string.time_beforeSleep)
    )
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        // 添加细的灰色条
        Column(
            modifier = modifier.fillMaxWidth()
        ){
            Spacer(modifier = Modifier.height(24.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color(0xFFE0E0E0))//0xFFE0E0E0
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                timeLabels.forEachIndexed { index, label ->
                    TimeBarItemP(
                        text = label,
                        isSelected = false,
                        onClick = { }
                    )
                }
            }
        }
    }
}

@Composable
fun TimeBarItemP(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
    ) {
        // 文本 - 选中时黑色，未选中时灰色
        Text(
            text = text,
            color = if (isSelected) Color.Black else Color(0xFF888888),
            fontSize = 14.sp,
            fontWeight = if (isSelected) MaterialTheme.typography.titleLarge.fontWeight
            else MaterialTheme.typography.bodyMedium.fontWeight
        )

        if(isSelected){
            Spacer(modifier = Modifier.height(2.dp))
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }


        // 下方的条 - 选中时蓝色且变粗，未选中时灰色
        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .width(40.dp)
                .height(if (isSelected) 5.dp else 2.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isSelected) Color(0xFF2196F3) else Color(0x00E0E0E0)),

                )
        }

    }
}




@Preview
@Composable
fun ImeBarPreview(){
    TimeBarP()
}