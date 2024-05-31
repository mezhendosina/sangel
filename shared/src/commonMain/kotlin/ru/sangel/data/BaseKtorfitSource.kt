package ru.sangel.data

open class BaseKtorfitSource {
    suspend fun <T> wrapKtorfitExceptions(block: suspend () -> T): T {
        return block.invoke()
    }
}
