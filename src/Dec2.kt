import kotlin.math.abs

fun safe(nums: List<Int>): Boolean {
    val increasing = nums[1] > nums[0]
    for (i in nums.indices.toList().dropLast(1)) {
        if (nums[i + 1] > nums[i] && !increasing) {
            return false
        }
        if (nums[i + 1] < nums[i] && increasing) {
            return false
        }
        if (1 > abs(nums[i + 1] - nums[i]) || abs(nums[i + 1] - nums[i]) > 3) {
            return false
        }
    }

    return true
}

fun main() {
    var sum = 0

    while (true) {
        val nums = readlnOrNull()?.takeIf { it.isNotBlank() }?.split(" ")?.map { it.toInt() } ?: break
        if (safe(nums)) sum++
    }

    println(sum)
}