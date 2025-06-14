package com.clovexx.shoppe.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.clovexx.shoppe.domain.usecase.GetSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor(
    private val getSettings: GetSettingsUseCase
) : ViewModel() {

    private val _pinCode = MutableStateFlow("")
    val pinCode: StateFlow<String> = _pinCode.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess.asStateFlow()

    private var correctCode: String? = null

    init {
        viewModelScope.launch {
            getSettings().collect { settings ->
                correctCode = settings.code
            }
        }
    }

    fun appendPin(num: Int) {
        if (_pinCode.value.length < 4) {
            _pinCode.value += num.toString()
            _error.value = null

            if (_pinCode.value.length == 4) {
                checkPin()
            }
        }
    }

    fun removeLastDigit() {
        if (_pinCode.value.isNotEmpty()) {
            _pinCode.value = _pinCode.value.dropLast(1)
            _error.value = null
        }
    }

    fun onPinEnter(navController: NavController) {
        if (_pinCode.value.length == 4) {
            checkPin(onSuccess = {
                navController.navigate("next_screen")
            })
        }
    }

    private fun checkPin(onSuccess: (() -> Unit)? = null) {
        if (_pinCode.value == correctCode) {
            _isSuccess.value = true
            onSuccess?.invoke()
        } else {
            _error.value = "Неверный код"
            viewModelScope.launch {
                delay(500)
                _pinCode.value = ""
            }
        }
    }
}
