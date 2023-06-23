package com.example.megagifs.di

import android.content.Context
import androidx.room.Room
import com.example.megagifs.data.GifDao
import com.example.megagifs.data.GifsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.data.di
 *
 * Created by Rafael Barbeyto Torrellas on 10/06/2023 at 17:00
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideGifDao(gifDatabase: GifsDatabase): GifDao{
        return gifDatabase.gifDao()
    }

    @Provides
    @Singleton
    fun provideGifDatabase(@ApplicationContext appContext: Context): GifsDatabase{
        return Room.databaseBuilder(appContext,GifsDatabase::class.java,"Gifs").build()
    }
}