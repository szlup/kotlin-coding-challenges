package com.igorwojda.queue.basic

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

//List Implementation
private class Queue_List<E> {
    var size = 0
        private set

    val list = mutableListOf<E>()

    fun add(element: E) {
        list.add(element)
        size++
    }

    fun remove(): E? {
        if (this.isEmpty()) return null
        else {
            val result = list[0]
            list.removeAt(0)
            size--
            return result
        }
    }

    fun peek(): E? {
        if (this.isEmpty()) return null
        else {
            val result = list[0]
            return result
        }
    }

    fun isEmpty(): Boolean {
        return list.isEmpty()
    }
}

//Linked List implementation
private class Queue<E> {
    var size = 0
        private set

    val list = mutableListOf<E>()

    var first: Element<E>? = null
    var last: Element<E>? = null

    inner class Element<E> (val content: E, var next: Queue<E>.Element<E>? = null)

    fun add(value: E) {
        if (this.isEmpty()) {
            this.first = Element<E>(value)
        }else {
            val toAdd = Element<E>(value)
            if (this.last == null) {
                this.last = toAdd
                this.first!!.next = toAdd
            }else {
                this.last!!.next = toAdd
                this.last = toAdd
            }
        }
        size++
    }

    fun remove(): E? {
        if (this.isEmpty()) return null
        else {
            val result = this.first
            this.first = this.first?.next
            size--
            if (size < 2) this.last = null
            return result?.content
        }
    }

    fun peek(): E? {
        if (this.isEmpty()) return null
        else {
            val result = this.first
            return result?.content
        }
    }

    fun isEmpty(): Boolean {
        return size == 0
    }
}

private class Test {
    @Test
    fun `can add elements to a queue`() {
        Queue<Int>().apply { add(1) }
    }

    @Test
    fun `can remove elements from empty queue`() {
        Queue<Int>().apply { remove() shouldBeEqualTo null }
    }

    @Test
    fun `can remove elements from a queue`() {
        Queue<String>().apply {
            add("ABC")
            remove()
        }
    }

    @Test
    fun `order of elements is maintained`() {
        Queue<Char>().apply {
            add('A')
            add('B')
            add('C')
            remove() shouldBeEqualTo 'A'
            remove() shouldBeEqualTo 'B'
            remove() shouldBeEqualTo 'C'
            remove() shouldBeEqualTo null
        }
    }

    @Test
    fun `peek returns, but does not remove element`() {
        Queue<Int>().apply {
            add(1)
            add(2)
            peek() shouldBeEqualTo 1
            peek() shouldBeEqualTo 1
            remove() shouldBeEqualTo 1
            peek() shouldBeEqualTo 2
            remove() shouldBeEqualTo 2
            peek() shouldBeEqualTo null
            remove() shouldBeEqualTo null
        }
    }

    @Test
    fun `isEmpty returns true`() {
        Queue<Int>().isEmpty() shouldBeEqualTo true
    }

    @Test
    fun `isEmpty returns false`() {
        Queue<Int>().apply {
            add(1)
            isEmpty() shouldBeEqualTo false
        }
    }

    @Test
    fun `queue has correct size`() {
        Queue<Char>().apply {
            size shouldBeEqualTo 0

            add('A')
            size shouldBeEqualTo 1

            add('B')
            size shouldBeEqualTo 2

            add('C')
            size shouldBeEqualTo 3

            remove()
            size shouldBeEqualTo 2

            remove()
            size shouldBeEqualTo 1

            remove()
            size shouldBeEqualTo 0
        }
    }

    @Test
    fun `remove item from empty queue`() {
        Queue<Int>().apply {
            remove()

            size shouldBeEqualTo 0
        }
    }
}
