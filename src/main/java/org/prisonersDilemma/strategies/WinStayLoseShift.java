package org.prisonersDilemma.strategies;
import org.prisonersDilemma.model.Move;

import java.util.List;


/**
 * Strategia, która kontynuuje swój ostatni ruch, jeśli przyniósł korzystny wynik;
 * zmienia go, jeśli wynik był niekorzystny.
 */

public class WinStayLoseShift implements Strategy {


    @Override
    public String getName() {
        return "Win-Stay, Lose-Shift";
    }

    @Override
    public Move makeMove(List<Move> myMoves, List<Move> opponentMoves) {
        if (myMoves.isEmpty() || opponentMoves.isEmpty()) {
            return Move.COOPERATE;
        }

        Move myLastMove = myMoves.get(myMoves.size() - 1);
        Move opponentLastMove = opponentMoves.get(opponentMoves.size() - 1);

        boolean wonLastRound = false;

        if (myLastMove == Move.COOPERATE && opponentLastMove == Move.COOPERATE) {
            wonLastRound = true;
        } else if (myLastMove == Move.DEFECT && opponentLastMove == Move.COOPERATE) {
            wonLastRound = true;
        }

        if (wonLastRound) {
            return myLastMove;
        } else {
            return (myLastMove == Move.COOPERATE) ? Move.DEFECT : Move.COOPERATE;
        }
    }




}
