package com.example.megagifs.principalscreen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.megagifs.principalscreen.data.network.response.GiphyItem
import com.example.megagifs.principalscreen.domain.SearchGifsUseCase
import com.example.megagifs.principalscreen.domain.SearchStickersUseCase
import com.example.megagifs.principalscreen.domain.TrendingEmojisUseCase
import com.example.megagifs.principalscreen.domain.TrendingGifsUseCase
import com.example.megagifs.principalscreen.domain.TrendingStickersUseCase

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

    private val _resultGifs =  MutableLiveData<List<GiphyItem>>()
    val resultGifs: LiveData<List<GiphyItem>> = _resultGifs

    private val _resultStickers =  MutableLiveData<List<GiphyItem>>()
    val resultStickers: LiveData<List<GiphyItem>> = _resultStickers

    private val _resultEmojis =  MutableLiveData<List<GiphyItem>>()
    val resultEmojis: LiveData<List<GiphyItem>> = _resultEmojis

    private val _resultSearchGifs =  MutableLiveData<List<GiphyItem>>()
    val resultSearchGifs: LiveData<List<GiphyItem>> = _resultSearchGifs

    private val _resultSearchStickers =  MutableLiveData<List<GiphyItem>>()
    val resultSearchStickers: LiveData<List<GiphyItem>> = _resultSearchStickers

    val trendingGifsUseCase = TrendingGifsUseCase()
    val trendingStickersUseCase = TrendingStickersUseCase()
    val trendingEmojisUseCase = TrendingEmojisUseCase()
    val searchingGifsUseCase = SearchGifsUseCase()
    val searchingStickersUseCase = SearchStickersUseCase()


    suspend fun onGetGifs(){
        _resultGifs.value = trendingGifsUseCase()?.data.orEmpty()
    }
    suspend fun onGetStickers(){
        _resultStickers.value = trendingStickersUseCase()?.data.orEmpty()
    }
    suspend fun onGetEmojis(){
        _resultEmojis.value = trendingEmojisUseCase()?.data.orEmpty()
    }
    suspend fun onGetSearchGifs(search:String){
        _resultSearchGifs.value = searchingGifsUseCase(search)?.data.orEmpty()
    }
    suspend fun onGetSearchStickers(search:String){
        _resultSearchStickers.value = searchingStickersUseCase(search)?.data.orEmpty()
    }



}