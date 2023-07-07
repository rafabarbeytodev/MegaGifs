package com.aireadevs.megagifs.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.aireadevs.megagifs.core.ADMON_BANNER_ID_PROD
import com.aireadevs.megagifs.core.ADMON_BANNER_ID_TEST
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.screenprincipal.ui.components
 *
 * Created by Rafael Barbeyto Torrellas on 05/07/2023 at 16:45
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Composable
fun BannerAdView() {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth(),
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = ADMON_BANNER_ID_TEST
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}