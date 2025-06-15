package com.example.medicinecontrolsystem.Camera


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors
import androidx.camera.core.ImageProxy
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.FlashlightOff
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.material3.ShapeDefaults
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset

class Camera : Fragment() {
    private var hasCameraPermission by mutableStateOf(false)
    private var firstBarcode by mutableStateOf("")
    private var secondBarcode by mutableStateOf("")
    private var showErrorDialog by mutableStateOf(false)
    private var scanningState by mutableStateOf(ScanningState.FIRST_BARCODE)

    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission = granted
        if (!granted) {
            // 处理权限被拒绝的情况
            Log.e("Camera", "Camera permission denied")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                CameraScreen(
                    hasCameraPermission = hasCameraPermission,
                    firstBarcode = firstBarcode,
                    secondBarcode = secondBarcode,
                    scanningState = scanningState,
                    showErrorDialog = showErrorDialog,
                    onPermissionRequest = { cameraPermissionLauncher.launch(Manifest.permission.CAMERA) },
                    onRetryScan = { resetScanning() },
                    onBack = { findNavController().popBackStack() },
                    onScanSuccess = {
                        findNavController().navigate("photo_submit")
                    },
                    onShowErrorDialog = { showErrorDialog = true },
                    onBarcodeScanned = { barcodeValue ->
                        // 根据当前状态更新对应的条形码和状态
                        when (scanningState) {
                            ScanningState.FIRST_BARCODE -> {
                                firstBarcode = barcodeValue
                                scanningState = ScanningState.SECOND_BARCODE
                            }
                            ScanningState.SECOND_BARCODE -> {
                                secondBarcode = barcodeValue
                                scanningState = ScanningState.COMPLETED
                            }
                            ScanningState.COMPLETED -> {
                                // 已经完成扫描，忽略新扫描
                            }
                        }
                    }
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        val permission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        )
        hasCameraPermission = permission == PackageManager.PERMISSION_GRANTED
        if (!hasCameraPermission) {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun resetScanning() {
        firstBarcode = ""
        secondBarcode = ""
        scanningState = ScanningState.FIRST_BARCODE
        showErrorDialog = false
    }
}

enum class ScanningState {
    FIRST_BARCODE,
    SECOND_BARCODE,
    COMPLETED
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScreen(
    hasCameraPermission: Boolean,
    firstBarcode: String,
    secondBarcode: String,
    scanningState: ScanningState,
    showErrorDialog: Boolean,
    onPermissionRequest: () -> Unit,
    onRetryScan: () -> Unit,
    onBack: () -> Unit,
    onScanSuccess: () -> Unit,
    onShowErrorDialog: () -> Unit,
    onBarcodeScanned: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // 当扫描完成时检查结果
    LaunchedEffect(scanningState) {
        if (scanningState == ScanningState.COMPLETED) {
            if (firstBarcode == secondBarcode && firstBarcode.isNotEmpty()) {
                onScanSuccess()
            } else {
                onShowErrorDialog()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("条形码扫描") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
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
            if (hasCameraPermission) {
                // 显示相机预览
                CameraPreview(
                    onBarcodeScanned = onBarcodeScanned
                )

                // 显示扫描状态
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = when (scanningState) {
                            ScanningState.FIRST_BARCODE -> "请扫描第一个条形码"
                            ScanningState.SECOND_BARCODE -> "请扫描第二个条形码"
                            ScanningState.COMPLETED -> "扫描完成"
                        },
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (firstBarcode.isNotEmpty()) {
                        Text("第一个条形码: $firstBarcode", style = MaterialTheme.typography.bodyMedium)
                    }

                    if (secondBarcode.isNotEmpty()) {
                        Text("第二个条形码: $secondBarcode", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            } else {
                // 没有权限时显示提示
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("需要相机权限才能使用扫描功能", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onPermissionRequest) {
                        Text("请求权限")
                    }
                }
            }

            // 错误对话框
            if (showErrorDialog) {
                AlertDialog(
                    onDismissRequest = { /* 不允许外部点击关闭 */ },
                    title = { Text("条形码不匹配") },
                    text = {
                        Column {
                            Text("扫描到的两个条形码不一致:", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("第一个: $firstBarcode", style = MaterialTheme.typography.bodyMedium)
                            Text("第二个: $secondBarcode", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("请确保扫描的是同一药品上的两个相同条形码", style = MaterialTheme.typography.bodyMedium)
                        }
                    },
                    confirmButton = {
                        Button(onClick = onRetryScan) {
                            Text("重新扫描")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = onBack) {
                            Text("返回")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CameraPreview(onBarcodeScanned: (String) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var cameraProviderFuture by remember { mutableStateOf<ListenableFuture<ProcessCameraProvider>?>(null) }

    DisposableEffect(Unit) {
        cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        onDispose {
            // 清理资源
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                val previewView = androidx.camera.view.PreviewView(ctx)
                previewView.scaleType = androidx.camera.view.PreviewView.ScaleType.FILL_CENTER

                cameraProviderFuture?.addListener({
                    val cameraProvider = cameraProviderFuture?.get() ?: return@addListener

                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                    val imageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .also {
                            it.setAnalyzer(Executors.newSingleThreadExecutor(), BarcodeAnalyzer { barcode ->
                                onBarcodeScanned(barcode)
                            })
                        }

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            CameraSelector.DEFAULT_BACK_CAMERA,
                            preview,
                            imageAnalysis
                        )
                    } catch (exc: Exception) {
                        Log.e("Camera", "Use case binding failed", exc)
                    }
                }, ContextCompat.getMainExecutor(context))

                previewView
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

class BarcodeAnalyzer(private val onBarcodeScanned: (String) -> Unit) : ImageAnalysis.Analyzer {
    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

    private val scanner: BarcodeScanner by lazy { BarcodeScanning.getClient(options) }

    @ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    // 使用 firstOrNull 查找第一个有效的条形码
                    barcodes.firstOrNull { barcode ->
                        barcode.rawValue?.isNotBlank() == true
                    }?.rawValue?.let { value ->
                        onBarcodeScanned(value)
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("BarcodeAnalyzer", "Barcode scanning failed", e)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }
}

// Camera.kt

// 顶部提示文本
@Composable
fun TopTextHint() {
    Text(
        text = "请将桌面条形码与药盒条形码同时放入框内",
        style = MaterialTheme.typography.titleMedium,
        color = Color.Black,
        modifier = Modifier.padding(16.dp))
}

// 相机预览框
@Composable
fun CameraBox(onBarcodeScanned: (String) -> Unit) {
    Box(
        modifier = Modifier
            .size(300.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF4CAF50), Color(0xFF2196F3))
                ),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        CameraPreview(onBarcodeScanned = onBarcodeScanned)
    }
}

// 闪光灯按钮
@Composable
fun FlashlightButton() {
    var isFlashOn by remember { mutableStateOf(false) }

    IconButton(
        onClick = { isFlashOn = !isFlashOn },
        modifier = Modifier
            .size(60.dp)
            .background(
                color = if (isFlashOn) Color.Yellow.copy(alpha = 0.3f) else Color.Transparent,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = if (isFlashOn) Icons.Filled.FlashlightOn else Icons.Filled.FlashlightOff,
            contentDescription = "闪光灯",
            tint = if (isFlashOn) Color.Yellow else Color.Gray,
            modifier = Modifier.size(36.dp)
        )
    }
}