package sample;

import java.util.List;

/**
 * The Thirteens class represents the board in a game of Thirteens.
 */
public class ThirteensBoard extends Board {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 10;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
            {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
            {"spades", "hearts", "diamonds", "clubs"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0};

    /**
     * Creates a new <code>ThirteensBoard</code> instance.
     */
    public ThirteensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Thirteens, the legal groups are (1) a pair of non-face cards
     * whose values add to 11, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     *         false otherwise.
     */
    @Override
    public boolean isLegal(List<Integer> selectedCards) {
        return (containsPairSum13(selectedCards) || containsK(selectedCards));
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Thirteens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 11, or (2) a group
     * of three cards consisting of a jack, a queen, and a king in some order.
     * @return true if there is a legal play left on the board;
     *         false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible() {
        return (containsPairSum13Whole(cardIndexes()) || containsKWhole(cardIndexes()));
    }

    /**
     * Check for an 11-pair in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 11-pair.
     * @return true if the board entries in selectedCards
     *              contain an 11-pair; false otherwise.
     */
    private boolean containsPairSum13(List<Integer> selectedCards) {
        int sum = 0;
        if(selectedCards.size() == 2)
        {
            for(int i : selectedCards)
                sum += this.cardAt(i).pointValue();
            return (sum == 13);
        }
        else if (selectedCards.size() == 9){
            for (int i = 0; i < selectedCards.size(); i++) {

                for (int j = i + 1; j < selectedCards.size(); j++) {
                    sum = this.cardAt(i).pointValue() + this.cardAt(j).pointValue();
                    if (sum == 13) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    private boolean containsPairSum13Whole(List<Integer> selectedCards) {
        for(int i=0;i<selectedCards.size()-1;i++)
        {
            for(int j=i+1;j<selectedCards.size();j++){
                if(cardAt(selectedCards.get(i)).pointValue()+cardAt(selectedCards.get(j)).pointValue()==13){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check for a JQK in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a JQK group.
     * @return true if the board entries in selectedCards
     *              include a jack, a queen, and a king; false otherwise.
     */
    private boolean containsK(List<Integer> selectedCards) {
        boolean k = false;
        for(int i = 0; i < selectedCards.size(); i++)
        {
            if(selectedCards.size() == 1 && cardAt(selectedCards.get(i)).rank().contains("king"))
            {
                k = true;
            }
        }
        return k;
    }

    private boolean containsKWhole(List<Integer> selectedCards)
    {
        boolean k = false;
        for(int i = 0; i < selectedCards.size(); i++)
        {
            if(cardAt(selectedCards.get(i)).rank().contains("king"))
            {
                k = true;
            }
        }
        return k;
    }
}
