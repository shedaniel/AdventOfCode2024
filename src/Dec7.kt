import java.io.File
import java.math.BigInteger

fun main() {
    fun canCalibrate(curr: BigInteger, result: BigInteger, numList: List<BigInteger>): Boolean {
        if (numList.isEmpty()) return curr == result
        val withAdd = canCalibrate(curr + numList[0], result, numList.drop(1).toList())
        val withMul = canCalibrate(curr * numList[0], result, numList.drop(1).toList())
        return withAdd || withMul
    }

    val lines = File("src/Dec7.txt").readLines()
    var sum = BigInteger.ZERO
    lines.forEach { line ->
        val (resultStr, nums) = line.split(": ")
        val result = resultStr.toBigInteger()
        val numList = nums.split(" ").map { it.toBigInteger() }

        if (canCalibrate(BigInteger.ZERO, result, numList)) {
            println(line)
            sum += result
        }
    }

    println(sum)
}