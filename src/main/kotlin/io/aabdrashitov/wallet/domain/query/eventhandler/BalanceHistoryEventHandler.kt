package io.aabdrashitov.wallet.domain.query.eventhandler

import io.aabdrashitov.wallet.domain.query.BalanceAmountHistoryItem
import io.aabdrashitov.wallet.domain.query.repository.Repository
import io.aabdrashitov.wallet.event.Event
import io.aabdrashitov.wallet.event.EventHandler
import io.aabdrashitov.wallet.event.NewTransaction
import java.math.BigDecimal.ZERO
import java.time.ZoneOffset.UTC
import java.time.temporal.ChronoUnit

class BalanceHistoryEventHandler(private val repository: Repository<BalanceAmountHistoryItem>) : EventHandler {
    override fun handle(event: Event) {
        when (event) {
            is NewTransaction -> {
                val currentHour = event.datetime.withZoneSameInstant(UTC).truncatedTo(ChronoUnit.HOURS).plusHours(1)
                val currentHourItem = repository.findByDatetime(currentHour)
                val prevHourItem = repository.findByDatetime(currentHour.minusHours(1))
                val nextHourItem = repository.findByDatetime(currentHour.plusHours(1))

                val currentHourAmount = currentHourItem?.amount ?: prevHourItem?.amount ?: ZERO
                val newCurrentHourAmount = currentHourAmount + event.amount

                repository.save(BalanceAmountHistoryItem(currentHour, newCurrentHourAmount))
                if (nextHourItem != null) {
                    repository.save(BalanceAmountHistoryItem(currentHour.plusHours(1), nextHourItem.amount + event.amount))
                }
            }
        }
    }
}