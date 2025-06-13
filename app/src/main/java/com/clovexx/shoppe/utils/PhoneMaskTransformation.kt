package com.clovexx.shoppe.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class AdaptivePhoneMaskTransformation(
    val mask: String
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }
        val maskIterator = mask.iterator()
        val out = StringBuilder()
        var digitIdx = 0

        while (maskIterator.hasNext()) {
            val c = maskIterator.nextChar()
            if (c == 'X') {
                if (digitIdx < digits.length) {
                    out.append(digits[digitIdx])
                    digitIdx++
                } else break
            } else {
                out.append(c)
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                var transformed = 0
                var digitsCount = 0
                mask.forEachIndexed { i, ch ->
                    if (ch == 'X') {
                        digitsCount++
                        if (digitsCount == offset + 1) {
                            transformed = i + 1
                            return@forEachIndexed
                        }
                    }
                }
                return out.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                var count = 0
                for (i in 0 until mask.length.coerceAtMost(offset)) {
                    if (mask[i] == 'X') count++
                }
                return count
            }
        }
        return TransformedText(AnnotatedString(out.toString()), offsetMapping)
    }
}

data class CountryMask(val mask: String, val maxDigits: Int)

val phoneMasks = mapOf(
    "+1" to CountryMask("(XXX) XXX-XXXX", 10),      // USA, Canada
    "+44" to CountryMask("XXXX XXX XXXX", 10),      // UK
    "+33" to CountryMask("X XX XX XX XX", 9),       // France
    "+7" to CountryMask("(XXX) XXX-XX-XX", 10),     // Russia
)

fun getPhoneMask(countryCode: String): CountryMask =
    phoneMasks[countryCode] ?: CountryMask("XXXXXXXXXXX", 11)
