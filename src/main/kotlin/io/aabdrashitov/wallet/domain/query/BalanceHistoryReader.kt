package io.aabdrashitov.wallet.domain.query

import java.math.BigDecimal
import java.time.ZonedDateTime

interface BalanceHistoryReader {
    fun get(): BalanceAmountHistory
}

data class BalanceAmountHistory(
    val map: Map<ZonedDateTime, BigDecimal>
)