package com.clovexx.shoppe.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.clovexx.shoppe.R
import com.clovexx.shoppe.presentation.components.InputField
import com.clovexx.shoppe.presentation.components.ShoppeButton
import com.clovexx.shoppe.presentation.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passError by remember { mutableStateOf<String?>(null) }

    val loginState by viewModel.loginState.collectAsState()

    LaunchedEffect(loginState.loggedIn) {
        if (loginState.loggedIn) {
            viewModel.getProfile()
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Box {
        Image(
            painter = painterResource(R.drawable.bubbles_login),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(Modifier.padding(12.dp)) {
            Spacer(Modifier.weight(0.5f))
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Login", style = MaterialTheme.typography.displayLarge)
                Text("Good to see you back! ♥")

                Column {
                    InputField(
                        value = email,
                        onValueChange = {
                            email = it; emailError = null
                        },
                        placeholder = "Email",
                        keyboardType = KeyboardType.Email,
                        isError = emailError != null
                    )
                    if (emailError != null) Text(
                        emailError!!,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp,
                    )
                }

                Column {
                    InputField(
                        value = pass,
                        onValueChange = {
                            pass = it; passError = null
                        },
                        placeholder = "Password",
                        isError = passError != null,
                        isPassword = true
                    )
                    if (passError != null) Text(
                        passError!!,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp,
                    )
                }

                if (loginState.error != null) {
                    Text(
                        loginState.error!!.substringBefore("URL"),
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp,
                    )
                }

                ShoppeButton(
                    onClick = {
                        val emailPattern = android.util.Patterns.EMAIL_ADDRESS
                        var hasError = false
                        if (!emailPattern.matcher(email).matches()) {
                            emailError = "Invalid email"
                            hasError = true
                        }
                        if (pass.isBlank()) {
                            passError = "Password required"
                            hasError = true
                        }
                        if (!hasError) {
                            viewModel.onLogin(email, pass)
                        }
                    },
                    enabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Text("Next", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
