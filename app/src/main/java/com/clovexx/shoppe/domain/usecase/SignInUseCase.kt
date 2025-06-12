package com.clovexx.shoppe.domain.usecase

import com.clovexx.shoppe.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(email: String, pass: String) = repo.signIn(email, pass)
}