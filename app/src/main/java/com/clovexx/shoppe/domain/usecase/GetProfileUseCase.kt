package com.clovexx.shoppe.domain.usecase

import com.clovexx.shoppe.domain.repository.AuthRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke() = repo.getProfile()
}
