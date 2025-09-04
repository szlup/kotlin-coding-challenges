package com.igorwojda.tree.heap.maxbinaryheap

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private class MaxBinaryHeap<E : Comparable<E>> {
    val items = mutableListOf<E>()

    fun add(element: E) {
        items.add(element)

        if (items.size == 1) return
        else bubbleUp(items.lastIndex)
    }

    //moves an item at the specified index up to the correct level of the tree
    fun bubbleUp(index: Int) {
        if (index == 0 || index !in 0..items.lastIndex) return

        var i = index
        var parent = getParentIndex(i)
        while (items[i] > items[parent]) {
            items.swap(i, parent)
            i = parent
            parent = getParentIndex(i)
        }
    }

    //moves an item at the specified index down to the correct position in the tree
    fun siftDown(index: Int) {
        if (index == items.lastIndex || index !in 0..items.lastIndex) return

        var i = index
        var leftIndex = getLeftChildIndex(i)
        var rightIndex = getRightChildIndex(i)
        var left = items.getOrNull(leftIndex)
        var right = items.getOrNull(rightIndex)
        while ((left != null && items[i] < left) || (right != null && items[i] < right)) {
            when {
                left == null -> {
                    items.swap(i, rightIndex)
                    i = rightIndex
                }
                right == null -> {
                    items.swap(i, leftIndex)
                    i = leftIndex
                }
                right > left -> {
                    items.swap(i, rightIndex)
                    i = rightIndex
                }
                left > right -> {
                    items.swap(i, leftIndex)
                    i = leftIndex
                }
            }
            leftIndex = getLeftChildIndex(i)
            rightIndex = getRightChildIndex(i)
            left = items.getOrNull(leftIndex)
            right = items.getOrNull(rightIndex)
        }
    }

    fun removeMax(): E? {
        if (this.isEmpty()) return null
        else if (items.size < 3) return items.removeAt(0)

        //swap root with last element, remove, then sift down
        items.swap(0, items.lastIndex)
        val result = items.removeLast()
        this.siftDown(0)

        return result
    }

    // formula: (i−1)/2 for index i (using integer division)
    private fun getParentIndex(index: Int): Int = (index - 1) / 2

    // formula: 2i + 1 for parent node index i
    private fun getLeftChildIndex(index: Int): Int = (index * 2) + 1

    // formula: 2i + 2 for parent node index i
    private fun getRightChildIndex(index: Int): Int = (index * 2) + 2

    fun isEmpty(): Boolean = items.isEmpty()

    private fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }
}

private class Test {
    @Test
    fun `build valid max binary heap`() {
        MaxBinaryHeap<Int>().apply {
            add(9)
            add(7)
            add(6)
            add(3)
            add(2)
            add(4)

            // ----------Heap------------
            //
            //           9
            //         /   \
            //        7     6
            //       / \   /
            //      3   2 4
            //
            // --------------------------

            items[0] shouldBeEqualTo 9
            items[1] shouldBeEqualTo 7
            items[2] shouldBeEqualTo 6
            items[3] shouldBeEqualTo 3
            items[4] shouldBeEqualTo 2
            items[5] shouldBeEqualTo 4
            items.size shouldBeEqualTo 6
        }
    }

    @Test
    fun `bubble-up added element one level up`() {
        MaxBinaryHeap<Int>().apply {
            add(9)
            add(7)
            add(6)
            add(3)
            add(2)
            add(4)

            // ----------Heap------------
            //
            //           9
            //         /   \
            //        7     6
            //       / \   /
            //      3   2 4
            //
            // --------------------------

            add(8) // and bubble it up

            // ----------Heap------------
            //
            //           9
            //         /   \
            //        7     8
            //       / \   / \
            //      3   2 4   6
            //
            // --------------------------

            items[0] shouldBeEqualTo 9
            items[1] shouldBeEqualTo 7
            items[2] shouldBeEqualTo 8
            items[3] shouldBeEqualTo 3
            items[4] shouldBeEqualTo 2
            items[5] shouldBeEqualTo 4
            items[6] shouldBeEqualTo 6
            items.size shouldBeEqualTo 7
        }
    }

    @Test
    fun `bubble-up added element to the root`() {
        MaxBinaryHeap<Int>().apply {
            add(9)
            add(7)
            add(6)
            add(3)
            add(2)
            add(4)

            // ----------Heap------------
            //
            //           9
            //         /   \
            //        7     6
            //       / \   /
            //      3   2 4
            //
            // --------------------------

            add(12) // and bubble it up

            // ----------Heap------------
            //
            //           12
            //         /    \
            //        7      9
            //       / \    / \
            //      3   2  4   6
            //
            // --------------------------

            items[0] shouldBeEqualTo 12
            items[1] shouldBeEqualTo 7
            items[2] shouldBeEqualTo 9
            items[3] shouldBeEqualTo 3
            items[4] shouldBeEqualTo 2
            items[5] shouldBeEqualTo 4
            items[6] shouldBeEqualTo 6
            items.size shouldBeEqualTo 7
        }
    }

    @Test
    fun `remove max element 9`() {
        MaxBinaryHeap<Int>().apply {
            add(9)
            add(7)
            add(6)
            add(3)
            add(2)
            add(4)

            // ----------Heap------------
            //
            //           9
            //         /   \
            //        7     6
            //       / \   /
            //      3   2 4
            //
            // --------------------------

            removeMax() shouldBeEqualTo 9

            // ----------Heap------------
            //
            //           7
            //         /   \
            //        4     6
            //       / \
            //      3   2
            //
            // --------------------------

            items[0] shouldBeEqualTo 7
            items[1] shouldBeEqualTo 4
            items[2] shouldBeEqualTo 6
            items[3] shouldBeEqualTo 3
            items[4] shouldBeEqualTo 2
            items.size shouldBeEqualTo 5
        }
    }

    @Test
    fun `remove max element 8`() {
        MaxBinaryHeap<Int>().apply {
            add(8)
            add(7)
            add(6)
            add(3)
            add(2)
            add(4)

            // ----------Heap------------
            //
            //           8
            //         /   \
            //        7     6
            //       / \   /
            //      3   2 4
            //
            // --------------------------

            removeMax() shouldBeEqualTo 8

            // ----------Heap------------
            //
            //           7
            //         /   \
            //        4     6
            //       / \
            //      3   2
            //
            // --------------------------

            items[0] shouldBeEqualTo 7
            items[1] shouldBeEqualTo 4
            items[2] shouldBeEqualTo 6
            items[3] shouldBeEqualTo 3
            items[4] shouldBeEqualTo 2
            items.size shouldBeEqualTo 5
        }
    }

    @Test
    fun `remove max element until heap is empty`() {
        MaxBinaryHeap<Int>().apply {
            add(8)
            add(7)
            add(6)

            // ----------Heap------------
            //
            //           8
            //         /   \
            //        7     6
            //
            // --------------------------

            removeMax() shouldBeEqualTo 8

            // ----------Heap------------
            //
            //           7
            //         /
            //        6
            //
            // --------------------------

            items[0] shouldBeEqualTo 7
            items[1] shouldBeEqualTo 6
            items.size shouldBeEqualTo 2

            removeMax() shouldBeEqualTo 7

            // ----------Heap------------
            //
            //           6
            //
            // --------------------------

            items[0] shouldBeEqualTo 6
            items.size shouldBeEqualTo 1

            removeMax() shouldBeEqualTo 6
            items.size shouldBeEqualTo 0
        }
    }
}
