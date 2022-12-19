package day1

import utils.readDatFile
import kotlin.math.max

fun main() {
    var max_cal = 0
    var curr_cal = 0

    "day1/input.dat".readDatFile().forEachLine {
        it.toIntOrNull()?.let { num ->
            curr_cal += num
        } ?: run {
            max_cal = max(max_cal, curr_cal)
            curr_cal = 0
        }
    }
    print(max_cal)
}