package com.igorwojda.list.sort.radixsort

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun radixSort(list: List<Int>): List<Number> {
    if (list.isEmpty() || list.size < 2) return list
    // group by key length
    var buckets = mutableListOf<MutableList<Int>>()
    val working = list.toMutableList()
    while (working.isNotEmpty()) {
        val toAdd = working.filter {it.digitCount == maxDigits(working)}.toMutableList()
        working.removeAll(toAdd)
        buckets.add(0, toAdd)
    }
    // within each group, sort with least-significant digits
    buckets = buckets.map {sortBucket(it)}.toMutableList()

    // flatten buckets and return
    return buckets.flatten()
}

private fun sortBucket(bucket: MutableList<Int>, i: Int = maxDigits(bucket) - 1): MutableList<Int> {
    // make new buckets for 0-9 (make a list of size 10), then add numbers into the matching index
    if (bucket.size < 2) return bucket

    // assumes that elements in bucket are all of the same key length
    val subBuckets = MutableList<MutableList<Int>>(10) {mutableListOf<Int>()}

    for (j in 0..bucket.lastIndex) {
        val dest = bucket[j].getDigitAt(i).digitToInt()
        subBuckets[dest].add(bucket[j])
    }

    val result = subBuckets.flatten().toMutableList()

    return if (i == 0) result
    else sortBucket(result, i - 1)
}

private fun Int.getDigitAt(index: Int): Char {
    val result = this.toString()
    return result.getOrElse(index) {'0'}
}

private val Int.digitCount get() = this.toString().length

private fun maxDigits(list: List<Int>): Int {
    if (list.isEmpty()) return 0
    else {
        val counts = list.map { it.digitCount }
        return counts.max()
    }
}

private class Test {
    @Test
    fun `getDigitAt at 0 for 123 is 1`() {
        123.getDigitAt(0) shouldBeEqualTo '1'
    }

    @Test
    fun `getDigitAt at 1 for 123 is 2`() {
        123.getDigitAt(1) shouldBeEqualTo '2'
    }

    @Test
    fun `getDigitAt at 2 for 123 is 3`() {
        123.getDigitAt(2) shouldBeEqualTo '3'
    }

    @Test
    fun `getDigitAt at 3 for 123 is 0`() {
        123.getDigitAt(3) shouldBeEqualTo '0'
    }

    @Test
    fun `getDigitAt at 7 for 123 is 0`() {
        123.getDigitAt(7) shouldBeEqualTo '0'
    }

    @Test
    fun `digitCount for 1 is 1`() {
        1.digitCount shouldBeEqualTo 1
    }

    @Test
    fun `digitCount for 123 is 3`() {
        123.digitCount shouldBeEqualTo 3
    }

    @Test
    fun `digitCount for 12345 is 5`() {
        12345.digitCount shouldBeEqualTo 5
    }

    @Test
    fun `maxDigits is equal to 0`() {
        maxDigits(listOf()) shouldBeEqualTo 0
    }

    @Test
    fun `maxDigits is equal to 1`() {
        maxDigits(listOf(1, 2, 4)) shouldBeEqualTo 1
    }

    @Test
    fun `maxDigits is equal to 2`() {
        maxDigits(listOf(1, 25, 42, 7, 9)) shouldBeEqualTo 2
    }

    @Test
    fun `maxDigits is equal to 5`() {
        maxDigits(listOf(1, 25, 42, 77898, 1)) shouldBeEqualTo 5
    }

    @Test
    fun `radix sort empty list`() {
        radixSort(mutableListOf()) shouldBeEqualTo listOf()
    }

    @Test
    fun `radix sort 7`() {
        radixSort(mutableListOf(7)) shouldBeEqualTo listOf(7)
    }

    @Test
    fun `radix sort empty list 9, 3`() {
        radixSort(mutableListOf(9, 3)) shouldBeEqualTo listOf(3, 9)
    }

    @Test
    fun `radix sort 5, 1, 4, 2`() {
        radixSort(mutableListOf(5, 1, 4, 2)) shouldBeEqualTo listOf(1, 2, 4, 5)
    }

    @Test
    fun `radix sort 51, 1, 24, 32, 74, 31`() {
        radixSort(mutableListOf(51, 1, 24, 32, 74, 31)) shouldBeEqualTo listOf(
            1,
            24,
            31,
            32,
            51,
            74,
        )
    }

    @Test
    fun `radix sort 11, 407, 211, 1250, 5678, 1204, 37, 44, 1054, 4979, 567, 71, 277, 2078, 721, 3179`() {
        val list = mutableListOf(11, 407, 211, 1250, 5678, 1204, 37, 44, 1054, 4979, 567, 71, 277, 2078, 721, 3179)
        val sorted = mutableListOf(11, 37, 44, 71, 211, 277, 407, 567, 721, 1054, 1204, 1250, 2078, 3179, 4979, 5678)

        radixSort(list) shouldBeEqualTo sorted
    }
}
