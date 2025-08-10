package com.igorwojda.string.ispalindrome.permutation

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun isPermutationPalindrome(str: String, oddFound: Boolean = false): Boolean {
    if (str.isEmpty()) return true
    else {
        val charCount = str.count {it == str[0]}
        val newStr = str.replace(str[0].toString(), "")
        if (charCount % 2 == 0) {
            return isPermutationPalindrome(newStr, oddFound = oddFound)
        } else {
            if (newStr.isEmpty()) {
                return if (oddFound) false
                else true
            } else {
                return isPermutationPalindrome(newStr, oddFound = true)
            }
        }
    }
}

private class Test {
    @Test
    fun `'gikig' is a palindrome`() {
        isPermutationPalindrome("gikig") shouldBeEqualTo true
    }

    @Test
    fun `'ookvk' is a palindrome`() {
        isPermutationPalindrome("ookvk") shouldBeEqualTo true
    }

    @Test
    fun `'sows' is not a palindrome`() {
        isPermutationPalindrome("sows") shouldBeEqualTo false
    }

    @Test
    fun `'tami' is not a palindrome`() {
        isPermutationPalindrome("tami") shouldBeEqualTo false
    }
}
