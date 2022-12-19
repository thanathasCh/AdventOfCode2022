package utils

import java.io.File

fun String.readDatFile(): File {
    return File("/Users/tchawengvora/Projects/AdventOfCode/src/main/kotlin/$this")
}

infix fun Pair<Int, Int>.`+`(op: Pair<Int, Int>): Pair<Int, Int> =
    Pair(this.first + op.first, this.second + op.second)


infix fun Pair<Int, Int>.`-`(op: Pair<Int, Int>): Pair<Int, Int> =
    Pair(this.first - op.first, this.second - op.second)