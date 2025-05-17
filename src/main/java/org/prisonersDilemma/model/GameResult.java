package org.prisonersDilemma.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Getter

public class GameResult {
    private final String strategy1Name;
    private final String strategy2Name;
    private final int iterations;
    private final int score1;
    private final int score2;
    private final LocalDateTime timestamp;
    private final List<Move> movesStrategy1;
    private final List<Move> movesStrategy2;

    public GameResult(String strategy1Name, String strategy2Name, int iterations,
                      int score1, int score2, List<Move> movesStrategy1, List<Move> movesStrategy2) {
        this.strategy1Name = strategy1Name;
        this.strategy2Name = strategy2Name;
        this.iterations = iterations;
        this.score1 = score1;
        this.score2 = score2;
        this.timestamp = LocalDateTime.now();
        this.movesStrategy1 = new ArrayList<>(movesStrategy1);
        this.movesStrategy2 = new ArrayList<>(movesStrategy2);
    }

    public String getWinner() {
        if (score1 > score2) {
            return strategy1Name;
        } else if (score2 > score1) {
            return strategy2Name;
        } else {
            return "Remis";
        }
    }

    public double getAverageScore1() {
        return (double) score1 / iterations;
    }

    public double getAverageScore2() {
        return (double) score2 / iterations;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format(
                "Wynik symulacji [%s]:%n" +
                        "Strategia 1: %s, Punkty: %d, Średnio per runda: %.2f%n" +
                        "Strategia 2: %s, Punkty: %d, Średnio per runda: %.2f%n" +
                        "Liczba rund: %d%n" +
                        "Zwycięzca: %s",
                timestamp.format(formatter),
                strategy1Name, score1, getAverageScore1(),
                strategy2Name, score2, getAverageScore2(),
                iterations,
                getWinner()
        );
    }

}
