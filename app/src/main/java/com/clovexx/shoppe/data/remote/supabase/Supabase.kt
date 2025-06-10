package com.clovexx.shoppe.data.remote.supabase

import com.clovexx.shoppe.data.remote.model.UserProfileDto
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import io.ktor.http.ContentType

class Supabase(
    baseUrl: String,
    apiKey: String
) {
    val client = createSupabaseClient(baseUrl, apiKey) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }

    suspend fun signUp(_email: String, _password: String): String? {
        val res = client.auth.signUpWith(Email) {
            email = _email
            password = _password
        }

        return res?.id
    }

    suspend fun insertProfile(dto: UserProfileDto) {
        client.postgrest["profiles"].insert(dto)
    }

    suspend fun signIn(_email: String, _password: String) {
        client.auth.signInWith(Email) {
            email = _email
            password = _password
        }
    }

    suspend fun sendOtp(email: String, type: OtpType.
Email) {
        client.auth.resendEmail(type, email)
    }

    suspend fun verifyOtp(email: String, token: String) {
        client.auth.verifyEmailOtp(OtpType.Email.EMAIL, email, token)
    }

    suspend fun getProfileById(id: String): UserProfileDto? {
        return client.postgrest["profiles"].select { filter { eq("id", id) } }
            .decodeSingleOrNull<UserProfileDto>()
    }

    suspend fun uploadAvatar(
        avatarPath: String,
        bytes: ByteArray,
        _contentType: ContentType = ContentType("image", "jpeg")
    ): String {
        client.storage["avatars"].upload(
            path = avatarPath,
            data = bytes
        ) {
            contentType = _contentType
            upsert = true
        }
        return client.storage["avatars"].publicUrl(avatarPath)
    }

    suspend fun updateProfileAvatar(userId: String, avatarUrl: String) {
        client.postgrest["profiles"].update(
            { set("avatar_url", avatarUrl) }
        ) { filter { eq("id", userId) } }
    }

    suspend fun resetPassword(email: String) {
        client.auth.resetPasswordForEmail(email)
    }

    suspend fun updatePassword(newPassword: String) {
        client.auth.updateUser {
            password = newPassword
        }
    }

    suspend fun signOut() = client.auth.signOut()
    fun currentUserId(): String? = client.auth.currentUserOrNull()?.id
    fun isLoggedIn() = client.auth.currentSessionOrNull() != null

}


