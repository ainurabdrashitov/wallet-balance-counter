package io.aabdrashitov.wallet.domain.command.aggregate

import io.aabdrashitov.wallet.domain.command.SINGLE_WALLET_ID
import io.aabdrashitov.wallet.event.NewTransaction
import java.math.BigDecimal
import java.time.ZonedDateTime

// wallet aggregate has no id and state for now
class Wallet : AggregateRoot(SINGLE_WALLET_ID) {

    override fun aggregateType(): AggregateType = AggregateType.WALLET

    fun applyTransaction(datetime: ZonedDateTime, transactionAmount: BigDecimal) {
        queueEvent(NewTransaction(datetime, transactionAmount))
    }
}