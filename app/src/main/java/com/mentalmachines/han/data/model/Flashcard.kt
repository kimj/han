package com.mentalmachines.han.data.model

import android.R.string

class Flashcard {
    var flashcard_id = 0
    var fields = arrayOfNulls<string>(2)

    constructor()
    constructor(flashcard_id: Int, fields: Array<string?>) {
        this.flashcard_id = 0
        this.fields = fields
    }
}