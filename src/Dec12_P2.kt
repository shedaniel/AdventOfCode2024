import java.io.File

fun main() {
    val grid = File("src/Dec12.txt").readLines().map { it.toCharArray() }
    val chars = grid.flatMapTo(mutableSetOf(), CharArray::toSet).toSet()
    var sum = 0

    chars.forEach { char ->
        val visited = mutableSetOf<Coord>()

        fun walkFrom(set: MutableSet<Coord>, coord: Coord) {
            set += coord
            for (offset in listOf(0 to -1, 0 to 1, 1 to 0, -1 to 0)) {
                val newCoord = coord + offset
                if (newCoord in grid && grid[newCoord] == char && set.add(newCoord)) {
                    walkFrom(set, newCoord)
                }
            }
        }

        grid.forEachMap { coord, c ->
            if (c == char && visited.add(coord)) {
                val all = mutableSetOf<Coord>()
                walkFrom(all, coord)
                visited += all
                var corners = 0

                all.forEach { newCoord ->
                    fun has(vararg none: Coord, has: List<Coord> = listOf()): Boolean {
                        return none.all {
                            val offsetCoord = newCoord + it
                            offsetCoord !in grid || grid[offsetCoord] != char
                        } && has.all {
                            val offsetCoord = newCoord + it
                            offsetCoord in grid && grid[offsetCoord] == char
                        }
                    }

                    var count = 0
                    // outer corners
                    if (has(0 to -1, -1 to 0)) count++
                    if (has(0 to -1, 1 to 0)) count++
                    if (has(0 to 1, -1 to 0)) count++
                    if (has(0 to 1, 1 to 0)) count++
                    // inner corners
                    if (has(1 to 1, has = listOf(0 to 1, 1 to 0))) count++
                    if (has(1 to -1, has = listOf(0 to -1, 1 to 0))) count++
                    if (has(-1 to -1, has = listOf(0 to -1, -1 to 0))) count++
                    if (has(-1 to 1, has = listOf(0 to 1, -1 to 0))) count++

                    if (count > 0) {
                        corners += count
                        println("$newCoord has $count corners")
                    }
                }

                sum += all.size * corners
                println("Area with $char: area = ${all.size}, corners = $corners")
            }
        }

    }

    println(sum)
}