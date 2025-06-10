package com.clovexx.shoppe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.clovexx.shoppe.navigation.AppNav
import com.clovexx.shoppe.navigation.Navigation
import com.clovexx.shoppe.presentation.ui.theme.ShoppeTheme
import com.clovexx.shoppe.presentation.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var keepOnSplash = true
        val splash = installSplashScreen()
        splash.setKeepOnScreenCondition { keepOnSplash }

        super.onCreate(savedInstanceState)

        setContent {

            val viewModel: AuthViewModel = hiltViewModel()

            var startDestination by remember { mutableStateOf<Navigation?>(null) }

            LaunchedEffect(Unit) {
                delay(1200)

                viewModel.getProfile()

                startDestination = if (viewModel.isLoggedIn()) {
                    Navigation.Home
                } else {
                    Navigation.Start
                }
                keepOnSplash = false
            }

            if (startDestination != null) {

                ShoppeTheme {
                    AppNav(viewModel, startDestination!!)
                }
            }
        }
    }

}