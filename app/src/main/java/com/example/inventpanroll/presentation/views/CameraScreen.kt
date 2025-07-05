package com.example.inventpanroll.presentation.views

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.camera.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.geometry.Offset
import android.util.Size as AndroidSize
import androidx.compose.ui.geometry.Size as ComposeSize
import androidx.compose.ui.graphics.BlendMode
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

@Composable
fun CameraScreen(
    onQrCodeScanned : (String) -> Unit,
) {
    val context = LocalContext.current // Mueve esto fuera del remember

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context, // Usa la variable context
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
    }

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (hasCameraPermission) {
            CameraPreview(
                onQrCodeScanned = onQrCodeScanned,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Se necesita permiso de cámara para escanear códigos QR", color = Color.Black)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { launcher.launch(Manifest.permission.CAMERA) }
                ) {
                    Text("Conceder permiso")
                }
            }
        }
    }
}

@androidx.annotation.OptIn(ExperimentalGetImage::class)
@Composable
fun CameraPreview(
    onQrCodeScanned: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    var isScanning by remember { mutableStateOf(true) }

    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val executor = ContextCompat.getMainExecutor(ctx)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val imageAnalysis = ImageAnalysis.Builder()
                    .setTargetResolution(AndroidSize(1280, 720))
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()

                imageAnalysis.setAnalyzer(executor) { imageProxy ->
                    if (isScanning) {
                        val image = imageProxy.image
                        if (image != null) {
                            val inputImage = InputImage.fromMediaImage(
                                image,
                                imageProxy.imageInfo.rotationDegrees
                            )

                            val scanner = BarcodeScanning.getClient()
                            scanner.process(inputImage)
                                .addOnSuccessListener { barcodes ->
                                    for (barcode in barcodes) {
                                        barcode.rawValue?.let { qrCode ->
                                            isScanning = false
                                            onQrCodeScanned(qrCode)
                                        }
                                    }
                                }
                                .addOnCompleteListener {
                                    imageProxy.close()
                                }
                        } else {
                            imageProxy.close()
                        }
                    } else {
                        imageProxy.close()
                    }
                }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalysis
                    )
                } catch (ex: Exception) {
                    Log.e("CameraPreview", "Error al iniciar cámara", ex)
                }
            }, executor)

            previewView
        },
        modifier = modifier
    )

    // Overlay con marco de escaneo
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val squareSize = minOf(canvasWidth, canvasHeight) * 0.6f

            // Fondo semi-transparente
            drawRect(
                color = Color.Black.copy(alpha = 0.5f),
                size = size
            )

            // Marco transparente para escaneo
            drawRect(
                color = Color.Transparent,
                topLeft = Offset(
                    (canvasWidth - squareSize) / 2f,
                    (canvasHeight - squareSize) / 2f
                ),
                size = ComposeSize(squareSize, squareSize),
                blendMode = BlendMode.Clear
            )
        }

        // Marco decorativo
        Box(
            modifier = Modifier
                .size(200.dp)
                .border(2.dp, Color.White, RoundedCornerShape(16.dp))
        )

        // Texto instructivo
        Text(
            text = if (isScanning) "Apunta al código QR" else "Procesando...",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
        )
    }
}