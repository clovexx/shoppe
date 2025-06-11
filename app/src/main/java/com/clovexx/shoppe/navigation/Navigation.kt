package com.clovexx.shoppe.navigation

sealed class Navigation(val route: String) {
    object Start : Navigation("start")
    object CreateAccount : Navigation("create_account")
    object Login : Navigation("login")
    object Password : Navigation("password/{email}") {
        fun createRoute(email: String) = "password/$email"
    }
    object Forgot : Navigation("forgot/{email}") {
        fun createRoute(email: String) = "forgot/$email"
    }

    object NewPassword : Navigation("new_password")
    object PinCodeCreate : Navigation("pincode/create")
    object PinCode : Navigation("pincode")
    object OtpReset : Navigation("otp/reset")
    object Otp : Navigation("otp")
    object Onboarding : Navigation("onboarding")
    object Home : Navigation("home")
}
