package io.aabdrashitov.wallet.api.transaction

import io.aabdrashitov.wallet.domain.command.CommandDispatcher
import io.aabdrashitov.wallet.domain.command.CommandDispatchingResult
import io.aabdrashitov.wallet.domain.command.TransactionCommand
import java.math.BigDecimal
import java.time.ZonedDateTime

class TransactionHandlerImpl(private val commandDispatcher: CommandDispatcher) : TransactionHandler {
    private val initializationTime = ZonedDateTime.now()
    init {
        commandDispatcher.handle(TransactionCommand(
            datetime = initializationTime,
            amount = BigDecimal(1000)
        ))
    }

    override fun handle(transaction: Transaction): SaveTransactionResponse {
        val isTransactionBeforeInitialization = transaction.datetime.isBefore(initializationTime)
        val isTransactionFromFuture = transaction.datetime.isAfter(ZonedDateTime.now())
        return if (isTransactionBeforeInitialization || isTransactionFromFuture)
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