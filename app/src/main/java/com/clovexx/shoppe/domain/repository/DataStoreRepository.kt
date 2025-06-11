package com.clovexx.shoppe.domain.repository

import com.clovexx.shoppe.data.local.model.Settings
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveSettings(settings: Settings)
    fun getSettings(): Flow<Settings>
}