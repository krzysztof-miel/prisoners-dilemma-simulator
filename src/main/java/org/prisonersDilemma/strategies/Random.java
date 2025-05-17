package org.prisonersDilemma.strategies;

import org.prisonersDilemma.model.Move;

import java.util.List;


/**
 * Strategia, która podejmuje decyzje o współpracy lub zdradzie losowo.
 */

public class Random implements Strategy {

    private final java.util.Random random = new java.util.Random();

    @Override
    public String getName() {
        return "Random";
    }

    @Override
    public Move makeMove(List<Move> myMoves, List<Move> opponentMoves) {
        return random.nextBoolean() ? Move.COOPERATE : Move.DEFECT;
    }

}
