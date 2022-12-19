package day2

import utils.readDatFile

fun main() {
    var point = 0

    val theirScoreMap = HashMap<String, Int>()
    val myScoreMap = HashMap<String, Int>()

    /*      Lose   Draw  Win
    Rock     Sc    Rock   Paper
    Paper    Rock  Paper  Sc
    Sc       Paper Sc     Rock
     */
    val transform = listOf(
        listOf("Z", "X", "Y"),
        listOf("X", "Y", "Z"),
        listOf("Y", "Z", "X")
    )
    val outputMetrics = listOf(
        listOf(3, 6, 0),
        listOf(0, 3, 6),
        listOf(6, 0, 3)
    )

    for ((i, e) in ('A'..'C').withIndex()) {
        theirScoreMap[e.toString()] = i
        myScoreMap[(e + 23).toString()] = i
    }

    "day2/input.dat".readDatFile().forEachLine {
        val (a, b) = it.split(" ")
        val their = theirScoreMap[a]!!
        val mine = myScoreMap[transform[their][myScoreMap[b]!!]]!!

        point += outputMetrics[their][mine] + mine + 1
    }

    println(point)
}