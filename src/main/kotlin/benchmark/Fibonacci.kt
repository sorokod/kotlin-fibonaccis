package benchmark

// https://blog.des.io/posts/2018-03-08-fibonacci.html

fun fibNaive(n: Int): Int =
        when (n) {
            0, 1 -> n
            else -> fibNaive(n - 1) + fibNaive(n - 2)
        }


fun fibCached(n: Int): Int {

    fun fibCachedHelper(n: Int, cache: MutableMap<Int, Int>): Int =

            cache.getOrPut(n) {
                val a = cache.getOrPut(n - 2) {
                    fibCachedHelper(n - 2, cache)
                }
                val b = cache.getOrPut(n - 1) {
                    fibCachedHelper(n - 1, cache)
                }
                a + b
            }

    return fibCachedHelper(n, mutableMapOf(Pair(0, 0), Pair(1, 1)))
}


fun T(p: Pair<Int, Int>): Pair<Int, Int> = Pair(p.first + p.second, p.first)


fun fibVecSum(n: Int): Int {

    fun fibVec(n: Int): Pair<Int, Int> =
            when (n) {
                1 -> Pair(1, 0)
                else -> T(fibVec(n - 1))
            }


    return when (n) {
        0, 1 -> n

        else -> {
            val (a, b) = fibVec(n - 1)
            a + b
        }
    }
}


fun fibVecSumKotlin(n: Int): Int {

    fun genPairSequence(): Sequence<Pair<Int, Int>> =
            generateSequence(Pair(1, 0), { T(it) })

    return genPairSequence().take(n + 1).last().second
}


fun fibTailVecSum(n: Int): Int {

    tailrec fun fibTailVec(acc: Int, a: Int, b: Int): Pair<Int, Int> =
            when (acc) {
                1 -> Pair(a, b)

                else -> fibTailVec(acc - 1, a + b, a)
            }


    return when (n) {
        0, 1 -> n

        else -> {
            val (a, b) = fibTailVec(n - 1, 1, 0)
            a + b
        }
    }
}


fun fibIterative(n: Int): Int {
    if (n < 2) {
        return n
    }

    var acc = n
    var a = 1
    var b = 0
    var tmp = 0

    while (acc > 2) {
        acc--
        tmp = a
        a += b
        b = tmp
    }

    return a + b
}


fun fibIterativeTabulated(n: Int): Int {
    val lookup: Array<Int> = Array(n + 1, { 0 })
    lookup[0] = 0
    lookup[1] = 1

    (2..n).forEach { i -> lookup[i] = lookup[i - 1] + lookup[i - 2] }
    return lookup[n]
}


fun main(args: Array<String>) {

    println("fibNaive(12) = ${fibNaive(12)}")
    println("fibNaive(40) = ${fibNaive(40)}")

    println("fibCached(12) = ${fibCached(12)}")
    println("fibCached(40) = ${fibCached(40)}")

    println("fibVecSum(12) = ${fibVecSum(12)}")
    println("fibVecSum(40) = ${fibVecSum(40)}")

    println("fibVecSumKotlin(12) = ${fibVecSumKotlin(12)}")
    println("fibVecSumKotlin(40) = ${fibVecSumKotlin(40)}")


    println("fibTailVecSum(12) = ${fibTailVecSum(12)}")
    println("fibTailVecSum(40) = ${fibTailVecSum(40)}")

    println("fibIterative(12) = ${fibIterative(12)}")
    println("fibIterative(40) = ${fibIterative(40)}")

    println("fibIterativeTabulated(12) = ${fibIterativeTabulated(12)}")
    println("fibIterativeTabulated(40) = ${fibIterativeTabulated(40)}")
}
