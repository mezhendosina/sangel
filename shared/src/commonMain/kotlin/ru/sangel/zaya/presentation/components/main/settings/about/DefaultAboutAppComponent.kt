package ru.sangel.zaya.presentation.components.main.settings.about

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.sangel.zaya.presentation.entities.AboutTeamEntity

class DefaultAboutAppComponent : AboutAppComponent {
    override val model: Value<AboutAppComponent.Model> =
        MutableValue(
            AboutAppComponent.Model(
                listOf(
                    AboutTeamEntity("iamceo05", "Денис Левшин", "Генеральный директор", "Контакт"),
                    AboutTeamEntity(
                        "baidyshev",
                        "Павел Касяник",
                        "Технический директор",
                        "Контакт"
                    ),
                    AboutTeamEntity(
                        "mezhendosina",
                        "Евгений Меньшенин",
                        "Android-разработчик",
                        "t.me/mezhendosina"
                    ),
                    AboutTeamEntity(
                        "dany_217mk",
                        "Данила Москалец",
                        "Backend-разработчик",
                        "Контакт"
                    ),
                    AboutTeamEntity("", "Антон", "Инженер", "Контакт"),
                    AboutTeamEntity("", "Ксения Колповская", "Дизайнер", "Контакт"),
                    AboutTeamEntity("", "Полина Рудяк", "Юрист", "Контакт"),
                    AboutTeamEntity("", "Тимофей Новиков", "Маркетолог", "Контакт"),
                    AboutTeamEntity("", "Анита Ракшина", "SMM", "Контакт"),
                ),
            ),
        )
}
