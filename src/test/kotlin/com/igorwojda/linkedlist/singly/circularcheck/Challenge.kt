package com.igorwojda.linkedlist.singly.circularcheck

import com.igorwojda.linkedlist.singly.base.Solution1.Node
import com.igorwojda.linkedlist.singly.base.Solution1.SinglyLinkedList
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun circularCheck(list: SinglyLinkedList<Char>): Boolean {
    if (list.first == null) return false
    else {
        var slow = list.first
        var fast = list.first

        list.forEach {
            slow = slow?.next
            fast = fast?.next?.next
            if (slow == null || fast == null) return false
            else if (slow == fast) return true
        }

        return false
    }
}

private class Test {
    @Test
    fun `circular detects circular linked lists`() {
        val l = SinglyLinkedList<Char>()
        val a = Node('a')
        val b = Node('b')
        val c = Node('c')

        l.head = a
        a.next = b
        b.next = c
        c.next = b

        circularCheck(l) shouldBeEqualTo true
    }

    @Test
    fun `circular detects circular linked lists linked at the head`() {
        val l = SinglyLinkedList<Char>()
        val a = Node('a')
        val b = Node('b')
        val c = Node('c')

        l.head = a
        a.next = b
        b.next = c
        c.next = a

        circularCheck(l) shouldBeEqualTo true
    }

    @Test
    fun `circular detects non-circular linked lists`() {
        val l = SinglyLinkedList<Char>()
        val a = Node('a')
        val b = Node('b')
        val c = Node('c')

        l.head = a
        a.next = b
        b.next = c
        c.next = null

        circularCheck(l) shouldBeEqualTo false
    }
}
