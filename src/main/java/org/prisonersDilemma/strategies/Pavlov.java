package org.prisonersDilemma.strategies;
import org.prisonersDilemma.model.Move;
import java.util.List;


/**
 * Strategia Pavlov stosuje regułę "wygraj-zostań, przegraj-zmień".
 * Kontynuuje swoją poprzednią decyzję, jeśli przyniosła dobry wynik;
 * zmienia ją, jeśli wynik był niekorzystny.
 */

public class Pavlov implements Strategy {

    @Override
    public String getName() {
        return "Pavlov";
    }

    @Override
    public Move makeMove(List<Move> myMoves, List<Move> opponentMoves) {
        if (myMoves.isEmpty() || opponentMoves.isEmpty()) {
            return Move.COOPERATE;
        }

        Move myLastMove = myMoves.get(myMoves.size() - 1);
        Move opponentLastMove = opponentMoves.get(opponentMoves.size() - 1);

        if ((myLastMove == Move.COOPERATE && opponentLastMove == Move.COOPERATE) ||
                (myLastMove == Move.DEFECT && opponentLastMove == Move.DEFECT)) {
            return myLastMove;
        } else {
            return (myLastMove == Move.COOPERATE) ? Move.DEFECT : Move.COOPERATE;
        }
    }

}
