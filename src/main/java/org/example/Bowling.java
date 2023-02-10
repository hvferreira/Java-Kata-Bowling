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

            if (num == MAXNUMROLL && arrayRolls[i].charAt(0) == 'X') {
                calculationStrike(num, i);

            } else if (i != 10 && convertCharatertoInt(String.valueOf((arrayRolls[i].charAt(1)))) == CONVERTERSLACH) {
                calculationSpare(num, i);
            } else {

                if (boolSpace.containsKey(i - 1) && i != 10) {
                    frame.set(i - 1, frame.get(i - 1) + num);
                }

                if (i != 10) {
                    num = num + convertCharatertoInt(String.valueOf(arrayRolls[i].charAt(1)));
                }


                if (boolStrike.containsKey(i - 1)) {
                    frame.set(i - 1, frame.get(i - 1) + num);
                    if (boolStrike.containsKey(i - 2)) {
                        frame.set(i - 2, frame.get(i - 2) + num);
                    }
                    frame.add(num);
                } else
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

    private void calculationStrike(int num, int i) {

        boolStrike.put(i, 1);
        if (boolStrike.containsKey(i - 1) && i < 10) {
            frame.set(i - 1, frame.get(i - 1) + num);
            if (boolStrike.containsKey(i - 2)) {
                frame.add(num);
                frame.set(i - 2, frame.get(i - 2) + num);
            } else {
                frame.add(num);
            }
        } else if (i >= 10) { //for last frame
            frame.add(num);
            if (boolStrike.containsKey(10) && boolStrike.containsKey(11) && boolStrike.containsKey(8)) {
                frame.set(8, 30);
            }
        } else {
            if (boolSpace.containsKey(i - 1)) {
                frame.set(i - 1, frame.get(i - 1) + num);
            }
            frame.add(num);
        }
    }

    private void calculationSpare(int num, int i) {
        boolSpace.put(i, 1);
        int firstCharacter = num;
        if (boolSpace.containsKey(i - 1) && i != 10) {
            frame.set(i - 1, frame.get(i - 1) + num);
        }
        num = num + MAXNUMROLL - num;
        if (boolStrike.containsKey(i - 1) && 0 != firstCharacter) {
            frame.set(i - 1, frame.get(i - 1) + num);
        }
        frame.add(num);

    }
}
