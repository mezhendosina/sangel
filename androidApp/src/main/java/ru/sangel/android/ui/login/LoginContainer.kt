package ru.sangel.android.ui.login

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.sangel.presentation.components.login.LoginComponent

@Composable
fun LoginContainer(loginComponent: LoginComponent) {
    Children(stack = loginComponent.stack) {
        when (val config = it.instance) {
            is LoginComponent.Child.Onboarding -> OnboardingScreen(component = config.onboardingComponent)
            is LoginComponent.Child.SignIn -> SignInScreen(component = config.signInComponent)
            is LoginComponent.Child.ConfirmCode -> ConfirmCodeScreen(component = config.confirmCodeComponent)
            is LoginComponent.Child.SignUp -> SignUpScreen(component = config.signUpComponent)
        }
    }
}
