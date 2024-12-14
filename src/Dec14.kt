import java.io.File

fun main() {
    data class Robot(var coord: Coord, val velocity: Coord)

    val text = File("src/Dec14.txt").readText()
    val regex = """p=(-?\d+),(-?\d+) v=(-?\d+),(-?\d+)""".toRegex()

    val frame = 101 to 103

    val robots = mutableListOf<Robot>()

    regex.findAll(text).forEach { result ->
        val p = result.groupValues[1].toInt() to result.groupValues[2].toInt()
        val v = result.groupValues[3].toInt() to result.groupValues[4].toInt()
        robots.add(Robot(p, v))
    }

    (0..<frame.y).forEach { y ->
        (0..<frame.x).forEach { x ->
            print(robots.count { it.coord.x == x && it.coord.y == y }.takeIf { it > 0 } ?: ".")
        }
        println()
    }

    println()

    for (i in 0..<100) {
        robots.forEach { robot ->
            val newCoord = (robot.coord + robot.velocity + frame) % frame
            robot.coord = newCoord
        }
    }

    (0..<frame.y).forEach { y ->
        (0..<frame.x).forEach { x ->
            print(robots.count { it.coord.x == x && it.coord.y == y }.takeIf { it > 0 } ?: ".")
        }
        println()
    }

    println()

    val s1 = (0..<frame.y / 2).sumOf { y ->
        (0..<frame.x / 2).sumOf { x ->
            robots.count { it.coord.x == x && it.coord.y == y }
        }
    }
    val s2 = (0..<frame.y / 2).sumOf { y ->
        (frame.x / 2 + 1..<frame.x).sumOf { x ->
            robots.count { it.coord.x == x && it.coord.y == y }
        }
    }
    val s3 = (frame.y / 2 + 1..<frame.y).sumOf { y ->
        (0..<frame.x / 2).sumOf { x ->
            robots.count { it.coord.x == x && it.coord.y == y }
        }
    }
    val s4 = (frame.y / 2 + 1..<frame.y).sumOf { y ->
        (frame.x / 2 + 1..<frame.x).sumOf { x ->
            robots.count { it.coord.x == x && it.coord.y == y }
        }
    }

    println(s1 * s2 * s3 * s4)
}