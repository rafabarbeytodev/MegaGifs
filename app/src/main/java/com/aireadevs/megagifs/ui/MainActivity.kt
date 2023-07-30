package com.aireadevs.megagifs.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.aireadevs.megagifs.navigation.AppNavigation
import com.aireadevs.megagifs.core.Routes.*
import com.aireadevs.megagifs.core.URL_PLAY_STORE
import com.aireadevs.megagifs.screendetails.ui.DetailsScreenViewModel
import com.aireadevs.megagifs.screenimages.ui.ImagesScreenViewModel
import com.aireadevs.megagifs.ui.components.ShowNewVersion
import com.aireadevs.megagifs.ui.theme.MegaGifsTheme
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.AndroidEntryPoint
import java.util.Collections

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val imagesVM: ImagesScreenViewModel by viewModels()
    private val detailsVM: DetailsScreenViewModel by viewModels()
    private val mainVM: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this)
        val configuration = RequestConfiguration.Builder()
            .setTestDeviceIds(Collections.singletonList("DEVICE ID"))
            .build()
        MobileAds.setRequestConfiguration(configuration)

        setContent {
            MegaGifsTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Control de versiones con Firebase
                    mainVM.checkVersion()
                    val newVersion by mainVM.newVersion.observeAsState(initial = false)
                    ShowNewVersion(
                        show = newVersion,
                        onDismiss = { finish() },
                        onConfirm = { goToPlayStore() })

                    //Gestion de la navegación
                    AppNavigation(
                        imagesVM,
                        detailsVM)
                }
            }
        }
    }
    private fun goToPlayStore() {
        val url = URL_PLAY_STORE
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}




