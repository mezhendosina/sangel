package ru.sangel.android.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.sangel.presentation.components.login.LoginComponent

@Composable
fun LoginContainer(loginComponent: LoginComponent) {
    Children(
        stack = loginComponent.stack,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (val config = it.instance) {
            is LoginComponent.Child.Onboarding -> OnboardingScreen(component = config.onboardingComponent)
            is LoginComponent.Child.SignIn -> SignInScreen(component = config.signInComponent)
            is LoginComponent.Child.ConfirmCode -> ConfirmCodeScreen(component = config.confirmCodeComponent)
            is LoginComponent.Child.SignUp -> SignUpScreen(component = config.signUpComponent)
        }
    }
}
