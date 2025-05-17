package org.prisonersDilemma;

import org.prisonersDilemma.model.GameResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ResultProcessor {

    private static final String SIMULATION_RESULTS_FILE = "wyniki_symulacji.txt";
    private static final String TOURNAMENT_RESULTS_FILE = "wyniki_turnieju.txt";

    public void saveSimulationResult(GameResult result) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SIMULATION_RESULTS_FILE, true))) {
            writer.println("=".repeat(50));
            writer.println("Data symulacji: " + formatDateTime(result.getTimestamp()));
            writer.println(result.toString());
            writer.println("=".repeat(50));
            writer.println();

            System.out.println("Wyniki symulacji zostały zapisane do pliku: " + SIMULATION_RESULTS_FILE);
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania wyników symulacji: " + e.getMessage());
        }
    }



    public void saveTournamentResults(Map<String, Integer> tournamentResults, String formattedResults) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TOURNAMENT_RESULTS_FILE, true))) {
            writer.println("=".repeat(50));
            writer.println("Data turnieju: " + formatDateTime(LocalDateTime.now()));
            writer.println(formattedResults);
            writer.println("=".repeat(50));
            writer.println();

            System.out.println("Wyniki turnieju zostały zapisane do pliku: " + TOURNAMENT_RESULTS_FILE);
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania wyników turnieju: " + e.getMessage());
        }
    }


    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }


    public void ensureResultFilesExist() {
        ensureFileExists(SIMULATION_RESULTS_FILE);
        ensureFileExists(TOURNAMENT_RESULTS_FILE);
    }


    private void ensureFileExists(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Utworzono plik: " + fileName);
            } catch (IOException e) {
                System.err.println("Błąd podczas tworzenia pliku " + fileName + ": " + e.getMessage());
            }
        }
    }

}
