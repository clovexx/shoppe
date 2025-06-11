package com.clovexx.shoppe.data.repository

import android.content.Context
import android.net.Uri
import com.clovexx.shoppe.data.mappers.toDomain
import com.clovexx.shoppe.data.remote.model.UserProfileDto
import com.clovexx.shoppe.data.remote.supabase.Supabase
import com.clovexx.shoppe.domain.model.UserProfile
import com.clovexx.shoppe.domain.repository.AuthRepository
import io.github.jan.supabase.auth.OtpType

class AuthRepositoryImpl(
    private val api: Supabase
): AuthRepository {

    override suspend fun signUp(email: String, pass: String, phone: String, name: String): UserProfile {
        var userId = api.signUp(email, pass)
        if (userId == null) {
            userId = api.currentUserId()
        }
        if (userId == null) {
            throw IllegalStateException("Не удалось получить id пользователя.")
        }
        val dto = UserProfileDto(
            id = userId, name = name, phone = phone
        )
        api.insertProfile(dto)
        return dto.toDomain()
    }

    override suspend fun uploadAvatar(uri: Uri, userId: String, context: Context): String {
        val bytes = readBytesFromUri(context, uri)
        return api.uploadAvatar(userId, bytes)
    }

    override suspend fun updateProfileAvatar(userId: String, avatarUrl: String) {
        api.updateProfileAvatar(userId, avatarUrl)
    }

    override suspend fun sendOtp(email: String, type: OtpType.Email) {
        api.sendOtp(email, type)
    }

    override suspend fun verifyOtp(email: String, token: String) {
        api.verifyOtp(email, token)
    }

    override suspend fun signIn(email: String, pass: String) = api.signIn(email, pass)

    override suspend fun getProfile(): UserProfile? =
        api.currentUserId()?.let { api.getProfileById(it)?.toDomain() }

    override suspend fun resetPassword(email: String) = api.resetPassword(email)

    override suspend fun updatePassword(pass: String) = api.updatePassword(pass)

    override suspend fun signOut() = api.signOut()

    override fun isLoggedIn(): Boolean = api.isLoggedIn()
}

fun readBytesFromUri(context: Context, uri: Uri): ByteArray {
    return context.contentResolver.openInputStream(uri).use { it?.readBytes() ?: ByteArray(0) }
}
