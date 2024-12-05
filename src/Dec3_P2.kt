import java.io.File

fun main() {
    val regex = """mul\((\d+),(\d+)\)|do\(\)|don't\(\)""".toRegex()
    val text = File("src/Dec3.txt").readText()
    val matches = regex.findAll(text)
    var result = 0
    var doIt = true
    matches.forEach {
        if (it.value == "do()") {
            doIt = true
        } else if (it.value == "don't()") {
            doIt = false
        } else if (doIt) {
            result += it.groupValues[1].toInt() * it.groupValues[2].toInt()
        }
    }
    println(result)
}