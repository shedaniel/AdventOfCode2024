import java.io.File
import kotlin.math.max

fun main() {
    fun calc(buttonA: Coord, buttonB: Coord, prize: Coord): Int? {
        val max = max(prize.x / buttonA.x + 1, prize.y / buttonA.y + 1)
        var minTokens: Int? = null

        for (aTries in 0..max) {
            for (bTries in 0..max) {
                val totalX = aTries * buttonA.x + bTries * buttonB.x
                val totalY = aTries * buttonA.y + bTries * buttonB.y

                if (totalX == prize.x && totalY == prize.y) {
                    val tokens = aTries * 3 + bTries
                    println("Cost for ($aTries, $bTries) = $tokens")
                    minTokens = if (minTokens == null) tokens else minOf(minTokens, tokens)
                }
            }
        }

        return minTokens
    }

    val text = File("src/Dec13.txt").readText()
    val regex = """Button A: X\+(\d+), Y\+(\d+)\nButton B: X\+(\d+), Y\+(\d+)\nPrize: X=(\d+), Y=(\d+)""".toRegex()

    var sum = 0

    regex.findAll(text).forEach { result ->
        val buttonA = result.groupValues[1].toInt() to result.groupValues[2].toInt()
        val buttonB = result.groupValues[3].toInt() to result.groupValues[4].toInt()
        val prize = result.groupValues[5].toInt() to result.groupValues[6].toInt()

        val min = calc(buttonA, buttonB, prize)

        println("Cost: $min")
        if (min != null) sum += min
    }

    println(sum)
}