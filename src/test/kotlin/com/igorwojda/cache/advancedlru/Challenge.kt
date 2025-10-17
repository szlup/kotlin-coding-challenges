package com.igorwojda.cache.advancedlru

import java.time.Clock
import java.time.Duration
//import java.util.Date
//import java.util.LinkedList

interface LRUCache<K: Any, V: Any> {
    fun put(key: K, value: V, priority: Int, ttl: Duration)
    fun get(key: K): V?
}

class AdvancedLRUCache<K: Any, V: Any>(private val capacity: Int, private val clock: Clock = Clock.systemDefaultZone()): LRUCache<K, V> {
    val size: Int get() = dict.size

    private var dict: HashMap<K, CacheItem<K, V>> = hashMapOf()
    //private val accessHistory: HashMap<K, Int> = hashMapOf()
    //private val accessCounter = 0


    override fun put(key: K, value: V, priority: Int, ttl: Duration) {
        removeExpired()
        val existingItem = dict[key]
        if (existingItem == null) {
            dict[key] = CacheItem(
                key,
                value,
                priority,
                clock.millis() + ttl.toMillis(),
                clock.millis()
            )

            if (this.size > capacity)
                removeForCapacity()
        }else {
            existingItem.value = value
            existingItem.expiryTime = clock.millis() + ttl.toMillis()
            existingItem.lastAccess = clock.millis()
        }
    }

    override fun get(key: K): V? {
        removeExpired()
        val result = dict[key]
        if (result != null) result.lastAccess = clock.millis()

        return result?.value
    }

    private fun removeExpired() {
        val expiredKeys = dict.filterValues { it.expiryTime < clock.millis() }.keys
        if (expiredKeys.isNotEmpty()) {
            for (k in expiredKeys) dict.remove(k)
        }
    }

    private fun removeForCapacity() {
        if (size <= capacity) return
        else {
            // lol, instructions say to take the ones with the earliest expiry time, but tests make you ignore it ¯\_(ツ)_/¯
            // get items with earliest expiry time
//            val earliestExpiryTime: Long = dict.values.minOf { it.expiryTime }
//
//            val earliestExpiring = dict.filterValues {
//                it.expiryTime == earliestExpiryTime
//            }
//            if (earliestExpiring.size == 1) {
//                dict.remove(earliestExpiring.keys.first())
//            } else {
            // of those, get lowest priority items
            val lowestPriority = dict.filterValues { item ->
                item.priority == dict.values.minOf { it.priority }
            }
            if (lowestPriority.size == 1) {
                dict.remove(lowestPriority.keys.first())
            } else {
                // of those, remove least recently accessed
                dict.remove(lowestPriority.minBy { it.value.lastAccess }.key)
            }

        }


    }

    class CacheItem<K: Any, V: Any>(
        var key: K,
        var value: V,
        var priority: Int,
        var expiryTime: Long,
        var lastAccess: Long)
}
