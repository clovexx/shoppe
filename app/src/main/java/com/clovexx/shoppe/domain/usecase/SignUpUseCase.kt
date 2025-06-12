package com.clovexx.shoppe.domain.usecase

import com.clovexx.shoppe.domain.model.UserProfile
import com.clovexx.shoppe.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(email: String, pass: String, phone: String, name: String): UserProfile {
        return repo.signUp(email, pass, phone, name)
    }
}
