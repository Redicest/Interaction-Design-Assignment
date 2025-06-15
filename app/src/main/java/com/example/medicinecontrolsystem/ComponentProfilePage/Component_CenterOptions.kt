package com.example.medicinecontrolsystem.ComponentProfilePage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Group
import androidx.compose.material.icons.rounded.Message
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp

@Composable
fun CenterOptions(
    modifier: Modifier = Modifier,
    baseUnit: Dp // 添加基础单位参数
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = baseUnit * 1.5f),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        IconWithTextGroup(text = "房间老人", baseUnit = baseUnit)
        IconWithTextCalendarMonth(text = "日程管理", baseUnit = baseUnit)
        IconWithTextMessage(text = "家属留言", baseUnit = baseUnit)
    }
}

@Composable
fun IconWithTextGroup(
    text: String,
    modifier:Modifier = Modifier,
    baseUnit:Dp
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        Icon(
            Icons.Rounded.Group,
            contentDescription = "房间老人",
            modifier = Modifier
                .size(baseUnit * 4f)
        )
        Text(
            text = text,
            fontWeight = FontWeight.W400,
            fontSize = (baseUnit.value * 1.8).sp
        )
    }
}

@Composable
fun IconWithTextCalendarMonth(
    text: String,
    modifier:Modifier = Modifier,
    baseUnit: Dp
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        Icon(
            Icons.Rounded.CalendarMonth,
            contentDescription = "房间老人",
            modifier = Modifier
                .size(baseUnit * 4f)
        )
        Text(
            text = text,
            fontWeight = FontWeight.W400,
            fontSize = (baseUnit.value * 1.8).sp
        )
    }
}

@Composable
fun IconWithTextMessage(
    text: String,
    modifier:Modifier = Modifier,
    baseUnit: Dp
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        Icon(
            Icons.AutoMirrored.Rounded.Message,
            contentDescription = "房间老人",
            modifier = Modifier
                .size(baseUnit * 4f)
        )
        Text(
            text = text,
            fontWeight = FontWeight.W400,
            fontSize = (baseUnit.value * 1.8).sp
        )
    }
}

@Preview(
    device = Devices.PIXEL_3A,
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CenterOptionsPreview(){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val baseUnit = min(screenHeight, screenWidth) / 40f
    CenterOptions(baseUnit = baseUnit)
}
