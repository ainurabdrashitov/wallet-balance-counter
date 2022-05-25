package io.aabdrashitov

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

private val logger = LoggerFactory.getLogger(Application::class.java)

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
        jackson {
            registerModule(JavaTimeModule())
            dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
        }
    }
    routing {
        post("/transaction") {
            val transaction = call.receive<Transaction>()
            logger.info("Received transaction: $transaction")
            call.respond(SaveTransactionResponse(status = "SUCCESS"))
        }
        post("/balance-history") {
            val request = call.receive<BalanceHistoryRequest>()
            logger.info("Received balance history request: $request")
            call.respond(listOf(
                BalanceState(datetime = ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS), amount = BigDecimal.ZERO),
                BalanceState(datetime = ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS), amount = BigDecimal.ONE),
            ))
        }
    }
}

data class Transaction(
    val datetime: ZonedDateTime,
    val amount: BigDecimal
)

data class SaveTransactionResponse(
    val status: String
)

data class BalanceHistoryRequest(
    val startDatetime: ZonedDateTime,
    val endDatetime: ZonedDateTime
)

data class BalanceState(
    val datetime: ZonedDateTime,
    val amount: BigDecimal
)