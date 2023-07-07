package com.aireadevs.megagifs.screenprincipal.data.network

import com.aireadevs.megagifs.core.API_KEY_GIPHY
import com.aireadevs.megagifs.screenprincipal.data.network.entity.GifsEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.screen6.data
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 16:41
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
interface GifsClient {
    @GET("/v1/gifs/trending?api_key=$API_KEY_GIPHY&limit&random_id&offset&rating&bundle")
    suspend fun getTrendingGifs(): Response<GifsEntity>

    @GET("/v1/stickers/trending?api_key=$API_KEY_GIPHY&limit&random_id&offset&rating&bundle")
    suspend fun getTrendingStickers(): Response<GifsEntity>

    @GET("/v2/emoji?api_key=$API_KEY_GIPHY&limit=500&offset")
    suspend fun getTrendingEmojis(): Response<GifsEntity>

    @GET("/v1/gifs/search?api_key=$API_KEY_GIPHY")
    suspend fun getSearchGifs(@Query("q") q: String): Response<GifsEntity>

    @GET("/v1/stickers/search?api_key=$API_KEY_GIPHY")
    suspend fun getSearchStickers(@Query("q") q: String): Response<GifsEntity>


}