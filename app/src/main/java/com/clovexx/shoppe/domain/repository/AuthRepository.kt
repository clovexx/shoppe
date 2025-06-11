package com.clovexx.shoppe.domain.repository

import android.content.Context
import android.net.Uri
import com.clovexx.shoppe.domain.model.UserProfile
import io.github.jan.supabase.auth.OtpType

interface AuthRepository {
    suspend fun signUp(email: String, pass: String, phone: String, name: String): UserProfile
    suspend fun signIn(email: String, pass: String)
    suspend fun getProfile(): UserProfile?
    suspend fun uploadAvatar(uri: Uri, userId: String, context: Context): String
    suspend fun updateProfileAvatar(userId: String, avatarUrl: String)
    suspend fun sendOtp(email: String, type: OtpType.Email)
    suspend fun verifyOtp(email: String, token: String)
    suspend fun resetPassword(email: String)
    suspend fun updatePassword(pass: String)
    suspend fun signOut()
    fun isLoggedIn(): Boolean
}
