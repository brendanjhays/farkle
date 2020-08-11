package com.example.farkle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Dice {

    public int num = 1;
    public boolean lock = false;

    Random rand;

    public Dice() {
        rand = new Random();
    }

    public void roll() {
        num = rand.nextInt(6) - 1;
    }

    public void lock(boolean state) {
        lock = state;
    }
}
