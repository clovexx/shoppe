package com.clovexx.shoppe.presentation.screens.home

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.clovexx.shoppe.presentation.viewmodel.AuthViewModel

@Composable
fun HomeScreen(viewModel: AuthViewModel) {
    val profile by viewModel.profile.collectAsState()
    LaunchedEffect(Unit) {
        if (profile == null) {
            viewModel.getProfile()
        }
    }
    if (profile == null) {
        CircularProgressIndicator()
    } else {
        Text("Добро пожаловать, ${profile?.name}!")
    }
}
