package org.prisonersDilemma;

import org.prisonersDilemma.model.GameResult;
import org.prisonersDilemma.model.Move;
import org.prisonersDilemma.strategies.Strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameSimulator {

    private static final int BOTH_COOPERATE_REWARD = 3;        // Obaj współpracują
    private static final int BOTH_DEFECT_PUNISHMENT = 1;       // Obaj zdradzają
    private static final int COOPERATE_AGAINST_DEFECT_SUCKER = 0; // Współpraca przeciwko zdradzie
    private static final int DEFECT_AGAINST_COOPERATE_TEMPTATION = 5; // Zdrada przeciwko współpracy

    public GameResult runSimulation(Strategy strategy1, Strategy strategy2, int iterations) {
        strategy1.reset();
        strategy2.reset();

        List<Move> movesStrategy1 = new ArrayList<>();
        List<Move> movesStrategy2 = new ArrayList<>();

        int score1 = 0;
        int score2 = 0;

        for (int i = 0; i < iterations; i++) {
            Move move1 = strategy1.makeMove(
                    new ArrayList<>(movesStrategy1),
                    new ArrayList<>(movesStrategy2));

            Move move2 = strategy2.makeMove(
                    new ArrayList<>(movesStrategy2),
                    new ArrayList<>(movesStrategy1));

            movesStrategy1.add(move1);
            movesStrategy2.add(move2);

            int[] roundScores = calculateScore(move1, move2);
            score1 += roundScores[0];
            score2 += roundScores[1];
        }

        return new GameResult(
                strategy1.getName(),
                strategy2.getName(),
                iterations,
                score1,
                score2,
                movesStrategy1,
                movesStrategy2
        );
    }

    public Map<String, Integer> runTournament(List<Strategy> strategies, int iterations) {
        Map<String, Integer> tournamentScores = new HashMap<>();
        List<GameResult> results = new ArrayList<>();

        for (Strategy strategy : strategies) {
            tournamentScores.put(strategy.getName(), 0);
        }

        for (int i = 0; i < strategies.size(); i++) {
            for (int j = i + 1; j < strategies.size(); j++) {
                Strategy strategy1 = strategies.get(i);
                Strategy strategy2 = strategies.get(j);

                GameResult result = runSimulation(strategy1, strategy2, iterations);
                results.add(result);

                tournamentScores.put(strategy1.getName(),
                        tournamentScores.get(strategy1.getName()) + result.getScore1());
                tournamentScores.put(strategy2.getName(),
                        tournamentScores.get(strategy2.getName()) + result.getScore2());
            }
        }

        return tournamentScores;
    }


    private int[] calculateScore(Move move1, Move move2) {
        int score1 = 0;
        int score2 = 0;

        if (move1 == Move.COOPERATE && move2 == Move.COOPERATE) {
            score1 = BOTH_COOPERATE_REWARD;
            score2 = BOTH_COOPERATE_REWARD;
        } else if (move1 == Move.COOPERATE && move2 == Move.DEFECT) {
            score1 = COOPERATE_AGAINST_DEFECT_SUCKER;
            score2 = DEFECT_AGAINST_COOPERATE_TEMPTATION;
        } else if (move1 == Move.DEFECT && move2 == Move.COOPERATE) {
            score1 = DEFECT_AGAINST_COOPERATE_TEMPTATION;
            score2 = COOPERATE_AGAINST_DEFECT_SUCKER;
        } else if (move1 == Move.DEFECT && move2 == Move.DEFECT) {
            score1 = BOTH_DEFECT_PUNISHMENT;
            score2 = BOTH_DEFECT_PUNISHMENT;
        }

        return new int[] {score1, score2};
    }


    public String getTournamentWinner(Map<String, Integer> tournamentScores) {
        return tournamentScores.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Brak zwycięzcy");
    }


    public String formatTournamentResults(Map<String, Integer> tournamentScores) {
        StringBuilder result = new StringBuilder();
        result.append("Wyniki turnieju:\n");

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(tournamentScores.entrySet());
        sortedEntries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            result.append(String.format("%s: %d punktów\n", entry.getKey(), entry.getValue()));
        }

        result.append("\nZwycięzca: ").append(getTournamentWinner(tournamentScores));

        return result.toString();
    }


}
