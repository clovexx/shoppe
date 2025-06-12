package com.clovexx.shoppe.domain.usecase

import com.clovexx.shoppe.domain.repository.AuthRepository
import io.github.jan.supabase.auth.OtpType
import javax.inject.Inject

class SendOtpUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(email: String, type: OtpType.Email) = repo.sendOtp(email, type)
}