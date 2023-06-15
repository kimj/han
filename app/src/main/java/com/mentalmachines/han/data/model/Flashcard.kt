package com.mentalmachines.han.data.model

import android.R.string
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Flashcard {
    @PrimaryKey
    var flashcard_id: Int = 0

    var fields = arrayOfNulls<string>(2)

    constructor()
    constructor(flashcard_id: Int, fields: Array<string?>) {
        this.flashcard_id = 0
        this.fields = fields
    }
}