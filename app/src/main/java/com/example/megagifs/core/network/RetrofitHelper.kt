package com.example.megagifs.core.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.core.network
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 16:35
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
object RetrofitHelper {
    fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.giphy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}