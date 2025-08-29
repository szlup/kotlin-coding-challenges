package com.igorwojda.list.flatten

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

fun flatten(list: List<*>): List<*> {
    return if (list.isEmpty()) list
    else if (list.all { it !is Collection<*>}) list
    else {
        val result = mutableListOf<Any>()

        for (elem in list) {
            if (elem is Collection<*>) {
                result.addAll(flatten_helper(listOf(elem)))
            } else elem?.let {result.add(it)}
        }

        return result
    }
}

fun flatten_helper(orig: List<Any>, dest: MutableList<Any> = mutableListOf<Any>()): List<Any> {
    if (orig.isEmpty()) return dest.toList()
    else if (orig.all { it !is Collection<*> }) {
        if (orig.size == 1) dest.add(orig[0])
        else dest.addAll(orig)
        return dest.toList()
    }else {
        val i = orig.indexOfFirst { it is Collection<*> }
        if (i != 0) dest.addAll(orig.subList(0, i))
        return if (i < orig.lastIndex) {
            flatten_helper(listOf(orig[i]), dest) + flatten_helper(orig.subList(i+1, orig.size))
        } else flatten_helper(listOf(orig[i]), dest)
    }
}

private class Test {
    @Test
    fun `flatten test 1`() {
        flatten(listOf(1)) shouldBeEqualTo listOf(1)
    }

    @Test
    fun `flatten test 2`() {
        flatten(listOf(1, listOf(2))) shouldBeEqualTo listOf(1, 2)
    }

    @Test
    fun `flatter test 3`() {
        flatten(listOf(1, listOf(2, listOf(3), emptyList<Any>()))) shouldBeEqualTo listOf(1, 2, 3)
    }

    @Test
    fun `flatter test 4`() {
        flatten(
            listOf(
                1,
                listOf(2, listOf<Any>(emptyList<Any>())),
                listOf(3),
            ),
        ) shouldBeEqualTo listOf(1, 2, 3)
    }

    @Test
    fun `flatter test 5`() {
        flatten(
            listOf(
                1,
                listOf(2, listOf(3), listOf(4, listOf(listOf(5)))),
            ),
        ) shouldBeEqualTo listOf(1, 2, 3, 4, 5)
    }
}
