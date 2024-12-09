import java.io.File

sealed interface Block {
    val size: Int
}

data class FreeBlock(override val size: Int): Block
data class UsedBlock(override val size: Int, val type: Int): Block

fun main() {
    val map = File("src/Dec9.txt").readText()
    val blocks = mutableListOf<Block>()
    var requiredSize = 0
    map.forEachIndexed { index, c ->
        if (index % 2 == 0) {
            blocks += UsedBlock(c.digitToInt(), index / 2)
            requiredSize += c.digitToInt()
        } else {
            blocks += FreeBlock(c.digitToInt())
        }
    }
    val visited = mutableSetOf<UsedBlock>()
    for (i in 0..<blocks.size) {
        val j = blocks.indexOfLast { it is UsedBlock && it !in visited }
        if (j == -1) continue
        val tmp = blocks[j] as UsedBlock
        visited += tmp
        blocks[j] = FreeBlock(tmp.size)
        val toPlace = blocks.indexOfFirst { it is FreeBlock && it.size >= tmp.size }
        if (toPlace == -1) continue
        val tmpToPlace = blocks[toPlace].size
        blocks[toPlace] = tmp
        if (tmpToPlace - tmp.size > 0) {
            blocks.add(toPlace + 1, FreeBlock(tmpToPlace - tmp.size))
        }
    }
    val arr = mutableListOf<Int>()
    blocks.forEach {
        if (it is FreeBlock) {
            for (i in 0..<it.size) {
                arr += -1
            }
        } else if (it is UsedBlock) {
            for (i in 0..<it.size) {
                arr += it.type
            }
        }
    }
    println(arr.joinToString("") { if (it == -1) "." else it.toString() })
    println(arr.mapIndexed { index, i -> i.toLong() * index.toLong() }.filterNot { it < 0 }.sum())
}