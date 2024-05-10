package ru.sangel

import android.content.res.Resources.NotFoundException

class UserNotFoundException() : NotFoundException("user_id")

class TokenNotFoundException() : NullPointerException()
