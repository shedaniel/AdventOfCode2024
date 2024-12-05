import java.io.File

fun main() {
    val graph = File("src/Dec4.txt").readLines().map { it.toCharArray() }

    var matches = 0
    val toMatch = "MAS"
    val reversed = toMatch.reversed()

    for (i in 1 .. graph.size - 2) {
        for (j in 1.. graph[0].size - 2) {
            val diag = CharArray(toMatch.length) { graph[i + it - 1][j + it - 1] }
            val diagOther = CharArray(toMatch.length) { graph[i + 1 - it][j + it - 1] }
            if (diag.joinToString("") in listOf(toMatch, reversed) && diagOther.joinToString("") in listOf(toMatch, reversed)) {
                matches++
            }
        }
    }

    println(matches)
}