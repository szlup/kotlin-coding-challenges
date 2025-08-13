package com.igorwojda.stack.basic

//import com.igorwojda.queue.basic.Queue
//import com.igorwojda.queue.basic.Queue.Element
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

//list implementation
private class List_Stack<E> {
    var size = 0
        private set

    private val list = mutableListOf<E>()

    fun add(element: E) {
        list.add(element)
        size++
    }

    fun remove(): E? {
        val result = list.lastOrNull()
        if (result != null) {
            size--
            list.removeLast()
        }
        return result
    }

    fun peek(): E? {
        return list.lastOrNull()
    }

    fun isEmpty(): Boolean {
        return (size == 0)
    }
}

//linked list implementation
private class Stack<E> {
    var size = 0
        private set

    var first: Element<E>? = null
    var last: Element<E>? = null

    inner class Element<E> (val content: E, var prev: Stack<E>.Element<E>? = null)

    fun add(element: E) {
        if (this.isEmpty()) {
            this.first = Element<E>(element)
        } else if (this.last == null) {
            val toAdd = Element<E>(element, this.first)
            this.last = toAdd
        } else {
            val toAdd = Element<E>(element, this.last)
            this.last = toAdd
        }
        size++
    }



    fun remove(): E? {
        if (this.isEmpty()) return null
        else if (this.last == null) {
            val result = this.first
            this.first = null
            size--
            return result?.content
        }else{
            val result = this.last
            size--
            if (size < 2) this.last = null
            else this.last = result!!.prev

            return result?.content
        }
    }

    fun peek(): E? {
        return this.last?.content ?: this.first?.content
    }

    fun isEmpty(): Boolean {
        return (size == 0)
    }
}

private class Test {
    @Test
    fun `stack can add and remove items`() {
        Stack<Int>().apply {
            add(1)
            remove() shouldBeEqualTo 1
            add(2)
            remove() shouldBeEqualTo 2
        }
    }

    @Test
    fun `stack can follows first in, last out`() {
        Stack<Int>().apply {
            add(1)
            add(2)
            add(3)
            remove() shouldBeEqualTo 3
            remove() shouldBeEqualTo 2
            remove() shouldBeEqualTo 1
        }
    }

    @Test
    fun `peek returns the first element but does not remove it`() {
        Stack<Char>().apply {
            add('A')
            add('B')
            add('C')
            peek() shouldBeEqualTo 'C'
            remove() shouldBeEqualTo 'C'
            peek() shouldBeEqualTo 'B'
            remove() shouldBeEqualTo 'B'
            peek() shouldBeEqualTo 'A'
            remove() shouldBeEqualTo 'A'
            peek() shouldBeEqualTo null
            remove() shouldBeEqualTo null
        }
    }

    @Test
    fun `newly created stack is empty`() {
        Stack<Char>().apply {
            isEmpty() shouldBeEqualTo true
        }
    }

    @Test
    fun `stack is empty after removing all items`() {
        Stack<Char>().apply {
            add('A')
            add('B')
            add('C')
            peek()
            remove()
            peek()
            remove()
            peek()
            remove()

            isEmpty() shouldBeEqualTo true
        }
    }

    @Test
    fun `stack with items is not empty`() {
        Stack<Char>().apply {
            add('A')
            isEmpty() shouldBeEqualTo false
            add('B')
            isEmpty() shouldBeEqualTo false
            add('C')
            isEmpty() shouldBeEqualTo false
        }
    }

    @Test
    fun `stack has correct size`() {
        Stack<Char>().apply {
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
    fun `remove item from empty stack`() {
        Stack<Char>().apply {
            remove()

            size shouldBeEqualTo 0
        }
    }
}
