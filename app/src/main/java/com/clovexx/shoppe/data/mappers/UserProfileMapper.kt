package com.clovexx.shoppe.data.mappers

import com.clovexx.shoppe.data.remote.model.UserProfileDto
import com.clovexx.shoppe.domain.model.UserProfile


fun UserProfileDto.toDomain(): UserProfile = UserProfile(
    id = id,
    name = name,
    phone = phone,
    avatarUrl = avatar_url,
    language = language,
    currency = currency
)

