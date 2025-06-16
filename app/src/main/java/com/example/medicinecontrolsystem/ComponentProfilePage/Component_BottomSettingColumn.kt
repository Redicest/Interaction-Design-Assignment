package com.example.medicinecontrolsystem.ComponentProfilePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.AccessAlarm
import androidx.compose.material.icons.rounded.AddAlert
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.Assessment
import androidx.compose.material.icons.rounded.FontDownload
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
fun BottomSettingColumn(
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
                    .padding(horizontal = baseUnit * 1f)
            ) {
                SettingRow(baseUnit = baseUnit, 1, "字体大小")
                SettingRow(baseUnit = baseUnit, 2, "闹钟设置")
                SettingRow(baseUnit = baseUnit, 3, "更新数据库")
            }
        }
    }
}

@Composable
fun SettingRow(baseUnit: Dp, optionIcon:Int, optionString: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = baseUnit * 1f)
        ) {
            when(optionIcon){
                1-> Icon(
                    Icons.Rounded.FontDownload,
                    contentDescription = "Font Size",
                    modifier = Modifier
                        .size(baseUnit * 3f),
                    tint = Color(0xFF8BC34A)
                )
                2->Icon(
                    Icons.Rounded.AccessAlarm,
                    contentDescription = "Alarm Setting",
                    modifier = Modifier
                        .size(baseUnit * 3f),
                    tint = Color(0xFFFFD700)
                )
                3->Icon(
                    Icons.Rounded.Assessment,
                    contentDescription = "Updating Database",
                    modifier = Modifier
                        .size(baseUnit * 3f),
                    tint = Color(0xFFF44336)
                )
            }

            Spacer(modifier = Modifier.width(baseUnit * 1f))
            Text(
                text = optionString,
                fontWeight = FontWeight.W400,
                fontSize = (baseUnit.value * 1.8).sp,
                color = Color.Black
            )
        }
        Icon(
            Icons.AutoMirrored.Rounded.ArrowForwardIos,
            contentDescription = "Font Size",
            modifier = Modifier
                .size(baseUnit * 2.5f),
            tint = Color.Gray
        )
    }
}

@Preview(
    device = Devices.PIXEL_3A,
    showSystemUi = true,
    showBackground = true,
    backgroundColor = 1
)
@Composable
fun BottomSettingColumnPreview(){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val baseUnit = min(screenHeight, screenWidth) / 40f
    BottomSettingColumn(baseUnit = baseUnit)
}