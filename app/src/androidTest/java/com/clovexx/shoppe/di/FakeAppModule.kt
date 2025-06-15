package com.clovexx.shoppe.di

import android.content.Context
import com.clovexx.shoppe.FakeAuthRepository
import com.clovexx.shoppe.data.local.DataStoreRepositoryImpl
import com.clovexx.shoppe.domain.repository.AuthRepository
import com.clovexx.shoppe.domain.repository.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {
    @Provides
    fun provideAuthRepository(): AuthRepository = FakeAuthRepository()
    // TODO пои дее заинжектит но если не
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository = DataStoreRepositoryImpl(context)
}
