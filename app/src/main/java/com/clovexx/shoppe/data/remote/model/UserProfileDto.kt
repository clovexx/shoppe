package com.clovexx.shoppe.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileDto(
    val id: String,
    val name: String,
    val phone: String?,
    val avatar_url: String? = null,
    val language: String = "ru",
    val currency: String = "USD"
)
