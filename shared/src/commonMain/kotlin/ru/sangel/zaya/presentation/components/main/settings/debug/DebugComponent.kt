package ru.sangel.zaya.presentation.components.main.settings.debug

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.sangel.zaya.presentation.entities.States

interface DebugComponent {
    val model: Value<Model>

    suspend fun observeNumbers()

    fun smsTofavorites()

    fun smsTo112()

    fun notificationToNear()

    fun updateNumber(number: String)

    fun updateIncomingNumber(number: String)

    fun saveNumber()

    fun saveIncomingNumber()

    fun changeUpdatingNumbers(boolean: Boolean)

    data class Model(
        val phoneNubmer: String,
        val incomingPhoneNumber: String,
        val updateNumbers: Boolean,
        val states: States,
    )

    companion object {
        fun stubComponent(): DebugComponent =
            object : DebugComponent {
                override val model: Value<Model>
                    get() = MutableValue(Model("", "", true, States.Loaded))

                override suspend fun observeNumbers() {
                    TODO("Not yet implemented")
                }

                override fun smsTofavorites() {
                    TODO("Not yet implemented")
                }

                override fun smsTo112() {
                    TODO("Not yet implemented")
                }

                override fun notificationToNear() {
                    TODO("Not yet implemented")
                }

                override fun updateNumber(number: String) {
                    TODO("Not yet implemented")
                }

                override fun updateIncomingNumber(number: String) {
                    TODO("Not yet implemented")
                }

                override fun saveNumber() {
                    TODO("Not yet implemented")
                }

                override fun saveIncomingNumber() {
                    TODO("Not yet implemented")
                }

                override fun changeUpdatingNumbers(boolean: Boolean) {
                    TODO("Not yet implemented")
                }
            }
    }
}
