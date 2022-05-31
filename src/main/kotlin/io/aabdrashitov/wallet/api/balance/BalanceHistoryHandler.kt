package io.aabdrashitov.wallet.api.balance

import io.aabdrashitov.wallet.domain.query.BalanceAmountHistoryItem
import java.time.ZonedDateTime

interface BalanceHistoryHandler {
    fun handle(request: BalanceHistoryRequest): List<BalanceAmountHistoryItem>
}

data class BalanceHistoryRequest(
    val startDatetime: ZonedDateTime,
    val endDatetime: ZonedDateTime
)