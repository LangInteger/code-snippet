package count

object CountMain extends App {
    println(new Counter(10).inc().dec().inc().inc().count)
    println(new Counter(10).inc.inc.dec.dec(2).count)

    println(new CounterWithGap(10).inc(10).dec(7).dec().count)
}