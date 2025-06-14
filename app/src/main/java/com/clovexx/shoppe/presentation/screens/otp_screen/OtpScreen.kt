package com.clovexx.shoppe.presentation.screens.otp_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.clovexx.shoppe.R
import com.clovexx.shoppe.presentation.components.AvatarCircle
import com.clovexx.shoppe.presentation.viewmodel.OtpViewModel

@Composable
fun OtpScreen(
    navController: NavController, otpViewModel: OtpViewModel = hiltViewModel()
) {
    LocalFocusManager.current

    var code by remember { mutableStateOf("") }
    val onCodeChanged: (String) -> Unit = { value ->
        code = value.filter { it.isDigit() }.take(6)
    }

    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bubbles_otp),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.weight(0.2f)) // Forbidden magic
            Column(
                modifier = Modifier
                    .weight(0.7f) // Forbidden magic
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AvatarCircle(
                        "https://gravatar.com/avatar/d74c423462b2665ce59a5acb0a620d30?s=400&d=robohash&r=x",
                        true
                    )

                    Spacer(Modifier.height(32.dp))

                    Text(
                        text = "Password Recovery",
                        fontWeight = FontWeight.Bold,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                        fontSize = 21.sp,
                        color = Color(0xFF202020),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        textAlign = TextAlign.Center,
                        letterSpacing = (-0.2).sp,
                    )

                    Spacer(Modifier.height(15.dp))

                    Text(
                        text = "Enter 6-digits code we sent you on your phone number",
                        fontWeight = FontWeight.Light,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                        fontSize = 19.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(20.dp))

                    Text(
                        text = "VERIFICATION CODE",
                        fontWeight = FontWeight.Bold,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        letterSpacing = 1.6.sp,
                    )

                    Spacer(Modifier.height(32.dp))

                    OtpInput(
                        code = code, onCodeChanged = onCodeChanged
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp)
                ) {

                    Button(
                        onClick = { /* TODO: resend method */ },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .width(201.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            "Send Again",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Light,
                            color = Color(0xFFF3F3F3)
                        )
                    }


                    Text(
                        text = "Didn’t receive code? Check your phone.",
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        color = Color(0xFF202020).copy(alpha = 0.9f)
                    )
                }
            }
            Spacer(Modifier.weight(0.1f))

        }
    }
}


@Composable
fun OtpInput(
    code: String, onCodeChanged: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(17.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {} // just to keep focus
    ) {
        repeat(6) { i ->
            Box(
                modifier = Modifier
                    .size(17.dp)
                    .background(
                        if (code.length > i) Color(0xFF202020) else Color(0xFFE5EBFC),
                        shape = CircleShape
                    )
            )
        }
    }
    Spacer(Modifier.height(8.dp))
    OutlinedTextField(
        value = code,
        onValueChange = onCodeChanged,
        modifier = Modifier
            .width(0.dp)
            .height(0.dp),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.NumberPassword
        ),
        colors = TextFieldDefaults.colors(

        )
    )
}

// TOD0: Remove forbidden magic
