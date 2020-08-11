package com.example.farkle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Game {

    static List<Integer> diceValue, straight, activeDiceValue;
    static Dice[] dice;
    static boolean pTurn;

    public static void init() {
        diceValue = new ArrayList<Integer>();
        dice = new Dice[6];
        for (int i=0;i<6;i++) {
            dice[i] = new Dice();
        }
        straight = new ArrayList<>();
        for (int i=1;i<7;i++) {
            straight.add(i);
        }
        activeDiceValue = diceValue;
    }

    public static int score(ArrayList<Integer> dice) {
        ArrayList<Integer> freq = new ArrayList<Integer>();
        for (int i=0;i<6;i++) {
            freq.add(Collections.frequency(dice, i));
        }
        //Straight
        if (Collections.frequency(freq, 1) == 6) return Settings.STRAIGHT;
        //Six of a kind
        if (freq.contains(6)) return Settings.SIX_OF_A_KIND;
        //Three pair
        if (Collections.frequency(freq, 2) == 3) return Settings.THREE_PAIR;
        //Triplets
        if (Collections.frequency(freq, 3) == 2) return Settings.TRIPLETS;
        //Kind of full house
        if (Collections.frequency(freq, 4) == 1 && Collections.frequency(freq, 2) == 1) return Settings.FULL_HOUSE;

        //5 of a kind
        if (Collections.frequency(freq, 5) == 1) {
            if (freq.get(0) == 1) return Settings.FIVE_OF_A_KIND + 100;
            if (freq.get(4) == 1) return Settings.FIVE_OF_A_KIND + 50;
            return Settings.FIVE_OF_A_KIND;
        }

        //4 of a kind
        if (Collections.frequency(freq, 4) == 1) {
            int cnt = 1000;
            if (freq.get(0) != 4) cnt += (freq.get(0) * 100);
            if (freq.get(4) != 4) cnt += (freq.get(4) * 50);
            return cnt;
        }

        //3 of a kind
        if (Collections.frequency(freq, 3) == 1) {
            int ind = freq.indexOf(3);
            if (ind != 0 && ind != 4) return (ind * 100 + (freq.get(0) * 100) + (freq.get(4) * 100));
            if (ind == 0) return (1000 + (freq.get(4) * 50));
            return (ind * 100 + (freq.get(0) * 100));
        }
        return ((freq.get(0) * 100) + (freq.get(4) * 100));
    }
}
