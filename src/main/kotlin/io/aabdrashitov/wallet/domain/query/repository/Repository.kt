package io.aabdrashitov.wallet.domain.query.repository

interface Repository<T> {
    fun save(model: T)
    fun get(): T
}