package ru.sangel.zaya.data

open class BaseKtorfitSource {
    suspend fun <T> wrapKtorfitExceptions(block: suspend () -> T): T {
        return block.invoke()
    }
}
