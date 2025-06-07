package com.example.medicinecontrolsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicinecontrolsystem.customFunctions.TimeViewModel

@Composable
fun CenterInformation(
    modifier:Modifier = Modifier,
    centerInformationViewModel: TimeViewModel = viewModel()
    ){
    val timePhrase by centerInformationViewModel.timePhrase
    val timeFormatted by centerInformationViewModel.formattedTime
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 80.dp),
        horizontalArrangement = Arrangement.SpaceBetween
        ){
        Text(
            text = stringResource(R.string.currentTasks),
            fontSize = 60.sp,
            fontWeight = FontWeight.W600
        )
        Text(
            text = timePhrase + timeFormatted,
            fontSize = 60.sp,
            fontWeight = FontWeight.W600
        )
    }
}

@Preview(widthDp = 1080, heightDp = 2160)
@Composable
fun CenterInformationPreview(){
    CenterInformation()
}