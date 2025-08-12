package com.igorwojda.list.smallestelements

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

private fun smallestElements(list: List<Int>, count: Int): List<Int> {
    if (list.isEmpty()) return list
    else if (list.size <= count) return list
    else {
        val queue = PriorityQueue<Int>()
        queue.addAll(list)

        val result = mutableListOf<Int>()
        for (i in 0 until count) {
            if (queue.peek() == null) break
            else result.add(0, queue.poll())
        }

        return result.toList()
    }

}

private class Test {
    @Test
    fun `4 smallest elements`() {
        val list = listOf(5, 1, 3)

        smallestElements(list, 3) shouldBeEqualTo listOf(5, 1, 3)
    }

    @Test
    fun `3 smallest elements`() {
        val list = listOf(5, 1, 3)

        smallestElements(list, 3) shouldBeEqualTo listOf(5, 1, 3)
    }

    @Test
    fun `2 smallest elements`() {
        val list = listOf(5, 1, 3)

        smallestElements(list, 2) shouldBeEqualTo listOf(3, 1)
    }

    @Test
    fun `1 smallest element`() {
        val list = listOf(5, 1, 3)

        smallestElements(list, 1) shouldBeEqualTo listOf(1)
    }
}
