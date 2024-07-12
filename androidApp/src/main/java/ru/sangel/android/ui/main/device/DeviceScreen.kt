package ru.sangel.android.ui.main.device

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sangel.android.R
import ru.sangel.android.ui.components.MyDevices
import ru.sangel.presentation.components.main.device.DeviceComponent
import ru.sangel.utils.isBluetoothAvailable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceScreen(component: DeviceComponent) {
    val model by component.model.subscribeAsState()
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        component.setBluetoothAvailability(context.isBluetoothAvailable())
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Image(
                        painterResource(id = R.drawable.ic_avatar),
                        null,
                        modifier =
                        Modifier
                            .padding(start = 20.dp, end = 16.dp)
                            .size(40.dp),
                    )
                },
                title = {
                    Text(model.name)
                },
                modifier =
                Modifier.clip(
                    RoundedCornerShape(
                        bottomEnd = 32.dp,
                        bottomStart = 32.dp,
                    ),
                ),
            )
        },
        containerColor = Color.Transparent,
    ) {
        Column(modifier = Modifier.padding(top = it.calculateTopPadding() + 72.dp)) {
            if (model.bluetoothAvailable) {
                MyDevices(
                    devices = model.devices,
                    onMainCardClick = component::onDeviceClick,
                    onAddDevice = component::toAddDevice,
                )
            } else {
                Text(
                    stringResource(R.string.bluetooth_is_not_available),
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
