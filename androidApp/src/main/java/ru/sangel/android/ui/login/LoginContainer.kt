package ru.sangel.android.ui.login

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.value.getValue
import ru.sangel.android.R
import ru.sangel.android.ui.components.ErrorState
import ru.sangel.android.ui.components.LoginImage
import ru.sangel.android.ui.components.OnboardingFirstCard
import ru.sangel.presentation.components.login.LoginComponent

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
