package com.mentalmachines.han.data.model

class Deck {
    // deck_id is a unique identifier
    var deck_id = 0

    // ---------------------------------------------------
    // flashcards is an array of flashcard(int, string[])
    // ---------------------------------------------------
    var flashcards: ArrayList<*> = ArrayList<Any?>()
    var flashcard_count = 0

    // constructors
    constructor()
    constructor(flashcards: ArrayList<*>) {
        deck_id = 0
        this.flashcards = flashcards
        flashcard_count = flashcards.size
    }

    // get/set methods
    private fun get_deck_id(): Int {
        return deck_id
    }

    private fun get_flashcards(): ArrayList<*> {
        return flashcards
    }

    private fun get_flashcard_count(): Int {
        return flashcard_count
    }

    private fun set_flashcards(cards: ArrayList<*>) {
        flashcards = cards
    }

    // methods
    fun add_flashcard(new_flashcard: Flashcard?) {
        flashcards.add(new_flashcard)
    }

    fun delete_flashcard(new_flashcard: Flashcard?) {
        flashcards.add(new_flashcard)
    }
}