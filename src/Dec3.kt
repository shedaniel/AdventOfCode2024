import java.io.File

fun main() {
    val regex = """mul\((\d+),(\d+)\)""".toRegex()
    val text = File("src/Dec3.txt").readText()
    val matches = regex.findAll(text)
    val result = matches.sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    println(result)
}