import java.io.File

fun main() {
    fun canCalibrate(curr: Long, result: Long, numList: List<Long>): Boolean {
        val first = numList.firstOrNull() ?: return curr == result
        val withAdd = canCalibrate(curr + first, result, numList.subList(1, numList.size))
        val withMul = canCalibrate(curr * first, result, numList.subList(1, numList.size))
        return withAdd || withMul
    }

    val lines = File("src/Dec7.txt").readLines()
    var sum = 0L
    lines.forEach { line ->
        val (resultStr, nums) = line.split(": ")
        val result = resultStr.toLong()
        val numList = nums.split(" ").map { it.toLong() }

        if (canCalibrate(0L, result, numList)) {
            println(line)
            sum += result
        }
    }

    println(sum)
}