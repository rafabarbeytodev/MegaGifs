package com.aireadevs.megagifs.screenimages.ui

import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aireadevs.megagifs.BuildConfig
import com.aireadevs.megagifs.screenimages.domain.favoritesUsesCase.GetGifsFavUseCase
import com.aireadevs.megagifs.screenimages.domain.searchsUsesCase.SearchGifsUseCase
import com.aireadevs.megagifs.screenimages.domain.searchsUsesCase.SearchStickersUseCase
import com.aireadevs.megagifs.screenimages.domain.trendingsUsesCase.TrendingEmojisUseCase
import com.aireadevs.megagifs.screenimages.domain.trendingsUsesCase.TrendingGifsUseCase
import com.aireadevs.megagifs.screenimages.domain.trendingsUsesCase.TrendingStickersUseCase
import com.aireadevs.megagifs.screenimages.ui.model.FavModel
import com.aireadevs.megagifs.screenimages.ui.model.GifsModel
import com.aireadevs.megagifs.ui.model.Report
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresApi
import com.aireadevs.megagifs.data.datastore.DataStoreRepository

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
    private val getGifsFavUseCase: GetGifsFavUseCase,
    private val dataStore: DataStoreRepository
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

    private val _mailDeveloper = MutableLiveData<String>()
    val mailDeveloper: LiveData<String> = _mailDeveloper

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
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun getReport(context: Context): String {

        val permissionReadMedia: Boolean = checkStatePermission(context,Manifest.permission.READ_MEDIA_IMAGES)
        val permissionReadExternal: Boolean = checkStatePermission(context,Manifest.permission.READ_EXTERNAL_STORAGE)
        val permissionWriteExternal: Boolean = checkStatePermission(context,Manifest.permission.WRITE_EXTERNAL_STORAGE)

        val report = Report(
            appVersion = BuildConfig.VERSION_NAME,
            model = "${Build.MANUFACTURER} - ${Build.MODEL}",
            versionOs = "${Build.VERSION.SDK_INT}",
            readMediaPermission = permissionReadMedia,
            readExternalPermission = permissionReadExternal,
            writeExternalPermission = permissionWriteExternal
        )
        return Gson().toJson(report)
    }

    private fun checkStatePermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context, permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun sendMail(context: Context, message: String, mail: String) {

        val txtReport = "\r\n************************************\r\n" +
                "Additional information for developer:\r\n " +
                getReport(context)

        val intentEmail = Intent(Intent.ACTION_SEND)
        intentEmail.type = "plain/text"
        intentEmail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        intentEmail.putExtra(
            Intent.EXTRA_SUBJECT,
            "Feedback from application MegaGifs"
        )
        intentEmail.putExtra(
            Intent.EXTRA_TEXT,
            "$message \r\n $txtReport"
        )
        intentEmail.putExtra(
            Intent.EXTRA_EMAIL,
            arrayOf(mail)
        )
        context.startActivity(
            Intent.createChooser(
                intentEmail,
                "Select mail client"
            )
        )
    }
    fun getMailDeveloper(){
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.getDataStore().collect{
                withContext(Dispatchers.Main){
                    if (it != null) {
                        _mailDeveloper.value = it.mailDeveloper
                    }
                }
            }
        }
    }

}