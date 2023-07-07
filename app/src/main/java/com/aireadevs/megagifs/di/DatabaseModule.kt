package com.aireadevs.megagifs.di

import android.content.Context
import androidx.room.Room
import com.aireadevs.megagifs.screenfavorites.data.room.GifsFavDao
import com.aireadevs.megagifs.screenfavorites.data.room.GifsFavDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.data.di
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
    fun provideGifDao(gifDatabase: GifsFavDatabase): GifsFavDao {
        return gifDatabase.gifFavDao()
    }

    @Provides
    @Singleton
    fun provideGifDatabase(@ApplicationContext appContext: Context): GifsFavDatabase {
        return Room.databaseBuilder(
            appContext,
            GifsFavDatabase::class.java,
            "GifsFav")
            .fallbackToDestructiveMigration()
            .build()
    }
}
