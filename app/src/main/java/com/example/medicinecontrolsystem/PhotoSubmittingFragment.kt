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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.medicinecontrolsystem.ComponentPhotoSubmittingPage.TopInformationBarP
import com.example.medicinecontrolsystem.ComponentPhotoSubmittingPage.CenterImagePart
import com.example.medicinecontrolsystem.ComponentPhotoSubmittingPage.TopInformationBarPageSubmitting
import com.example.medicinecontrolsystem.ComponentRecordPage.CenterDateBarList
import com.example.medicinecontrolsystem.ComponentRecordPage.PatientRecordList
import com.example.medicinecontrolsystem.data.patients

class PhotoSubmittingFragment : Fragment() {
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


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PhotoSubmittingPage(onBackClick: () -> Unit) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("照片提交") },
//                navigationIcon = {
//                    IconButton(onClick = onBackClick) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "返回"
//                        )
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .background(
//                    brush = Brush.verticalGradient(
//                        colors = listOf(Color(0xFFFFFCF7), Color(0xFFE6F1FF))
//                    )
//                ),
//            contentAlignment = Alignment.Center
//        ) {
//            Text("这里是照片提交页面", style = MaterialTheme.typography.headlineMedium)
//        }
//    }
//}

@Composable
fun PhotoSubmittingPage(patientId: Int?) {
    // 获取屏幕尺寸并计算基础单位
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val baseUnit = min(screenHeight, screenWidth) / 40f

    // 根据ID查找病人信息
    val patient = remember(patientId) {
        patients.find { it.id == patientId }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // 顶部区域
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)   // 使用 weight 函数分配空间
                .align(alignment = Alignment.CenterHorizontally)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                Spacer(modifier = Modifier.height(baseUnit * 4))
                if (patient != null) {
                    TopInformationBarPageSubmitting(
                        patient = patient,
                        baseUnit = baseUnit
                    )
                } else {
                    Text("未选择")
                }
            }
        }

        // 底部区域
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f) // 使用 weight 函数分配空间
        ) {
            CenterImagePart(
                baseUnit = baseUnit
            )
        }
    }
}

@Preview(
    device = Devices.PIXEL_3A,
    showSystemUi = true,
    showBackground = true)
@Composable
fun PhotoSubmittingPagePreview(){
    PhotoSubmittingPage(2)
}