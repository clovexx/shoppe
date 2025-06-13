package com.clovexx.shoppe.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clovexx.shoppe.presentation.screens.create_account.CountryData
import com.clovexx.shoppe.utils.AdaptivePhoneMaskTransformation
import com.clovexx.shoppe.utils.CountryMask

@Composable
fun PhoneInputField(
    country: CountryData,
    onCountryClick: () -> Unit,
    phone: String,
    onPhoneChanged: (String) -> Unit,
    isError: Boolean,
    mask: CountryMask,
    enabled: Boolean
) {
    InputField(
        value = phone,
        onValueChange = {
            val digitsOnly = it.filter { it.isDigit() }.take(mask.maxDigits)
            onPhoneChanged(digitsOnly)
        },
        placeholder = "Your number",
        isError = isError,
        enabled = enabled,
        keyboardType = KeyboardType.Phone,
        leadingIcon = {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(enabled, onClick = onCountryClick)
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = country.emoji, fontSize = 20.sp)
                Spacer(Modifier.width(5.dp))
                Text(text = country.code, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Icon(
                    Icons.Default.ArrowDropDown,
                    null,
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
            }
        },
        visualTransformation = if (phone.isEmpty()) VisualTransformation.None else AdaptivePhoneMaskTransformation(
            mask.mask
        )
    )
}
