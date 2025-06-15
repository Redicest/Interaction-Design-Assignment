package com.example.medicinecontrolsystem.ComponentReminderPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicinecontrolsystem.R
import com.example.medicinecontrolsystem.customFunctions.TimeViewModel

@Composable
fun TopInformation(
    baseUnit: Dp, // 添加基础单位参数
    modifier: Modifier = Modifier,
    systemTimeViewModel: TimeViewModel
){
    val formattedTime by systemTimeViewModel.formattedTime
    val formattedDate by systemTimeViewModel.formattedDate
    val formattedWeekDay by systemTimeViewModel.formattedWeekDay
    val formattedYear by systemTimeViewModel.formattedYear
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.taking_medicine_reminder),
                fontWeight = FontWeight.W800,
                fontSize = (baseUnit.value * 2.7).sp // 相对字体大小
            )
        }
//        Spacer(modifier = Modifier.height(baseUnit * 1f))
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            Text(
//                text = formattedYear + formattedDate,
//                fontWeight = FontWeight.W600,
//                fontSize = (baseUnit.value * 1.8).sp,
//                color = Color(0xFF5D5D5D)
//            )
//            Spacer(modifier = Modifier.width(baseUnit * 0.5f))
//            Text(
//                text = formattedWeekDay,
//                fontWeight = FontWeight.W600,
//                fontSize = (baseUnit.value * 1.8).sp,
//                color = Color(0xFF5D5D5D)
//            )
//            Spacer(modifier = Modifier.width(baseUnit * 0.5f))
//            Card(
//                shape = RoundedCornerShape(baseUnit * 2f), // 相对圆角
//                modifier = Modifier
//                    .size(width = baseUnit * 5f, height = baseUnit * 2.5f)
//            ){
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(color = Color(0x6BFFDA97)),
//                    contentAlignment = Alignment.Center
//                ){
//                    Text(
//                        text = "今天",
//                        textAlign = TextAlign.Center,
//                        fontSize = (baseUnit.value * 1.7).sp, // 相对字体大小
//                        fontWeight = FontWeight.W400,
//                        color = Color(0xFFFF9800),
//                        maxLines = 1,
//                    )
//                }
//            }
//        }
    }
}

@Preview(
    device = Devices.PIXEL_3A,
    showSystemUi = true,
    showBackground = true
)
@Composable
fun TopInformationPreview(){
    // 获取屏幕尺寸
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val baseUnit = min(screenHeight, screenWidth) / 40f
//    TopInformation(baseUnit)
}