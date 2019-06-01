package playground

import org.openjdk.jmh.annotations._

@State(Scope.Benchmark)
class StringLikeBenchmark {
  @Benchmark
  def testScalaTimesAllocation() = {
    for(i <- 0 to 1) {
      "*" * 1024
    }
  }
}
