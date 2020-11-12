package adder

import count.Counter

class ExtendedCounter(val count: Int) {
    def adjust(adder: Adder): Counter = {
        new Counter(adder(count))
    }
}
