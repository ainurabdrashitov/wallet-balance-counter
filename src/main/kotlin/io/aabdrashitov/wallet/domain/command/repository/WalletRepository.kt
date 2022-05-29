package io.aabdrashitov.wallet.domain.command.repository

import io.aabdrashitov.wallet.domain.command.aggregate.Wallet
import io.aabdrashitov.wallet.event.EventStore

class WalletRepository(eventStore: EventStore) : EventSourcedRepository<Wallet>(eventStore) {
    override fun new(): Wallet = Wallet()
}