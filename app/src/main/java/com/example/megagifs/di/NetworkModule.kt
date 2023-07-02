package com.example.megagifs.di

import com.example.megagifs.screenprincipal.data.network.GifsClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.core.di
 *
 * Created by Rafael Barbeyto Torrellas on 08/06/2023 at 15:35
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.giphy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideGifsClient(retrofit: Retrofit): GifsClient {
        return retrofit.create(GifsClient::class.java)
    }



}