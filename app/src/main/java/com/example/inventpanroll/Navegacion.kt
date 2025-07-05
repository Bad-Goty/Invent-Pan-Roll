package com.example.inventpanroll

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventpanroll.presentation.views.CPUView
import com.example.inventpanroll.presentation.views.CameraScreen
import com.example.inventpanroll.presentation.views.DetailsCPUView
import com.example.inventpanroll.presentation.views.MainView

@Composable
fun Navegacion() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
        ) {
        composable(route = "home"){ MainView(navController) }
        composable(route = "cpu") { CPUView(navController) }
        composable(route = "detailscpu/{noSerie}") { backStackEntry ->
            val noSerie = backStackEntry.arguments?.getString("noSerie") ?: ""
            DetailsCPUView(noSerie = noSerie)
        }
        composable("camera") {
            CameraScreen(
                onQrCodeScanned  = { qrCode ->
                    // Cuando escanee un QR, navega a los detalles
                    navController.navigate("detailscpu/$qrCode") {
                        popUpTo("camera") { inclusive = true } // Cierra la cámara
                    }
                }
            )
        }
    }
}