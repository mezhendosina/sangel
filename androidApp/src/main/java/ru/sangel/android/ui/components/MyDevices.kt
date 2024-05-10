package ru.sangel.android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.sangel.android.R
import ru.sangel.presentation.entities.DeviceUiEntity

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyDevices(
    devices: List<DeviceUiEntity>,
    onMainCardClick: (address: String) -> Unit,
    onAddDevice: () -> Unit,
) {
    if (devices.isEmpty()) {
        AddNewDeviceCard(onAddDevice)
        return
    }
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Row {
            Text(
                text = stringResource(R.string.my_devices),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.size(20.dp))

        FlowRow(
            modifier =
                Modifier
                    .fillMaxWidth(),
            maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            MainDeviceCard(devices.get(0), onMainCardClick)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AdditionalDeviceCard(onClick = {})
                AddNewDeviceCard(onClick = onAddDevice)
            }
        }
    }
}

// @Preview(
//    showSystemUi = true,
//    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
// )
// @Composable
// private fun PrevMyDevices() {
//    SangelTheme {
//        MyDevices {
//        }
//    }
// }
//
// @Preview(
//    showSystemUi = true,
//    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
//    device = "spec:width=1080px,height=2340px,dpi=640",
// )
// @Composable
// private fun PrevMyDevicesSmall() {
//    SangelTheme {
//        MyDevices {
//        }
//    }
// }
