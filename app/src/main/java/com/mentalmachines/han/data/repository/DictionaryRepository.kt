package com.mentalmachines.han.data.repository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DictionaryRepository @Inject constructor(
    private val DictionaryDao: DictionaryDao
) {
    var dictionary: List<String>? = null
}