package com.igorwojda.list.search.binarysearch

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun binarySearch(list: List<Char>, element: Char, start: Int = 0, end: Int = list.lastIndex): Int {
    if (list.isEmpty() || start > end) return -1

    val mid = (start + end) / 2

    return if (element == list[mid]) mid
    else if (element < list[mid]) binarySearch(list, element, start, mid-1)
    else binarySearch(list, element, mid+1, end)

}

private class Test {
    @Test
    fun `index of A in A, B, C is 0`() {
        binarySearch(listOf('A', 'B', 'C'), 'A') shouldBeEqualTo 0
    }

    @Test
    fun `index of B in A, B, C is 1`() {
        binarySearch(listOf('A', 'B', 'C'), 'B') shouldBeEqualTo 1
    }

    @Test
    fun `index of C in A, B, C is 2`() {
        binarySearch(listOf('A', 'B', 'C'), 'C') shouldBeEqualTo 2
    }

    @Test
    fun `index of H in A, B, C is -1`() {
        binarySearch(listOf('A', 'B', 'C'), 'H') shouldBeEqualTo -1
    }

    @Test
    fun `index of A in A, B, C, D is 0`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'A') shouldBeEqualTo 0
    }

    @Test
    fun `index of B in A, B, C, D is 1`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'B') shouldBeEqualTo 1
    }

    @Test
    fun `index of C in A, B, C, D is 2`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'C') shouldBeEqualTo 2
    }

    @Test
    fun `index of D in A, B, C, D is 2`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'D') shouldBeEqualTo 3
    }

    @Test
    fun `index of H in A, B, C, D is -1`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'H') shouldBeEqualTo -1
    }
}
