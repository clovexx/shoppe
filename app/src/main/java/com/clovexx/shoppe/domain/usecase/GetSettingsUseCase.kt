package com.clovexx.shoppe.domain.usecase

import com.clovexx.shoppe.domain.repository.DataStoreRepository
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(private val repo: DataStoreRepository) {
    suspend operator fun invoke() = repo.getSettings()
}