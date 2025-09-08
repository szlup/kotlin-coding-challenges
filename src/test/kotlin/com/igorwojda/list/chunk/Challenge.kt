package com.igorwojda.list.listchunk

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

// approach 1
private fun chunk(list: List<Int>, size: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    var count = 0
    var toAdd = mutableListOf<Int>()
    for (num in list) {
        toAdd.add(num)
        count++
        if (count >= size) {
            result.add(toAdd)
            toAdd = mutableListOf<Int>()
            count = 0
        }
    }

    if (toAdd.isNotEmpty()) result.add(toAdd)

    return result
}

//approach 2 (apparently this is slower!)
//private fun chunk(list: List<Int>, size: Int): List<List<Int>> {
//    val result = mutableListOf<List<Int>>()
//
//    var start = 0
//    while (start <= list.lastIndex) {
//        val end = if (start + size <= list.lastIndex) start + size else list.lastIndex + 1
//        result.add(list.subList(start, end))
//        start = end
//    }
//
//    return result
//}

private class Test {
    @Test
    fun `chunk divides an list of 10 elements with chunk size 2`() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        chunk(list, 2) shouldBeEqualTo listOf(
            listOf(1, 2),
            listOf(3, 4),
            listOf(5, 6),
            listOf(7, 8),
            listOf(9, 10),
        )
    }

    @Test
    fun `chunk divides an list of 3 elements with chunk size 1`() {
        val list = listOf(1, 2, 3)
        chunk(list, 1) shouldBeEqualTo listOf(listOf(1), listOf(2), listOf(3))
    }

    @Test
    fun `chunk divides an list of 5 elements with chunk size 3`() {
        val list = listOf(1, 2, 3, 4, 5)
        chunk(list, 3) shouldBeEqualTo listOf(listOf(1, 2, 3), listOf(4, 5))
    }

    @Test
    fun `chunk divides an list of 13 elements with chunk size 5`() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
        chunk(list, 5) shouldBeEqualTo listOf(
            listOf(1, 2, 3, 4, 5),
            listOf(6, 7, 8, 9, 10),
            listOf(11, 12, 13),
        )
    }
}
