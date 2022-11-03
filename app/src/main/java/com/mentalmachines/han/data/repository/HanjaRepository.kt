package com.mentalmachines.han.data.repository

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HanjaRepository @Inject constructor(
    private val HanjaDao: HanjaDao
) {

    suspend fun createHanja(hanjaId: String) {
        val Hanja = Hanja(hanjaId)
        HanjaDao.insertHanja(Hanja)
    }

    suspend fun removeHanja(Hanja: Hanja) {
        HanjaDao.deleteHanja(Hanja)
    }

    fun getHanja() = HanjaDao.getHanja()
}

@Singleton
class HanjaRepositoryImpl @Inject constructor(
    private val HanjaDao: HanjaDao
)