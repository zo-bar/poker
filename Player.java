package com.zoya.miniprojects.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Player {
	private String name;
	private ArrayList<Card> cards = new ArrayList<>();
	private int money;
	private int bet;

	public Player(String name, int money) {
		this.name = name;
		this.money = money;
	}

	public void in() {
		cards = new ArrayList<>();
	}

	public void out() {
		money -= bet;
		bet = 0;
		cards = null;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public int getBet() {
		return bet;
	}

	public String getName() {
		return name;
	}

	public boolean addCard(Card c) {
		if (cards != null) {
			cards.add(c);
			return true;
		}
		return false;
	}

	public boolean isInGame() {
		return cards != null;
	}

	public void printState() {
		if (isInGame()) {
			System.out.println("Player " + name + " has cards " + cards
					+ ", money: " + money + ", bet: " + bet);
		} else {
			System.out.println("Player " + name + " is out, money: " + money);
		}
	}
}
