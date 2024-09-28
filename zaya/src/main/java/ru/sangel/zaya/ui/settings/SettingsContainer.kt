package ru.sangel.zaya.ui.settings

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import ru.sangel.zaya.R
import ru.sangel.zaya.presentation.components.main.settings.SettingsComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsContainer(component: SettingsComponent) {
    val stack by component.stack.subscribeAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    AnimatedContent(targetState = stack) {
                        Text(it.getTopBarTitle())
                    }
                },
                navigationIcon = {
                    AnimatedVisibility(
                        visible = stack.active.instance !is SettingsComponent.Child.Root,
                        enter = fadeIn(),
                        exit = fadeOut(),
                    ) {
                        IconButton(
                            onClick = component::onBack,
                            modifier = Modifier.padding(start = 24.dp),
                        ) {
                            Image(
                                Icons.AutoMirrored.Default.KeyboardArrowLeft,
                                stringResource(R.string.back),
                            )
                        }
                    }
                },
                actions = {
                    Image(
                        painterResource(id = R.drawable.ic_logo),
                        null,
                        modifier =
                            Modifier
                                .padding(end = 46.dp)
                                .size(24.dp),
                    )
                },
                colors =
                    TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    ),
            )
        },
        containerColor = Color.Transparent,
    ) { paddingValues ->
        Children(
            stack = component.stack,
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(top = 16.dp),
            animation = stackAnimation(slide() + fade()),
        ) {
            when (val config = it.instance) {
                is SettingsComponent.Child.Root -> SettingsScreen(config.component)
                is SettingsComponent.Child.Privacy -> PrivacyScreen(config.component)
                is SettingsComponent.Child.Contacts -> ContactsScreen(config.component)
                is SettingsComponent.Child.Debug -> DebugScreen(config.component)
                is SettingsComponent.Child.About -> AboutAppScreen(config.component)
                is SettingsComponent.Child.Account -> AccountScreen(component = config.component)
                else -> {}
            }
        }
    }
}

@Composable
private fun ChildStack<*, SettingsComponent.Child>.getTopBarTitle() =
    when (active.instance) {
        is SettingsComponent.Child.Root -> stringResource(R.string.settings)
        is SettingsComponent.Child.About -> stringResource(R.string.about_app)
        is SettingsComponent.Child.Account -> stringResource(R.string.account)
        is SettingsComponent.Child.Privacy -> stringResource(R.string.privacy)
        is SettingsComponent.Child.Contacts -> stringResource(R.string.contacts)
        is SettingsComponent.Child.Debug -> "Debug"
    }
