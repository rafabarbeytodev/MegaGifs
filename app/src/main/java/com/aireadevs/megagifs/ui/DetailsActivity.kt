package com.aireadevs.megagifs.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.aireadevs.megagifs.R
import com.aireadevs.megagifs.core.URL_PLAY_STORE
import com.aireadevs.megagifs.navigation.AppNavigation
import com.aireadevs.megagifs.screendetails.ui.DetailsScreenViewModel
import com.aireadevs.megagifs.screenimages.ui.ImagesScreenViewModel
import com.aireadevs.megagifs.ui.components.ShowNewVersion
import com.aireadevs.megagifs.ui.ui.theme.MegaGifsTheme
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.AndroidEntryPoint
import java.util.Collections

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {

    private val imagesVM: ImagesScreenViewModel by viewModels()
    private val detailsVM: DetailsScreenViewModel by viewModels()
    private val mainVM: MainViewModel by viewModels()

    private var backPressedSlotTime: Long = 0
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Control de versiones con Firebase
        mainVM.checkVersion()

        //Control de mail de contacto con developer

        //Cargamos el dato de firebase cuando arrancamos la aplicación
        mainVM.getMailDeveloper()
        //Observamos cualquier cambio del mail de contacto
        mainVM.checkMailDeveloper()

        setupBackPressed()

        MobileAds.initialize(this)

        val configuration = RequestConfiguration.Builder()
            .setTestDeviceIds(Collections.singletonList("DEVICE ID"))
            .build()
        MobileAds.setRequestConfiguration(configuration)

        setContent {
            MegaGifsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val newVersion by mainVM.newVersion.observeAsState(initial = false)
                    ShowNewVersion(
                        show = newVersion,
                        onDismiss = { finish() },
                        onConfirm = { goToPlayStore() })
                    //Gestion de la navegación
                    AppNavigation(mainVM, imagesVM, detailsVM)
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

    private fun setupBackPressed() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (backPressedSlotTime + 2000 > System.currentTimeMillis()) {
                        finishAffinity()
                    } else {
                        Toast.makeText(
                            this@DetailsActivity,
                            getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT
                        ).show()
                        backPressedSlotTime = System.currentTimeMillis()
                    }
                }
            }
        onBackPressedDispatcher.addCallback(this, callback)
    }
}

