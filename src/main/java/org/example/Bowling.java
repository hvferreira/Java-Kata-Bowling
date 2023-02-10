package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Bowling {


    private final int MAXNUMROLL = 10;
    private final int CONVERTERSLACH = -1;
    private final int POINTS30 = 30;
    private final int FRAMETEN = 10;
    private final int FRAMEEIGHT = 8;
    private final int FRAMEELEVEN = 11;
    private final int FIRSTPOSITIONFRAME = 0;
    private final int SECONDTPOSITIONFRAME = 1;
    private ArrayList<Integer> frame;
    private int total; // total game

    HashMap<Integer, Integer> boolStrike;
    HashMap<Integer, Integer> boolSpace;

    public Bowling() {
        this.total = 0;
        frame = new ArrayList<Integer>();
        boolStrike = new HashMap<>();
        boolSpace = new HashMap<>();
    }


    public int total(String rolls) {
        sumRolls(rolls.split(" "));
        return sumFrames();
    }

    //sum all rools
    private void sumRolls(String[] arrayRolls) {

        int num = 0;
        for (int i = 0; i < arrayRolls.length; i++) {
            num = convertCharatertoInt(String.valueOf((arrayRolls[i].charAt(FIRSTPOSITIONFRAME))));

            if (num == MAXNUMROLL && arrayRolls[i].charAt(FIRSTPOSITIONFRAME) == 'X') {
                calculationStrike(num, i);

            } else if (i != 10 && convertCharatertoInt(String.valueOf((arrayRolls[i].charAt(SECONDTPOSITIONFRAME)))) == CONVERTERSLACH) {
                calculationSpare(num, i);

            } else {
                calculationFrame(num, i, arrayRolls);
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
            if (boolStrike.containsKey(FRAMETEN) && boolStrike.containsKey(FRAMEELEVEN) && boolStrike.containsKey(8)) {
                frame.set(FRAMEEIGHT, POINTS30);
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

    private void calculationFrame(int num, int i, String[] arrayRolls) {

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
