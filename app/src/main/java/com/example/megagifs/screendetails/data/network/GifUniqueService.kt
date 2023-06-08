package com.example.megagifs.screendetails.data.network

import android.util.Log
import com.example.megagifs.screendetails.data.network.response.GifUniqueResponse
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
class GifUniqueService @Inject constructor(
    private val gifUniqueClient: GifUniqueClient
) {

    //private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getGif(id: String): GifUniqueResponse? {
        return withContext(Dispatchers.IO){
            try {
                val response = gifUniqueClient.getGif(id)
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