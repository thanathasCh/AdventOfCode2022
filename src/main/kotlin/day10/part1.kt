package day10

import utils.readDatFile

fun main() {
    var x = 1
    val signals = arrayListOf<Int>(1)
    val interestedSignal = arrayOf(20, 60, 100, 140, 180, 220)

    "day10/input.dat".readDatFile().forEachLine {
        signals.add(x)
        it.split(" ").let { op ->
            if (op.size == 2) {
                x += op[1].toInt()
                signals.add(x)
            }
        }
    }

    // ans1
    println(interestedSignal.sumOf {
        signals[it - 1] * it
    })

    // ans2
    for ((i, v) in signals.withIndex()) {
        val newV = v % 40
        if ((i % 40)+1 in v..(v+2)) {
            print("#")
        } else {
            print(".")
        }

        if ((i + 1) % 40 == 0) {
            println()
        }
    }
}