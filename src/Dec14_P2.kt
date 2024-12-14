import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    data class Robot(var coord: Coord, val velocity: Coord)

    fun write(robots: List<Robot>, frame: Coord, i: Int) {
        val image = BufferedImage(frame.x, frame.y, BufferedImage.TYPE_INT_ARGB)

        (0..<frame.y).forEach { y ->
            (0..<frame.x).forEach { x ->
                image.setRGB(x, y, if (robots.any { it.coord.x == x && it.coord.y == y }) -1 else 0xFF000000.toInt())
            }
        }

        val file = File("output/Dec14/frame$i.png")
        file.parentFile.mkdirs()
        ImageIO.write(image, "png", file)
    }

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

    for (i in 0..<10000) {
        robots.forEach { robot ->
            val newCoord = (robot.coord + robot.velocity + frame) % frame
            robot.coord = newCoord
        }

        if ((i - 570) % 101 == 0)
            write(robots, frame, i)
    }
}
