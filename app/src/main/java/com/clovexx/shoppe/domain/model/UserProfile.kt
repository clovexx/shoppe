package com.clovexx.shoppe.domain.model

data class UserProfile(
    val id: String,
    val name: String,
    val phone: String?,
    val avatarUrl: String? = null,
    val language: String = "ru",
    val currency: String = "USD"
)
