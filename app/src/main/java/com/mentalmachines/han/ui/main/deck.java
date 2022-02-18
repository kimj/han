package com.mentalmachines.cjkdroid;

import java.util.ArrayList;


public class deck {
	// deck_id is a unique identifier
	int deck_id;

	// ---------------------------------------------------
	// flashcards is an array of flashcard(int, string[])
	// ---------------------------------------------------
	ArrayList flashcards = new ArrayList();

	int flashcard_count;
	
	// constructors
	public deck(){};
	public deck(ArrayList flashcards) {
		this.deck_id = 0;
		this.flashcards = flashcards;
		this.flashcard_count = flashcards.size();
	}
	
	// get/set methods
	private int get_deck_id() {
		return this.deck_id;
	}

	private ArrayList get_flashcards() {
		return this.flashcards;
	}

	private int get_flashcard_count() {
		return this.flashcard_count;
	}
	
	private void set_flashcards(ArrayList cards){
		this.flashcards = cards;
	}
	
	// methods
	public void add_flashcard(flashcard new_flashcard){
		this.flashcards.add(new_flashcard);	
	}
	
	public void delete_flashcard(flashcard new_flashcard){
		this.flashcards.add(new_flashcard);	
	}	
}
