package com.github.truejacobg.aoc

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.html.respondHtml
import io.ktor.server.netty.Netty
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.html.body
import kotlinx.html.p

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    addHealthChecking()
    configureRouting()
}

fun Application.addHealthChecking() {
    routing {
        get("/health") {
            val runtime = Runtime.getRuntime()
            val usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024
            val maxMemory = runtime.maxMemory() / 1024 / 1024

            call.respondHtml {
                body {
                    p {
                        +"Status: UP"
                    }
                    p {
                        +"Used Memory: ${usedMemory}MB"
                    }
                    p {
                        +"Max Memory: ${maxMemory}MB"
                    }
                }
            }
        }
    }
}
