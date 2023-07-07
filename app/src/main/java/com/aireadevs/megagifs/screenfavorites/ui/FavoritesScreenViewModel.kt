package com.aireadevs.megagifs.screenfavorites.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aireadevs.megagifs.screenfavorites.domain.AddGifFavUseCase
import com.aireadevs.megagifs.screenfavorites.domain.DeleteGifFavUseCase
import com.aireadevs.megagifs.screenfavorites.domain.GetGifsFavUseCase
import com.aireadevs.megagifs.screenfavorites.ui.model.FavModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.screenfavorites.ui
 *
 * Created by Rafael Barbeyto Torrellas on 25/06/2023 at 13:53
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val getGifsFavUseCase: GetGifsFavUseCase,
    private val addGifFavUseCase: AddGifFavUseCase,
    private val deleteGifFavUseCase: DeleteGifFavUseCase
) : ViewModel() {

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress

    private val _resultGifsFav = MutableLiveData<List<FavModel>>()
    val resultGifsFav: LiveData<List<FavModel>> = _resultGifsFav

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

    fun onAddFav(favModel: FavModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addGifFavUseCase.invoke(favModel)
        }
    }

    fun onDeleteFav(favModel: FavModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteGifFavUseCase.invoke(favModel)
        }
    }

    fun onShowProgress(state: Boolean) {
        _showProgress.value = state
    }
}
