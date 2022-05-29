package io.aabdrashitov.wallet.event

class InMemoryEventPublisher : EventPublisher {
    private val handlers: MutableList<EventHandler> = mutableListOf()

    override fun publish(event: Event) {
        handlers.forEach { it.handle(event) }
    }

    override fun register(eventHandler: EventHandler): EventPublisher {
        handlers += eventHandler
        return this
    }
}