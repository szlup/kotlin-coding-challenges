package com.igorwojda.list.capitalizefirst

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

fun main() {
    val temp:List<String> = listOf()


}

private fun capitalizeFirst(list: List<String>): List<String> {
    if(list.size == 0) return list
    else if(list.size > 1) {
        return capitalizeFirst(list.subList(0, 1)) + capitalizeFirst(list.subList(1, list.size))
    } else {
        var str = list.get(0)
        if (str.length > 0) {
            str = str.get(0).uppercase() + str.substring(1 until str.length)
        }
        return listOf(str)
    }
}

private class Test {
    @Test
    fun `capitalize list with one string`() {
        capitalizeFirst(listOf("igor")) shouldBeEqualTo listOf("Igor")
    }

    @Test
    fun `capitalize list with two strings`() {
        capitalizeFirst(listOf("igor", "wojda")) shouldBeEqualTo listOf("Igor", "Wojda")
    }

    @Test
    fun `capitalize list with empty string`() {
        capitalizeFirst(listOf("")) shouldBeEqualTo listOf("")
    }

    @Test
    fun `capitalize list with sentence`() {
        capitalizeFirst(listOf("what a", "beautiful", "morning")) shouldBeEqualTo listOf(
            "What a",
            "Beautiful",
            "Morning",
        )
    }
}
