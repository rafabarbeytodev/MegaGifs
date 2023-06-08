package com.example.megagifs.screenprincipal.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.megagifs.screenprincipal.data.network.response.GifItem
import com.example.megagifs.screenprincipal.domain.searchs.SearchGifsUseCase
import com.example.megagifs.screenprincipal.domain.searchs.SearchStickersUseCase
import com.example.megagifs.screenprincipal.domain.trendings.TrendingEmojisUseCase
import com.example.megagifs.screenprincipal.domain.trendings.TrendingGifsUseCase
import com.example.megagifs.screenprincipal.domain.trendings.TrendingStickersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen6.ui
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 7:41
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@HiltViewModel
class PrincipalScreenViewModel @Inject constructor(
    private val trendingGifsUseCase: TrendingGifsUseCase,
    private val trendingStickersUseCase: TrendingStickersUseCase,
    private val trendingEmojisUseCase: TrendingEmojisUseCase,
    private val searchingGifsUseCase: SearchGifsUseCase,
    private val searchingStickersUseCase: SearchStickersUseCase
) : ViewModel() {

    private val _resultGifs = MutableLiveData<List<GifItem>>()
    val resultGifs: LiveData<List<GifItem>> = _resultGifs

    private val _resultStickers = MutableLiveData<List<GifItem>>()
    val resultStickers: LiveData<List<GifItem>> = _resultStickers

    private val _resultEmojis = MutableLiveData<List<GifItem>>()
    val resultEmojis: LiveData<List<GifItem>> = _resultEmojis

    private val _resultSearchGifs = MutableLiveData<List<GifItem>>()
    val resultSearchGifs: LiveData<List<GifItem>> = _resultSearchGifs

    private val _resultSearchStickers = MutableLiveData<List<GifItem>>()
    val resultSearchStickers: LiveData<List<GifItem>> = _resultSearchStickers

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress


    suspend fun onGetGifs() {
        _resultGifs.value = trendingGifsUseCase()?.data.orEmpty()
    }

    suspend fun onGetStickers() {
        _resultStickers.value = trendingStickersUseCase()?.data.orEmpty()
    }

    suspend fun onGetEmojis() {
        _resultEmojis.value = trendingEmojisUseCase()?.data.orEmpty()
    }

    suspend fun onGetSearchGifs(search: String) {
        _resultSearchGifs.value = searchingGifsUseCase(search)?.data.orEmpty()
    }

    suspend fun onGetSearchStickers(search: String) {
        _resultSearchStickers.value = searchingStickersUseCase(search)?.data.orEmpty()
    }

    fun showProgress(state: Boolean) {
        _showProgress.value = state
    }
}