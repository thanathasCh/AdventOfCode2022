package day8

import utils.readDatFile

fun calculateDistance(curr: Char, row: String): Int {
    return row.indexOfFirst { it >= curr }.let {
        if (it == -1) {
            row.length
        } else {
            it + 1
        }
    }
}

fun main() {
    val forest = "day8/input.dat".readDatFile().readLines()
    val height = forest.size
    val width = forest[0].length
    var ans1 = (height * 2) + ((width - 2) * 2)
    val ans2 = arrayListOf<Int>()

    val row = MutableList(height) { "" }
    val col = MutableList(width) { "" }

    for (i in 0 until height) {
        row[i] += forest[i]

        for (j in col.indices) {
            col[j] += forest[i][j].toString()
        }
    }

    for (i in 1 until height - 1) {
        for (j in 1 until width - 1) {
            val curr = forest[i][j]
            val up = col[j].substring(0, i)
            val down = col[j].substring(i + 1)
            val left = row[i].substring(0, j)
            val right = row[i].substring(j + 1)

            if (
                up.max() < curr ||
                down.max() < curr ||
                left.max() < curr ||
                right.max() < curr
            ) {
                ans1++
            }

            ans2.add(
                calculateDistance(curr, up.reversed()) *
                        calculateDistance(curr, down) *
                        calculateDistance(curr, left.reversed()) *
                        calculateDistance(curr, right)
            )
        }
    }

    println(ans1)
    println(ans2.max())

}