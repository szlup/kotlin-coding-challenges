package com.igorwojda.list.subtract

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun getSubtraction(list1: List<String>, list2: List<String>): List<String> {
    val map1 = list1.associateWith { item -> list1.count { it == item } }
    val map2 = list2.associateWith { item -> list2.count { it == item } }

    if (map1 == map2) return emptyList()
    else {
        val result = mutableListOf<String>()
        for (key in map1.keys) {
            if (key !in map2.keys) repeat(map1[key] ?: 0) { result.add(key) }
            else {
                val count1 = map1[key] ?: 0
                val count2 = map2[key] ?: 0
                if (count1 != count2) repeat(count1 - count2) {result.add(key)}
            }
        }
        return result

    }
}

private class Test {
    @Test
    fun `A, B, C and B ,C returns A`() {
        getSubtraction(listOf("A", "B", "C"), listOf("B", "C")) shouldBeEqualTo listOf("A")
    }

    @Test
    fun `E, F, D and D returns E, F`() {
        getSubtraction(listOf("E", "F", "D"), listOf("D")) shouldBeEqualTo listOf("E", "F")
    }

    @Test
    fun `G and G returns empty list`() {
        getSubtraction(listOf("G"), listOf("G")) shouldBeEqualTo emptyList()
    }

    @Test
    fun `I, B, H and I, B ,H returns empty list`() {
        getSubtraction(listOf("I", "B", "H"), listOf("I", "B", "H")) shouldBeEqualTo emptyList()
    }

    @Test
    fun `B, Z, Z, G and B returns B, Z, Z`() {
        getSubtraction(listOf("B", "Z", "Z", "G"), listOf("Z", "Z")) shouldBeEqualTo listOf(
            "B",
            "G",
        )
    }

    @Test
    fun `G, D, D, D, A, A and D, A returns G, D, D, A`() {
        getSubtraction(
            listOf("G", "D", "D", "D", "A", "A"),
            listOf("D", "A"),
        ) shouldBeEqualTo listOf("G", "D", "D", "A")
    }

    @Test
    fun `G, H, H, B, B and empty list returns G, H, H, B, B`() {
        getSubtraction(listOf("G", "H", "H", "B", "B"), emptyList()) shouldBeEqualTo listOf(
            "G",
            "H",
            "H",
            "B",
            "B",
        )
    }
}
