package ru.sangel.android.ui.main.map

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import ru.sangel.android.R
import ru.sangel.android.databinding.FragmentContainerBinding
import ru.sangel.presentation.components.main.map.MapComponent

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    padding: PaddingValues,
    component: MapComponent,
) {
    val contactPermission =
        rememberPermissionState(permission = Manifest.permission.ACCESS_COARSE_LOCATION)
    if (!contactPermission.status.isGranted) {
        LaunchedEffect(Unit) {
            contactPermission.launchPermissionRequest()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidViewBinding(FragmentContainerBinding::inflate, modifier = Modifier) {
            root.getFragment<MapFragment>()
        }

        Column(
            modifier =
                Modifier
                    .align(Alignment.CenterEnd)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background, CircleShape)
                    .width(42.dp)
                    .padding(horizontal = 8.dp)
                    .align(Alignment.Center),
        ) {
            IconButton(
                onClick = component::plusZoom,
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_map_plus),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )
            }
            HorizontalDivider()
            IconButton(
                onClick = component::minusZoom,
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
        Row(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            MapCornerButton(painterResource(id = R.drawable.ic_find_me), component::cameraToUser)
        }
    }
}

@Composable
private fun MapCornerButton(
    image: Painter,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier =
            Modifier
                .size(42.dp)
                .background(MaterialTheme.colorScheme.background, CircleShape),
    ) {
        Image(
            painter = image,
            contentDescription = null,
        )
    }
}
