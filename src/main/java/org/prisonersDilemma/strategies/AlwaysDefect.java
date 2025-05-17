package org.prisonersDilemma.strategies;

import org.prisonersDilemma.model.Move;

import java.util.List;

/**
 * Strategia, która zawsze zdradza, niezależnie od ruchów przeciwnika.
 */

public class AlwaysDefect implements Strategy {

    @Override
    public String getName() {
        return "Always Defect";
    }

    @Override
    public Move makeMove(List<Move> myMoves, List<Move> opponentMoves) {
        return Move.DEFECT;
    }
}
