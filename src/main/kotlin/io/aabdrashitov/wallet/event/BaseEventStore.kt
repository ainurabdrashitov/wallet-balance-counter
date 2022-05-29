package io.aabdrashitov.wallet.event

import io.aabdrashitov.wallet.domain.command.aggregate.AggregateID
import io.aabdrashitov.wallet.domain.command.aggregate.AggregateType

abstract class BaseEventStore(private val eventPublisher: EventPublisher) : EventStore {
    protected abstract fun stream(key: StreamKey): Iterable<EventWrapper>?
    protected abstract fun appendEventWrapper(key: StreamKey, eventWrapper: EventWrapper)

    override fun saveEvents(
        aggregateType: AggregateType,
        aggregateId: AggregateID,
        events: Iterable<Event>
    ): Int {
        val streamKey = StreamKey(aggregateType, aggregateId)
        return appendAndPublish(streamKey, events)
    }

    private fun appendAndPublish(streamKey: StreamKey, events: Iterable<Event>): Int {
        events.forEach { event ->
            val eventWrapper = EventWrapper(streamKey, event)
            appendEventWrapper(streamKey, eventWrapper)
            eventPublisher.publish(event)
        }
        return events.count()
    }
}

data class EventWrapper(val streamKey: StreamKey, val event: Event)

data class StreamKey(val aggregateType: AggregateType, val aggregateID: AggregateID)