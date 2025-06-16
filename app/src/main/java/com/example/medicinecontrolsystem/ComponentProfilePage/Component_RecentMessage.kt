package com.example.medicinecontrolsystem.ComponentProfilePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddAlert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp

@Composable
fun RecentMessage(
    modifier: Modifier = Modifier,
    baseUnit: Dp // 添加基础单位参数
){
    Card(
        modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = baseUnit * 1.5f),
        shape = RoundedCornerShape(baseUnit)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = baseUnit * 1f, vertical = baseUnit * 1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "最近消息",
                        fontWeight = FontWeight.W400,
                        fontSize = (baseUnit.value * 1.6).sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "还有一条未读消息",
                        fontWeight = FontWeight.W400,
                        fontSize = (baseUnit.value * 1.6).sp,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(baseUnit * 1f))
                SystemReminderBar(
                    baseUnit = baseUnit,
                    informationContent = "新的分药日程已更新，请查看。",
                    time = "2小时前"
                    )
                Spacer(modifier = Modifier.height(baseUnit * 1f))
                SystemReminderBar(
                    baseUnit = baseUnit,
                    informationContent = "新的分药日程即将开始，请做好准备。",
                    time = "3天前"
                )
            }
        }
    }
}

@Composable
fun SystemReminderBar(
    baseUnit:Dp,
    informationContent: String,
    time: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(){
                Icon(
                    Icons.Rounded.AddAlert,
                    contentDescription = "System Information",
                    modifier = Modifier
                        .size(baseUnit * 2.5f),
                    tint = Color(0xFFFFD700)
                )
                Spacer(modifier = Modifier.width(baseUnit * 0.5f))
                Column() {
                    Text(
                        text = "系统通知",
                        fontWeight = FontWeight.W400,
                        fontSize = (baseUnit.value * 1.6).sp,
                        color = Color.Black
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = informationContent,
                            fontWeight = FontWeight.W400,
                            fontSize = (baseUnit.value * 1.6).sp,
                            color = Color.Gray,
                        )
                        Text(
                            text = time,
                            fontWeight = FontWeight.W400,
                            fontSize = (baseUnit.value * 1.3).sp,
                            color = Color(0xFFA2A2A2),
                        )
                    }

                }
            }
        }
    }
}

@Preview(
    device = Devices.PIXEL_3A,
    showSystemUi = true,
    showBackground = true,
    backgroundColor = 1
)
@Composable
fun RecentMessagePreview(){
    // 获取屏幕尺寸
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val baseUnit = min(screenHeight, screenWidth) / 40f
    RecentMessage(baseUnit = baseUnit)
}
