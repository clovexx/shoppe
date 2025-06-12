package com.clovexx.shoppe.domain.usecase

import android.content.Context
import android.net.Uri
import com.clovexx.shoppe.domain.repository.AuthRepository
import javax.inject.Inject

class ProfileAvatarUpdateUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(uri: String, id: String) = repo.updateProfileAvatar(id, uri)
}
