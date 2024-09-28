package ru.sangel.zaya.presentation.components.login.onboarding

import com.arkivanov.decompose.ComponentContext

class DefaultOnboardingComponent(
    private val componentContext: ComponentContext,
    private val toSignIn: () -> Unit,
) : OnboardingComponent, ComponentContext by componentContext {
    override fun toSignIn() = toSignIn.invoke()
}
