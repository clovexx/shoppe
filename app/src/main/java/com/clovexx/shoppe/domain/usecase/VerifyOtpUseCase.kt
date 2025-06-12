package com.clovexx.shoppe.domain.usecase

import com.clovexx.shoppe.domain.repository.AuthRepository
import javax.inject.Inject

class VerifyOtpUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(email: String, token: String) = repo.verifyOtp(email, token)
}
