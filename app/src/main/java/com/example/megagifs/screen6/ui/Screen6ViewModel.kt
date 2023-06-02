package com.example.megagifs.screen6.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.megagifs.screen6.data.network.response.GifsResponse
import com.example.megagifs.screen6.domain.GifsUseCase
import kotlinx.coroutines.launch

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

    val gifsUseCase = GifsUseCase()

    suspend fun onGetGifs(): GifsResponse? {
        return gifsUseCase()
    }
}