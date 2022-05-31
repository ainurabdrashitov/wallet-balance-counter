package io.aabdrashitov.wallet.domain.query.repository

import io.aabdrashitov.wallet.domain.query.BalanceAmountHistoryItem
import java.time.ZonedDateTime

class PostgresBalanceStateRepository : Repository<BalanceAmountHistoryItem> {
    override fun save(model: BalanceAmountHistoryItem) {
        TODO("Not yet implemented")
    }

    override fun findByDatetime(datetime: ZonedDateTime): BalanceAmountHistoryItem? {
        TODO("Not yet implemented")
    }

    override fun findByDatetime(start: ZonedDateTime, end: ZonedDateTime): List<BalanceAmountHistoryItem> {
        TODO("Not yet implemented")
    }
}