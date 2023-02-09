package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class BowlingTest {

    //->Requirements
//-> play is called is frame
//-> each frame has 2 rolls
//-> if in both plays you leave minus 10 below the points are the ones lying down.
//-> if in the first move you do strike ends the frame, and next frame
//-> in the frame after the strike frame, strike becomes 10+ dakele frame and the frame becomes newStrike plus itself.
//-> 10 frame is different from the others is played individually each move of the frame
//-> game total is sum of takes to frames

//-> frame 10 you don't always have 3 rolls depends if you do strike or spare
//-> spare and strike give access 3 rolls in frame 10
//-> spare is no pin to stand on the second move
//-> strike is all on first roll
//-> number of 10 pins
//->

// - - - - - - - - - - - - - - - - - - - - -,0
// 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 -,20
// X X X X X X X X X X X X X X X X X X X X X,300




    @ParameterizedTest
    @CsvFileSource(resources = "/dataTest.csv", numLinesToSkip = 1)
    void checkTotal(String rolls,int total) {
        Bowling bowling = new Bowling();

        assertEquals(total, bowling.total(rolls));
    }


}