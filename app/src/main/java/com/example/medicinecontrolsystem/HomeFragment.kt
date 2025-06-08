package com.example.medicinecontrolsystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

import com.example.medicinecontrolsystem.ComponentMainPage.CenterInformation
import com.example.medicinecontrolsystem.ComponentMainPage.PatientInformationList
import com.example.medicinecontrolsystem.ComponentMainPage.TimeBar
import com.example.medicinecontrolsystem.ComponentMainPage.TopInformationCard

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

            }
        }
    }
}

@Composable
fun HomeFragmentPage(){
    Box(
        modifier = Modifier.fillMaxSize().background(brush = Brush.verticalGradient(colors = listOf(Color(0xFFFFFCF7),
            Color(0xFFE6F1FF)))),
        contentAlignment = Alignment.TopCenter,
    ){
        Column(){
            Spacer(modifier = Modifier.height(100.dp))
            TopInformationCard(gradinetBrush = Brush.horizontalGradient(colors = listOf(Color(0xFFD9F0FF),
                Color(0xFFF2F7FB))))
            Spacer(modifier = Modifier.height(50.dp))
            TimeBar()
            Spacer(modifier = Modifier.height(50.dp))
            CenterInformation()
            Spacer(modifier = Modifier.height(50.dp))
            PatientInformationList()
        }
    }

}

@Preview(widthDp = 1080, heightDp = 2160)
@Composable
fun HomeFragmentPreview(){
        Scaffold(
            bottomBar = { BottomNavBar(navController = rememberNavController()) }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                HomeFragmentPage()
            }
        }
    }