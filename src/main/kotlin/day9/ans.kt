package day9

import utils.`+`
import utils.`-`
import utils.readDatFile
import kotlin.math.abs

fun Pair<Int, Int>.isX(loc: Pair<Int, Int>): Boolean =
    abs(this.first - loc.first) == 1 && abs(this.second - loc.second) == 1

fun Pair<Int, Int>.`is+`(loc: Pair<Int, Int>): Boolean =
    abs(this.first - loc.first) + abs(this.second - loc.second) == 1

fun main() {
    var h = Pair(0, 0)
    var t = Pair(0, 0)
    val ts = arrayListOf(Pair(0, 0))
    val visited: MutableSet<Pair<Int, Int>> = mutableSetOf(Pair(0, 0))
    val visited2: MutableSet<Pair<Int, Int>> = mutableSetOf(Pair(0, 0))
    val directions = hashMapOf(
        "U" to Pair(0, 1),
        "L" to Pair(-1, 0),
        "R" to Pair(1, 0),
        "D" to Pair(0, -1)
    )

    "day9/test.dat".readDatFile().forEachLine {
        val (direction, times) = it.split(" ")

        for (i in 0 until times.toInt()) {
            val newH = h `+` directions[direction]!!

            if (newH != t && h != t && !newH.isX(t) && !newH.`is+`(t)) {
                // part 1
                t = h
                visited.add(t)

                ts.add(h)
                if (ts.size < 10) {
                    ts.removeAt(0)
                    visited2.add(ts[0])
                }
            }
            h = newH

        }
    }

    println(visited.size)
    println(visited2.size)
}

/*
 ->
   add t to stack of n
   if stack.size == 10 -> add last to visited

   if moving
     - which direction new t is going
     - apply to all


     2419



     -1 1      0 1       1 1
     -1 0      0 0       1 0
     -1 -1     0 -1      1 -1
 */