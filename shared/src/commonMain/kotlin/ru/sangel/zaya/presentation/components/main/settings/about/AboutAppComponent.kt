package ru.sangel.zaya.presentation.components.main.settings.about

import com.arkivanov.decompose.value.Value
import ru.sangel.zaya.presentation.entities.AboutTeamEntity

interface AboutAppComponent {
    val model: Value<Model>

    data class Model(
        val team: List<AboutTeamEntity>,
    )

    companion object{
        fun stubComponent(): AboutAppComponent = object : AboutAppComponent{
            override val model: Value<Model>
                get() = TODO("Not yet implemented")
        }
    }
}
