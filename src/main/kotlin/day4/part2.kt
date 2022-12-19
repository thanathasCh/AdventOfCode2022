package day4

import utils.readDatFile

fun main() {
    var ans = 0
    "day4/input.dat".readDatFile().forEachLine { input ->
        val (input1, input2) = input.split(",")
        val (min1, max1) = input1.split("-").map { it.toInt() }
        val (min2, max2) = input2.split("-").map { it.toInt() }

        if ((min1 in min2..max2 || max1 in min2..max2)
            || (min2 in min1..max1 || max2 in min1..max1)
        ) {
            ans += 1
        }
    }

    print(ans)
}