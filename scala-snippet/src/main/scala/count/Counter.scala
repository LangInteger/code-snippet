package count

class Counter(val count: Int) {
    def inc(): Counter = {
        new Counter(count + 1)
    }

    def dec(): Counter = {
        new Counter(count - 1)
    }

    def int(amount: Int): Counter = {
        new Counter(count + amount)
    }

    def dec(amount: Int): Counter = {
        new Counter(count - amount)
    }
}