package playground

import com.twitter.util.StdBenchAnnotations
import org.openjdk.jmh.annotations._

@State(Scope.Benchmark)
class StringLikeBenchmark extends StdBenchAnnotations {
  @Benchmark
  def testScalaTimesAllocation() = {
    for(i <- 0 to 1) {
      "*" * 1024
    }
  }
}
