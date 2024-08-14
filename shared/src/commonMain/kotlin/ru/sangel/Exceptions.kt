package ru.sangel

import android.content.res.Resources.NotFoundException

class UserNotFoundException() : NotFoundException("user_id")

class LocationNotFoundException : NullPointerException() {
    override fun getLocalizedMessage(): String? = "Не найдено местоположение"
}

class EmergencyNumberNotFountException : NullPointerException() {
    override fun getLocalizedMessage(): String? = "Не сохранен номер для отправки"
}
