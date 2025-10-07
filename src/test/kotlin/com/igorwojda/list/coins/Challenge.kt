package com.igorwojda.list.coins

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test


//maybe try this as a tree?  Start with empty list and each child level adds each element to the list.  Return when a node's sum >= amount.
//tree needs more structure so that you're not counting combinations with different order
private fun getCoins(amount: Int, coins: List<Int>): Int {
    if (coins.isEmpty()) return 0
    else if (amount == 0) return 1
    else if (coins.all { it > amount }) return 0
    else {
        var sum = 0
        for (i in 0..coins.lastIndex) {
            sum += getCoinsHelper(amount, coins.subList(i, coins.size) , listOf(coins[i]))
        }
        return sum
    }
}

private fun getCoinsHelper(amount: Int, coins: List<Int>,  tempList: List<Int> = listOf()): Int {
    val tempSum = tempList.sum()
    when {
        tempSum == amount -> return 1
        tempSum > amount -> return 0
        else -> {
            var sum = 0
            for (i in 0..coins.lastIndex) {
                sum += getCoinsHelper(amount, coins.subList(i, coins.size), tempList + coins[i])
            }
            return sum
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
