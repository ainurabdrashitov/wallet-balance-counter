package io.aabdrashitov.wallet.domain.command.repository

import io.aabdrashitov.wallet.domain.command.aggregate.AggregateID
import io.aabdrashitov.wallet.domain.command.aggregate.AggregateRoot
import io.aabdrashitov.wallet.event.EventStore

abstract class EventSourcedRepository<A : AggregateRoot>(private val eventStore: EventStore) : Repository<A> {
    override fun save(aggregate: A): A {
        val uncommittedChanges = aggregate.getUncommittedChanges()
        eventStore.saveEvents(aggregate.aggregateType(), aggregate.id, uncommittedChanges)
        aggregate.markChangesAsCommitted()
        return aggregate
    }

    override fun get(id: AggregateID): A = new()
}