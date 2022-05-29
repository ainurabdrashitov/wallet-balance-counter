package io.aabdrashitov.wallet.domain.query.repository

import io.aabdrashitov.wallet.domain.query.BalanceAmountHistory

class InMemoryBalanceStateRepository : Repository<BalanceAmountHistory> {
    private var state: BalanceAmountHistory = BalanceAmountHistory(emptyMap())

    override fun save(model: BalanceAmountHistory) {
        state = model
    }

    override fun get(): BalanceAmountHistory {
        return state
    }
}