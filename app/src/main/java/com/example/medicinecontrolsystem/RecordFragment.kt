package com.example.medicinecontrolsystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

import com.example.medicinecontrolsystem.ComponentRecordPage.TopInformationBar
import com.example.medicinecontrolsystem.ComponentRecordPage.CenterDateBarList
import com.example.medicinecontrolsystem.ComponentRecordPage.PatientRecordList


class RecordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                RecordFragmentPage()
            }
        }
    }
}

@Composable
fun RecordFragmentPage(){
    Box(
        modifier = Modifier.fillMaxSize().background(brush = Brush.verticalGradient(colors = listOf(Color(0xFFFFFCF7),
            Color(0xFFE6F1FF)))),
        contentAlignment = Alignment.TopCenter,
    ){
        Column(){
            Spacer(modifier = Modifier.height(80.dp))
            TopInformationBar()
            Spacer(modifier = Modifier.height(40.dp))
            CenterDateBarList()
            Spacer(modifier = Modifier.height(40.dp))
            PatientRecordList()
        }
    }
}



@Preview(widthDp = 1080, heightDp = 2160)
@Composable
fun RecordFragmentPagePreview(){
        Scaffold(
            bottomBar = { BottomNavBar(navController = rememberNavController()) }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
    RecordFragmentPage()
            }
        }
}