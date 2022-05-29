package io.aabdrashitov.wallet.api.balance

import java.math.BigDecimal
import java.time.ZonedDateTime

interface BalanceHistoryHandler {
    fun handle(request: BalanceHistoryRequest): List<BalanceStateResponse>
}

data class BalanceHistoryRequest(
    val startDatetime: ZonedDateTime,
    val endDatetime: ZonedDateTime
)

data class BalanceStateResponse(
    val datetime: ZonedDateTime,
    val amount: BigDecimal
)