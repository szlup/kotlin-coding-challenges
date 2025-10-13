package com.igorwojda.list.medianoftwosorted

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

fun medianOfSortedLists(list1: List<Int>, list2: List<Int>): Double {

    when {
        list1.isEmpty() && list2.isEmpty() -> return 0.0
        list1.size == 1 && list2.size == 1 -> return list1[0] + list2[0] / 2.0
        else -> {
            val merged = mutableListOf<Int>()
            val medIndex = (list1.size + list2.size) / 2
            val isEven = (list1.size + list2.size) % 2 == 0

            val left = list1.toMutableList()
            val right = list2.toMutableList()

            fun getMedian(index: Int): Double {
                return if (isEven) (merged[index] + merged[index - 1]) / 2.0
                else merged[index].toDouble()
            }

            while (left.isNotEmpty()) {
                if (right.isEmpty() || left[0] < right[0]) merged.add(left.removeFirst())
                else merged.add(right.removeFirst())

                if (merged.lastIndex == medIndex) return getMedian(medIndex)
            }
            if (right.isNotEmpty()) {
                right.forEach {
                    merged.add(it)
                    if (merged.lastIndex == medIndex) return getMedian(medIndex)
                }
            }
            return getMedian(medIndex) //probably will never reach this,but so that Kotlin doesn't complain...

        }
    }

}

private class Test {
    @Test
    fun `median of sorted lists 1, 3 and 2 returns 2,0`() {
        medianOfSortedLists(listOf(1, 3), listOf(2)) shouldBeEqualTo 2.0
    }

    @Test
    fun `median of sorted lists 1, 3 and 2 returns 2,5`() {
        medianOfSortedLists(listOf(1, 2), listOf(3, 4)) shouldBeEqualTo 2.5
    }

    @Test
    fun `median of sorted lists 2 and 1, 3 returns 2,0`() {
        medianOfSortedLists(listOf(2), listOf(1, 3)) shouldBeEqualTo 2.0
    }

    @Test
    fun `median of sorted lists 1, 3, 4 and 2 returns 3,5`() {
        medianOfSortedLists(listOf(1, 5, 7), listOf(2)) shouldBeEqualTo 3.5
    }

    @Test
    fun `median of sorted lists 2 and 1, 3, 4 returns 4,0`() {
        medianOfSortedLists(listOf(2), listOf(1, 6, 7)) shouldBeEqualTo 4.0
    }
}
