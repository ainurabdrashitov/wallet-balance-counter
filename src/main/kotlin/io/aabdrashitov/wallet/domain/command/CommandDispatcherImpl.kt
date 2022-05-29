package io.aabdrashitov.wallet.domain.command

import io.aabdrashitov.wallet.domain.command.repository.WalletRepository

const val SINGLE_WALLET_ID = "single"

class CommandDispatcherImpl(private val walletRepository: WalletRepository) : CommandDispatcher {
    override fun handle(command: Command): CommandDispatchingResult {
        return when (command) {
            is TransactionCommand -> {
                runCatching {
                    val wallet = walletRepository.get(SINGLE_WALLET_ID)
                    wallet.applyTransaction(command.datetime, command.amount)
                    walletRepository.save(wallet)
                }
                    .map { CommandDispatchingResult.SUCCESS }
                    .getOrElse { CommandDispatchingResult.ERROR }
            }
        }
    }
}