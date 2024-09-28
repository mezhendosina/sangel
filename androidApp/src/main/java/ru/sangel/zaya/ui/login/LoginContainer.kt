package ru.sangel.zaya.ui.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sangel.zaya.presentation.components.login.LoginComponent
import ru.sangel.zaya.ui.components.LoginImage
import ru.sangel.zaya.ui.components.OnboardingFirstCard

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginContainer(loginComponent: LoginComponent) {
    val stack by loginComponent.stack.subscribeAsState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
        ) {
            Box(
                Modifier
                    .background(
                        Color(0xFFF1C064),
                        shape = RoundedCornerShape(bottomEnd = 52.dp, bottomStart = 52.dp),
                    )
                    .animateContentSize(spring(stiffness = Spring.StiffnessVeryLow))
                    .fillMaxHeight(
                        if (stack.active.instance is LoginComponent.Child.Onboarding) 0.7f else 0.5f
                    ),
            ) {
                if (stack.active.instance is LoginComponent.Child.Onboarding) {
                    OnboardingFirstCard()
                } else {
                    LoginImage()
                }
            }

            Children(
                stack = loginComponent.stack,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background),
                animation = stackAnimation { child ->
                    fade(animationSpec = tween(delayMillis = 100))
                }
            ) { stack ->

                when (val config = stack.instance) {
                    is LoginComponent.Child.Onboarding -> OnboardingScreen(component = config.onboardingComponent)
                    is LoginComponent.Child.SignIn -> SignInScreen(component = config.signInComponent)
                    is LoginComponent.Child.ConfirmCode -> ConfirmCodeScreen(component = config.confirmCodeComponent)
                    is LoginComponent.Child.SignUp -> ModalBottomSheet(
                        onDismissRequest = {
                            loginComponent.onBack()
                            showBottomSheet = false
                        },
                        sheetState = sheetState,
                        dragHandle = {},
                        windowInsets = WindowInsets(top = 0.dp)
                    ) {
                        SignUpScreen(component = config.signUpComponent)
                    }
                }
            }
        }
    }
}
