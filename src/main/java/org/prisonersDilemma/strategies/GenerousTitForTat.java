package org.prisonersDilemma.strategies;


import org.prisonersDilemma.model.Move;

import java.util.List;
import java.util.Random;

/**
 * Ulepszona wersja Tit for Tat z elementem losowego wybaczania.
 * Z pewnym prawdopodobieństwem (domyślnie 10%) wybacza zdradę przeciwnika.
 */

public class GenerousTitForTat implements Strategy {

    private final Random random = new java.util.Random();

    private final double forgivenessProbability;

    public GenerousTitForTat() {
        this(0.1);
    }
    public GenerousTitForTat(double forgivenessProbability) {
        this.forgivenessProbability = forgivenessProbability;
    }

    @Override
    public String getName() {
        return "Generous Tit for Tat";
    }


    @Override
    public Move makeMove(List<Move> myMoves, List<Move> opponentMoves) {

        if (opponentMoves.isEmpty()) {
            return Move.COOPERATE;
        }

        Move opponentLastMove = opponentMoves.get(opponentMoves.size() - 1);

        if (opponentLastMove == Move.COOPERATE) {
            return Move.COOPERATE;
        }

        if ( random.nextDouble() < forgivenessProbability) {
            return Move.COOPERATE;
        }

        return Move.DEFECT;
    }
}
