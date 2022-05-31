package io.aabdrashitov.wallet.api.balance

import io.aabdrashitov.wallet.domain.query.BalanceAmountHistoryItem
import io.aabdrashitov.wallet.domain.query.BalanceHistoryReader
import java.time.Duration
import java.time.ZoneOffset.UTC
import java.time.ZonedDateTime

class BalanceHistoryHandlerImpl(private val balanceHistoryReader: BalanceHistoryReader) : BalanceHistoryHandler {
    override fun handle(request: BalanceHistoryRequest): List<BalanceAmountHistoryItem> {
        val start = request.startDatetime.withZoneSameInstant(UTC)
        val end = request.endDatetime.withZoneSameInstant(UTC)
        return if (isInvalidPeriod(start, end)) emptyList()
        else balanceHistoryReader.get(start, end)
    }

    private fun isInvalidPeriod(start: ZonedDateTime, end: ZonedDateTime): Boolean {
        return end.isBefore(start) || Duration.between(start, end).toHours() < 1
    }
}