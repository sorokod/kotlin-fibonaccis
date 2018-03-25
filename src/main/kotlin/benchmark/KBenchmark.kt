package benchmark

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.annotations.Mode.AverageTime
import java.util.concurrent.TimeUnit.NANOSECONDS


@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 15)
@Measurement(iterations = 15)
@BenchmarkMode(AverageTime)
@OutputTimeUnit(NANOSECONDS)


open class KBenchmark {

    @Param("12", "40")
    var n = 0


    @Benchmark
    fun fibNaive() = fibNaive(n)


    @Benchmark
    fun fibCached() = fibCached(n)


    @Benchmark
    fun fibVecSum() = fibVecSum(n)


    @Benchmark
    fun fibTailVecSum() = fibTailVecSum(n)


    @Benchmark
    fun fibVecSumKotlin()  = fibVecSumKotlin(n)


    @Benchmark
    fun fibIterative() = fibIterative(n)


    @Benchmark
    fun fibIterativeTabulated() = fibIterativeTabulated(n)
}