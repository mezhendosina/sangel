package ru.sangel.android.ui.main.device

import android.Manifest
import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import ru.sangel.presentation.components.main.device.connect.ConnectDeviceComponent
import ru.sangel.presentation.entities.ConnectDeviceEntity

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ConnectDeviceScreen(
    paddingValues: PaddingValues,
    component: ConnectDeviceComponent,
) {
    val model by component.model.subscribeAsState()
    val smsPermission =
        rememberMultiplePermissionsState(
            permissions =
                listOf(
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS,
                ),
        )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val bluetoothPermission =
            rememberPermissionState(permission = Manifest.permission.BLUETOOTH_SCAN)
        LaunchedEffect(Unit) {
            if (!bluetoothPermission.status.isGranted) {
                bluetoothPermission.launchPermissionRequest()
            }
            component.observeForDevices()
        }
    } else {
        LaunchedEffect(Unit) {
            component.observeForDevices()
        }
    }

    LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
        items(model.list.toList()) {
            ConnectDeviceItem(connectDeviceEntity = it, {
                component.connect(it, smsPermission::launchMultiplePermissionRequest)
            })
        }
    }
}

@Composable
fun ConnectDeviceItem(
    connectDeviceEntity: ConnectDeviceEntity,
    onClick: (String) -> Unit,
) {
    Column(
        modifier =
            Modifier.clickable {
                onClick.invoke(connectDeviceEntity.id)
            },
    ) {
        Text(connectDeviceEntity.name)
        Text(connectDeviceEntity.id)
    }
}

@Preview(device = "id:Nexus One")
@Preview(device = "id:Galaxy Nexus")
@Preview(device = "id:Nexus 4")
@Preview(device = "id:pixel_3a")
@Preview(device = "id:pixel_fold")
@Composable
private fun PreviewConnectDeviceScreen() {
    ConnectDeviceScreen(
        paddingValues = PaddingValues(),
        component =
            object : ConnectDeviceComponent {
                override val model: Value<ConnectDeviceComponent.Model> =
                    MutableValue(
                        ConnectDeviceComponent.Model(emptyList()),
                    )

                override fun observeForDevices() {
                    TODO("Not yet implemented")
                }

                override fun connect(
                    id: String,
                    onConnected: () -> Unit,
                ) {
                    TODO("Not yet implemented")
                }
            },
    )
}
