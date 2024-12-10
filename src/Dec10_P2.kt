import java.io.File

fun main() {
    val map = File("src/Dec10.txt").readLines().map { it.toCharArray() }

    fun walkFrom(original: Coord, coord: Coord, visited: MutableSet<Coord>): List<Pair<Coord, Coord>> {
        return listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1).flatMap { delta ->
            val new = coord + delta
            if (new !in visited && new in map && (map[coord].digitToInt() + 1).digitToChar() == map[new]) {
                if (map[new] == '9') return@flatMap listOf(original to new)
                val copy = visited.toMutableSet()
                copy += new
                return@flatMap walkFrom(original, new, copy)
            } else {
                return@flatMap emptyList()
            }
        }
    }

    val sum = mutableListOf<Pair<Coord, Coord>>()
    map.forEachMap { coord, c ->
        if (c == '0') {
            sum += walkFrom(coord, coord, mutableSetOf())
        }
    }
    println(sum)
    println(sum.size)
}