package io.aabdrashitov.wallet.domain.command.aggregate

import io.aabdrashitov.wallet.domain.command.SINGLE_WALLET_ID
import io.aabdrashitov.wallet.event.NewTransaction
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.time.ZonedDateTime

// wallet aggregate has no id and state for now
class Wallet : AggregateRoot(SINGLE_WALLET_ID) {

    override fun aggregateType(): AggregateType = AggregateType.WALLET

    @Suppress("ReplaceCallWithBinaryOperator")
    fun applyTransaction(datetime: ZonedDateTime, transactionAmount: BigDecimal) {
        if (transactionAmount.compareTo(ZERO) <= 0) {
            throw RuntimeException("Invalid amount")
        }
        queueEvent(NewTransaction(datetime, transactionAmount))
    }
}