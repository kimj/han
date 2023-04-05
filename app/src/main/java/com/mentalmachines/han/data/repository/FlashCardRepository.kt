package com.mentalmachines.han.data.repository

import com.mentalmachines.han.data.model.Deck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FlashCardRepository(private val database: FlashCardDatabase) {
    var grammar: List<Deck>? = null

    /*val videos: LiveData<List<DevByteVideo>> = Transformations.map(database.videoDao.getVideos()) {
        it.asDomainModel()
    }*/

    suspend fun refreshFlashCardList() {
        withContext(Dispatchers.IO) {
            val playlist = DevByteNetwork.devbytes.getPlaylist()
            database.flashCardDao().insertAll(playlist.asDatabaseModel())
        }
    }


}