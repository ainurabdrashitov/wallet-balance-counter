package io.aabdrashitov.wallet.domain.query.repository

import java.time.ZonedDateTime

interface Repository<T> {
    fun save(model: T)
    fun findByDatetime(datetime: ZonedDateTime): T?
    fun findByDatetime(start: ZonedDateTime, end: ZonedDateTime): List<T>
}