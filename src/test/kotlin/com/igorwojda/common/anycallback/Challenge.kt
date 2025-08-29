package com.igorwojda.common.anycallback

fun <T: Any> anyCallback(list: List<T>, predicate: (T) -> Boolean): Boolean {
    if (list.isEmpty()) return false
    else {
        list.forEach {
            if (predicate(it)) return true
        }
        return false
    }
}


