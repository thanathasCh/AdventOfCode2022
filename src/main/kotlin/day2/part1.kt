package day2

import utils.readDatFile

fun main() {
    var point = 0
    val theirScoreMap = HashMap<String, Int>()
    val myScoreMap = HashMap<String, Int>()

    /*
    their/me  Rock    Paper    Sc
    Rock       3        6       0
    Paper      0        3       6
    Sc         6        0       3
     */
    val outputMetrics = listOf(
        listOf(3, 6, 0),
        listOf(0, 3, 6),
        listOf(6, 0, 3)
    )

    for ((i, e) in ('A'..'C').withIndex()) {
        theirScoreMap[e.toString()] = i
        myScoreMap[(e + 23).toString()] = i
    }

    "day2/input.dat".readDatFile().forEachLine { input ->
        val (a, b) = input.split(" ")
        val their = theirScoreMap[a]!!
        val mine = myScoreMap[b]!!

        point += outputMetrics[their][mine] + mine + 1
    }

    print(point)
}