import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicinecontrolsystem.ComponentReminderPage.CenterReminderList
import com.example.medicinecontrolsystem.ComponentReminderPage.TopInformation
import com.example.medicinecontrolsystem.customFunctions.TimeViewModel

//package com.example.medicinecontrolsystem
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.ComposeView
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.tooling.preview.Devices
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.min
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.medicinecontrolsystem.ComponentMainPage.CenterInformation
//import com.example.medicinecontrolsystem.ComponentMainPage.PatientInformationList
//import com.example.medicinecontrolsystem.ComponentMainPage.TimeBar
//import com.example.medicinecontrolsystem.ComponentMainPage.TopInformationCard
//
//import com.example.medicinecontrolsystem.ComponentRecordPage.TopInformationBar
//import com.example.medicinecontrolsystem.ComponentRecordPage.CenterDateBarList
//import com.example.medicinecontrolsystem.ComponentRecordPage.PatientRecordList
//import com.example.medicinecontrolsystem.customFunctions.CompletedTaskViewModel
//import com.example.medicinecontrolsystem.customFunctions.MedicineTakingStateViewModel
//import com.example.medicinecontrolsystem.customFunctions.TimeBarViewModel
//import com.example.medicinecontrolsystem.customFunctions.TimeViewModel
//
//
//class RecordFragment : Fragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return ComposeView(requireContext()).apply {
//            setContent {
////                RecordFragmentPage()
//            }
//        }
//    }
//}
//
////@Composable
////fun RecordFragmentPage(){
////    Box(
////        modifier = Modifier.fillMaxSize().background(brush = Brush.verticalGradient(colors = listOf(Color(0xFFFFFCF7),
////            Color(0xFFE6F1FF)))),
////        contentAlignment = Alignment.TopCenter,
////    ){
////        Column(){
////            Spacer(modifier = Modifier.height(80.dp))
////            TopInformationBar()
////            Spacer(modifier = Modifier.height(40.dp))
////            CenterDateBarList()
////            Spacer(modifier = Modifier.height(40.dp))
////            PatientRecordList()
////        }
////    }
////}
//
//
//
//
//
////@Composable
////fun HomeFragmentPage(navController: NavController) {
////    // 获取 ViewModel 实例
////    val timeViewModel: TimeViewModel = viewModel()
////    val timeBarViewModel: TimeBarViewModel = viewModel()
////    val completedTaskViewModel: CompletedTaskViewModel = hiltViewModel()
////    val medicineTakingStateViewModel: MedicineTakingStateViewModel = hiltViewModel()
////
////    // 获取屏幕尺寸
////    val configuration = LocalConfiguration.current
////    val screenHeight = configuration.screenHeightDp.dp
////    val screenWidth = configuration.screenWidthDp.dp
////    val baseUnit = min(screenHeight, screenWidth) / 40f
////
////    Box(
////        modifier = Modifier
////            .fillMaxSize()
////            .background(
////                brush = Brush.verticalGradient(
////                    colors = listOf(
////                        Color(0xFFFFFCF7),
////                        Color(0xFFE6F1FF)
////                    )
////                )
////            )
////    ) {
////        Column(
////            modifier = Modifier
////                .fillMaxSize()
////                .padding(horizontal = baseUnit * 1.5f)
////        ) {
////            TopInformationCard(
////                gradinetBrush = Brush.horizontalGradient(
////                    colors = listOf(
////                        Color(0xFFD9F0FF),
////                        Color(0xFFF2F7FB)
////                    )
////                ),
////                systemTimeViewModel = timeViewModel,
////                completedTaskViewModel = completedTaskViewModel,
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .height(baseUnit * 20f)
////            )
////            Spacer(modifier = Modifier.height(baseUnit * 0.5f))
////            TimeBar(
////                timeBarViewModel = timeBarViewModel,
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .height(baseUnit * 3.3f)
////            )
////            Spacer(modifier = Modifier.height(baseUnit * 0.5f))
////            CenterInformation(
////                centerInformationViewModel = timeViewModel,
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .height(baseUnit * 3f)
////            )
////            Spacer(modifier = Modifier.height(baseUnit * 0.5f))
////            PatientInformationList(
////                navController = navController,
////                medicineTakingStateViewModel = medicineTakingStateViewModel,
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .weight(1f),
////                baseUnit = baseUnit
////            )
////        }
////    }
////}
////
////
////@Preview(
////    device = Devices.PIXEL_3A,
////    showSystemUi = true,
////    showBackground = true
////)
////@Composable
////fun HomeFragmentPreview() {
////    val mockNavController = rememberNavController()
////    Scaffold(
////        bottomBar = { BottomNavBar(navController = rememberNavController()) }
////    ) { innerPadding ->
////        Box(Modifier.padding(innerPadding)) {
////            HomeFragmentPage(mockNavController)
////        }
////    }
////}
//
//
//@Composable
//fun RecordPage() {
//    // 获取 ViewModel 实例
//    val timeViewModel: TimeViewModel = viewModel()
//    val medicineTakingStateViewModel: MedicineTakingStateViewModel = hiltViewModel()
//
//    // 获取屏幕尺寸
//    val configuration = LocalConfiguration.current
//    val screenHeight = configuration.screenHeightDp.dp
//    val screenWidth = configuration.screenWidthDp.dp
//    val baseUnit = min(screenHeight, screenWidth) / 40f
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                brush = Brush.verticalGradient(
//                    colors = listOf(
//                        Color(0xFFFFFCF7),
//                        Color(0xFFE6F1FF)
//                    )
//                )
//            ),
//        contentAlignment = Alignment.TopCenter,
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = baseUnit * 1.5f)
//        ) {
//            Spacer(modifier = Modifier.height(baseUnit * 3f))
//            TopInformationBar(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(baseUnit * 4f),
//                baseUnit = baseUnit
//            )
//            Spacer(modifier = Modifier.height(baseUnit))
//            CenterDateBarList(
//                timeViewModel = timeViewModel,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(baseUnit * 8f),
//                baseUnit = baseUnit
//            )
//            Spacer(modifier = Modifier.height(baseUnit))
//            PatientRecordList(
//                viewModel = medicineTakingStateViewModel,
//                systemTimeViewModel = timeViewModel,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f),
//                baseUnit = baseUnit // 传递基础单位
//            )
//        }
//    }
//}
//
//@Preview(
//    device = Devices.PIXEL_3A,
//    showSystemUi = true,
//    showBackground = true
//)
//@Composable
//fun RecordFragmentPreview() {
//    RecordPage()
//}

@Composable
fun ReminderPage(){
    val timeViewModel: TimeViewModel = viewModel()

    // 获取屏幕尺寸
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val baseUnit = min(screenHeight, screenWidth) / 40f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFCF7),
                        Color(0xFFE6F1FF)
                    )
                )
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = baseUnit * 1.5f)
        ){
            Spacer(modifier = Modifier.height(baseUnit * 4))
            TopInformation(
                baseUnit = baseUnit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(baseUnit * 5f),
                systemTimeViewModel = timeViewModel
            )
            Spacer(modifier = Modifier.height(baseUnit))
            CenterReminderList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(20f),
                baseUnit = baseUnit,
                viewModel = timeViewModel
            )
        }
    }
}

@Preview(
    device = Devices.PIXEL_3A,
    showSystemUi = true,
    showBackground = true
)
@Composable
fun RecordFragmentPreview() {
    ReminderPage()
}