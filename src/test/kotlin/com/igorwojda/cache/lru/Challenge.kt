package com.igorwojda.cache.lru

import java.util.LinkedList

interface LRUCache<K: Any, V: Any> {
    val size: Int

    fun get(key: K): V?
    fun put(key: K, value: V)
}

class LRUCacheImpl<K: Any, V: Any>(private val capacity: Int) : LRUCache<K, V> {
    override val size: Int get() = dict.size

    private val dict: HashMap<K, V> = hashMapOf()
    private val accessHistory: LinkedList<K> = LinkedList()


    override fun get(key: K): V? {
        val result = dict[key]
        if(accessHistory.lastOrNull() != key) accessHistory.add(key)
        if(accessHistory.firstOrNull() == key) accessHistory.removeFirst()
        return result
    }

    override fun put(key: K, value: V) {
        dict[key] = value
        accessHistory.add(key)
        if (this.size > capacity) {
            dict.remove(accessHistory.removeFirst())
        }

    }
}
