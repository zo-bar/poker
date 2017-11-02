package com.zoya.miniprojects.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Deck {
	public enum SUITS {
		hearts,
		diamonds,
		clubs,
		spades
	};
	
	public enum RANKS {
		two,
		three,
		four,
		five,
		six,
		seven,
		eight,
		nine,
		ten,
		jack,
		queen,
		king,
		ace
	};
	
	private LinkedList<Card> cards;
	public Deck(){
		cards = new LinkedList<>();
		for (SUITS s: SUITS.values()){
			for (RANKS r: RANKS.values()){
				Card c = new Card(s,r);
				cards.add(c);
			}
		}
	}
	
	public void shuffle(){
		Collections.shuffle(cards);
	}
	
	public Card dealCard(){
		if (cards.size() == 0)
			return null;
		return cards.poll();
	}
}
