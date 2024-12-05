fun main() {
    val l1 = ArrayList<Int>()
    val l2 = HashMap<Int, Int>()

    while (true) {
        val (i1, i2) = readlnOrNull()?.split("   ") ?: break
        l1.add(i1.toInt())
        l2[i2.toInt()] = l2.getOrDefault(i2.toInt(), 0) + 1
    }

    var sum = 0

    for (i1 in l1) {
        val i2 = l2[i1] ?: 0
        sum += i2 * i1
    }

    println(sum)
}