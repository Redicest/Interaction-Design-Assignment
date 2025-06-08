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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicinecontrolsystem.customFunctions.TimeViewModel
import com.example.medicinecontrolsystem.customFunctions.CompletedTaskViewModel

//组件：顶部信息卡
@Composable
fun TopInformationCard(modifier:Modifier = Modifier,
                       gradinetBrush: Brush,
                       systemTimeViewModel: TimeViewModel = viewModel(),
                       completedTaskViewModel: CompletedTaskViewModel = viewModel()
){
    val formattedTime by systemTimeViewModel.formattedTime
    val formattedDate by systemTimeViewModel.formattedData
    val formattedWeekDay by systemTimeViewModel.formattedWeekDay

    val totalTasks = completedTaskViewModel.getTaskProgress().second
    val completedTasks = completedTaskViewModel.getTaskProgress().first


    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ){
        Box(
        ){
            Card(
                modifier = Modifier
                    .size(width = 1012.dp, height = 474.dp),
                shape = RoundedCornerShape(25.dp)
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
                            .align(Alignment.TopEnd) // 右侧中间
                            .padding(top = 22.dp,end = 55.dp) // 只添加右侧内边距
                            .size(68.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // 添加水平居中
                        modifier = Modifier.fillMaxWidth() // 添加宽度填充
                    ){
                        Spacer(modifier = Modifier.height(37.dp))
                        Text(
                            text = formattedDate + "  " + formattedWeekDay,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W400,
                            fontSize = 48.sp,
                        )
                    }
                    Text(
                        text = formattedTime,
                        textAlign = TextAlign.Center,
                        fontSize = 256.sp,
                        fontWeight = FontWeight.W700,
                        modifier = Modifier.align(Alignment.TopCenter).padding(top = 80.dp)
                    )
                    Text(
                        text ="已完成"+"${completedTasks}"+"/"+"${totalTasks}",
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.W400,
                        modifier = Modifier.align(Alignment.BottomStart).padding(start = 34.dp, bottom = 71.dp)
                    )
                    taskProgressBar(
                        completed = completedTasks,
                        total = totalTasks,
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 45.dp)
                    )
                }
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



@Preview(widthDp = 1080, heightDp = 2160)
@Composable
fun TopInformationCardPreview(){
    TopInformationCard(gradinetBrush = Brush.horizontalGradient(colors = listOf(Color(0xFFD9F0FF),
        Color(0xFFF2F7FB)
    )))
}