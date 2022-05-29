package io.aabdrashitov.wallet.event

import java.math.BigDecimal
import java.time.ZonedDateTime

interface EventHandler {
    fun handle(event: Event)
}

sealed class Event

data class NewTransaction(
    val datetime: ZonedDateTime,
    val amount: BigDecimal
) : Event()