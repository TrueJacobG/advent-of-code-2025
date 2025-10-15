package com.github.truejacobg


import io.ktor.htmx.html.hx
import io.ktor.utils.io.ExperimentalKtorApi
import java.nio.file.Paths
import kotlin.io.path.exists
import kotlin.io.path.useLines
import kotlin.random.Random
import kotlinx.html.BODY
import kotlinx.html.DIV
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.h2
import kotlinx.html.id
import kotlinx.html.style
import kotlinx.html.table
import kotlinx.html.tbody
import kotlinx.html.td
import kotlinx.html.th
import kotlinx.html.thead
import kotlinx.html.tr

val random = Random(System.currentTimeMillis())

fun BODY.leaderboardPage() {
    h1 {
        +"Leaderboard"
    }
    randomRows(10)
}

fun BODY.randomRows(numberOfRows: Int) {
    div {
        id = "main-div"
        loadMoreRows(numberOfRows)

        table {
            id = "leaderboard"
            thead {
                tr {
                    th { +"Alias" }
                    th { +"Score" }
                }
            }
            tbody {
                for (contestant in generateSequence { random.nextContestant() }.take(10)) {
                    tr {
                        td { +contestant.alias }
                        td { +contestant.score.toString() }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalKtorApi::class)
fun DIV.loadMoreRows(numberOfRows: Int) {
    div {
        id = "load-more"
        style = "text-align: center;"

        button {
            attributes.hx {
                get = "/more-rows"
                target = "#main-div"
                trigger = "click"
            }
            +"Load More"
        }
    }
    totalCount(numberOfRows)
}

fun DIV.totalCount(numberOfRows: Int) {
    h2 {
        id = "total-count"
        +"Total: $numberOfRows"
    }
}

private val dictionary: List<String> by lazy {
    listOf("/usr/share/dict/words", "/usr/dict/words").map(Paths::get).firstOrNull {
        it.exists()
    }?.let { dictionaryFile ->
        dictionaryFile.useLines(Charsets.UTF_8) { lines ->
            lines.filter { it.length >= 4 }.toList()
        }
    } ?: listOf("red", "blue", "green", "yellow", "pink", "violet", "black")
}

fun Random.nextContestant(): Contestant = Contestant(
    nextAlias(), nextInt(1_000_000)
)

fun Random.nextAlias(): String = dictionary[nextInt(dictionary.size - 1)]

data class Contestant(
    val alias: String,
    val score: Int,
)

