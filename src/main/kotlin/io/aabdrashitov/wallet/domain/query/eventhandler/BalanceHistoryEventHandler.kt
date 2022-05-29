package io.aabdrashitov.wallet.domain.query.eventhandler

import io.aabdrashitov.wallet.domain.query.BalanceAmountHistory
import io.aabdrashitov.wallet.domain.query.repository.Repository
import io.aabdrashitov.wallet.event.Event
import io.aabdrashitov.wallet.event.EventHandler
import io.aabdrashitov.wallet.event.NewTransaction
import java.math.BigDecimal.ZERO
import java.time.ZoneOffset.UTC
import java.time.temporal.ChronoUnit

class BalanceHistoryEventHandler(private val repository: Repository<BalanceAmountHistory>) : EventHandler {
    override fun handle(event: Event) {
        when (event) {
            is NewTransaction -> {
                val history = repository.get()
                val map = history.map.toMutableMap()
                val dateTime = event.datetime.withZoneSameInstant(UTC).truncatedTo(ChronoUnit.HOURS).plusHours(1)
                map[dateTime] = (history.map[dateTime] ?: ZERO) + event.amount
                repository.save(BalanceAmountHistory(map))
            }
        }
    }
}