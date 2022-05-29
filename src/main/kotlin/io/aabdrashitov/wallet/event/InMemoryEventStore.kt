package io.aabdrashitov.wallet.event

class InMemoryEventStore(eventPublisher: EventPublisher) : BaseEventStore(eventPublisher) {
    private val streams: MutableMap<StreamKey, MutableList<EventWrapper>> = mutableMapOf()

    override fun stream(key: StreamKey): Iterable<EventWrapper>? {
        return streams[key]?.toList()
    }

    override fun appendEventWrapper(key: StreamKey, eventWrapper: EventWrapper) {
        val stream = streams[key] ?: mutableListOf()
        stream.add(eventWrapper)
        streams[key] = stream
    }
}