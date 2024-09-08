package ru.sangel.presentation.components.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.sangel.LOGIN_STACK
import ru.sangel.data.auth.AuthRepository
import ru.sangel.data.firebase.FirebaseRepository
import ru.sangel.data.settings.AppPrefs
import ru.sangel.presentation.components.login.checkCode.DefaultConfirmCodeComponent
import ru.sangel.presentation.components.login.onboarding.DefaultOnboardingComponent
import ru.sangel.presentation.components.login.signIn.DefaultSignInComponent
import ru.sangel.presentation.components.login.signUp.DefaultSignUpComponent

class DefaultLoginComponent(
    private val authRepository: AuthRepository,
    private val firebaseRepository: FirebaseRepository,
    private val appPrefs: AppPrefs,
    private val componentContext: ComponentContext,
    private val toMain: () -> Unit,
) : LoginComponent,
    ComponentContext by componentContext {
    private val navigation = StackNavigation<LoginConfig>()

    override val stack: Value<ChildStack<*, LoginComponent.Child>> =
        childStack(
            source = navigation,
            key = LOGIN_STACK,
            serializer = LoginConfig.serializer(),
            initialConfiguration = LoginConfig.Onboarding,
            handleBackButton = true,
            childFactory = ::child,
        )

    override fun onBack() {
        navigation.pop()
    }

    private fun child(
        config: LoginConfig,
        componentContext: ComponentContext,
    ): LoginComponent.Child =
        when (config) {
            is LoginConfig.Onboarding ->
                LoginComponent.Child.Onboarding(onboardingComponent(componentContext))

            is LoginConfig.SignIn ->
                LoginComponent.Child.SignIn(
                    signInComponent(componentContext),
                )

            is LoginConfig.ConfirmCode ->
                LoginComponent.Child.ConfirmCode(confirmCodeComponent(componentContext))

            is LoginConfig.SignUp -> LoginComponent.Child.SignUp(signUpComponent(config.withMail))
        }

    private fun signUpComponent(email: String) =
        DefaultSignUpComponent(email, authRepository, { navigation.pop() }) {
            navigation.push(LoginConfig.ConfirmCode)
        }

    private fun onboardingComponent(componentContext: ComponentContext) =
        DefaultOnboardingComponent(componentContext) {
            navigation.push(LoginConfig.SignIn)
        }

    private fun signInComponent(componentContext: ComponentContext) =
        DefaultSignInComponent(
            firebaseRepository = firebaseRepository,
            authRepository = authRepository,
            componentContext = componentContext,
            toCheckCode = {
                toMain.invoke()
            },
        ) {
            navigation.push(DefaultLoginComponent.LoginConfig.SignUp(it))
        }

    private fun confirmCodeComponent(componentContext: ComponentContext) =
        DefaultConfirmCodeComponent(authRepository, componentContext, toMain)

    @Serializable
    private sealed interface LoginConfig {
        @Serializable
        data object Onboarding : LoginConfig

        @Serializable
        data object SignIn : LoginConfig

        @Serializable
        data class SignUp(
            val withMail: String,
        ) : LoginConfig

        @Serializable
        data object ConfirmCode : LoginConfig
    }
}
