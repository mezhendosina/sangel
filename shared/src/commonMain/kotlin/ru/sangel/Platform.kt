package ru.sangel

interface Platform {
    val apiKey: String
}

expect fun getPlatform(): Platform
