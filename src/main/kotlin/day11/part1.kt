package day11

import utils.readDatFile


abstract class Monkey(
    private val ifTrue: Int,
    private val ifFalse: Int,
    val divisible: Int
) {
    var count: Int = 0
    val items: ArrayList<Int> = arrayListOf()

    abstract fun operation(item: Int): Int

    fun throwTo(mod: Int): Pair<Int, Int> {
        count++
        val item = items.removeAt(0)
        val op = operation(item)

        (op % mod).let {
            return Pair(it, if (it % divisible == 0) ifTrue else ifFalse)
        }
    }
}

fun generateFuc(txt: String): (Int) -> Int {
    txt.split("=")[1].split(" ").let {

        return { item: Int ->
            with(txt) {
                val operator = if (it.last() == "old") item else it.last().toInt()
                when {
                    contains("+") -> item + operator
                    contains("-") -> item - operator
                    contains("*") -> item * operator
                    contains("/") -> item / operator
                    else -> 0
                }
            }
        }
    }
}

fun main() {
    val input = "day11/test.dat".readDatFile().readText()
    val monkeys = arrayListOf<Monkey>()

    for (i in input.split("\n\n")) {
        val monkeyInput = i.split("\n")
        val divisible = monkeyInput[3].split(" ").last().toInt()
        val ifTrue = monkeyInput[4].split(" ").last().toInt()
        val ifFalse = monkeyInput[5].split(" ").last().toInt()

        val monkey = object : Monkey(ifTrue, ifFalse, divisible) {
            override fun operation(item: Int): Int = generateFuc(monkeyInput[2])(item)
        }
        monkey.items.addAll(
            monkeyInput[1].split(":")[1].split(",").map { it.trim().toInt() }
        )

        monkeys.add(monkey)
    }

    var mod = 1
    for (monkey in monkeys) {
        mod *= monkey.divisible
    }

    for (i in 1..20) {
        for (monkey in monkeys) {
            while (monkey.items.size > 0) {
                val (item, to) = monkey.throwTo(mod)
                monkeys[to].items.add(item)
            }
        }
    }

    monkeys.sortByDescending { it.count }

    println(monkeys[0].count * monkeys[1].count)
}