package ru.sangel.android.ui.main.map

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
            this.root.getFragment<MapFragment>()
        }

        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = padding.calculateTopPadding()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = component::toProfile) {
                Image(
                    painter = painterResource(id = R.drawable.ic_avatar),
                    contentDescription = stringResource(R.string.profile),
                    modifier = Modifier.size(32.dp),
                )
            }

            IconButton(onClick = component::toQuestion) {
                Image(
                    painter = painterResource(id = R.drawable.ic_question),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
        Column(modifier = Modifier.align(Alignment.CenterEnd)) {
            IconButton(onClick = component::plusZoom) {
                Image(painter = painterResource(id = R.drawable.ic_plus), contentDescription = null)
            }
            IconButton(onClick = component::minusZoom) {
                Image(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = null,
                )
            }
        }
        Row(
            modifier =
            Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
//            IconButton(onClick = {}) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_avatar),
//                    contentDescription = stringResource(R.string.profile),
//                    modifier = Modifier.size(24.dp),
//                )
//            }

            IconButton(onClick = component::cameraToUser) {
                Image(
                    painter = painterResource(id = R.drawable.ic_find_me),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.size(48.dp),
                )
            }
        }
    }
}
