package com.example.megagifs.screendetails.data.network

import com.example.megagifs.core.API_KEY
import com.example.megagifs.screendetails.data.network.entity.GifUniqueEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen6.data
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 16:41
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
interface GifUniqueClient {
    @GET("/v1/gifs/{id}?api_key=$API_KEY&gif_id=&random_id&rating")
    suspend fun getGif(@Path("id") id: String): Response<GifUniqueEntity>
}