package com.igorwojda.string.caesarcipher

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun encodeCaesarCipher(str: String, shift: Int): String {
    assert(str.matches( Regex("[a-z]*")))

    val aCode = 'a'.code
    val zCode = 'z'.code

    return str.map {
        val newCode = it.code + (shift % 26)
        if (newCode in aCode..zCode) Char(newCode)
        else Char(((newCode % zCode) % 26) + (aCode - 1))
    }.joinToString(separator = "")
}

private class Test {
    @Test
    fun `'abc' with shift 1 return 'bcd'`() {
        encodeCaesarCipher("abc", 1) shouldBeEqualTo "bcd"
    }

    @Test
    fun `'abcdefghijklmnopqrstuvwxyz' shift 1 returns 'bcdefghijklmnopqrstuvwxyza'`() {
        encodeCaesarCipher(
            "abcdefghijklmnopqrstuvwxyz",
            1,
        ) shouldBeEqualTo "bcdefghijklmnopqrstuvwxyza"
    }

    @Test
    fun `'abcdefghijklmnopqrstuvwxyz' shift 7 returns 'hijklmnopqrstuvwxyzabcdefg'`() {
        encodeCaesarCipher(
            "abcdefghijklmnopqrstuvwxyz",
            7,
        ) shouldBeEqualTo "hijklmnopqrstuvwxyzabcdefg"
    }

    @Test
    fun `'abcdefghijklmnopqrstuvwxyz' shift 50 returns 'yzabcdefghijklmnopqrstuvwx'`() {
        encodeCaesarCipher(
            "abcdefghijklmnopqrstuvwxyz",
            50,
        ) shouldBeEqualTo "yzabcdefghijklmnopqrstuvwx"
    }
}
