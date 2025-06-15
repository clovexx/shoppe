package com.clovexx.shoppe

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.clovexx.shoppe.domain.usecase.AvatarUploadUseCase
import com.clovexx.shoppe.domain.usecase.GetProfileUseCase
import com.clovexx.shoppe.domain.usecase.ProfileAvatarUpdateUseCase
import com.clovexx.shoppe.domain.usecase.SignInUseCase
import com.clovexx.shoppe.domain.usecase.SignUpUseCase
import com.clovexx.shoppe.presentation.viewmodel.AuthViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class AuthViewModelInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var signUpUseCase: SignUpUseCase
    @Inject
    lateinit var signInUseCase: SignInUseCase
    @Inject
    lateinit var getProfileUseCase: GetProfileUseCase
    @Inject
    lateinit var uploadUseCase: AvatarUploadUseCase
    @Inject
    lateinit var updateUseCase: ProfileAvatarUpdateUseCase

    lateinit var viewModel: AuthViewModel

    @Before
    fun init() {
        hiltRule.inject()
        viewModel = AuthViewModel(signUpUseCase, signInUseCase, getProfileUseCase, uploadUseCase, updateUseCase)
    }

    @Test
    fun testCreateAccountInstrumented(@ApplicationContext context: Context) = runBlocking {
        viewModel.onCreateAccount("test@inst.com", "pass", "8888", "Android", context, null)
        assertTrue(viewModel.signupState.value.createdSuccessfully)
    }
}
