package ru.sangel.android.ui.main.device

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sangel.presentation.components.main.device.connect.ConnectDeviceComponent
import ru.sangel.presentation.entities.ConnectDeviceEntity

@Composable
fun ConnectDeviceScreen(
    paddingValues: PaddingValues,
    component: ConnectDeviceComponent,
) {
    val model by component.model.subscribeAsState()
    LazyColumn(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
        items(model.list.toList()) {
            ConnectDeviceItem(connectDeviceEntity = it, component::connect)
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
