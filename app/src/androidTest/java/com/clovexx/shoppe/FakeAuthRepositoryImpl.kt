package com.clovexx.shoppe

import android.content.Context
import android.net.Uri
import android.util.Log
import com.clovexx.shoppe.domain.model.UserProfile
import com.clovexx.shoppe.domain.repository.AuthRepository

class FakeAuthRepository : AuthRepository {
    private var user: UserProfile? = null

    override suspend fun signUp(email: String, pass: String, phone: String, name: String): UserProfile {
        Log.e("Fake", "called")
        val profile = UserProfile("test_id", name, phone, email)
        user = profile
        return profile
    }

    override suspend fun signIn(email: String, pass: String) {
    }

    override suspend fun getProfile(): UserProfile? = user
    override suspend fun uploadAvatar(
        uri: Uri,
        userId: String,
        context: Context
    ): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateProfileAvatar(userId: String, avatarUrl: String) {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(email: String) {}

    override suspend fun updatePassword(pass: String) {}

    override suspend fun signOut() { user = null }

    override fun isLoggedIn(): Boolean = user != null
}
