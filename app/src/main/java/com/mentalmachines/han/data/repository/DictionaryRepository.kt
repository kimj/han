package com.mentalmachines.han.data.repository

class DictionaryRepository(private val database: DictionaryDatabase) {
    var dictionary: List<String>? = null
}