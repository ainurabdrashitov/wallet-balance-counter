package io.aabdrashitov.wallet.event

class KafkaEventStore(private val eventPublisher: EventPublisher) : BaseEventStore(eventPublisher) {
    override fun stream(key: StreamKey): Iterable<EventWrapper>? {
        TODO("Not yet implemented")
    }

    override fun appendEventWrapper(key: StreamKey, eventWrapper: EventWrapper) {
        TODO("Not yet implemented")
    }
}