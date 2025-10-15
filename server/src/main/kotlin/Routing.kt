package com.github.truejacobg

import io.ktor.server.application.Application
import io.ktor.server.html.respondHtml
import io.ktor.server.http.content.staticResources
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.link
import kotlinx.html.script
import kotlinx.html.table
import kotlinx.html.tbody
import kotlinx.html.title

fun Application.configureRouting() {
    routing {
        staticResources("/", "/web")

        get("/") {
            call.respondHtml {
                head {
                    title("Main page")
                    script {
                        src = "https://unpkg.com/htmx.org@2.0.3"
                    }
                }
                body{
                    leaderboardPage()
                }
            }
        }
    }
}
