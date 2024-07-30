package ru.sangel.presentation.entities

import android.graphics.Bitmap

data class AboutTeamEntity(
    val name: String,
    val grade: String,
    val contact: String,
    val photo: Bitmap?,
)
