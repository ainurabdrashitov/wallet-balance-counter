package io.aabdrashitov.wallet.event

interface EventPublisher {
    fun publish(event: Event)
    fun register(eventHandler: EventHandler): EventPublisher
}