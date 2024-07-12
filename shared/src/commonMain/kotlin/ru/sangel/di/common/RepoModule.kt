package ru.sangel.di.common

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sangel.data.auth.AuthRepository
import ru.sangel.data.auth.AuthRepositoryImpl
import ru.sangel.data.auth.AuthSource
import ru.sangel.data.auth.KtorfitAuthSource
import ru.sangel.data.auth.StubAuthSource
import ru.sangel.data.contacts.ContactsRepository
import ru.sangel.data.contacts.ContactsRepositoryImpl
import ru.sangel.data.device.DeviceRepository
import ru.sangel.data.device.DeviceRepositoryImpl
import ru.sangel.data.firebase.FirebaseRepository
import ru.sangel.data.firebase.FirebaseRepositoryImpl
import ru.sangel.data.messages.DefaultMessagesSource
import ru.sangel.data.messages.MessagesSource
import ru.sangel.data.users.KtorfitUsersSource
import ru.sangel.data.users.StubUsersSource
import ru.sangel.data.users.UsersRepository
import ru.sangel.data.users.UsersRepositoryImpl
import ru.sangel.data.users.UsersSource

val repoModule =
    module {
        singleOf(::AuthRepositoryImpl) bind AuthRepository::class
        singleOf(::UsersRepositoryImpl) bind UsersRepository::class
        singleOf(::ContactsRepositoryImpl) bind ContactsRepository::class
        singleOf(::DeviceRepositoryImpl) bind DeviceRepository::class
        singleOf(::FirebaseRepositoryImpl) bind FirebaseRepository::class
    }

val sourceModule =
    module {
        singleOf(::KtorfitAuthSource) bind AuthSource::class
        singleOf(::KtorfitUsersSource) bind UsersSource::class
        singleOf(::DefaultMessagesSource) bind MessagesSource::class
    }

val stubSourceModule = module {
    singleOf(::StubAuthSource) bind AuthSource::class
    singleOf(::StubUsersSource) bind UsersSource::class
    singleOf(::DefaultMessagesSource) bind MessagesSource::class
}
