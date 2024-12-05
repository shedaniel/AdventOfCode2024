import java.io.File

fun main() {
    val graph = File("src/Dec4.txt").readLines().map { it.toCharArray() }

    var matches = 0
    val toMatch = "XMAS"
    val reversed = toMatch.reversed()

    // hor
    for (i in graph.indices) {
        for (j in 0..graph[0].size - toMatch.length) {
            val row = CharArray(toMatch.length) { graph[i][j + it] }
            if (row.joinToString("") in listOf(toMatch, reversed)) {
                matches++
            }
        }
    }

    // ver
    for (i in 0..graph.size - toMatch.length) {
        for (j in graph[0].indices) {
            val col = CharArray(toMatch.length) { graph[i + it][j] }
            if (col.joinToString("") in listOf(toMatch, reversed)) {
                matches++
            }
        }
    }

    // dia
    for (i in 0..graph.size - toMatch.length) {
        for (j in 0..graph[0].size - toMatch.length) {
            val diag = CharArray(toMatch.length) { graph[i + it][j + it] }
            if (diag.joinToString("") in listOf(toMatch, reversed)) {
                matches++
            }
        }
    }

    // anti-dia
    for (i in 0..graph.size - toMatch.length) {
        for (j in toMatch.length - 1 until graph[0].size) {
            val diag = CharArray(toMatch.length) { graph[i + it][j - it] }
            if (diag.joinToString("") in listOf(toMatch, reversed)) {
                matches++
            }
        }
    }

    println(matches)
}