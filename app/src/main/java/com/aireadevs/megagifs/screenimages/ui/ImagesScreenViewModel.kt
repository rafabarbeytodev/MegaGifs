package com.aireadevs.megagifs.screenimages.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aireadevs.megagifs.screenimages.domain.favoritesUsesCase.GetGifsFavUseCase
import com.aireadevs.megagifs.screenimages.domain.searchsUsesCase.SearchGifsUseCase
import com.aireadevs.megagifs.screenimages.domain.searchsUsesCase.SearchStickersUseCase
import com.aireadevs.megagifs.screenimages.domain.trendingsUsesCase.TrendingEmojisUseCase
import com.aireadevs.megagifs.screenimages.domain.trendingsUsesCase.TrendingGifsUseCase
import com.aireadevs.megagifs.screenimages.domain.trendingsUsesCase.TrendingStickersUseCase
import com.aireadevs.megagifs.screenimages.ui.model.FavModel
import com.aireadevs.megagifs.screenimages.ui.model.GifsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.screen6.ui
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 7:41
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@HiltViewModel
class ImagesScreenViewModel @Inject constructor(
    private val trendingGifsUseCase: TrendingGifsUseCase,
    private val trendingStickersUseCase: TrendingStickersUseCase,
    private val trendingEmojisUseCase: TrendingEmojisUseCase,
    private val searchingGifsUseCase: SearchGifsUseCase,
    private val searchingStickersUseCase: SearchStickersUseCase,
    private val getGifsFavUseCase: GetGifsFavUseCase
) : ViewModel() {

    private val _resultGifs = MutableLiveData<GifsModel?>()
    val resultGifs: LiveData<GifsModel?> = _resultGifs

    private val _resultStickers = MutableLiveData<GifsModel?>()
    val resultStickers: LiveData<GifsModel?> = _resultStickers

    private val _resultEmojis = MutableLiveData<GifsModel?>()
    val resultEmojis: LiveData<GifsModel?> = _resultEmojis

    private val _resultGifsFav = MutableLiveData<List<FavModel>>()
    val resultGifsFav: LiveData<List<FavModel>> = _resultGifsFav

    private val _resultSearchGifs = MutableLiveData<GifsModel?>()
    val resultSearchGifs: LiveData<GifsModel?> = _resultSearchGifs

    private val _resultSearchStickers = MutableLiveData<GifsModel?>()
    val resultSearchStickers: LiveData<GifsModel?> = _resultSearchStickers

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress

    private val _typeImage = MutableLiveData<Int>()
    val typeImage: LiveData<Int> = _typeImage

    suspend fun onGetGifs() {
        _resultGifs.value = trendingGifsUseCase()
    }
    suspend fun onGetStickers() {
        _resultStickers.value = trendingStickersUseCase()
    }
    suspend fun onGetEmojis() {
        _resultEmojis.value = trendingEmojisUseCase()
    }
    suspend fun onGetGifsFav() {
        val resultGet = getGifsFavUseCase.invoke()
        withContext(Dispatchers.Main) {
            _resultGifsFav.value = resultGet
        }
    }
    suspend fun checkIdIsFavorite(id: String): Boolean = suspendCoroutine { continuation ->
        viewModelScope.launch(Dispatchers.IO) {
            val resultGet = getGifsFavUseCase.invoke()
            val isFavourite = resultGet.any {
                it.id == id
            }
            withContext(Dispatchers.Main) {
                continuation.resume(isFavourite)
            }
        }
    }
    suspend fun onGetGifById(id: String): List<FavModel> {
        val resultGet = getGifsFavUseCase.invoke().filter {
            it.id == id
        }
        return resultGet
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
    fun onTypeImage(type: Int) {
        _typeImage.value = type
    }
}