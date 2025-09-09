package com.igorwojda.linkedlist.singly.midpoint

import com.igorwojda.linkedlist.singly.base.Solution1.Node
import com.igorwojda.linkedlist.singly.base.Solution1.SinglyLinkedList
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun midpoint(list: SinglyLinkedList<Char>): Node<Char>? {
    if (list.first == null) return null
    else {
        val slow: Node<Char>? = list.first!!.next
        var fast: Node<Char>? = list.first!!.next?.next
        if (slow == null || fast == null) return list.first

        var result = list.first
        var inc = false
        list.forEach {
            fast = it.next?.next
            if (inc) {
                result = result!!.next
                inc = false
            }else if (fast == null) inc = false
            else inc = true
        }

        return result
    }
}

private class Test {
    @Test
    fun `midpoint of list with 0 elements`() {
        SinglyLinkedList<Char>().apply {
            midpoint(this)?.data shouldBeEqualTo null
        }
    }

    @Test
    fun `midpoint of list with 3 elements`() {
        SinglyLinkedList<Char>().apply {
            insertLast('a')
            insertLast('b')
            insertLast('c')

            midpoint(this)?.data shouldBeEqualTo 'b'
        }
    }

    @Test
    fun `midpoint of list with 5 elements`() {
        SinglyLinkedList<Char>().apply {
            insertLast('a')
            insertLast('b')
            insertLast('c')
            insertLast('d')
            insertLast('e')

            midpoint(this)?.data shouldBeEqualTo 'c'
        }
    }

    @Test
    fun `midpoint of list with 2 elements`() {
        SinglyLinkedList<Char>().apply {
            insertLast('a')
            insertLast('b')

            midpoint(this)?.data shouldBeEqualTo 'a'
        }
    }

    @Test
    fun `midpoint of list with 4 elements`() {
        SinglyLinkedList<Char>().apply {
            insertLast('a')
            insertLast('b')
            insertLast('c')
            insertLast('d')

            midpoint(this)?.data shouldBeEqualTo 'b'
        }
    }
}
