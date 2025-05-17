package org.prisonersDilemma.strategies;


import org.prisonersDilemma.model.Move;

import java.util.List;

/**
 * Strategia, która zdradza dopiero po dwóch kolejnych zdradach przeciwnika.
 */

public class TitForTwoTats implements Strategy {

    @Override
    public String getName() {
        return "Tit for Two Tats";
    }

    @Override
    public Move makeMove(List<Move> myMoves, List<Move> opponentMoves) {
        if (opponentMoves.size() < 2) {
            return Move.COOPERATE;
        }

        if (opponentMoves.get(opponentMoves.size() - 1) == Move.DEFECT &&
                opponentMoves.get(opponentMoves.size() - 2) == Move.DEFECT) {
            return Move.DEFECT;
        }

        return Move.COOPERATE;
    }

}
