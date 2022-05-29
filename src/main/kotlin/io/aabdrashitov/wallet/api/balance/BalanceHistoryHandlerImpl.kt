package io.aabdrashitov.wallet.api.balance

import io.aabdrashitov.wallet.domain.query.BalanceHistoryReader
import java.math.BigDecimal
import java.time.Duration
import java.time.ZoneOffset.UTC
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class BalanceHistoryHandlerImpl(private val balanceHistoryReader: BalanceHistoryReader) : BalanceHistoryHandler {
    override fun handle(request: BalanceHistoryRequest): List<BalanceStateResponse> {
        val start = request.startDatetime.withZoneSameInstant(UTC)
        val end = request.endDatetime.withZoneSameInstant(UTC)
        return if (isInvalidPeriod(start, end)) emptyList() else buildResponse(start, end)
    }

    private fun buildResponse(start: ZonedDateTime, end: ZonedDateTime): List<BalanceStateResponse> {
        val history = balanceHistoryReader.get()
        val statisticsStart = start.truncatedTo(ChronoUnit.HOURS).plusHours(1)
        val hours = generateSequence(statisticsStart) {
            val next = it.plusHours(1)
            if (next.isBefore(end) && next.isBefore(ZonedDateTime.now())) next
            else null
        }
            .toList()
        var currentAmount: BigDecimal? = null
        val result = mutableListOf<BalanceStateResponse>()
        for (hour in hours) {
            val amount = history.map[hour]
            when {
                currentAmount == null && amount != null -> {
                    currentAmount = amount
                    result.add(BalanceStateResponse(hour, currentAmount))
                }
                currentAmount != null && amount == null -> {
                    result.add(BalanceStateResponse(hour, currentAmount))
                }
                currentAmount != null && amount != null -> {
                    currentAmount += amount
                    result.add(BalanceStateResponse(hour, currentAmount))
                }
            }
        }
        return result
    }

    private fun isInvalidPeriod(start: ZonedDateTime, end: ZonedDateTime): Boolean {
        return end.isBefore(start) || Duration.between(start, end).toHours() < 1
    }
}