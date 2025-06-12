package com.clovexx.shoppe.domain.usecase

import com.clovexx.shoppe.data.local.model.Settings
import com.clovexx.shoppe.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveSettingsUseCase @Inject constructor(private val repo: DataStoreRepository) {
    suspend operator fun invoke(settings: Settings) = repo.saveSettings(settings)
}