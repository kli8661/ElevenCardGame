package sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Board {
    private Card[] cards;
    private Deck deck;
    private static final boolean I_AM_DEBUGGING = false;

    public Board(int var1, String[] var2, String[] var3, int[] var4) {
        this.cards = new Card[var1];
        this.deck = new Deck(var2, var3, var4);
        this.dealMyCards();
    }

    public void newGame() {
        this.deck.shuffle();
        this.dealMyCards();
    }

    public int size() {
        return this.cards.length;
    }

    public boolean isEmpty() {
        for(int var1 = 0; var1 < this.cards.length; ++var1) {
            if (this.cards[var1] != null) {
                return false;
            }
        }

        return true;
    }

    public void deal(int var1) {
        this.cards[var1] = this.deck.deal();
    }

    public int deckSize() {
        return this.deck.size();
    }

    public Card cardAt(int var1) {
        return this.cards[var1];
    }

    public void replaceSelectedCards(List<Integer> var1) {
        Iterator var2 = var1.iterator();

        while(var2.hasNext()) {
            Integer var3 = (Integer)var2.next();
            this.deal(var3);
        }

    }

    public List<Integer> cardIndexes() {
        ArrayList var1 = new ArrayList();

        for(int var2 = 0; var2 < this.cards.length; ++var2) {
            if (this.cards[var2] != null) {
                var1.add(new Integer(var2));
            }
        }

        return var1;
    }

    public String toString() {
        String var1 = "";

        for(int var2 = 0; var2 < this.cards.length; ++var2) {
            var1 = var1 + var2 + ": " + this.cards[var2] + "\n";
        }

        return var1;
    }

    public boolean gameIsWon() {
        if (this.deck.isEmpty()) {
            Card[] var1 = this.cards;
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Card var4 = var1[var3];
                if (var4 != null) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public abstract boolean isLegal(List<Integer> var1);

    public abstract boolean anotherPlayIsPossible();

    private void dealMyCards() {
        for(int var1 = 0; var1 < this.cards.length; ++var1) {
            this.cards[var1] = this.deck.deal();
        }

    }
}
