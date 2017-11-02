package com.zoya.miniprojects.poker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Game {
	private Deck deck;
	private ArrayList<Player> players;
	private List<Card> communityCards;
	private int button;

	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public Game(int playersCount) {
		this.deck = new Deck();
		this.players = new ArrayList<>();
		for (int i = 0; i < playersCount; i++) {
			players.add(new Player(String.valueOf(i), 100));
		}
		this.communityCards = new ArrayList<>();
		button = (int) Math.random() * playersCount;
	}

	public void deal() {
		deck.shuffle();
		for (Player player : players) {
			player.addCard(deck.dealCard());
			player.addCard(deck.dealCard());
			player.printState();
		}
	}

	public void makeBets(int minBet) {
		int i = button;
		int minBetIndex = button;
		if (players.get(i).isInGame() && players.get(i).getBet() == 0) {
			// start of the game, make initial bets
			players.get(i).setBet(minBet);
			minBet = minBet * 2;
			i = i > players.size() ? 0 : i + 1;
			players.get(i).setBet(minBet);
			minBetIndex = i;
			i++;
		}

		do {
			Player p = players.get(i % players.size());
			if (p.isInGame()) {
				try {
					int pbet = -1;
					while (p.isInGame() && pbet < minBet) {
						System.out.println("Player " + p.getName()
								+ ", please, make your bet. Minimum bet is "
								+ minBet + ", Type '0' to finish this round");
						pbet = Integer.valueOf(in.readLine());
						if (pbet == 0) {
							p.out();
						}
					}
					if (p.isInGame()) {
						if (pbet > minBet) {
							minBet = pbet;
							minBetIndex = i;
						}
						p.setBet(pbet);
					}
					i++;
				} catch (IOException e) {
					System.out.println("Wrong number, please, try again");
				}
			} else {
				i++;
			}
		} while (minBetIndex % players.size() != i % players.size());
	}
	
	private int activePlayersCount(){
		return (int) players.stream().filter(p->p.isInGame()).count();
	}
	

	public void drawCommunityCard() {
		if (communityCards == null || communityCards.size() >= 5) {
			System.out.println("Unable to draw communitycard");
			return;
		}
		this.communityCards.add(deck.dealCard());
	}

	public void printState() {
		System.out.println("Current state: ");
		for (Player p : players) {
			p.printState();
		}
		System.out.println("CommunityCards: " + communityCards);
	}

	public void playRound() {
		for (Player p : players) {
			p.in();
		}
		deal();
		makeBets(2);
		drawCommunityCard();
		drawCommunityCard();
		drawCommunityCard();
		printState();
		makeBets(2);
		drawCommunityCard();
		printState();
		makeBets(2);
		drawCommunityCard();
		printState();
	}

	public static void main(String[] args) {
		Game game = new Game(3);
		game.playRound();

	}
}
