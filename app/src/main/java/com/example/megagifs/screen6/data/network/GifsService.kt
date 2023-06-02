package com.example.megagifs.screen6.data.network

import android.util.Log
import com.example.megagifs.core.network.RetrofitHelper
import com.example.megagifs.screen6.data.network.response.GifsResponse
import com.example.megagifs.screen6.data.network.response.GiphyItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

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

    suspend fun getGifs(): GifsResponse? {
        return withContext(Dispatchers.IO){
            try {
                val response = retrofit.create(GifsClient::class.java).getGifs()
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