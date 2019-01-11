package sample;

import java.util.Iterator;
import java.util.List;

public class ElevensBoard extends Board {
    private static final int BOARD_SIZE = 9;
    private static final String[] RANKS = new String[]{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
    private static final String[] SUITS = new String[]{"spades", "hearts", "diamonds", "clubs"};
    private static final int[] POINT_VALUES = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

    public ElevensBoard() {
        super(9, RANKS, SUITS, POINT_VALUES);
    }

    public boolean isLegal(List<Integer> var1) {
        if (var1.size() == 2) {
            return this.containsPairSum11(var1);
        } else {
            return var1.size() == 3 ? this.containsJQK(var1) : false;
        }
    }

    public boolean anotherPlayIsPossible() {
        List var1 = this.cardIndexes();
        return this.containsPairSum11(var1) || this.containsJQK(var1);
    }

    private boolean containsPairSum11(List<Integer> var1) {
        for(int var2 = 0; var2 < var1.size(); ++var2) {
            int var3 = (Integer)var1.get(var2);

            for(int var4 = var2 + 1; var4 < var1.size(); ++var4) {
                int var5 = (Integer)var1.get(var4);
                if (this.cardAt(var3).pointValue() + this.cardAt(var5).pointValue() == 11) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean containsJQK(List<Integer> var1) {
        boolean var2 = false;
        boolean var3 = false;
        boolean var4 = false;
        Iterator var5 = var1.iterator();

        while(var5.hasNext()) {
            Integer var6 = (Integer)var5.next();
            int var7 = var6;
            if (this.cardAt(var7).rank().equals("jack")) {
                var2 = true;
            } else if (this.cardAt(var7).rank().equals("queen")) {
                var3 = true;
            } else if (this.cardAt(var7).rank().equals("king")) {
                var4 = true;
            }
        }

        return var2 && var3 && var4;
    }

    public boolean playIfPossible() {
        return false;
    }

    public boolean playPairSum11IfPossible() {
        return false;
    }

    public boolean playJQKIfPossible() {
        return false;
    }
}
