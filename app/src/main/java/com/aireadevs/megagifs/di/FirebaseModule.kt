package com.aireadevs.megagifs.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.di
 *
 * Created by Rafael Barbeyto Torrellas on 03/07/2023 at 16:51
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }
}