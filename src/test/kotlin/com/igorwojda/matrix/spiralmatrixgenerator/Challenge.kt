package com.igorwojda.matrix.spiralmatrixgenerator

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun generateSpiralMatrix(n: Int): List<MutableList<Int?>> {
    if (n==0) return listOf<MutableList<Int?>>()
    else {
        val result = mutableListOf<MutableList<Int?>>()
        repeat(n) {
            result.add(MutableList<Int?>(n) {null})
        }

        var nextDirection = "right"
        var x = 0
        var y = 0
        for (i in 1..(n*n)){
            result[y][x] = i

            when (nextDirection) {
                "right" -> {
                    if (x == result[y].lastIndex || result[y][x+1] != null) {
                        nextDirection = "down"
                        y++
                    } else x++
                }"down" -> {
                    if (y == result.lastIndex || result[y+1][x] != null) {
                        nextDirection = "left"
                        x--
                    } else y++
                }"left" -> {
                    if (x == 0 || result[y][x-1] != null) {
                        nextDirection = "up"
                        y--
                    }else x--
                }"up" -> {
                    if (y==0 || result[y-1][x] != null) {
                        nextDirection = "right"
                        x++
                    }else y--
                }else -> throw Exception("invalid value for nextDirection")
            }
        }

        return result.toList()

    }
}

private class Test {
    @Test
    fun `generateSpiralMatrix generates a 2x2 matrix`() {
        val matrix = generateSpiralMatrix(2)
        matrix.size shouldBeEqualTo 2
        matrix[0] shouldBeEqualTo listOf(1, 2)
        matrix[1] shouldBeEqualTo listOf(4, 3)
    }

    @Test
    fun `generateSpiralMatrix generates a 3x3 matrix`() {
        val matrix = generateSpiralMatrix(3)
        matrix.size shouldBeEqualTo 3
        matrix[0] shouldBeEqualTo listOf(1, 2, 3)
        matrix[1] shouldBeEqualTo listOf(8, 9, 4)
        matrix[2] shouldBeEqualTo listOf(7, 6, 5)
    }

    @Test
    fun `generateSpiralMatrix generates a 4x4 matrix`() {
        val matrix = generateSpiralMatrix(4)
        matrix.size shouldBeEqualTo 4
        matrix[0] shouldBeEqualTo listOf(1, 2, 3, 4)
        matrix[1] shouldBeEqualTo listOf(12, 13, 14, 5)
        matrix[2] shouldBeEqualTo listOf(11, 16, 15, 6)
        matrix[3] shouldBeEqualTo listOf(10, 9, 8, 7)
    }
}
