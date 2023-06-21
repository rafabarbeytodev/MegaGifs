package com.example.megagifs.screendetails.data.network

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

    suspend fun getGif(id: String): GifUniqueResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = gifUniqueClient.getGif(id)
                if (response.isSuccessful) {
                    //RESPUESTA OK
                    response.body()
                } else {
                    //RESPUESTA NOK
                    null
                }
            } catch (e: Exception) {
                //ERROR EN RESPUESTA API
                null
            }
        }
    }
}