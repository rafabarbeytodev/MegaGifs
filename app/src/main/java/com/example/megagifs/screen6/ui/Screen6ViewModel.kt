package com.example.megagifs.screen6.ui

import androidx.lifecycle.ViewModel
import com.example.megagifs.principalscreen.data.network.response.GifsResponse
import com.example.megagifs.principalscreen.domain.TrendingGifsUseCase

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen6.ui
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 7:41
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class Screen6ViewModel : ViewModel() {

    val trendingGifsUseCase = TrendingGifsUseCase()

    suspend fun onGetGifs(): GifsResponse? {
        return trendingGifsUseCase()
    }
}