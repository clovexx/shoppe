package com.clovexx.shoppe.app

import android.app.Application
import com.clovexx.shoppe.data.repository.AuthRepositoryImpl
import com.clovexx.shoppe.di.GlobalEntryPoint
import com.clovexx.shoppe.domain.repository.AuthRepository
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

open class BaseApp : Application() {
    fun getAuthRepository() = EntryPointAccessors.fromApplication(this, GlobalEntryPoint::class.java)
}

@HiltAndroidApp
class ShoppeApp : BaseApp()


