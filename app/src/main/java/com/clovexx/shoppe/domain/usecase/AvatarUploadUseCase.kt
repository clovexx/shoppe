package com.clovexx.shoppe.domain.usecase

import android.content.Context
import android.net.Uri
import com.clovexx.shoppe.domain.repository.AuthRepository
import javax.inject.Inject

class AvatarUploadUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(uri: Uri, id: String, context: Context) = repo.uploadAvatar(uri, id, context)
}
