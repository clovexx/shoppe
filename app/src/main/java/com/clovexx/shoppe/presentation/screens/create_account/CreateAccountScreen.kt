package com.clovexx.shoppe.presentation.screens.create_account

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.clovexx.shoppe.R
import com.clovexx.shoppe.navigation.Navigation
import com.clovexx.shoppe.presentation.components.AvatarCircle
import com.clovexx.shoppe.presentation.components.InputField
import com.clovexx.shoppe.presentation.components.PhoneInputField
import com.clovexx.shoppe.presentation.viewmodel.AuthViewModel
import com.clovexx.shoppe.utils.getPhoneMask

@Composable
fun CreateAccountScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val signupState by viewModel.signupState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordRepeat by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<String?>(null) }
    val (country, setCountry) = remember { mutableStateOf(CountryData.default()) }
    var countrySelectorExpanded by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var nameError by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Box(
        Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.bubbles_create),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.weight(0.2f))

            Column(
                Modifier.weight(1.6f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Create Account",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                    color = Color(0xFF212121),
                    modifier = Modifier.align(Alignment.Start)
                )

                AvatarCircle(
                    imageUri = imageUri,
                    onImagePicked = { imageUri = it },
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {


                    InputField(
                        value = name,
                        onValueChange = { name = it; nameError = null },
                        placeholder = "Name",
                        isError = nameError != null
                    )
                    if (nameError != null) Text(
                        nameError!!,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )

                    InputField(
                        value = email,
                        onValueChange = { email = it; emailError = null },
                        placeholder = "Email",
                        isError = emailError != null,
                        keyboardType = KeyboardType.Email
                    )
                    if (emailError != null) Text(
                        emailError!!,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        val mask = getPhoneMask(country.code)

                        PhoneInputField(
                            country = country,
                            onCountryClick = { countrySelectorExpanded = true },
                            phone = phone,
                            onPhoneChanged = { phone = it; phoneError = null },
                            isError = phoneError != null,
                            mask = mask,
                            enabled = !signupState.isLoading
                        )

                        DropdownMenu(
                            expanded = countrySelectorExpanded,
                            onDismissRequest = { countrySelectorExpanded = false }
                        ) {
                            CountryData.list.forEach {
                                DropdownMenuItem(
                                    text = { Text("${it.emoji}  ${it.name}  ${it.code}") },
                                    onClick = {
                                        setCountry(it)
                                        countrySelectorExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    if (phoneError != null)
                        Text(
                            phoneError!!,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp
                        )

                    InputField(
                        value = password,
                        onValueChange = { password = it; passwordError = null },
                        placeholder = "Password",
                        isError = passwordError != null,
                        isPassword = true,
                        passwordVisible = passwordVisible,
                        onPasswordVisibilityChange = { passwordVisible = it }
                    )

                    InputField(
                        value = passwordRepeat,
                        onValueChange = { passwordRepeat = it; passwordError = null },
                        placeholder = "Repeat password",
                        isError = passwordError != null,
                        isPassword = true,
                        passwordVisible = passwordVisible,
                        onPasswordVisibilityChange = { passwordVisible = it }
                    )
                    if (passwordError != null) Text(
                        passwordError!!,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )


                }

                Column {
                    Button(
                        onClick = {
                            emailError = if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                                    .matches()
                            ) "Invalid email" else null
                            passwordError =
                                if (password.length < 6) "Password too short (min 6)" else if (password != passwordRepeat) "Passwords must be the same" else null
                            phoneError = if (phone.length < 7) "Invalid phone" else null
                            nameError = if (name.isBlank()) "Name can't be empty" else null

                            if (emailError == null && passwordError == null && phoneError == null && nameError == null) {
                                viewModel.onCreateAccount(
                                    email.trim(),
                                    password,
                                    imageUri = imageUri as Uri?,
                                    context = context,
                                    name = name,
                                    phone = country.code + phone.trim(),
                                )
                            }
                        },
                        enabled = !signupState.isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF268AFF),
                            contentColor = Color.White
                        )
                    ) {
                        if (signupState.isLoading) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Done", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    if (signupState.error != null) {
                        Text(
                            signupState.error!!.substringBefore("URL"),
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    if (signupState.createdSuccessfully) {
                        LaunchedEffect(Unit) {
                            viewModel.getProfile()
                            navController.navigate(Navigation.Home.route) {
                                popUpTo(Navigation.CreateAccount.route) { inclusive = true }
                            }
                        }
                    }

                    TextButton(
                        onClick = { navController.popBackStack() },
                        enabled = !signupState.isLoading,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Cancel", color = Color.Gray, fontSize = 16.sp)
                    }
                }

            }

        }
    }
}

data class CountryData(val emoji: String, val code: String, val name: String) {
    companion object {
        fun default() = CountryData("🇷🇺", "+7", "Russia")
        val list = listOf(
            CountryData("🇺🇸", "+1", "USA"),
            CountryData("🇬🇧", "+44", "UK"),
            CountryData("🇫🇷", "+33", "France"),
            CountryData("🇷🇺", "+7", "Russia")
        )
    }
}

