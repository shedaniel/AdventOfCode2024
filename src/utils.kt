typealias P<A, B> = Pair<A, B>
typealias Coord = P<Int, Int>
typealias LCoord = P<Long, Long>

val Coord.x: Int
    get() = this.first

val Coord.y: Int
    get() = this.second

val LCoord.x: Long
    get() = this.first

val LCoord.y: Long
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

operator fun Coord.rem(scalar: Int): Coord {
    return Coord(this.x % scalar, this.y % scalar)
}

operator fun Coord.rem(coord: Coord): Coord {
    return Coord(this.x % coord.x, this.y % coord.y)
}

operator fun Coord.unaryMinus(): Coord {
    return Coord(-this.x, -this.y)
}

@JvmName("LCoordPlusLCoord")
operator fun LCoord.plus(coord: LCoord): LCoord {
    return LCoord(this.x + coord.x, this.y + coord.y)
}

@JvmName("LCoordMinusLCoord")
operator fun LCoord.minus(coord: LCoord): LCoord {
    return LCoord(this.x - coord.x, this.y - coord.y)
}

@JvmName("LCoordTimesLong")
operator fun LCoord.times(scalar: Long): LCoord {
    return LCoord(this.x * scalar, this.y * scalar)
}

@JvmName("LCoordDivLong")
operator fun LCoord.div(scalar: Long): LCoord {
    return LCoord(this.x / scalar, this.y / scalar)
}

@JvmName("LCoordRemLong")
operator fun LCoord.rem(scalar: Long): LCoord {
    return LCoord(this.x % scalar, this.y % scalar)
}

@JvmName("LCoordRemLCoord")
operator fun LCoord.rem(coord: LCoord): LCoord {
    return LCoord(this.x % coord.x, this.y % coord.y)
}

@JvmName("LCoordUnaryMinus")
operator fun LCoord.unaryMinus(): LCoord {
    return LCoord(-this.x, -this.y)
}

@JvmName("LCoordPlusCoord")
operator fun LCoord.plus(coord: Coord): LCoord {
    return this + coord.toLCoord()
}

@JvmName("LCoordMinusCoord")
operator fun LCoord.minus(coord: Coord): LCoord {
    return this - coord.toLCoord()
}

fun Coord.toLCoord(): LCoord {
    return this.x.toLong() to this.y.toLong()
}

fun LCoord.toCoord(): Coord {
    return this.first.toInt() to this.second.toInt()
}

val List<Array<Any>>.width: Int
    @JvmName("ListArrayWidth")
    get() = get(0).size

val List<Array<Any>>.height: Int
    @JvmName("ListArrayHeight")
    get() = size

val List<List<Any>>.width: Int
    @JvmName("ListListWidth")
    get() = get(0).size

val List<List<Any>>.height: Int
    @JvmName("ListListHeight")
    get() = size

val List<CharArray>.width: Int
    @JvmName("ListCharArrayWidth")
    get() = get(0).size

val List<CharArray>.height: Int
    @JvmName("ListCharArrayHeight")
    get() = size

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