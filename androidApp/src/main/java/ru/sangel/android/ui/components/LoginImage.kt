package ru.sangel.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.sangel.android.R

@Composable
fun LoginImage(modifier: Modifier = Modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_login_background),
            null,
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(bottomStart = 52.dp, bottomEnd = 52.dp),
                ),
            contentScale = ContentScale.Crop,
            )
}