package ru.sangel.zaya.di.common

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sangel.zaya.data.auth.AuthRepository
import ru.sangel.zaya.data.auth.AuthRepositoryImpl
import ru.sangel.zaya.data.auth.AuthSource
import ru.sangel.zaya.data.auth.KtorfitAuthSource
import ru.sangel.zaya.data.auth.StubAuthSource
import ru.sangel.zaya.data.contacts.ContactsRepository
import ru.sangel.zaya.data.contacts.ContactsRepositoryImpl
import ru.sangel.zaya.data.contacts.api.FavoritesSource
import ru.sangel.zaya.data.contacts.api.KtorfitFavoritesSource
import ru.sangel.zaya.data.contacts.api.StubFavoritesSource
import ru.sangel.zaya.data.device.DeviceRepository
import ru.sangel.zaya.data.device.DeviceRepositoryImpl
import ru.sangel.zaya.data.device.api.DeviceSource
import ru.sangel.zaya.data.device.api.KtorfitDeviceSource
import ru.sangel.zaya.data.device.api.StubDeviceSource
import ru.sangel.zaya.data.firebase.FirebaseRepository
import ru.sangel.zaya.data.firebase.FirebaseRepositoryImpl
import ru.sangel.zaya.data.messages.DefaultMessagesSource
import ru.sangel.zaya.data.messages.source.KtorfitNotificationsSource
import ru.sangel.zaya.data.messages.MessagesRepository
import ru.sangel.zaya.data.messages.MessagesRepositoryImpl
import ru.sangel.zaya.data.messages.MessagesSource
import ru.sangel.zaya.data.messages.source.NotificationsSource
import ru.sangel.zaya.data.messages.source.StubNotificationsSource
import ru.sangel.zaya.data.users.KtorfitUsersSource
import ru.sangel.zaya.data.users.StubUsersSource
import ru.sangel.zaya.data.users.UsersRepository
import ru.sangel.zaya.data.users.UsersRepositoryImpl
import ru.sangel.zaya.data.users.UsersSource

val repoModule =
    module {
        singleOf(::AuthRepositoryImpl) bind AuthRepository::class
        singleOf(::UsersRepositoryImpl) bind UsersRepository::class
        singleOf(::ContactsRepositoryImpl) bind ContactsRepository::class
        singleOf(::DeviceRepositoryImpl) bind DeviceRepository::class
        singleOf(::FirebaseRepositoryImpl) bind FirebaseRepository::class
        singleOf(::MessagesRepositoryImpl) bind MessagesRepository::class
    }

val sourceModule =
    module {
        singleOf(::KtorfitAuthSource) bind AuthSource::class
        singleOf(::KtorfitUsersSource) bind UsersSource::class
        singleOf(::KtorfitDeviceSource) bind DeviceSource::class
        singleOf(::KtorfitFavoritesSource) bind FavoritesSource::class
        singleOf(::KtorfitNotificationsSource) bind NotificationsSource::class
        singleOf(::DefaultMessagesSource) bind MessagesSource::class
    }

val stubSourceModule = module {
    singleOf(::StubAuthSource) bind AuthSource::class
    singleOf(::StubUsersSource) bind UsersSource::class
    singleOf(::StubFavoritesSource) bind FavoritesSource::class
    singleOf(::StubDeviceSource) bind DeviceSource::class
    singleOf(::StubNotificationsSource) bind StubNotificationsSource::class
    singleOf(::DefaultMessagesSource) bind MessagesSource::class
}
