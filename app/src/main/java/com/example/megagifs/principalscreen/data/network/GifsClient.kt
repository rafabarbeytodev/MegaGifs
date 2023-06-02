package com.example.megagifs.principalscreen.data.network

import com.example.megagifs.principalscreen.data.network.response.GifsResponse
import retrofit2.Response
import retrofit2.http.GET

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
    @GET("/v1/gifs/trending?api_key=ZCwXdskKJKjq3cMU3Yihc73t5eQNnsHk&limit&random_id&offset&rating&bundle")
    suspend fun getGifs(): Response<GifsResponse>
}