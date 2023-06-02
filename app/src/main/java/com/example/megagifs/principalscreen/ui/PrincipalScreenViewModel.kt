package com.example.megagifs.principalscreen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.megagifs.principalscreen.data.network.response.GifsResponse
import com.example.megagifs.principalscreen.domain.GifsUseCase

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen6.ui
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 7:41
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class PrincipalScreenViewModel : ViewModel() {

    private val _result =  MutableLiveData<GifsResponse>()
    val result: LiveData<GifsResponse> = _result

    val gifsUseCase = GifsUseCase()

    suspend fun onGetGifs(){
        _result.value = gifsUseCase()
    }
}