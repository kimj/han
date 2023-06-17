package com.mentalmachines.han.data.repository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GrammarRepository @Inject constructor(
    private val GrammarDao: GrammarDao
) {
    var grammar: List<String>? = null

}

class GrammarRepositoryImpl {
    var grammar: List<String>? = null

}