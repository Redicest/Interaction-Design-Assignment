package com.example.medicinecontrolsystem

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.example.medicinecontrolsystem.R
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicinecontrolsystem.customFunctions.TimeViewModel

//组件：顶部信息卡
@Composable
fun TopInformationCard(modifier:Modifier = Modifier,
                       gradinetBrush: Brush,
                       viewModel: TimeViewModel = viewModel()
){
    val formattedTime by viewModel.formattedTime
    val formattedDate by viewModel.formattedData
    val formattedWeekDay by viewModel.formattedWeekDay

    Card(
        modifier = modifier
            .size(width = 240.dp, height = 100.dp)
    ){
        Box(
            modifier = Modifier.fillMaxSize().background(brush = gradinetBrush),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = formattedTime + "  " + formattedWeekDay,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun ScreenComponentPreview(){
    TopInformationCard(gradinetBrush = Brush.horizontalGradient(colors = listOf(Color(0xFF2196F3),
        Color(0xFF9C27B0))))
}