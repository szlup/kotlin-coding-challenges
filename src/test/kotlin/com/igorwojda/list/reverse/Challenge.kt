package com.igorwojda.list.reverse

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test



private fun reverse(list: List<String>): List<String> {
    if (list.isEmpty()) return listOf()
    else {
        val result: MutableList<String> = mutableListOf()
        for (i in (list.size - 1) downTo 0) {
            result.add(list[i])
        }
        return result.toList()
    }
}

private class Test {
    @Test
    fun `reverse empty`() {
        val list = listOf<String>()
        reverse(list) shouldBeEqualTo listOf<String>()
    }

    @Test
    fun `reverse a`() {
        val list = listOf("a")
        reverse(list) shouldBeEqualTo listOf("a")
    }

    @Test
    fun `reverse even list`() {
        val list = listOf("a", "b")
        reverse(list) shouldBeEqualTo listOf("b", "a")
    }

    @Test
    fun `reverse odd list`() {
        val list = listOf("a", "b", "c")
        reverse(list) shouldBeEqualTo listOf("c", "b", "a")
    }
}
