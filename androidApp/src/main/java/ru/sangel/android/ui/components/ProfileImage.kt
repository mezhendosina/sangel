package ru.sangel.android.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.sangel.android.R

@Composable
fun ProfileImage(image: ImageBitmap?, modifier: Modifier = Modifier) {
    if (image == null) {
        Image(
            painter = painterResource(id = R.drawable.ic_change_avatar),
            contentDescription = null,
            modifier = modifier,
        )
    } else {
        Image(bitmap = image, contentDescription = null, modifier = modifier)
    }
}

@Composable
fun ProfileImage(image: Painter?, modifier: Modifier = Modifier) {
    if (image == null) {
        Image(
            painter = painterResource(id = R.drawable.ic_change_avatar),
            contentDescription = null,
            modifier = modifier,
        )
    } else {
        Image(painter = image, contentDescription = null, modifier = modifier)
    }
}