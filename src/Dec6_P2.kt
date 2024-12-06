import java.io.File
import java.util.concurrent.CompletableFuture

fun isStuck(lines: List<CharArray>, ix: Int, iy: Int, idir: Int): Boolean {
    var x = ix
    var y = iy
    var dir = idir
    val visited = mutableSetOf<Pair<Int, Int>>()
    val visitedWithDir = mutableSetOf<Triple<Int, Int, Int>>()

    while (true) {
        val dx = if (dir == 1) 1 else if (dir == 3) -1 else 0
        val dy = if (dir == 0) -1 else if (dir == 2) 1 else 0

        if (y + dy !in lines.indices || x + dx !in lines.indices) {
            return false
        }
        if (lines[y + dy][x + dx] == '#') {
            dir = (dir + 1) % 4
        } else if (visitedWithDir.contains(Triple(x + dx, y + dy, dir))) {
            return true
        } else {
            x += dx
            y += dy
            visited.add(x to y)
            visitedWithDir.add(Triple(x, y, dir))
        }
    }
}

fun main() {
    val lines = File("src/Dec6.txt").readLines().map { it.toCharArray() }
    var guardX = 0
    var guardY = 0

    for (y in lines.indices) {
        for (x in lines.indices) {
            if (lines[y][x] == '^') {
                guardX = x
                guardY = y
                break
            }
        }
    }

    var dir = 0
    // 0 up, 1 right, 2 down, 3 left

    println(isStuck(lines, guardX, guardY, dir))

    var sum = 0
    val total = lines.size * lines.size
    var done = 0

    for (yy in lines.indices) {
        for (xx in lines.indices) {
            val num = done++
            if (lines[yy][xx] == '.') {
                val copy = lines.map { it.copyOf() }
                copy[yy][xx] = '#'
                CompletableFuture.runAsync {
                    if (isStuck(copy, guardX, guardY, dir)) {
                        sum++
                    }
                    println("${num}/$total")
                }.join()
            }
        }
    }
    println(sum)
}