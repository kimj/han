package com.mentalmachines.han.data.repository

import com.mentalmachines.han.data.model.Deck
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlashCardRepository @Inject constructor(
    private val flashCardDao: FlashCardDao
) {
    var flashCards: List<Deck>? = null

    /*val videos: LiveData<List<DevByteVideo>> = Transformations.map(database.videoDao.getVideos()) {
        it.asDomainModel()
    }*/

    fun refreshFlashCardList() {

    }


}