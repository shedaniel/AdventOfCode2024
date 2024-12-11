import java.io.File

fun main() {
    val stones = File("src/Dec11.txt").readText().split(" ").map { it.toLong() }.toMutableList()
    val cache = mutableMapOf<Pair<Long, Int>, Long>()

    fun run(stone: Long, times: Int): Long {
        if (times == 0) return 1L
        val key = Pair(stone, times)
        if (key in cache) return cache[key]!!

        val result = if (stone == 0L) {
            return run(1L, times - 1)
        } else if (stone.toString().length % 2 == 0) {
            val str = stone.toString()
            run(str.substring(0, str.length / 2).toLong(), times - 1) + run(str.substring(str.length / 2).toLong(), times - 1)
        } else {
            run(stone * 2024L, times - 1)
        }

        cache[key] = result
        return result
    }

    val sum = stones.map { run(it, 75) }.sumOf { it }

    println(sum)
}