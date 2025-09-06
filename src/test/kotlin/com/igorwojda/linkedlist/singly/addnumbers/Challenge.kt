package com.igorwojda.linkedlist.singly.addnumbers

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private data class ListNode(
    var data: Int,
    var next: ListNode? = null,
)

private fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    when {
        l1 == null && l2 == null -> return null
        l1 != null && l2 == null -> return l1
        l1 == null && l2 != null -> return l2
        else -> {
            var n1 = l1
            var n2 = l2


            var result: ListNode? = null
            var current: ListNode? = null

            while (n1 != null && n2 != null) {
                val digit = n1.data + n2.data
                val toAdd = if (digit < 10)
                    ListNode(digit)
                else {
                    if (n1.next == null) n1.next = ListNode(1)
                    else n1.next!!.data++

                    ListNode(digit % 10)
                }

                if (result == null) {
                    result = toAdd
                    current = toAdd
                }
                else {
                    current?.next = toAdd
                    current = toAdd
                }
                n1 = n1.next
                n2 = n2.next
            }
            var remaining: ListNode? = if (n1 != null) n1 else if (n2 != null) n2 else null

            while (remaining != null) {
                current?.next = remaining
                current = remaining
                remaining = remaining.next
            }

            return result
        }
    }
}

private class Test {
    @Test
    fun `add 5, 3, 7 to 7, 3, 8 returns 2, 7, 5, 1`() {
        val number1 = getList(5, 3, 7)
        val number2 = getList(7, 3, 8)
        val result = getList(2, 7, 5, 1)

        addTwoNumbers(number1, number2) shouldBeEqualTo result
    }

    @Test
    fun `add 0 to 0 returns 0`() {
        val number1 = getList(0)
        val number2 = getList(0)
        val result = getList(0)

        addTwoNumbers(number1, number2) shouldBeEqualTo result
    }

    @Test
    fun `add 7 to 2, 3, 5 returns 9, 3, 5`() {
        val number1 = getList(7)
        val number2 = getList(2, 3, 5)
        val result = getList(9, 3, 5)

        addTwoNumbers(number1, number2) shouldBeEqualTo result
    }

    private fun getList(vararg ints: Int): ListNode? {
        var head: ListNode? = null
        var current: ListNode? = null

        ints.forEach {
            val node = ListNode(it)

            if (head == null) {
                head = node
                current = node
            } else {
                current?.next = node
                current = node
            }
        }

        return head
    }
}
