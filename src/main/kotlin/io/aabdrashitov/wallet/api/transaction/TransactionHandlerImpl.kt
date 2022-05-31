package io.aabdrashitov.wallet.api.transaction

import io.aabdrashitov.wallet.domain.command.CommandDispatcher
import io.aabdrashitov.wallet.domain.command.CommandDispatchingResult
import io.aabdrashitov.wallet.domain.command.TransactionCommand
import java.math.BigDecimal
import java.time.ZonedDateTime

class TransactionHandlerImpl(private val commandDispatcher: CommandDispatcher) : TransactionHandler {

    @Suppress("ReplaceCallWithBinaryOperator")
    override fun handle(transaction: Transaction): SaveTransactionResponse {
        val isTransactionTooOld = transaction.datetime.isBefore(ZonedDateTime.now().minusHours(1))
        val isTransactionFromFuture = transaction.datetime.isAfter(ZonedDateTime.now())
        val isInvalidAmount = transaction.amount.compareTo(BigDecimal.ZERO) <= 0
        return if (isTransactionTooOld || isTransactionFromFuture || isInvalidAmount)
            CommandDispatchingResult.ERROR.toResponse()
        else
            commandDispatcher.handle(transaction.toCommand()).toResponse()
    }
}

fun Transaction.toCommand() = TransactionCommand(
    datetime = datetime,
    amount = amount
)

fun CommandDispatchingResult.toResponse() = SaveTransactionResponse(status = name)