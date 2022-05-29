package io.aabdrashitov.wallet.domain.command.aggregate

import io.aabdrashitov.wallet.event.Event

abstract class AggregateRoot(val id: AggregateID) {
    private val uncommittedChanges = ArrayList<Event>()

    abstract fun aggregateType(): AggregateType

    fun getUncommittedChanges(): Iterable<Event> = uncommittedChanges.toList()

    fun markChangesAsCommitted() {
        uncommittedChanges.clear()
    }

    protected fun queueEvent(event: Event) {
        uncommittedChanges.add(event)
    }
}

typealias AggregateID = String

enum class AggregateType {
    WALLET
}