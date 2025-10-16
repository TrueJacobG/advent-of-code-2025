package com.github.truejacobg.aoc

import com.github.truejacobg.aoc.days.Day
import com.github.truejacobg.aoc.days.getMainDayPage
import io.ktor.server.application.Application
import io.ktor.server.html.respondHtml
import io.ktor.server.request.receiveParameters
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.p
import kotlinx.html.script
import kotlinx.html.title

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondHtml {
                head {
                    title("Main page")
                    script {
                        src = "https://unpkg.com/htmx.org@2.0.3"
                    }
                }
                body {
                    div {
                        p { +"Welcome to Advent of Code!" }
                        p { +"Please select a day between 1 and 25 by navigating to /day/{number}" }
                        (1..25).forEach { day ->
                            p {
                                a(href = "/day/$day") { +"Go to Day $day" }
                            }
                        }
                    }
                }
            }
        }

        get("/day/{number}") {
            val day = getDayNumber(call)

            if (day == -1) {
                call.respondHtml {
                    body {
                        +"Day must be a number between 1 and 25"
                    }
                }
                return@get
            }

            call.respondHtml {
                head {
                    title("Day $day")
                    script {
                        src = "https://unpkg.com/htmx.org@2.0.3"
                    }
                }
                body {
                    getMainDayPage(day)
                }
            }
        }

        post("/day/{number}/submit") {
            val day = getDayNumber(call)

            if (day == -1) {
                call.respondHtml {
                    body {
                        +"Day must be a number between 1 and 25"
                    }
                }
                return@post
            }

            val userInput = call.receiveParameters()["userInput"] ?: ""

            val daySolver = getDaySolver(day)
            val result = daySolver.solve(userInput)

            call.respondHtml {
                body {
                    div {
                        p { +"You entered: $userInput" }
                        p { +"Result: $result" }
                    }
                }
            }
        }
    }
}

fun getDayNumber(call: io.ktor.server.application.ApplicationCall): Int {
    val day = call.parameters["number"]

    if (day == null || day.toIntOrNull() == null || day.toInt() !in 1..25) {
        return -1
    }

    return day.toInt()
}

fun getDaySolver(day: Int): Day {
    val clazz = Class.forName("com.github.truejacobg.aoc.days.day$day.Day$day")
    return clazz.getDeclaredConstructor().newInstance() as Day
}
