package day6

import utils.readDatFile

fun main() {
    val signal = "day6/input.dat".readDatFile().readText()
    val numberOfChunk = 4

    for (j in numberOfChunk - 1..signal.length) {
        if (signal.substring(j - (numberOfChunk-1)..j).toSet().size == numberOfChunk) {
            println(j + 1)
            break
        }
    }

    println(signal.toList().windowed(numberOfChunk).indexOfFirst { it.toSet().size == numberOfChunk } + numberOfChunk)
}