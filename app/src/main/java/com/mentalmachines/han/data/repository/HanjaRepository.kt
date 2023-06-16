package com.mentalmachines.han.data.repository

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HanjaRepository @Inject constructor(
    private val HanjaDao: HanjaDao
) {

    // fun getHanja() = HanjaDao.getHanja()
}
