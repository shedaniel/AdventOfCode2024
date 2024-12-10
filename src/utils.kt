typealias P<A, B> = Pair<A, B>
typealias Coord = P<Int, Int>

val Coord.x: Int
    get() = this.first

val Coord.y: Int
    get() = this.second

operator fun Coord.plus(coord: Coord): Coord {
    return Coord(this.x + coord.x, this.y + coord.y)
}

operator fun Coord.minus(coord: Coord): Coord {
    return Coord(this.x - coord.x, this.y - coord.y)
}

operator fun Coord.times(scalar: Int): Coord {
    return Coord(this.x * scalar, this.y * scalar)
}

operator fun Coord.div(scalar: Int): Coord {
    return Coord(this.x / scalar, this.y / scalar)
}

@JvmName("ListArrayContains")
operator fun <T> List<Array<out T>>.contains(coord: Coord): Boolean {
    return coord.y in this.indices && coord.x in this[coord.y].indices
}

@JvmName("ListCharArrayContains")
operator fun List<CharArray>.contains(coord: Coord): Boolean {
    return coord.y in this.indices && coord.x in this[coord.y].indices
}

@JvmName("ListListContains")
operator fun <T> List<List<T>>.contains(coord: Coord): Boolean {
    return coord.y in this.indices && coord.x in this[coord.y].indices
}

@JvmName("ListArrayGet")
operator fun <T> List<Array<out T>>.get(coord: Coord): T {
    return this[coord.y][coord.x]
}

operator fun List<CharArray>.get(coord: Coord): Char {
    return this[coord.y][coord.x]
}

@JvmName("ListListGet")
operator fun <T> List<List<T>>.get(coord: Coord): T {
    return this[coord.y][coord.x]
}

@JvmName("ListArrayForEach")
fun <T> List<Array<out T>>.forEachMap(action: (Coord, T) -> Unit) {
    for (y in this.indices) {
        for (x in this[y].indices) {
            action(x to y, this[y][x])
        }
    }
}

@JvmName("ListCharArrayForEach")
fun List<CharArray>.forEachMap(action: (Coord, Char) -> Unit) {
    for (y in this.indices) {
        for (x in this[y].indices) {
            action(x to y, this[y][x])
        }
    }
}

@JvmName("ListListForEach")
fun <T> List<List<T>>.forEachMap(action: (Coord, T) -> Unit) {
    for (y in this.indices) {
        for (x in this[y].indices) {
            action(x to y, this[y][x])
        }
    }
}