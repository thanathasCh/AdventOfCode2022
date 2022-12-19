package day3

import utils.readDatFile

fun main() {
    val rack = arrayListOf<Set<Char>>()
    "day3/input.dat".readDatFile().forEachLine {
        rack.add(it.toSet())
    }

    println((0 until rack.size step 3).sumOf {
        val c = rack.subList(it, it + 3).reduce { s1, s2 -> s1.intersect(s2) }
            .elementAtOrNull(0)

        c?.let { (if (c in 'A'..'Z') c - 38 else c - 96).code } ?: run { 0 }
    })
}
