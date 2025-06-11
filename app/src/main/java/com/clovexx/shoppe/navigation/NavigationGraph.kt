package com.clovexx.shoppe.navigation

import com.clovexx.shoppe.presentation.screens.onboarding.OnboardingScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.clovexx.shoppe.presentation.screens.create_account.CreateAccountScreen
import com.clovexx.shoppe.presentation.screens.home.HomeScreen
import com.clovexx.shoppe.presentation.screens.login.LoginScreen
import com.clovexx.shoppe.presentation.screens.otp_screen.OtpScreen
import com.clovexx.shoppe.presentation.screens.password_setup.NewPasswordScreen
import com.clovexx.shoppe.presentation.screens.pincode.PinCodeScreen
import com.clovexx.shoppe.presentation.screens.start.StartScreen
import com.clovexx.shoppe.presentation.viewmodel.AuthViewModel

@Composable
fun AppNav(authViewModel: AuthViewModel, startDestination: Navigation) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = startDestination.route) {
        composable(Navigation.Start.route) { StartScreen(navController) }
        composable(Navigation.CreateAccount.route) { CreateAccountScreen(navController) }
        composable(Navigation.Login.route) { LoginScreen(navController) }
        composable(Navigation.PinCode.route) { PinCodeScreen(navController) }
        composable(Navigation.Otp.route) { OtpScreen(navController) }
        composable(Navigation.OtpReset.route) { OtpScreen(navController) }
        composable(Navigation.Password.route, arguments = listOf(navArgument("email") { type = NavType.StringType })) {
            val email = it.arguments?.getString("email") ?: ""
//            PasswordScreen(navController, authViewModel, email)
        }
        composable(Navigation.Forgot.route, arguments = listOf(navArgument("email") { type = NavType.StringType })) {
            val email = it.arguments?.getString("email") ?: ""
//            ForgotScreen(navController, authViewModel, email)
        }
        composable(Navigation.NewPassword.route) {
            NewPasswordScreen(navController)
        }
        composable(Navigation.Onboarding.route) { OnboardingScreen(navController) }
        composable(Navigation.Home.route) {
            HomeScreen(authViewModel)
        }
    }
}
