package io.aabdrashitov.wallet.domain.command

import java.math.BigDecimal
import java.time.ZonedDateTime

interface CommandDispatcher {
    fun handle(command: Command): CommandDispatchingResult
}

enum class CommandDispatchingResult {
    SUCCESS, ERROR
}

sealed class Command

data class TransactionCommand(
    val datetime: ZonedDateTime,
    val amount: BigDecimal
) : Command()