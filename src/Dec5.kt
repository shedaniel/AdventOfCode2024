import java.io.File

fun main() {
    val lines = File("src/Dec5.txt").readLines()
    val order = lines.filter { it.contains('|') }.map { it.split("|").map { it.toInt() } }

    val mustBefore = mutableMapOf<Int, MutableSet<Int>>()
    order.forEach { (a, b) ->
        mustBefore.getOrPut(b, ::mutableSetOf).add(a)
    }

    val cmp = Comparator<Int> { a, b ->
        if (a in (mustBefore[b] ?: emptySet())) {
            -1
        } else if (b in (mustBefore[a] ?: emptySet())) {
            1
        } else {
            0
        }
    }

    val sum = lines.filter { it.contains(',') }
        .map { it.split(",").map(String::toInt) }
        .filter { it == it.sortedWith(cmp) }
        .sumOf { it[it.size / 2] }

    println(sum)
}