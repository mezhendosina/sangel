package ru.sangel.zaya.presentation.components.login

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.sangel.zaya.presentation.components.login.checkCode.ConfirmCodeComponent
import ru.sangel.zaya.presentation.components.login.onboarding.OnboardingComponent
import ru.sangel.zaya.presentation.components.login.signIn.SignInComponent
import ru.sangel.zaya.presentation.components.login.signUp.SignUpComponent

interface LoginComponent {
    val stack: Value<ChildStack<*, Child>>

    fun onBack()

    sealed class Child {
        class Onboarding(val onboardingComponent: OnboardingComponent) : Child()

        class SignIn(val signInComponent: SignInComponent) : Child()

        class SignUp(val signUpComponent: SignUpComponent) : Child()

        class ConfirmCode(val confirmCodeComponent: ConfirmCodeComponent) : Child()
    }
}
