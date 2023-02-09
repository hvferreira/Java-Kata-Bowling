package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Bowling {


    private final int MAXNUMROLL = 10;//total roll
    private final int CONVERTERSLACH = -1;//total roll
    private ArrayList<Integer> frame;
    private int total; // total game

    HashMap<Integer, Integer> boolStrike = new HashMap<>();
    HashMap<Integer, Integer> boolSpace = new HashMap<>();

    public Bowling() {
        this.total = 0;
        frame = new ArrayList<Integer>();
    }


    public int total(String rolls) {
        sumRolls(rolls.split(" "));

        return sumFrames();
    }

    //sum all rools
    private void sumRolls(String[] arrayRolls) {

        int num = 0;

        for (int i = 0; i < arrayRolls.length; i++) {
            num = convertCharatertoInt(String.valueOf((arrayRolls[i].charAt(0))));


            System.out.println();

            if (num == MAXNUMROLL && arrayRolls[i].charAt(0) == 'X') {
                boolStrike.put(i, 1);
                if (boolStrike.containsKey(i - 1)) {
                    frame.set(i - 1, frame.get(i - 1) + num);
                    if (boolStrike.containsKey(i - 2)) {
                        frame.add(num);
                        //frame.add(num + frame.get(i - 1) + num + frame.get(i - 2) + num);
                        frame.set(i - 2, frame.get(i - 2) + num);
                        //frame.set(i - 1, frame.get(i - 1) + num);
                    } else {
                        //frame.add(i - 1, frame.get(i - 1) + num);
                        frame.add(num);
                    }

                } else {
                    frame.add(num);
                }


            } else if ((arrayRolls[i].charAt(1)) == CONVERTERSLACH) {

                num = num + MAXNUMROLL - Integer.parseInt(String.valueOf(arrayRolls[i].charAt(0)));
                frame.add(num);
            } else {


                num = num + convertCharatertoInt(String.valueOf(arrayRolls[i].charAt(1)));
                if (boolStrike.containsKey(i - 1)) {
                    frame.set(i - 1, frame.get(i - 1) + num);
                }
                frame.add(num);
            }


        }


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

        for (int i : frame) {
            total = total + i;
            System.out.print(i + " ");
        }
        return total;
    }
}
