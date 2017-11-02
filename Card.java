package com.zoya.miniprojects.poker;

import com.zoya.miniprojects.poker.Deck.RANKS;
import com.zoya.miniprojects.poker.Deck.SUITS;

public class Card {
	private Deck.SUITS suit;
	private Deck.RANKS rank;

	public Card(SUITS suit, RANKS rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}

	public Deck.SUITS getSuit() {
		return suit;
	}

	public Deck.RANKS getRank() {
		return rank;
	}

	public String toString() {
		return suit + " " + rank;
	}
}
