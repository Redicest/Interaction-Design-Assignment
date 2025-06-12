package com.example.medicinecontrolsystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.medicinecontrolsystem.ComponentPhotoSubmittingPage.TopInformationBarP
import com.example.medicinecontrolsystem.ComponentPhotoSubmittingPage.CenterImagePart
import com.example.medicinecontrolsystem.ComponentRecordPage.CenterDateBarList
import com.example.medicinecontrolsystem.ComponentRecordPage.PatientRecordList

class PhotoSubmittingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PhotoSubmittingPage(
                    onBackClick = { findNavController().popBackStack() }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoSubmittingPage(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("照片提交") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "返回"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFFCF7), Color(0xFFE6F1FF))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text("这里是照片提交页面", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
fun PhotoSubmittingPageP(){
    Box(
        modifier = Modifier.fillMaxSize().background(color = Color.White),
        contentAlignment = Alignment.TopCenter,
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ){

            Spacer(modifier = Modifier.padding(top = 40.dp))
            TopInformationBarP()

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ){
            CenterImagePart()
        }

    }
}

@Preview(
    device = Devices.PIXEL_3A,
    showSystemUi = true,
    showBackground = true)
@Composable
fun PhotoSubmittingPagePreview(){
    PhotoSubmittingPageP()
}