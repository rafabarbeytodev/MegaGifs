package com.aireadevs.megagifs.di

import android.content.Context
import com.aireadevs.megagifs.data.datastore.DataStoreRepository
import com.aireadevs.megagifs.data.datastore.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.di
 *
 * Created by Rafael Barbeyto Torrellas on 01/08/2023 at 15:40
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(context)

}