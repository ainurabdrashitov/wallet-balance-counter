package io.aabdrashitov.wallet

import io.aabdrashitov.wallet.api.balance.BalanceHistoryHandlerImpl
import io.aabdrashitov.wallet.api.transaction.TransactionHandlerImpl
import io.aabdrashitov.wallet.domain.command.CommandDispatcherImpl
import io.aabdrashitov.wallet.domain.command.repository.WalletRepository
import io.aabdrashitov.wallet.domain.query.BalanceHistoryReaderImpl
import io.aabdrashitov.wallet.domain.query.eventhandler.BalanceHistoryEventHandler
import io.aabdrashitov.wallet.domain.query.repository.InMemoryBalanceStateRepository
import io.aabdrashitov.wallet.event.InMemoryEventPublisher
import io.aabdrashitov.wallet.event.InMemoryEventStore

class Dependencies {
    // query
    private val balanceStateRepository = InMemoryBalanceStateRepository()
    private val balanceHistoryReader = BalanceHistoryReaderImpl(balanceStateRepository)

    // event
    private val balanceHistoryEventHandler = BalanceHistoryEventHandler(balanceStateRepository)
    private val eventPublisher = InMemoryEventPublisher().register(balanceHistoryEventHandler)
    private val eventStore = InMemoryEventStore(eventPublisher)

    // command
    private val walletRepository = WalletRepository(eventStore)
    private val commandDispatcher = CommandDispatcherImpl(walletRepository)

    // request handlers
    val transactionHandler = TransactionHandlerImpl(commandDispatcher)
    val balanceHistoryHandler = BalanceHistoryHandlerImpl(balanceHistoryReader)
}