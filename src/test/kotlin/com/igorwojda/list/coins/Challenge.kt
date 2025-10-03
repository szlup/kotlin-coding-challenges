package com.igorwojda.list.coins

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun getCoins(amount: Int, coins: List<Int>): Int {
    if (coins.isEmpty()) return 0
    else if (coins.all { it > amount }) return 1
    else {
        var result = 0
        val tempList = mutableListOf<Int>()
        var tempSum = 0
        var i = 0
        while (i < coins.size) {
            var current = coins[i]
            while (tempSum + current <= amount) {
                tempList.add(current)
                tempSum += current
            }
            if (tempSum == amount) result++

            while (tempSum + coins[i+1] > amount) {
                tempSum -= tempList.removeLast()
            }


        }
    }
}

private class Test {
    @Test
    fun `4 ways`() {
        val actual: Int = getCoins(4, listOf(1, 2, 3))
        val expected = 4
        actual shouldBeEqualTo expected
    }

    @Test
    fun `one way`() {
        val actual: Int = getCoins(0, listOf(1, 2))
        val expected = 1
        actual shouldBeEqualTo expected
    }

    @Test
    fun `no coins returns 0`() {
        val actual: Int = getCoins(1, listOf())
        val expected = 0
        actual shouldBeEqualTo expected
    }

    @Test
    fun `big coins`() {
        val actual: Int = getCoins(5, listOf(25, 50))
        val expected = 0
        actual shouldBeEqualTo expected
    }

    @Test
    fun `big amount`() {
        val actual: Int = getCoins(50, listOf(5, 10))
        val expected = 6
        actual shouldBeEqualTo expected
    }

    @Test
    fun `a lot of change`() {
        val actual: Int = getCoins(100, listOf(1, 5, 10, 25, 50))
        val expected = 292
        actual shouldBeEqualTo expected
    }
}
