package com.github.truejacobg.aoc.days

import kotlinx.html.BODY
import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.h1
import kotlinx.html.id
import kotlinx.html.input

fun BODY.getMainDayPage(day: Int) {
    div {
        id = "day"
        h1 { +"Day: $day" }

        form {
            attributes["hx-post"] = "/day/$day/submit"
            attributes["hx-target"] = "#result"

            input {
                type = InputType.text
                name = "userInput"
                placeholder = "Enter text..."
            }
            button {
                type = ButtonType.submit
                +"Submit"
            }
        }

        div {
            id = "result"
        }
    }
}


