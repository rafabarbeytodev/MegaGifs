package com.example.megagifs.principalscreen.data.network

import android.util.Log
import com.example.megagifs.core.network.RetrofitHelper
import com.example.megagifs.principalscreen.data.network.response.GifsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen6.data.network
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 22:10
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class GifsService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getTrendingGifs(): GifsResponse? {
        return withContext(Dispatchers.IO){
            try {
                val response = retrofit.create(GifsClient::class.java).getTrendingGifs()
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
                val response = retrofit.create(GifsClient::class.java).getTrendingStickers()
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
                val response = retrofit.create(GifsClient::class.java).getTrendingEmojis()
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
                val response = retrofit.create(GifsClient::class.java).getSearchGifs(search)
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
                val response = retrofit.create(GifsClient::class.java).getSearchStickers(search)
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