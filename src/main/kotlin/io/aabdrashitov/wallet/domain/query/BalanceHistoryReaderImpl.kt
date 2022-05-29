package io.aabdrashitov.wallet.domain.query

import io.aabdrashitov.wallet.domain.query.repository.Repository

class BalanceHistoryReaderImpl(private val repository: Repository<BalanceAmountHistory>) : BalanceHistoryReader {
    override fun get(): BalanceAmountHistory {
        return repository.get()
    }
}