package io.aabdrashitov.wallet.domain.command.repository

import io.aabdrashitov.wallet.domain.command.aggregate.AggregateID
import io.aabdrashitov.wallet.domain.command.aggregate.AggregateRoot

interface Repository<A : AggregateRoot> {
    fun get(id: AggregateID): A
    fun save(aggregate: A): A
    fun new(): A
}