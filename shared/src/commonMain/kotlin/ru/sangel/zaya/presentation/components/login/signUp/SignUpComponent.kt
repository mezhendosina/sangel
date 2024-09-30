package ru.sangel.zaya.presentation.components.login.signUp

import android.graphics.Bitmap
import com.arkivanov.decompose.value.Value
import ru.sangel.zaya.presentation.entities.States

interface SignUpComponent {
    val model: Value<Model>

    fun changeName(name: String)

    fun changePhone(phone: String)

    fun changeMail(mail: String)

    fun changePassword(password: String)

    fun singUp()

    fun onBack()

    data class Model(
        val photo: Bitmap?,
        val firstName: String,
        val secondName: String,
        val middleName: String,
        val phoneNumber: String,
        val email: String,
        val password: String,
        val state: States,
    ) {
        companion object {
            fun init(): Model =
                Model(
                    null,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    States.Loaded,
                )
        }
    }
}
