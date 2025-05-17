package org.prisonersDilemma.strategies;

import org.prisonersDilemma.model.Move;

import java.util.List;


/**
 * Strategia, która zawsze współpracuje, niezależnie od ruchów przeciwnika.
 */
public class AlwaysCooperate implements Strategy {

    @Override
    public String getName() {
        return "Always Cooperate";
    }

    @Override
    public Move makeMove(List<Move> myMoves, List<Move> opponentMoves) {
        return Move.COOPERATE;
    }

}
