package com.example.megagifs.screenprincipal.data.network

import android.util.Log
import com.example.megagifs.screenprincipal.data.network.response.GifsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen6.data.network
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 22:10
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

class GifsService @Inject constructor(
    private val gifsClient: GifsClient
) {
    suspend fun getTrendingGifs(): GifsResponse? {
        return withContext(Dispatchers.IO){
            try {
                val response = gifsClient.getTrendingGifs()
                if (response.isSuccessful) {
                    Log.i("DEVELOPRAFA", "RESPUESTA OK")
                    response.body()
                } else {
                    Log.i("DEVELOPRAFA", "RESPUESTA NOK : ${response.message()}")
                    null
                }
            }catch(e:Exception){
                Log.i("DEVELOPRAFA", "ERROR EN RESPUESTA API: ${e.message.toString()}")
                null
            }
        }
    }
    suspend fun getTrendingStickers(): GifsResponse? {
        return withContext(Dispatchers.IO){
            try {
                val response = gifsClient.getTrendingStickers()
                if (response.isSuccessful) {
                    Log.i("DEVELOPRAFA", "RESPUESTA OK")
                    response.body()
                } else {
                    Log.i("DEVELOPRAFA", "RESPUESTA NOK : ${response.message()}")
                    null
                }
            }catch(e:Exception){
                Log.i("DEVELOPRAFA", "ERROR EN RESPUESTA API: ${e.message.toString()}")
                null
            }
        }
    }
    suspend fun getTrendingEmojis(): GifsResponse? {
        return withContext(Dispatchers.IO){
            try {
                val response = gifsClient.getTrendingEmojis()
                if (response.isSuccessful) {
                    Log.i("DEVELOPRAFA", "RESPUESTA OK")
                    response.body()
                } else {
                    Log.i("DEVELOPRAFA", "RESPUESTA NOK : ${response.message()}")
                    null
                }
            }catch(e:Exception){
                Log.i("DEVELOPRAFA", "ERROR EN RESPUESTA API: ${e.message.toString()}")
                null
            }
        }
    }
    suspend fun getSearchGifs(search: String): GifsResponse? {
        return withContext(Dispatchers.IO){
            try {
                val response = gifsClient.getSearchGifs(search)
                if (response.isSuccessful) {
                    Log.i("DEVELOPRAFA", "RESPUESTA OK")
                    response.body()
                } else {
                    Log.i("DEVELOPRAFA", "RESPUESTA NOK : ${response.message()}")
                    null
                }
            }catch(e:Exception){
                Log.i("DEVELOPRAFA", "ERROR EN RESPUESTA API: ${e.message.toString()}")
                null
            }
        }
    }
    suspend fun getSearchStickers(search: String): GifsResponse? {
        return withContext(Dispatchers.IO){
            try {
                val response = gifsClient.getSearchStickers(search)
                if (response.isSuccessful) {
                    Log.i("DEVELOPRAFA", "RESPUESTA OK")
                    response.body()
                } else {
                    Log.i("DEVELOPRAFA", "RESPUESTA NOK : ${response.message()}")
                    null
                }
            }catch(e:Exception){
                Log.i("DEVELOPRAFA", "ERROR EN RESPUESTA API: ${e.message.toString()}")
                null
            }
        }
    }
}