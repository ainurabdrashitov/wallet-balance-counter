package io.aabdrashitov

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.aabdrashitov.wallet.Dependencies
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.text.SimpleDateFormat

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(ContentNegotiation) {
        jackson {
            registerModule(JavaTimeModule())
            dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
        }
    }
    routing {
        post("/transaction") {
            call.respond(dependencies.transactionHandler.handle(call.receive()))
        }
        post("/balance-history") {
            call.respond(dependencies.balanceHistoryHandler.handle(call.receive()))
        }
    }
}

private val dependencies = Dependencies()