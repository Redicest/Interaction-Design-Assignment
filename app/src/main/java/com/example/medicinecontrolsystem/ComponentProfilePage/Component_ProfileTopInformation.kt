package com.example.medicinecontrolsystem.ComponentProfilePage

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.medicinecontrolsystem.R

@Composable
fun ProfileTopInformation(
    modifier: Modifier = Modifier,
    baseUnit: Dp // 添加基础单位参数
    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = baseUnit * 1.5f),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(baseUnit * 1.5f))
            Card(
                modifier = modifier
                    .height(baseUnit * 7f)
                    .width(baseUnit * 7f),
                shape = RoundedCornerShape(100),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.zj03hicr),
                        contentDescription = "护工头像",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Spacer(modifier = Modifier.width(baseUnit * 2f))
            Column() {
                Text(
                    text = "刘美丽",
                    fontWeight = FontWeight.W600,
                    fontSize = (baseUnit.value * 2.1).sp,
                )
                Text(
                    text = "A61房间",
                    fontWeight = FontWeight.W400,
                    fontSize = (baseUnit.value * 1.8).sp,
                    color = Color.Gray
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                Icons.Rounded.Settings,
                contentDescription = "Settings",
                modifier = Modifier
                    .size(baseUnit * 3f)
            )
            Spacer(modifier = Modifier.width(baseUnit * 2f))
        }

    }
}


@Preview(
    device = Devices.PIXEL_3A,
    showSystemUi = true,
    showBackground = true
)
@Composable
fun ProfileTopInformationPreview(){
    // 获取屏幕尺寸
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val baseUnit = min(screenHeight, screenWidth) / 40f
    ProfileTopInformation(baseUnit = baseUnit)
}