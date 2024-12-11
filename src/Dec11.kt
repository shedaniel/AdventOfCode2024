import java.io.File

fun main() {
    val stones = File("src/Dec11.txt").readText().split(" ").map { it.toLong() }.toMutableList()

    fun run(stone: Long, times: Int): Int {
        if (times == 0) return 1
        if (stone == 0L) return run(1L, times - 1)
        if (stone.toString().length % 2 == 0) {
            val str = stone.toString()
            return run(str.substring(0, str.length / 2).toLong(), times - 1) + run(str.substring(str.length / 2).toLong(), times - 1)
        }
        return run(stone * 2024L, times - 1)
    }

    val sum = stones.map { run(it, 25) }.sumOf { it }

    println(sum)
}