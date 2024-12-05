fun main() {
    var sum = 0

    while (true) {
        val nums = readlnOrNull()?.takeIf { it.isNotBlank() }?.split(" ")?.map { it.toInt() } ?: break
        if (safe(nums)) sum++
        else {
            if (nums.indices
                    .any {
                        val copy = ArrayList(nums)
                        copy.removeAt(it)
                        safe(copy)
                    }
            ) {
                sum++
            }
        }
    }

    println(sum)
}