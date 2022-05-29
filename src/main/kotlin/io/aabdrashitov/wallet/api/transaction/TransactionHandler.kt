package io.aabdrashitov.wallet.api.transaction

import java.math.BigDecimal
import java.time.ZonedDateTime

interface TransactionHandler {
    fun handle(transaction: Transaction): SaveTransactionResponse
}

data class Transaction(
    val datetime: ZonedDateTime,
    val amount: BigDecimal
)

data class SaveTransactionResponse(
    val status: String
)