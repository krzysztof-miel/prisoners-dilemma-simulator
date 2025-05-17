package org.prisonersDilemma.strategies;


import org.prisonersDilemma.model.Move;

import java.util.List;

/**
 * Strategia, która zaczyna od współpracy, ale jeśli przeciwnik choć raz zdradzi,
 * zawsze zdradza w przyszłości.
 */

public class Grudger implements Strategy {

    @Override
    public String getName() {
        return "Grudger";
    }

    @Override
    public Move makeMove(List<Move> myMoves, List<Move> opponentMoves) {
        if (opponentMoves.isEmpty()) {
            return Move.COOPERATE;
        }

        for (Move move : opponentMoves) {
            if (move == Move.DEFECT) {
                return Move.DEFECT;
            }
        }

        return Move.COOPERATE;
    }

}
