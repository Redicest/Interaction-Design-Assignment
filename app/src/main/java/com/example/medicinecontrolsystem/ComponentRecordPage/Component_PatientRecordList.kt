package com.example.medicinecontrolsystem.ComponentRecordPage

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.medicinecontrolsystem.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicinecontrolsystem.data.patients
import com.example.medicinecontrolsystem.customFunctions.TimeViewModel
import com.example.medicinecontrolsystem.customFunctions.MedicineTakingStateViewModel
import com.example.medicinecontrolsystem.data.data_Patient

@Composable
fun PatientRecordList(
    modifier:Modifier = Modifier,
    viewModel: MedicineTakingStateViewModel,
    systemTimeViewModel: TimeViewModel,
    baseUnit: Dp // 新增基础单位参数
){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(baseUnit * 0.5f) // 添加项间距
    ){
        items(patients) {
            PatientRecordItem(
                patient = it,
                viewModel = viewModel,
                systemTimeViewModel = systemTimeViewModel,
                baseUnit = baseUnit // 传递基础单位
            )
        }
    }
}
@Composable
fun PatientRecordItem(
    patient: data_Patient,
    modifier: Modifier = Modifier,
    viewModel: MedicineTakingStateViewModel,
    systemTimeViewModel: TimeViewModel,
    baseUnit: Dp // 新增基础单位参数
){
    // 观察该病人的状态变化
    val medicineState by viewModel.statesFlow.collectAsState()
    val isTaken:Boolean = medicineState[patient.id] ?: false

    val formattedTime by systemTimeViewModel.formattedTime
    val formattedDate by systemTimeViewModel.formattedDate
    val formattedWeekDay by systemTimeViewModel.formattedWeekDay

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = baseUnit * 1.2f, vertical = baseUnit * 0.5f)
            .height(baseUnit * 20f),
        shape = RoundedCornerShape(baseUnit),
        elevation = CardDefaults.cardElevation(defaultElevation = baseUnit * 0.4f)
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Image(
                painter = painterResource(id = R.drawable.zj03hicr),
                contentDescription = "病人记录背景",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(baseUnit * 4.5f)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .align(Alignment.BottomCenter)
            )
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .padding(bottom = baseUnit * 0.75f)
            ){
                Row(
                    verticalAlignment = Alignment.Bottom
                ){
                    PatientImage(
                        patient.imageResourceId,
                        modifier = Modifier.padding(horizontal = baseUnit * 0.5f)
                    )
                    PatientText(
                        patient.patientName,
                        patient.patientBedNumber,
                        formattedDate,
                        formattedTime,
                        baseUnit = baseUnit // 传递基础单位
                    )
                }
            }
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(end = baseUnit * 0.5f, bottom = baseUnit * 1.25f)
            ){
                signIfmedicineTaken(
                    isTaken = isTaken,
                    baseUnit = baseUnit, // 传递基础单位
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
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
        modifier = modifier.size(50.dp), // 保持相对固定，或改为相对尺寸
        painter = painterResource(patientImage),
        contentDescription = null
    )
}

@Composable
fun PatientText(
    @StringRes patientName:Int,
    @StringRes patientBedNumber:Int,
    dateString: String,
    timeString: String,
    baseUnit: Dp, // 新增基础单位参数
    modifier:Modifier = Modifier
){
    Column(
        modifier = modifier
    ){
        Row(){
            Text(
                text = stringResource(patientName),
                fontSize = (baseUnit.value * 1.2).sp, // 相对字体大小
                fontWeight = FontWeight.W600,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(baseUnit * 0.6f))
            Text(
                text = stringResource(patientBedNumber),
                fontSize = (baseUnit.value * 1.2).sp, // 相对字体大小
                fontWeight = FontWeight.W600,
                color = Color.White
            )
        }
        Row(){
            Text(
                text = dateString + timeString,
                fontSize = (baseUnit.value * 0.95).sp, // 相对字体大小
                fontWeight = FontWeight.W400,
                color = Color.White
            )
        }
    }
}

@Composable
fun signIfmedicineTaken(isTaken:Boolean, baseUnit: Dp, modifier: Modifier = Modifier){
    Card(
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.size(width = baseUnit * 5f, height = baseUnit * 2.7f)
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
}

@Preview(widthDp = 1080, heightDp = 2160)
@Composable
fun PatientRecordListPreview(){
}