package com.example.megagifs.screendetails.ui

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.megagifs.screendetails.data.network.response.GifUniqueItem
import com.example.megagifs.screendetails.domain.SearchGifUniqueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
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

    private val _resultGif = MutableLiveData<GifUniqueItem>()
    val resultGif: LiveData<GifUniqueItem> = _resultGif

    suspend fun onGetSearchGif(id: String) {
        _resultGif.value = searchingGifUseCase(id)?.data
    }

    fun getGifBytesFromUrl(url: String): ByteArray? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        return try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.bytes()
            } else {
                null
            }
        } catch (e: IOException) {
            null
        }
    }

    private fun getFileFromCache(context: Context): File {
        val cachePath = File(context.cacheDir, "shared_gif").apply { mkdirs() }
        return File(cachePath, "shared_gif.gif")
    }

    fun getUriFromBytes(bytes: ByteArray, context: Context): Uri {

        val file = getFileFromCache(context)

        FileOutputStream(file).use { outputStream ->
            outputStream.write(bytes)
        }

        return FileProvider.getUriForFile(context, context.packageName + ".provider", file)
    }

    suspend fun saveGif(bytes: ByteArray, context: Context) {
        val uri = getUriFromBytes(bytes, context)
        saveGifToPhotos(uri, context)
    }

    private suspend fun saveGifToPhotos(uri: Uri, context: Context) {

        val contentResolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "shared_gif.gif")
            put(MediaStore.Images.Media.MIME_TYPE, "image/gif")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/MegaGifs")
        }

        val imageUri =
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        imageUri?.let { destinationUri ->
            contentResolver.openInputStream(uri).use { inputStream: InputStream? ->
                if (inputStream == null) return
                contentResolver.openOutputStream(destinationUri).use { out ->
                    if (out == null) return
                    // Transfer bytes from in to out
                    val buf = ByteArray(1024)
                    var len: Int
                    while (inputStream.read(buf).also { len = it } > 0) {
                        out.write(buf, 0, len)
                    }
                }
            }
        }

        withContext(Dispatchers.Main) {
            Toast.makeText(context, "GIF saved to Photos", Toast.LENGTH_SHORT).show()
        }
    }
}