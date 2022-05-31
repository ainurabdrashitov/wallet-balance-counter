package io.aabdrashitov.wallet.domain.query

import io.aabdrashitov.wallet.domain.query.repository.Repository
import java.time.ZonedDateTime

class BalanceHistoryReaderImpl(private val repository: Repository<BalanceAmountHistoryItem>) : BalanceHistoryReader {
    override fun get(start: ZonedDateTime, end: ZonedDateTime): List<BalanceAmountHistoryItem> {
        return repository.findByDatetime(start, end)
    }
}