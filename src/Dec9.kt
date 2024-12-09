import java.io.File

fun main() {
    val map = File("src/Dec9.txt").readText()
    val arr = mutableListOf<Int>()
    var requiredSize = 0
    map.forEachIndexed { index, c ->
        if (index % 2 == 0) {
            for (i in 0..<c.digitToInt()) {
                arr += index / 2
            }
            requiredSize += c.digitToInt()
        } else {
            for (i in 0..<c.digitToInt()) {
                arr += -1
            }
        }
    }
    for (i in 0..<(arr.size - requiredSize)) {
        val j = arr.indexOfLast { it != -1 }
        val tmp = arr[j]
        arr[j] = -1
        arr[arr.indexOfFirst { it == -1 }] = tmp
    }
    println(arr.joinToString("") { if (it == -1) "." else it.toString() })
    println(arr.mapIndexed { index, i -> i.toLong() * index.toLong() }.filterNot { it < 0 }.sum())
}