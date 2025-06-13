package com.clovexx.shoppe.presentation.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovexx.shoppe.domain.model.UserProfile
import com.clovexx.shoppe.domain.usecase.AvatarUploadUseCase
import com.clovexx.shoppe.domain.usecase.GetProfileUseCase
import com.clovexx.shoppe.domain.usecase.ProfileAvatarUpdateUseCase
import com.clovexx.shoppe.domain.usecase.SignInUseCase
import com.clovexx.shoppe.domain.usecase.SignUpUseCase
import com.clovexx.shoppe.domain.usecase.UpdatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SignupState(
    val isLoading: Boolean = false,
    val createdSuccessfully: Boolean = false,
    val error: String? = null
)

data class LoginState(
    val loggedIn: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUp: SignUpUseCase,
    private val signIn: SignInUseCase,
    private val updatePassword: UpdatePasswordUseCase,
    private val _getProfile: GetProfileUseCase,
    private val uploadAvatar: AvatarUploadUseCase,
    private val updateProfileAvatar: ProfileAvatarUpdateUseCase
) : ViewModel() {

    private val _signupState = MutableStateFlow(SignupState())
    val signupState: StateFlow<SignupState> = _signupState

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState
    private val _profile = MutableStateFlow<UserProfile?>(null)
    val profile: StateFlow<UserProfile?> = _profile

    fun getProfile() {
        viewModelScope.launch {
            _profile.value = _getProfile()
        }
    }

    fun isLoggedIn(): Boolean = _profile.value != null

    fun onCreateAccount(
        email: String,
        pass: String,
        phone: String,
        name: String,
        context: Context,
        imageUri: Uri?,
    ) {
        _signupState.value = SignupState(isLoading = true)
        viewModelScope.launch {
            try {
                val user = signUp(email, pass, phone, name)
                delay(300)
                val userId = user.id
                if (imageUri != null) {
                    val avatarUrl = uploadAvatar(imageUri, userId, context)
                    updateProfileAvatar(userId, avatarUrl)
                }
                _signupState.value = SignupState(createdSuccessfully = true)
            } catch (e: Exception) {
                _signupState.value = SignupState(error = e.message)
            }
        }
    }

    fun onLogin(email: String, pass: String) {
        _loginState.value = LoginState()
        viewModelScope.launch {
            try {
                signIn(email, pass)
                _loginState.value = LoginState(loggedIn = true)

            } catch (e: Exception) {
                _loginState.value = LoginState(error = e.message)
            }
        }
    }

    fun changePassword(password: String) {
        viewModelScope.launch {
            try {
                changePassword(password)
            } catch (e: Exception) {

            }
        }
    }
}
