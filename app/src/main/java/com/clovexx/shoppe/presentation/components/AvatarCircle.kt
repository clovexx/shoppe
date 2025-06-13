package com.clovexx.shoppe.presentation.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun AvatarCircle(
    imageUri: String?,
    withBackground: Boolean = false,
    onImagePicked: ((String) -> Unit)? = null
) {
    val launcher =
        if (onImagePicked != null) rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                onImagePicked(uri.path.toString())
            }
        } else null

    val outerModifier = if (withBackground) {
        Modifier
            .shadow(
                elevation = 8.dp,
                shape = CircleShape,
                ambientColor = Color(0x22000000),
                spotColor = Color(0x33000000)
            )
            .size(105.dp)
            .background(Color.White, shape = CircleShape)
            .padding(6.dp)
    } else {
        Modifier.size(90.dp)
    }.then(if (onImagePicked != null) Modifier.clip(CircleShape).clickable { launcher!!.launch("image/*") } else Modifier)

    Box(
        contentAlignment = Alignment.Center,
        modifier = outerModifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(Color(0xFFF4E7FF))
        ) {
            if (imageUri == null) {
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = null,
                    tint = Color(0xFF268AFF),
                    modifier = Modifier.size(36.dp)
                )
            } else {
                SubcomposeAsyncImage(
                    model = imageUri,
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    loading = {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(strokeWidth = 2.dp)
                        }
                    }
                )
            }
        }
    }
}
