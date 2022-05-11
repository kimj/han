package com.mentalmachines.han.data.repository.base

class RepositoryImpl : Repository<Any?> {
    override fun add(item: Any?) {}
    override fun add(items: Iterable<*>?) {}
    override fun update(item: Any?) {}
    override fun remove(item: Any?) {}
    override fun remove(id: Int) {}
    override fun query(id: Int): List<*>? {
        return null
    }
}