package com.example.megagifs.screendetails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.megagifs.screendetails.data.network.response.GifUniqueItem
import com.example.megagifs.screendetails.domain.SearchGifUniqueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screendetails.ui
 *
 * Created by Rafael Barbeyto Torrellas on 07/06/2023 at 17:55
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val searchingGifUseCase: SearchGifUniqueUseCase
) : ViewModel() {

    private val _resultGif =  MutableLiveData<GifUniqueItem>()
    val resultGif: LiveData<GifUniqueItem> = _resultGif

    suspend fun onGetSearchGif(id:String){
        _resultGif.value = searchingGifUseCase(id)?.data
    }

}