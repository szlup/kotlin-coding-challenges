package com.igorwojda.integer.digitfrequency

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun equalDigitFrequency(i1: Int, i2: Int): Boolean {
    val seq1 = i1.toString()
    val map1 = seq1.associate { digit ->
        digit to seq1.count {it == digit}
    }

    val seq2 = i2.toString()
    val map2 = seq2.associate { digit ->
        digit to seq2.count {it == digit}
    }

    return map1 == map2
}

private class Test {
    @Test
    fun `'789' and '897' have the same digit frequency`() {
        equalDigitFrequency(789, 897) shouldBeEqualTo true
    }

    @Test
    fun `'123445' and '451243' have the same digit frequency`() {
        equalDigitFrequency(123445, 451243) shouldBeEqualTo true
    }

    @Test
    fun `'447' and '477' have different digit frequency`() {
        equalDigitFrequency(447, 477) shouldBeEqualTo false
    }

    @Test
    fun `'578' and '0' have different digit frequency`() {
        equalDigitFrequency(578, 0) shouldBeEqualTo false
    }

    @Test
    fun `'0' and '0' have the same digit frequency`() {
        equalDigitFrequency(0, 0) shouldBeEqualTo true
    }
}
