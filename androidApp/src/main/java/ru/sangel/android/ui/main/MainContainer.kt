package ru.sangel.android.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sangel.android.R
import ru.sangel.android.ui.main.device.ConnectDeviceScreen
import ru.sangel.android.ui.main.device.DeviceScreen
import ru.sangel.android.ui.main.map.MapScreen
import ru.sangel.android.ui.settings.SettingsContainer
import ru.sangel.presentation.components.main.MainComponent
import kotlin.reflect.KClass

@Composable
fun MainContainer(component: MainComponent) {
    val childStack by component.stack.subscribeAsState()
    val activeComponent = childStack.active.instance
    Scaffold(
        bottomBar = {
            MainNavigationBar(activeComponent, component)
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    ) { paddingValues ->
        Children(
            stack = component.stack,
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
        ) {
            when (val config = it.instance) {
                is MainComponent.Child.Device -> DeviceScreen(config.component)
                is MainComponent.Child.Settings -> SettingsContainer(config.component)
                is MainComponent.Child.Map -> MapScreen(paddingValues, config.component)
                is MainComponent.Child.AddDevice ->
                    ConnectDeviceScreen(
                        paddingValues,
                        component = config.compmonent,
                    )

                else -> {}
            }
        }
    }
}

@Composable
private fun MainNavigationBar(
    activeComponent: MainComponent.Child,
    component: MainComponent,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier =
        Modifier
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .navigationBarsPadding(),
    ) {
        NavigationItem(
            painterResource(id = R.drawable.ic_logo),
            activeComponent,
            MainComponent.Child.Device::class,
            component::toDevice,
        )
        NavigationItem(
            painterResource(id = R.drawable.ic_map),
            activeComponent,
            MainComponent.Child.Map::class,
            component::toMap,
        )
        NavigationItem(
            painterResource(id = R.drawable.ic_assistant),
            activeComponent,
            MainComponent.Child.Assistant::class,
            component::toAssistant,
        )
        NavigationItem(
            painterResource(id = R.drawable.ic_settings),
            activeComponent,
            MainComponent.Child.Settings::class,
            component::toSettings,
        )
    }
}

@Composable
private fun <T : MainComponent.Child> RowScope.NavigationItem(
    painter: Painter,
    activeChild: MainComponent.Child,
    targetChild: KClass<T>,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        selected = activeChild::class == targetChild,
        onClick = onClick,
        colors =
        NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onBackground,
            indicatorColor = Color.Transparent,
        ),
        icon = {
            Image(
                painter,
                null,
                modifier = Modifier.size(24.dp),
                colorFilter =
                ColorFilter.tint(
                    if (activeChild::class == targetChild) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    },
                ),
            )
        },
    )
}
