package io.aabdrashitov.wallet.domain.query

import java.math.BigDecimal
import java.time.ZonedDateTime

interface BalanceHistoryReader {
    fun get(start: ZonedDateTime, end: ZonedDateTime): List<BalanceAmountHistoryItem>
}

data class BalanceAmountHistoryItem(
    val datetime: ZonedDateTime,
    val amount: BigDecimal
)