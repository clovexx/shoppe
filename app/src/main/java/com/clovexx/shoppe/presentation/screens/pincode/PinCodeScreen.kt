package com.clovexx.shoppe.presentation.screens.pincode

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.clovexx.shoppe.R
import com.clovexx.shoppe.presentation.components.AvatarCircle
import com.clovexx.shoppe.presentation.viewmodel.PinCodeViewModel

@Composable
fun PinCodeScreen(
    navController: NavController,
    profileViewModel: PinCodeViewModel = hiltViewModel()
) {
    val pinCode by profileViewModel.pinCode.collectAsState()
    val userName = "Romina" // TODO

    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bubbles_code),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 48.dp)
        ) {
            Spacer(modifier = Modifier.height(36.dp))

            AvatarCircle("https://gravatar.com/avatar/d74c423462b2665ce59a5acb0a620d30?s=400&d=robohash&r=x")

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Hello, $userName!!",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(text = "Type your password", style = MaterialTheme.typography.titleLarge, color = Color.Gray)

            Spacer(modifier = Modifier.height(36.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(4) { index ->
                    val filled = index < pinCode.length
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(16.dp)
                            .background(
                                if (filled) Color(0xFF004BFE) else Color(0xFFE5EBFC),
                                CircleShape
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            PinKeyboard(
                onNumberClick = { profileViewModel.appendPin(it) },
                onDelete = { profileViewModel.removeLastDigit() },
                onEnter = { profileViewModel.onPinEnter(navController) }
            )
        }
    }
}

@Composable
fun PinKeyboard(
    onNumberClick: (Int) -> Unit,
    onDelete: () -> Unit,
    onEnter: () -> Unit
) {
    val numbers = listOf(
        listOf(1,2,3),
        listOf(4,5,6),
        listOf(7,8,9)
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(34.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        numbers.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(34.dp)
            ) {
                row.forEach { num ->
                    PinKeyboardButton(number = num, onClick = { onNumberClick(num) })
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(34.dp)
        ) {
            PinIconButton(iconRes = Icons.AutoMirrored.Filled.Backspace, onClick = onDelete)
            PinKeyboardButton(number = 0, onClick = { onNumberClick(0) })
            PinIconButton(
                iconRes = Icons.AutoMirrored.Filled.ArrowRightAlt,
                onClick = onEnter,
                backgroundColor = Color(0xFF004CFF),
                tint = Color.White
            )
        }
    }
}


@Composable
fun PinKeyboardButton(number: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = CircleShape,
        modifier = Modifier.size(60.dp).background(Color.Transparent)
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
    }
}

@Composable
fun PinIconButton(
    iconRes: ImageVector,
    onClick: () -> Unit,
    backgroundColor: Color = Color.Transparent,
    tint: Color = Color.Black
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        modifier = Modifier.size(60.dp)
    ) {
        Icon(
            painter = rememberVectorPainter(iconRes),
            contentDescription = null,
            tint = tint
        )
    }
}


