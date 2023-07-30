package com.aireadevs.megagifs.screenimages.data.retrofit

import com.aireadevs.megagifs.screenimages.data.retrofit.entity.GifsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.screen6.data.network
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 22:10
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

class GifsService @Inject constructor(
    private val gifsClient: GifsClient
) {
    suspend fun getTrendingGifs(): GifsEntity? {
        return withContext(Dispatchers.IO){
            try {
                val response = gifsClient.getTrendingGifs()
                if (response.isSuccessful) {
                   //RESPUESTA OK
                    response.body()
                } else {
                    //RESPUESTA NOK
                    null
                }
            }catch(e:Exception){
                //ERROR EN RESPUESTA API
                null
            }
        }
    }
    suspend fun getTrendingStickers(): GifsEntity? {
        return withContext(Dispatchers.IO){
            try {
                val response = gifsClient.getTrendingStickers()
                if (response.isSuccessful) {
                    //RESPUESTA OK
                    response.body()
                } else {
                    //RESPUESTA NOK
                    null
                }
            }catch(e:Exception){
                //ERROR EN RESPUESTA API
                null
            }
        }
    }
    suspend fun getTrendingEmojis(): GifsEntity? {
        return withContext(Dispatchers.IO){
            try {
                val response = gifsClient.getTrendingEmojis()
                if (response.isSuccessful) {
                    //RESPUESTA OK
                    response.body()
                } else {
                    //RESPUESTA NOK
                    null
                }
            }catch(e:Exception){
                //ERROR EN RESPUESTA API
                null
            }
        }
    }
    suspend fun getSearchGifs(search: String): GifsEntity? {
        return withContext(Dispatchers.IO){
            try {
                val response = gifsClient.getSearchGifs(search)
                if (response.isSuccessful) {
                    //RESPUESTA OK
                    response.body()
                } else {
                    //RESPUESTA NOK
                    null
                }
            }catch(e:Exception){
                //ERROR EN RESPUESTA API
                null
            }
        }
    }
    suspend fun getSearchStickers(search: String): GifsEntity? {
        return withContext(Dispatchers.IO){
            try {
                val response = gifsClient.getSearchStickers(search)
                if (response.isSuccessful) {
                    //RESPUESTA OK
                    response.body()
                } else {
                    //RESPUESTA NOK
                    null
                }
            }catch(e:Exception){
                //ERROR EN RESPUESTA API
                null
            }
        }
    }
}