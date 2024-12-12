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
                var perm = 0
                all.forEach { newCoord ->
                    for (offset in listOf(0 to -1, 0 to 1, 1 to 0, -1 to 0)) {
                        val offsetCoord = newCoord + offset

                        if (offsetCoord in grid && grid[offsetCoord] == char) continue
                        perm++
                    }
                }
                sum += all.size * perm
                println("Area with $char: area = ${all.size}, perm = $perm")
            }
        }

    }

    println(sum)
}