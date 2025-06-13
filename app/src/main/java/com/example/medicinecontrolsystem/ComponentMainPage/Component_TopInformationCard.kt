package com.example.medicinecontrolsystem.ComponentMainPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Devices
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicinecontrolsystem.customFunctions.TimeViewModel
import com.example.medicinecontrolsystem.customFunctions.CompletedTaskViewModel

//组件：顶部信息卡
@Composable
fun TopInformationCard(modifier:Modifier = Modifier,
                       gradinetBrush: Brush,
                       systemTimeViewModel: TimeViewModel = viewModel(),
                       completedTaskViewModel: CompletedTaskViewModel
){
    val formattedTime by systemTimeViewModel.formattedTime
    val formattedDate by systemTimeViewModel.formattedDate
    val formattedWeekDay by systemTimeViewModel.formattedWeekDay

    val totalTasks = completedTaskViewModel.getTaskProgress().second
    val completedTasks = completedTaskViewModel.getTaskProgress().first

    // 获取当前组合的尺寸约束
    val density = LocalDensity.current

    Box( //总体Box
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradinetBrush),
            contentAlignment = Alignment.TopCenter
        ) {
            Icon(
                imageVector = Icons.Rounded.DateRange,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp, end = 8.dp)
                    .size(24.dp) // 相对尺寸
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ){
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$formattedDate  $formattedWeekDay",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp, // 固定字体大小（或根据密度调整）
                )
            }
            Text(
                text = formattedTime,
                textAlign = TextAlign.Center,
                fontSize = 96.sp, // 固定字体大小
                fontWeight = FontWeight.W700,
                modifier = Modifier
                    .align(Alignment.Center)
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 10.dp)
                    .align(alignment = Alignment.BottomStart)
            ){
                Text(
                    text ="已完成 $completedTasks/$totalTasks",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp, // 固定字体大小
                    fontWeight = FontWeight.W400,
                )
                Spacer(modifier = Modifier.height(3.dp))
                taskProgressBar(
                    completed = completedTasks,
                    total = totalTasks,
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                )
            }

        }
    }
}

@Composable
fun taskProgressBar(
    completed:Int,
    total:Int,
    modifier: Modifier = Modifier
){
    val progress = if(total > 0) completed.toFloat()/total.toFloat()
    else 0f
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFFE0E0E))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(6.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFFFD700),
                                Color(0xFFFFA000)
                            )
                        )
                    )
            )
        }
    }
}

//组件：顶部信息卡
@Composable
fun TopInformationCardP(modifier:Modifier = Modifier,
                       gradinetBrush: Brush,
                       systemTimeViewModel: TimeViewModel = viewModel(),
){
    val formattedTime by systemTimeViewModel.formattedTime
    val formattedDate by systemTimeViewModel.formattedDate
    val formattedWeekDay by systemTimeViewModel.formattedWeekDay


    // 获取当前组合的尺寸约束
    val density = LocalDensity.current

    Box( //总体Box
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradinetBrush),
            contentAlignment = Alignment.TopCenter
        ) {
            Icon(
                imageVector = Icons.Rounded.DateRange,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp, end = 8.dp)
                    .size(24.dp) // 相对尺寸
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ){
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$formattedDate  $formattedWeekDay",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp, // 固定字体大小（或根据密度调整）
                )
            }
            Text(
                text = formattedTime,
                textAlign = TextAlign.Center,
                fontSize = 96.sp, // 固定字体大小
                fontWeight = FontWeight.W700,
                modifier = Modifier
                    .align(Alignment.Center)
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 10.dp)
                    .align(alignment = Alignment.BottomStart)
            ){
                Text(
                    text ="已完成 2/7",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp, // 固定字体大小
                    fontWeight = FontWeight.W400,
                )
                Spacer(modifier = Modifier.height(3.dp))
                taskProgressBar(
                    completed = 2,
                    total = 7,
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                )
            }

        }
    }
}

@Preview
@Composable
fun TopInformationCardPreview(){
    TopInformationCardP(gradinetBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFD9F0FF),
            Color(0xFFF2F7FB)
        )
    )
    )
}