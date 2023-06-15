package com.mentalmachines.han.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Flashcard {
    @PrimaryKey
    var flashcard_id: Int = 0
    // var fields = ArrayList<String>()

    constructor()
    constructor(flashcard_id: Int) {
        this.flashcard_id = 0
    }
}