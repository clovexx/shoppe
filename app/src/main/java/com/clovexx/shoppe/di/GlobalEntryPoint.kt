package com.clovexx.shoppe.di

import com.clovexx.shoppe.domain.repository.AuthRepository
import com.clovexx.shoppe.domain.repository.DataStoreRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// zadolbalsya
@EntryPoint
@InstallIn(SingletonComponent::class)
interface GlobalEntryPoint {
    fun authRepository(): AuthRepository
    fun dataStoreRepository(): DataStoreRepository
}
