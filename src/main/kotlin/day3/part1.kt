package day3

import utils.readDatFile

fun main() {
    var ans = 0

    "day3/input.dat".readDatFile().forEachLine {
        val s1 = it.substring(0, it.length / 2).toSet()
        val s2 = it.substring(it.length / 2, it.length).toSet()
        val c = s1.intersect(s2).elementAt(0)

        ans += (if (c in 'A'..'Z') c - 38 else c - 96).code
    }

    print(ans)
}