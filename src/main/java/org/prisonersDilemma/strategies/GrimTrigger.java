package org.prisonersDilemma.strategies;


import org.prisonersDilemma.model.Move;

import java.util.List;

/**
 * Strategia, która współpracuje, dopóki przeciwnik nie zdradzi,
 * a wtedy bezwarunkowo zdradza do końca gry.
 */


public class GrimTrigger implements Strategy {
    @Override
    public String getName() {
        return "Grim Trigger";
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
