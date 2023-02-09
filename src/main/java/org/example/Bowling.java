package org.example;

import java.util.ArrayList;

public class Bowling {


    private final int MAXNUMROLL = 10;//total roll
    private final int CONVERTERSLACH = -1;//total roll
    private ArrayList<Integer> Strike;
    private int total; // total game

    public Bowling() {
        this.total = 0;
        Strike = new ArrayList<Integer>();
    }


    public int total(String rolls) {
        return sumRolls(rolls.split(" "));
    }

    //sum all rools
    private int sumRolls(String[] arrayRolls) {

        int num = 0;
        int frame = 0;
        for (int i = 0; i < arrayRolls.length; i++) {
            num = convertCharatertoInt(arrayRolls[i]);

            if (num == CONVERTERSLACH) {
                num = MAXNUMROLL - Integer.parseInt(arrayRolls[i - 1]);
                //System.out.println(num);
            }


            total = total + num;
        }

        return total;
    }

    private int convertCharatertoInt(String roll) {

        return switch (roll) {
            case "X" -> 10;
            case "-" -> 0;
            case "/" -> -1;
            default -> Integer.parseInt(roll);
        };
    }

    private int sumFrames() {


        return total;
    }
}
