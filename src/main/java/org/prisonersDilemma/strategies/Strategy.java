package org.prisonersDilemma.strategies;

import org.prisonersDilemma.model.Move;

import java.util.List;

public interface Strategy {

    String getName();

    Move makeMove(List<Move> myMoves, List<Move> opponentMoves);

    default void reset() {};
}
