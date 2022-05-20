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

    fun isPlanted(plantId: String) =
        HanjaDao.isPlanted(plantId)

    fun getHanja() = HanjaDao.getHanja()
}