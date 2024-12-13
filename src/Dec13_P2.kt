import java.io.File

fun main() {
    fun calc(buttonA: P<Long, Long>, buttonB: P<Long, Long>, prize: P<Long, Long>): Long? {
        val det = buttonA.first * buttonB.second - buttonA.second * buttonB.first
        if (det == 0L) return null

        val bTries = (buttonA.first * prize.second - buttonA.second * prize.first).toDouble() / det.toDouble()
        if (bTries % 1 != 0.0) return null
        val bTriesL = bTries.toLong()

        val aTries = (prize.first - buttonB.first * bTriesL).toDouble() / buttonA.first.toDouble()
        if (aTries % 1 != 0.0) return null
        val aTriesL = aTries.toLong()

        var min = aTriesL * 3 + bTriesL

        for (k in -100..100) { // bruce force lol
            val aAdjusted = aTriesL - k * (buttonA.second / det)
            val bAdjusted = bTriesL + k * (buttonB.second / det)

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