package com.clovexx.shoppe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.annotation.DrawableRes
import com.clovexx.shoppe.R

@HiltViewModel
class OnboardingViewModel @Inject constructor(

) : ViewModel() {
    private val _pages = listOf(
        OnboardPage(
            imageRes = R.drawable.onboarding_1,
            backgroundRes = R.drawable.bubbles_onboarding_1,
            title = "Добро пожаловать",
            description = "Это приложение для покупок. Здесь самые низкие цены!"
        ),
        OnboardPage(
            imageRes = R.drawable.onboarding_1,
            backgroundRes = R.drawable.bubbles_onboarding_2,
            title = "Лёгкая оплата",
            description = "Платите удобно картой или QR-кодом"
        ),
        OnboardPage(
            imageRes = R.drawable.onboarding_1,
            backgroundRes = R.drawable.bubbles_onboarding_1,
            title = "Быстрая доставка",
            description = "Ваш заказ всегда придёт вовремя"
        ),
        OnboardPage(
            imageRes = R.drawable.onboarding_1,
            backgroundRes = R.drawable.bubbles_onboarding_2,
            title = "Быстрая доставка",
            description = "Ваш заказ всегда придёт вовремя"
        )
    )
    val pages: List<OnboardPage> get() = _pages

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    fun onNext() {
        if (_currentPage.value < _pages.size - 1) {
            _currentPage.value++
        }
    }

    fun onPrev() {
        if (_currentPage.value > 0) {
            _currentPage.value--
        }
    }
}

data class OnboardPage(
    @DrawableRes val imageRes: Int,
    @DrawableRes val backgroundRes: Int,
    val title: String,
    val description: String
)
