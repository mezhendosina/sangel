package ru.sangel.di.common

import org.koin.dsl.module
import ru.sangel.data.messages.emergencyChat.createEmegencyChat

val emergencyModule =
    module {
        single { createEmegencyChat() }
    }
