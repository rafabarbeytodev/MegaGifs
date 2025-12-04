package com.aireadevs.megagifs.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.aireadevs.megagifs.ui.theme.MegaGifsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

        val screenSplash = installSplashScreen() //Instalamos el Splash
        super.onCreate(savedInstanceState)

        WindowCompat.enableEdgeToEdge(window)

        screenSplash.setKeepOnScreenCondition { true }
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
        finish()

        setContent {
            MegaGifsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}




