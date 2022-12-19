package day1

import utils.readDatFile

fun main() {
    val col_list = arrayListOf<Int>()
    var curr_cal = 0

    "day1/input.dat".readDatFile().forEachLine {
        it.toIntOrNull()?.let { num ->
            curr_cal += num
        } ?: run {
            col_list.add(curr_cal)
            curr_cal = 0
        }
    }

    col_list.sortDescending()
    print(col_list.take(3).sum())
}