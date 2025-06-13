package com.clovexx.shoppe.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.clovexx.shoppe.presentation.ui.theme.BlueButton

@Composable
fun ShoppeButton(
    onClick: () -> Unit,
    color: Color = BlueButton,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(60.dp),
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    content: @Composable () -> Unit
) {
    Button(
        onClick,
        enabled = enabled,
        modifier = modifier,
        shape = shape,
        colors = buttonColors(color)
    ) {
        content()
    }
}