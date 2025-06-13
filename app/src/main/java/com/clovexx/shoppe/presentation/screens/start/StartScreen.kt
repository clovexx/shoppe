package com.clovexx.shoppe.presentation.screens.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.clovexx.shoppe.R
import com.clovexx.shoppe.navigation.Navigation
import com.clovexx.shoppe.presentation.components.ShoppeButton
import com.clovexx.shoppe.presentation.ui.theme.BlueButton

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StartScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            Modifier.weight(0.6f)
        )
        Column(
            Modifier.weight(1.4f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Box(
                    Modifier
                        .size(140.dp)
                        .shadow(12.dp, CircleShape)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon),
                        contentScale = ContentScale.Fit,
                        contentDescription = "Shoppe app icon",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(100.dp)
                    )
                }
                Text(
                    "Shoppe",
                    style = MaterialTheme.typography.displayLarge,
                )

                Text(
                    "Beautiful eCommerce UI Kit\nfor your online store",
                    textAlign = TextAlign.Center
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ShoppeButton(onClick = { navController.navigate(Navigation.CreateAccount.route) }) {
                    Text("Let's get started", style = MaterialTheme.typography.bodyLarge)
                }

                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color.Transparent,
                    modifier = Modifier.clickable(
                        onClick = { navController.navigate(Navigation.Login.route)},
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "I already have an account",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Box(
                            modifier = Modifier
                                .size(30.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(BlueButton, CircleShape)
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

