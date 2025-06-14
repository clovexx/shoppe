package com.clovexx.shoppe.presentation.screens.password_setup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.clovexx.shoppe.R
import com.clovexx.shoppe.presentation.components.AvatarCircle
import com.clovexx.shoppe.presentation.components.ShoppeButton
import com.clovexx.shoppe.presentation.components.InputField
import com.clovexx.shoppe.presentation.viewmodel.AuthViewModel

@Composable
fun NewPasswordScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    var newPassword by remember { mutableStateOf("") }
    var newPasswordRepeat by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var passwordError by remember { mutableStateOf<String?>(null) }
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bubbles_otp),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
            ) {
            Spacer(Modifier.weight(0.2f))
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AvatarCircle(
                        "https://gravatar.com/avatar/d74c423462b2665ce59a5acb0a620d30?s=400&d=robohash&r=x",
                        true
                    )

                    Text(
                        "Setup New Password",
                    )

                    Text("Please, setup a new password for your account")

                    InputField(
                        value = newPassword,
                        onValueChange = { newPassword = it; passwordError = null },
                        placeholder = "Password",
                        placeholderAlignment = Alignment.Center,
                        isError = passwordError != null,
                        isPassword = true,
                        passwordVisible = passwordVisible,
                        onPasswordVisibilityChange = { passwordVisible = it }
                    )

                    InputField(
                        value = newPasswordRepeat,
                        onValueChange = { newPasswordRepeat = it; passwordError = null },
                        placeholder = "Repeat password",
                        placeholderAlignment = Alignment.Center,
                        isError = passwordError != null,
                        isPassword = true,
                        passwordVisible = passwordVisible,
                        onPasswordVisibilityChange = { passwordVisible = it }
                    )

                    ShoppeButton(
                        onClick = {
                            passwordError = if (newPassword.length < 6) "Password too short (min 6)" else if (newPassword != newPasswordRepeat) "Passwords are not equal" else null

                            if (passwordError == null) {
                                authViewModel.changePassword(newPassword)
                            }
                        },
                        enabled = true,
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}