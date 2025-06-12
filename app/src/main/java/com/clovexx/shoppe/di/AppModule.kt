package com.clovexx.shoppe.di

import android.content.Context
import com.clovexx.shoppe.data.local.DataStoreRepositoryImpl
import com.clovexx.shoppe.data.remote.supabase.Supabase
import com.clovexx.shoppe.data.repository.AuthRepositoryImpl
import com.clovexx.shoppe.domain.repository.AuthRepository
import com.clovexx.shoppe.domain.repository.DataStoreRepository
import com.clovexx.shoppe.domain.usecase.GetProfileUseCase
import com.clovexx.shoppe.domain.usecase.SignInUseCase
import com.clovexx.shoppe.domain.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSupabaseApi(): Supabase = Supabase(
        baseUrl = "https://lipdzzyglvugaladiszs.supabase.co",
        apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImxpcGR6enlnbHZ1Z2FsYWRpc3pzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDkwOTc4MjIsImV4cCI6MjA2NDY3MzgyMn0.tbcS5VLJAGaOIdlO2HtFmUgm_cYe4zE1oQAFQ7C0uJg"
    )

    @Provides
    @Singleton
    fun provideAuthRepository(api: Supabase): AuthRepository =
        AuthRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository =
        DataStoreRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideSignUpUseCase(repo: AuthRepository): SignUpUseCase = SignUpUseCase(repo)

    @Provides
    @Singleton
    fun provideSignInUseCase(repo: AuthRepository): SignInUseCase = SignInUseCase(repo)

    @Provides
    @Singleton
    fun provideGetProfileUseCase(repo: AuthRepository): GetProfileUseCase = GetProfileUseCase(repo)
}
