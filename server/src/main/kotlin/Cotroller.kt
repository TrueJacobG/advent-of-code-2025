package com.github.truejacobg

import io.ktor.server.application.Application
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.html.body


val numberOfRecords = AtomicInteger(10)

fun Application.controller() {
    routing {
        get("/more-rows") {
            numberOfRecords.addAndGet(10)
            call.respondHtml {
                body {
                    randomRows(numberOfRecords.get())
                }
            }
        }
    }

}
