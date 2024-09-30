package ru.sangel.zaya.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sangel.zaya.R
import ru.sangel.zaya.ui.components.LoginButton
import ru.sangel.common.ui.theme.SangelTheme
import ru.sangel.zaya.presentation.components.login.onboarding.OnboardingComponent

@Composable
fun OnboardingScreen(component: OnboardingComponent) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                LoginButton(
                    onClick = component::toSignIn,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(bottom = 16.dp),
                ) {
                    Text(stringResource(R.string.start))
                    Spacer(modifier = Modifier.size(16.dp))
                    Image(
                        painterResource(id = R.drawable.ic_arrow),
                        null,
                        modifier = Modifier.width(32.dp),
                    )
                }
            }
        }
    }
}

@Preview(device = "id:Nexus One")
@Composable
private fun PreviewOboardingScreenSmall() {
    SangelTheme {
        OnboardingScreen(
            object : OnboardingComponent {
                override fun toSignIn() {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}

@Preview(device = "id:Galaxy Nexus")
@Composable
private fun PreviewOboardingScreenGalaxyNexus() {
    SangelTheme {
        OnboardingScreen(
            object : OnboardingComponent {
                override fun toSignIn() {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}

@Preview(device = "id:Nexus 4")
@Composable
private fun PreviewOboardingScreenNexus4() {
    SangelTheme {
        OnboardingScreen(
            object : OnboardingComponent {
                override fun toSignIn() {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}

@Preview(device = "id:pixel_3a")
@Composable
private fun PreviewOboardingScreenPixel3a() {
    SangelTheme {
        OnboardingScreen(
            object : OnboardingComponent {
                override fun toSignIn() {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}

@Preview(device = "id:pixel_fold")
@Composable
private fun PreviewOboardingScreen() {
    SangelTheme {
        OnboardingScreen(
            object : OnboardingComponent {
                override fun toSignIn() {
                    TODO("Not yet implemented")
                }
            },
        )
    }
}
