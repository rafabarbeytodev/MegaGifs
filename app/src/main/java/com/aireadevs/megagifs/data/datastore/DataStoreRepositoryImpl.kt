package com.aireadevs.megagifs.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.aireadevs.megagifs.core.MAIL_DEVELOPER
import com.aireadevs.megagifs.core.USER_PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)

class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context
): DataStoreRepository{

    override suspend fun putString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getDataStore(): Flow<DataStoreEntity?> {
        return context.dataStore.data.map { preferences ->
            DataStoreEntity(
                mailDeveloper = preferences[stringPreferencesKey(MAIL_DEVELOPER)].orEmpty()
            )
        }
    }
}