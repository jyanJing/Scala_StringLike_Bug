package playground

import org.openjdk.jmh.annotations._
import com.twitter.util.Promise
import com.twitter.util.Future
import com.twitter.util.Try
import com.twitter.util.Await

@State(Scope.Benchmark)
class FutureBench {

  val f = Promise[Int]()

  val mapF: Int => Int = _ + 1

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  def map =
    Promise[Int]().map(mapF).map(mapF).map(mapF)

    
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  def plus10(f: Future[Int]) =
    f.map(_ + 1).map(_ + 1).map(_ + 1).map(_ + 1)
      .map(_ + 1).map(_ + 1).map(_ + 1).map(_ + 1)
      .map(_ + 1).map(_ + 1)

  @Benchmark
  def execMap = {
    val p = Promise[Int]()
    p.map(mapF).map(mapF).map(mapF)
    p.setValue(1)
  }

  val flatMapF: Int => Future[Int] = i => Future.value(i + 1)

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  def flatMap =
    Promise[Int]().flatMap(flatMapF).flatMap(flatMapF) //.flatMap(flatMapF)

  val transformF: Try[Int] => Future[Int] = i => Future.value(i.get + 1)

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  def transform =
    Promise[Int]().transform(transformF).transform(transformF).transform(transformF)

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  def transformMap =
    Promise[Int]().transform(transformF).map(mapF)

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  def mapFlatMap =
    Promise[Int]().map(mapF).flatMap(flatMapF)

  val respondF: Try[Int] => Unit = _ => ()

  //  Promise[Int]().fusedRespond(respondF).respond(respondF)

  @Benchmark
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  def respond =
    Promise[Int]().respond(respondF).respond(respondF)

  //  @Benchmark //  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  //  def fused =
  //    Promise[Int]().fusedMap(f1).fusedMap(f2).map(f3)
}
