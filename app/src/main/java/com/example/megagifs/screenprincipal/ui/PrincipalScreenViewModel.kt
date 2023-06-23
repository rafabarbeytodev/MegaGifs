package com.example.megagifs.screenprincipal.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.megagifs.screenprincipal.data.network.entity.GifsEntity
import com.example.megagifs.screenprincipal.domain.searchsUsesCase.SearchGifsUseCase
import com.example.megagifs.screenprincipal.domain.searchsUsesCase.SearchStickersUseCase
import com.example.megagifs.screenprincipal.domain.trendingsUsesCase.TrendingEmojisUseCase
import com.example.megagifs.screenprincipal.domain.trendingsUsesCase.TrendingGifsUseCase
import com.example.megagifs.screenprincipal.domain.trendingsUsesCase.TrendingStickersUseCase
import com.example.megagifs.screenprincipal.ui.model.GifsModel
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

    private val _resultGifs = MutableLiveData<GifsModel?>()
    val resultGifs: LiveData<GifsModel?> = _resultGifs

    private val _resultStickers = MutableLiveData<GifsModel?>()
    val resultStickers: LiveData<GifsModel?> = _resultStickers

    private val _resultEmojis = MutableLiveData<GifsModel?>()
    val resultEmojis: LiveData<GifsModel?> = _resultEmojis

    private val _resultSearchGifs = MutableLiveData<GifsModel?>()
    val resultSearchGifs: LiveData<GifsModel?> = _resultSearchGifs

    private val _resultSearchStickers = MutableLiveData<GifsModel?>()
    val resultSearchStickers: LiveData<GifsModel?> = _resultSearchStickers

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress

    suspend fun onGetGifs() {
        _resultGifs.value = trendingGifsUseCase()
    }
    suspend fun onGetStickers() {
        _resultStickers.value = trendingStickersUseCase()
    }
    suspend fun onGetEmojis() {
        _resultEmojis.value = trendingEmojisUseCase()
    }
    suspend fun onGetSearchGifs(search: String) {
        _resultSearchGifs.value = searchingGifsUseCase(search)
    }
    suspend fun onGetSearchStickers(search: String) {
        _resultSearchStickers.value = searchingStickersUseCase(search)
    }
    fun onShowProgress(state: Boolean) {
        _showProgress.value = state
    }
}