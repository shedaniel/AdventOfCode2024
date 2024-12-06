import java.io.File

fun main() {
    val lines = File("src/Dec6.txt").readLines().map { it.toCharArray() }
    var x = 0
    var y = 0

    for (yy in lines.indices) {
        for (xx in lines.indices) {
            if (lines[yy][xx] == '^') {
                x = xx
                y = yy
                break
            }
        }
    }

    var dir = 0
    // 0 up, 1 right, 2 down, 3 left

    val visited = mutableSetOf<Pair<Int, Int>>()

    while (true) {
        val dx = if (dir == 1) 1 else if (dir == 3) -1 else 0
        val dy = if (dir == 0) -1 else if (dir == 2) 1 else 0

        if (y + dy !in lines.indices || x + dx !in lines.indices) {
            println(visited.size)
            break
        }
        if (lines[y + dy][x + dx] == '#') {
            dir = (dir + 1) % 4
        } else {
            x += dx
            y += dy
            visited.add(x to y)
        }
    }
}