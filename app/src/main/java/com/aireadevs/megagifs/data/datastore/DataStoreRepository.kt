package com.aireadevs.megagifs.data.datastore

import kotlinx.coroutines.flow.Flow

/*****
 * Proyect: sms2mailphone
 * Package: com.aireadevs.android.kotlin.sms2mailphone.data.model
 *
 * Created by Rafael Barbeyto Torrellas on 04/10/2022 at 10:13
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2022.
 *****/
interface DataStoreRepository {

    suspend fun putString(key: String, value: String)
    suspend fun getDataStore(): Flow<DataStoreEntity?>

}