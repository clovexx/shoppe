package com.clovexx.shoppe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clovexx.shoppe.domain.usecase.SendOtpUseCase
import com.clovexx.shoppe.domain.usecase.VerifyOtpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.auth.OtpType
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


data class OtpState(
    val verificationState: VerificationState = VerificationState.NotSent,
    val isLoading: Boolean = false,
    val otpCode: String? = null,
    val error: String? = null,
)

enum class VerificationState {
    NotSent,
    Sent,
    Verified
}

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val _sendOtp: SendOtpUseCase,
    private val verifyOtp: VerifyOtpUseCase
) : ViewModel() {
    private val _otpState: MutableStateFlow<OtpState> = MutableStateFlow(OtpState())
    val otpState: StateFlow<OtpState> = _otpState

    fun sendOtp(email: String, type: OtpType.Email) {
        _otpState.value = OtpState(isLoading = true)
        viewModelScope.launch {
            try {
                _sendOtp(email, type)
                _otpState.value =
                    OtpState(isLoading = true, verificationState = VerificationState.Sent)
            } catch (e: Exception) {
                _otpState.value = OtpState(error = e.message)
            } finally {
                _otpState.value = OtpState(isLoading = false)
            }
        }
    }

    fun verifyOtp(email: String, otp: String) {
        _otpState.value = OtpState(isLoading = true)
        viewModelScope.launch {
            try { verifyOtp(email = email, token = otp)
                _otpState.value =
                    OtpState(isLoading = true, verificationState = VerificationState.Verified)
            } catch (e: Exception) {
                _otpState.value = OtpState(error = e.message)
            } finally {
                _otpState.value = OtpState(isLoading = false)
            }
        }
    }

    fun clearError() {
        _otpState.value = _otpState.value.copy(
            error = null
        )
    }
}
