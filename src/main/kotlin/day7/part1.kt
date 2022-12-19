package day7

import utils.readDatFile

data class Directory(
    val subDir: ArrayList<Directory> = arrayListOf(),
    var size: Int = 0,
    var cacheSize: Int? = null
) {
    fun getDirSize(): Int {
        if (cacheSize == null) {
            cacheSize = size + subDir.sumOf { it.getDirSize() }
            return cacheSize!!
        } else {
            return cacheSize!!
        }
    }
}

    fun main() {
        var currDir = Directory()
        val addresses = HashMap<String, Directory>()
        addresses["/"] = Directory()

        "day7/input.dat".readDatFile().forEachLine {
            if (it != "$ ls" && it != "$ cd ..") {
                val cmd = it.split(" ")

                if (cmd[0] == "$" && cmd[1] == "cd") {
                    currDir = addresses[cmd[2]]!!
                } else {
                    if (cmd[0] == "dir") {
                        val newDir = Directory()
                        currDir.subDir.add(newDir)
                        addresses[cmd[1]] = newDir
                    } else {
                        currDir.size += cmd[0].toInt()
                    }
                }
            }
        }

        println(addresses
            .values
            .filter { it.getDirSize() < 100000 }
            .sumOf { it.getDirSize() })

        val leftSize = 70000000 - addresses["/"]!!.getDirSize()
        println(leftSize)
        println(addresses
            .values
            .filter { it.getDirSize() + leftSize >= 30000000 }
            .minOf { it.getDirSize() }
        )
    }

/*
1306611
13210366
 */