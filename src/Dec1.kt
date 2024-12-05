import java.util.*
import kotlin.math.abs

fun main() {
    val l1 = PriorityQueue<Int>()
    val l2 = PriorityQueue<Int>()

    while (true) {
        val (i1, i2) = readlnOrNull()?.split("   ") ?: break
        l1.add(i1.toInt())
        l2.add(i2.toInt())
    }

    var sum = 0

    while (l1.isNotEmpty() && l2.isNotEmpty()) {
        val i1 = l1.poll()
        val i2 = l2.poll()
        sum += abs(i1 - i2)
    }

    println(sum)
}