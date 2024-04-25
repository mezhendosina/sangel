package ru.sangel

class AndroidPlatform : Platform {
    override val apiKey: String
        get() = ""
}

actual fun getPlatform(): Platform = AndroidPlatform()
