package com.example.medicinecontrolsystem.ComponentMainPage

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.example.medicinecontrolsystem.R

import com.example.medicinecontrolsystem.data.data_Patient
import com.example.medicinecontrolsystem.data.patients
import com.example.medicinecontrolsystem.customFunctions.MedicineTakingStateViewModel

@Composable
fun PatientInformationList(
    modifier:Modifier = Modifier,
    navController: NavController ?= null,
    medicineTakingStateViewModel: MedicineTakingStateViewModel,
    baseUnit: Dp // 新增基础单位参数
){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(baseUnit * 0.5f) // 项之间添加间距
    ){
        items(patients) {
            PatientInformationItem(
                patient = it,
                navController = navController,
                viewModel = medicineTakingStateViewModel,
                baseUnit = baseUnit // 传递基础单位
            )
        }
    }
}

@Composable
fun PatientInformationItem(
    patient: data_Patient,
    modifier: Modifier = Modifier,
    viewModel: MedicineTakingStateViewModel,
    navController: NavController? = null,
    baseUnit: Dp // 新增基础单位参数
) {
    // 观察该病人的状态变化
    val medicineState by viewModel.statesFlow.collectAsState()
    val isTaken = medicineState[patient.id] ?: false

    // 控制第二个卡片是否显示的状态
    val expanded = remember { mutableStateOf(false) }

    // 整个项使用 Column 布局
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Card1 - 主卡片
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(baseUnit * 8f) // 使用基础单位计算高度
                .clickable { expanded.value = !expanded.value },
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PatientImage(
                        patient.imageResourceId,
                        modifier = Modifier
                            .size(baseUnit * 6f) // 使用基础单位
                            .padding(start = baseUnit)
                    )

                    PatientText(
                        patient.patientName,
                        patient.patientBedNumber,
                        baseUnit = baseUnit, // 传递基础单位
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = baseUnit),
                    )

                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(end = baseUnit)
                    ) {
                        Card(
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier.size(width = baseUnit * 5f, height = baseUnit * 2.7f),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        color = if (isTaken) Color(0xFFFFD700)
                                        else Color(0xFF989898)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(
                                        if (isTaken) R.string.have_taking_medicine
                                        else R.string.not_taking_medicine
                                    ),
                                    textAlign = TextAlign.Center,
                                    fontSize = (baseUnit.value * 1.3).sp, // 相对字体大小
                                    color = Color(0xFFFFFFFF)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(baseUnit * 0.2f))
                        Text(
                            text = stringResource(R.string.medicine_box),
                            fontSize = (baseUnit.value * 1.3).sp, // 相对字体大小
                            fontWeight = FontWeight.W400
                        )
                    }
                }
            }
        }

        // Card2 - 可展开的卡片
        AnimatedVisibility(
//            visible = expanded.value,
            visible = true,
            enter = slideInVertically(animationSpec = tween(300)) { fullHeight -> fullHeight },
            exit = slideOutVertically(animationSpec = tween(300)) { fullHeight -> fullHeight }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(baseUnit * 3.5f), // 使用基础单位
                shape = RoundedCornerShape(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFD9F0FF))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                navController?.navigate("photo_submit")
                            }
                        ) {
                            Icon(
                                Icons.Rounded.Create,
                                contentDescription = null,
                                modifier = Modifier.size(baseUnit * 1.5f)
                            )
                            Text(
                                text = stringResource(R.string.additional_inormation),
                                fontSize = (baseUnit.value * 1.2).sp,
                                fontWeight = FontWeight.W400,
                                modifier = Modifier.padding(start = baseUnit * 0.5f)
                            )
                        }

                        // 添加白色细竖线
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(baseUnit * 2f)
                                .background(Color.White)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                // 扫描检查操作
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.scanning_check),
                                fontSize = (baseUnit.value * 1.2).sp,
                                fontWeight = FontWeight.W400,
                                modifier = Modifier.padding(end = baseUnit * 0.5f)
                            )
                            Icon(
                                Icons.Rounded.PlayArrow,
                                contentDescription = null,
                                modifier = Modifier.size(baseUnit * 2f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PatientImage(
    @DrawableRes patientImage:Int,
    modifier: Modifier=Modifier
){
    Image(
        modifier = modifier,
        painter = painterResource(patientImage),
        contentDescription = null
    )
}

@Composable
fun PatientText(
    @StringRes patientName:Int,
    @StringRes patientBedNumber:Int,
    baseUnit: Dp, // 新增基础单位参数
    modifier:Modifier = Modifier
){
    Column(
        modifier = modifier
    ){
        Text(
            text = stringResource(patientName),
            fontWeight = FontWeight.W600,
            fontSize = (baseUnit.value * 1.6).sp // 相对字体大小
        )
        Spacer(modifier = Modifier.height(baseUnit * 0.3f))
        Text(
            text = stringResource(patientBedNumber),
            fontWeight = FontWeight.W400,
            fontSize = (baseUnit.value * 1.2).sp // 相对字体大小
        )
    }
}