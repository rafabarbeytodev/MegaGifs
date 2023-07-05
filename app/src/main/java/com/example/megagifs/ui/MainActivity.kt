package com.example.megagifs.ui

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
import com.example.megagifs.navigation.AppNavigation
import com.example.megagifs.core.Routes.*
import com.example.megagifs.core.URL_PLAY_STORE
import com.example.megagifs.screendetails.ui.DetailsScreenViewModel
import com.example.megagifs.screenfavorites.ui.FavoritesScreenViewModel
import com.example.megagifs.screenprincipal.ui.PrincipalScreenViewModel
import com.example.megagifs.ui.components.ShowNewVersion
import com.example.megagifs.ui.theme.MegaGifsTheme
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.AndroidEntryPoint
import java.util.Collections

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val principalViewModel: PrincipalScreenViewModel by viewModels()
    private val detailsViewModel: DetailsScreenViewModel by viewModels()
    private val favoriteViewModel: FavoritesScreenViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()

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
                    mainViewModel.checkVersion()
                    val newVersion by mainViewModel.newVersion.observeAsState(initial = false)
                    ShowNewVersion(
                        show = newVersion,
                        onDismiss = { finish() },
                        onConfirm = { goToPlayStore() })

                    //Gestion de la navegación
                    AppNavigation(
                        principalViewModel,
                        detailsViewModel,
                        favoriteViewModel)
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




