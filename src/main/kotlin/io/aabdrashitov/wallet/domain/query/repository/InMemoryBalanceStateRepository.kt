package io.aabdrashitov.wallet.domain.query.repository

import io.aabdrashitov.wallet.domain.query.BalanceAmountHistoryItem
import java.time.ZonedDateTime

class InMemoryBalanceStateRepository : Repository<BalanceAmountHistoryItem> {
    private var state: MutableMap<ZonedDateTime, BalanceAmountHistoryItem> = mutableMapOf()

    override fun save(model: BalanceAmountHistoryItem) {
        state[model.datetime] = model
    }

    override fun findByDatetime(datetime: ZonedDateTime): BalanceAmountHistoryItem? {
        return state[datetime]
    }

    override fun findByDatetime(start: ZonedDateTime, end: ZonedDateTime): List<BalanceAmountHistoryItem> {
        return state.filter { it.key in start..end && it.key < ZonedDateTime.now() }.values.toList()
    }
}