import java.io.File

fun main() {
    fun calc(buttonA: LCoord, buttonB: LCoord, prize: LCoord): Long? {
        val det = buttonA.x * buttonB.y - buttonA.y * buttonB.x
        if (det == 0L) return null

        val bTries = (buttonA.x * prize.y - buttonA.y * prize.x).toDouble() / det.toDouble()
        if (bTries % 1 != 0.0) return null
        val bTriesL = bTries.toLong()

        val aTries = (prize.x - buttonB.x * bTriesL).toDouble() / buttonA.x.toDouble()
        if (aTries % 1 != 0.0) return null
        val aTriesL = aTries.toLong()

        var min = aTriesL * 3 + bTriesL

        for (k in -100..100) { // bruce force lol
            val aAdjusted = aTriesL - k * (buttonA.y / det)
            val bAdjusted = bTriesL + k * (buttonB.y / det)

            if (bAdjusted >= 0 && aAdjusted >= 0) {
                val currentCost = aAdjusted * 3 + bAdjusted
                println("Cost for ($aAdjusted, $bAdjusted) = $currentCost")
                min = minOf(min, currentCost)
            }
        }

        return min
    }

    val text = File("src/Dec13.txt").readText()
    val regex = """Button A: X\+(\d+), Y\+(\d+)\nButton B: X\+(\d+), Y\+(\d+)\nPrize: X=(\d+), Y=(\d+)""".toRegex()

    var sum = 0L

    regex.findAll(text).forEach { result ->
        val buttonA = result.groupValues[1].toLong() to result.groupValues[2].toLong()
        val buttonB = result.groupValues[3].toLong() to result.groupValues[4].toLong()
        val prize = result.groupValues[5].toLong() + 10000000000000 to result.groupValues[6].toLong() + 10000000000000

        val min = calc(buttonA, buttonB, prize)

        println("Cost: $min")
        if (min != null) sum += min
    }

    println(sum)
}