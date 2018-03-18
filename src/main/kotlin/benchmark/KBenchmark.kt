package benchmark

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.annotations.Mode.AverageTime
import java.util.concurrent.TimeUnit.NANOSECONDS


@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(AverageTime)
@OutputTimeUnit(NANOSECONDS)

// mvn clean install && java -jar target/benchmarks.jar

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