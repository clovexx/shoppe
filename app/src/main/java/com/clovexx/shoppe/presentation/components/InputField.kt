package com.clovexx.shoppe.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    placeholderAlignment: Alignment = Alignment.CenterStart,
    isError: Boolean = false,
    enabled: Boolean = true,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation? = null,
    passwordVisible: Boolean? = null,
    onPasswordVisibilityChange: ((Boolean) -> Unit)? = null,
) {
    var internalPasswordVisible by remember { mutableStateOf(false) }
    val actualPasswordVisible = passwordVisible ?: internalPasswordVisible
    val actualOnPasswordVisibilityChange = onPasswordVisibilityChange ?: { internalPasswordVisible = it }

    val colors = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colors.surface)
            .border(
                BorderStroke(
                    1.dp,
                    if (isError) colors.error else colors.outline
                ),
                RoundedCornerShape(16.dp)
            )
            .height(56.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = when {
                isPassword && !actualPasswordVisible -> PasswordVisualTransformation()
                visualTransformation != null -> visualTransformation
                else -> VisualTransformation.None
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) { innerTextField ->
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                }
                Box(
                    Modifier.weight(1f)
                ) {
                    if (value.isEmpty()) {
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = placeholderAlignment
                        ) {
                            Text(
                                placeholder,
                                color = colors.onSurface.copy(alpha = 0.5f),
                                fontSize = 17.sp
                            )
                        }
                    } else {
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            innerTextField()
                        }
                    }
                }
                if (isPassword && value.isNotEmpty()) {
                    IconButton(onClick = { actualOnPasswordVisibilityChange(!actualPasswordVisible) }) {
                        Icon(
                            imageVector = if (actualPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Show/Hide password",
                            tint = colors.onSurface.copy(alpha = 0.7f)
                        )
                    }
                } else if (trailingIcon != null) {
                    trailingIcon()
                }
            }
        }
    }
}
