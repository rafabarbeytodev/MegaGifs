package com.example.megagifs.screenprincipal.data.network

import com.example.megagifs.core.API_KEY
import com.example.megagifs.screenprincipal.data.network.response.GifsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen6.data
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 16:41
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
interface GifsClient {
    @GET("/v1/gifs/trending?api_key=$API_KEY&limit&random_id&offset&rating&bundle")
    suspend fun getTrendingGifs(): Response<GifsResponse>

    @GET("/v1/stickers/trending?api_key=$API_KEY&limit&random_id&offset&rating&bundle")
    suspend fun getTrendingStickers(): Response<GifsResponse>

    @GET("/v2/emoji?api_key=$API_KEY&limit&offset")
    suspend fun getTrendingEmojis(): Response<GifsResponse>

    @GET("/v1/gifs/search?api_key=$API_KEY")
    suspend fun getSearchGifs(@Query("q") q: String): Response<GifsResponse>

    @GET("/v1/stickers/search?api_key=$API_KEY")
    suspend fun getSearchStickers(@Query("q") q: String): Response<GifsResponse>


}