package com.mentalmachines.han.data.repository.base

interface Repository<T> {
    fun add(item: T)
    fun add(items: Iterable<T>?)
    fun update(item: T)
    fun remove(item: T)
    fun remove(id: Int)
    fun query(id: Int): List<T>?
}