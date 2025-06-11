package com.clovexx.shoppe.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.clovexx.shoppe.data.local.model.Settings
import com.clovexx.shoppe.domain.repository.DataStoreRepository
import com.clovexx.shoppe.utils.SettingsSerializer
import kotlinx.coroutines.flow.Flow

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

val Context.protoDataStore: DataStore<Settings> by dataStore(
    fileName = "settings.json",
    serializer = SettingsSerializer
)

class DataStoreRepositoryImpl(
    val context: Context
) : DataStoreRepository {

    private val dataStore = context.dataStore

    private val protoDataStore = context.protoDataStore

    override suspend fun saveSettings(settings: Settings) {
        protoDataStore.updateData {
            settings
        }
    }

    override fun getSettings(): Flow<Settings> =
        protoDataStore.data
}
