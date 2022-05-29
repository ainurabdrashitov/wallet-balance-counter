package io.aabdrashitov.wallet.event

import io.aabdrashitov.wallet.domain.command.aggregate.AggregateID
import io.aabdrashitov.wallet.domain.command.aggregate.AggregateType

interface EventStore {
    fun saveEvents(
        aggregateType: AggregateType,
        aggregateId: AggregateID,
        events: Iterable<Event>
    ): Int
}