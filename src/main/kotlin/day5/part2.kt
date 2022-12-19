package day5

import utils.readDatFile

fun main() {
    val (visual, orders) = "day5/input.dat".readDatFile().readText().split("\n\n")
    val listOrder = orders.split("\n").map { it.split("move", "from", "to").filter { t -> t.isNotEmpty() } }
    val crates = visual.split("\n").map { it.split("").subList(1, it.length + 1) }.let {
        it.subList(0, it.size - 1)
    }
    val crateStack = arrayListOf<ArrayDeque<String>>()

    for (i in 0..crates.size) {
        crateStack.add(ArrayDeque())
    }


    for (crate in crates) {
        for (j in 1..crate.size step 4) {
            val box = crate[j]

            if (box.isNotEmpty() && box.isNotBlank()) {
                crateStack[(j - 1) / 4].add(box)
            }
        }
    }

    for (order in listOrder) {
        val (move, from, to) = order.map { it.trim().toInt() }
        for (i in move-1 downTo 0) {
            crateStack[to - 1].addFirst(crateStack[from - 1].removeAt(i))
        }
    }

    println(crateStack.joinToString("") { it.removeFirst() })
}
