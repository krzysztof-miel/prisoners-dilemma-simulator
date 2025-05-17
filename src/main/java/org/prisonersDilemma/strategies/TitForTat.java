package org.prisonersDilemma.strategies;


import org.prisonersDilemma.model.Move;

import java.util.List;

/**
 * Strategia, która zaczyna od współpracy, a następnie powtarza ostatni ruch przeciwnika.
 */
public class TitForTat implements Strategy {

    @Override
    public String getName() {
        return "Tit for Tat";
    }

    @Override
    public Move makeMove(List<Move> myMoves, List<Move> opponentMoves) {
        if (opponentMoves.isEmpty()) {
            return Move.COOPERATE;
        }

        return opponentMoves.get(opponentMoves.size() - 1);
    }


}
