package com.clovexx.shoppe.domain.usecase

import com.clovexx.shoppe.domain.repository.AuthRepository
import javax.inject.Inject


class UpdatePasswordUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(password: String) = repo.updatePassword(password)
}