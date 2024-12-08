import java.io.File

fun main() {
    val map = File("src/Dec8.txt").readLines().map { it.toCharArray() }
    val towers = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
    val anti = mutableSetOf<Pair<Int, Int>>()
    map.forEachIndexed { y, chars ->
        chars.forEachIndexed { x, c ->
            if (c != '.') {
                towers.getOrPut(c, ::mutableListOf) += x to y
            }
        }
    }

    for (ts in towers.values) {
        ts.forEach { t1 ->
            ts.filterNot { it == t1 }.forEach { t2 ->
                val new = (t1.first - (t2.first - t1.first)) to (t1.second - (t2.second - t1.second))
                val new2 = (t2.first + (t2.first - t1.first)) to (t2.second + (t2.second - t1.second))
                if (new.first in map[0].indices && new.second in map.indices) {
                    anti += new
                }

                if (new2.first in map[0].indices && new2.second in map.indices) {
                    anti += new2
                }
            }
        }
    }

    map.forEachIndexed { y, chars ->
        chars.forEachIndexed { x, c ->
            if (x to y in anti) {
                print('#')
            } else {
                print(c)
            }
        }
        println()
    }

    println()
    println(anti.size)
}