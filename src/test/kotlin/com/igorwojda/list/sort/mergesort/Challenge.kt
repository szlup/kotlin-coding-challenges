package com.igorwojda.list.sort.mergesort

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun mergeSort(list: List<Int>): List<Int> {
    // Take two sorted lists and merge them together into one sorted list
    fun merge(left: MutableList<Int>, right: MutableList<Int>): MutableList<Int> {
        if (left.size == 1 && right.size == 1) {
            if (left[0] > right[0]) return mutableListOf(right[0], left[0])
            else return mutableListOf(left[0], right[0])
        } else {
            val result = mutableListOf<Int>()

            while (!left.isEmpty()) {
                if (right.isEmpty() || left[0] < right[0]) result.add(left.removeFirst())
                else result.add(right.removeFirst())
            }
            if (!right.isEmpty()) result.addAll(right)

            return result
        }
    }


    if (list.size < 2) return list
    else {
        val left = list.subList(0, list.size / 2).toMutableList()
        val right = list.subList(list.size / 2, list.size).toMutableList()

        return if (left.size == 1 && right.size == 1) merge(left, right)
        else if (left.size == 1) merge(left, mergeSort(right).toMutableList())
        else if (right.size == 1) merge(mergeSort(left).toMutableList(), right)
        else merge(mergeSort(left).toMutableList(), mergeSort(right).toMutableList())

    }
}

private class Test {
    @Test
    fun `merge sort empty list`() {
        mergeSort(mutableListOf()) shouldBeEqualTo listOf()
    }

    @Test
    fun `merge sort 7`() {
        mergeSort(mutableListOf(7)) shouldBeEqualTo listOf(7)
    }

    @Test
    fun `merge sort empty list 9, 3`() {
        mergeSort(mutableListOf(9, 3)) shouldBeEqualTo listOf(3, 9)
    }

    @Test
    fun `merge sort 5, 1, 4, 2`() {
        mergeSort(mutableListOf(5, 1, 4, 2)) shouldBeEqualTo listOf(1, 2, 4, 5)
    }

    @Test
    fun `merge sort 5, 2, 1, 8, 4, 7, 6, 3`() {
        mergeSort(mutableListOf(5, 2, 1, 8, 4, 7, 6, 3)) shouldBeEqualTo listOf(
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
        )
    }

    @Test
    fun `merge sort 17, 4, 12, 19, 80, 75, 16`() {
        mergeSort(mutableListOf(17, 4, 12, 19, 80, 75, 16)) shouldBeEqualTo listOf(
            4,
            12,
            16,
            17,
            19,
            75,
            80,
        )
    }

    @Test
    fun `merge sort 11, 40, 40, 50, 43, 10, 30, 42, 20, 6, 19, 32, 20, 41, 23, 27`() {
        val list = mutableListOf(11, 40, 40, 50, 43, 10, 30, 42, 20, 6, 19, 32, 20, 41, 23, 27)
        val sorted = mutableListOf(6, 10, 11, 19, 20, 20, 23, 27, 30, 32, 40, 40, 41, 42, 43, 50)

        mergeSort(list) shouldBeEqualTo sorted
    }
}
