package count

class CounterWithGap(val count: Int) {
    def inc(gap: Int = 1): CounterWithGap = {
        new CounterWithGap(count + gap)
    }

    def dec(gap: Int = 1): CounterWithGap = {
        new CounterWithGap(count - gap)
    }
}