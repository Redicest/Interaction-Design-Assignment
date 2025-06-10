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
import androidx.navigation.NavController
import com.example.medicinecontrolsystem.R

import com.example.medicinecontrolsystem.data.data_Patient
import com.example.medicinecontrolsystem.data.patients
import com.example.medicinecontrolsystem.customFunctions.MedicineTakingStateViewModel

@Composable
fun PatientInformationList(
    modifier:Modifier = Modifier,
    navController: NavController ?= null,
    medicineTakingStateViewModel: MedicineTakingStateViewModel
    ){
    LazyColumn {
        items(patients) {
            PatientInformationItem(
                patient = it,
                navController= navController,
                viewModel = medicineTakingStateViewModel
                )
        }
    }
}

@Composable
fun PatientInformationItem(
    patient: data_Patient,
    modifier: Modifier = Modifier,
    viewModel: MedicineTakingStateViewModel,
    navController: NavController ?= null
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
            .padding(horizontal = 50.dp, vertical = 15.dp)
    ) {
        // Card1 - 主卡片
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clickable { expanded.value = !expanded.value },
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Spacer(modifier = Modifier.width(30.dp))
                    PatientImage(
                        patient.imageResourceId,
                        modifier = modifier.align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    PatientText(
                        patient.patientName,
                        patient.patientBedNumber,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Card(
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier.size(width = 150.dp, height = 70.dp),
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
                                    fontSize = 40.sp,
                                    color = Color(0xFFFFFFFF)
                                )
                            }
                        }
                        Text(
                            text = stringResource(R.string.medicine_box),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.W400
                        )
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                }
            }
        }

        // Card2 - 可展开的卡片
        AnimatedVisibility(
            visible = expanded.value,
//            visible = true, // 调试用
            enter = slideInVertically(animationSpec = tween(300)) { fullHeight -> fullHeight },
            exit = slideOutVertically(animationSpec = tween(300)) { fullHeight -> fullHeight }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFD9F0FF))
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .align(Alignment.Center),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.Create,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 50.dp)
                                    .size(50.dp)
                                    .clickable{
                                        navController?.navigate("photo_submit")
                                    }
                            )
                            Text(
                                text = stringResource(R.string.additional_inormation),
                                fontSize = 40.sp,
                                fontWeight = FontWeight.W400,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        }

                        // 添加白色细竖线
                        Box(
                            modifier = Modifier
                                .width(5.dp)
                                .height(100.dp)
                                .background(Color.White)
                                .align(Alignment.CenterVertically)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.scanning_check),
                                fontSize = 40.sp,
                                fontWeight = FontWeight.W400,
                                modifier = Modifier.padding(end = 20.dp)
                            )
                            Icon(
                                Icons.Rounded.PlayArrow,
                                contentDescription = null,
                                modifier = Modifier.padding(end = 50.dp).size(50.dp)
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
        modifier = modifier.size(100.dp),
        painter = painterResource(patientImage),
        contentDescription = null
    )
}

@Composable
fun PatientText(
    @StringRes patientName:Int,
    @StringRes patientBedNumber:Int,
    modifier:Modifier = Modifier
    ){
    Column(
        modifier = modifier
    ){
        Text(
            text = stringResource(patientName),
            fontWeight = FontWeight.W600,
            fontSize = 40.sp
        )
        Text(
            text = stringResource(patientBedNumber),
            fontWeight = FontWeight.W400,
            fontSize = 40.sp
        )
    }
}