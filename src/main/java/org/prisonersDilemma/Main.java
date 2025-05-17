package org.prisonersDilemma;

import org.prisonersDilemma.model.GameResult;
import org.prisonersDilemma.strategies.*;
import org.prisonersDilemma.strategies.Random;

import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final GameSimulator gameSimulator = new GameSimulator();
    private static final ResultProcessor resultProcessor = new ResultProcessor();

    public static void main(String[] args) {

        System.out.println("Symulator Dylematu Więźnia");
        System.out.println("==========================");

        resultProcessor.ensureResultFilesExist();

        while (true) {
            showMainMenu();
            int choice = getValidChoice(1, 4);

            switch (choice) {
                case 1:
                    runSingleSimulation();
                    break;
                case 2:
                    runTournament();
                    break;
                case 3:
                    showStrategyDescriptions();
                    break;
                case 4:
                    System.out.println("Dziękujemy za korzystanie z symulatora. Do widzenia!");
                    scanner.close();
                    return;
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\nWybierz opcję:");
        System.out.println("1. Przeprowadź pojedynczą symulację");
        System.out.println("2. Przeprowadź turniej wszystkich strategii");
        System.out.println("3. Wyświetl opisy strategii");
        System.out.println("4. Wyjdź z programu");
        System.out.print("Twój wybór: ");
    }

    private static void runSingleSimulation() {
        System.out.println("\n=== Pojedyncza symulacja ===");

        System.out.println("\nWybierz pierwszą strategię:");
        Strategy strategy1 = selectStrategy();

        System.out.println("\nWybierz drugą strategię:");
        Strategy strategy2 = selectStrategy();

        System.out.print("\nPodaj liczbę iteracji (rund): ");
        int iterations = getValidChoice(1, 1000);

        System.out.println("\nPrzeprowadzanie symulacji...");
        GameResult result = gameSimulator.runSimulation(strategy1, strategy2, iterations);

        System.out.println("\nWynik symulacji:");
        System.out.println(result);

        resultProcessor.saveSimulationResult(result);

        System.out.println("\nNaciśnij ENTER, aby kontynuować...");
        scanner.nextLine();
    }

    private static void runTournament() {
        System.out.println("\n=== Turniej wszystkich strategii ===");

        System.out.print("\nPodaj liczbę iteracji (rund) dla każdej gry: ");
        int iterations = getValidChoice(1, 1000);

        List<Strategy> strategies = getAllStrategies();

        System.out.println("\nRozpoczęcie turnieju z udziałem " + strategies.size() + " strategii...");
        System.out.println("Każda strategia zagra z każdą inną przez " + iterations + " rund.");

        Map<String, Integer> tournamentResults = gameSimulator.runTournament(strategies, iterations);

        String formattedResults = gameSimulator.formatTournamentResults(tournamentResults);
        System.out.println("\n" + formattedResults);

        resultProcessor.saveTournamentResults(tournamentResults, formattedResults);

        System.out.println("\nNaciśnij ENTER, aby kontynuować...");
        scanner.nextLine();
    }

    private static Strategy selectStrategy() {
        List<Strategy> strategies = getAllStrategies();

        for (int i = 0; i < strategies.size(); i++) {
            System.out.println((i + 1) + ". " + strategies.get(i).getName());
        }

        System.out.print("Wybierz strategię (1-" + strategies.size() + "): ");
        int choice = getValidChoice(1, strategies.size());

        return strategies.get(choice - 1);
    }

    private static List<Strategy> getAllStrategies() {
        return Arrays.asList(
                new TitForTat(),
                new AlwaysCooperate(),
                new AlwaysDefect(),
                new Pavlov(),
                new TitForTwoTats(),
                new Grudger(),
                new Random(),
                new WinStayLoseShift(),
                new GrimTrigger(),
                new GenerousTitForTat()
        );
    }

    private static int getValidChoice(int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            try {
                choice = scanner.nextInt();
                if (choice < min || choice > max) {
                    System.out.print("Nieprawidłowy wybór. Podaj liczbę z zakresu " + min + "-" + max + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Nieprawidłowy format. Podaj liczbę z zakresu " + min + "-" + max + ": ");
                scanner.next();
            }
        }
        scanner.nextLine();
        return choice;
    }

    private static void showStrategyDescriptions() {
        System.out.println("\n=== Opisy strategii ===");

        Map<String, String> strategyDescriptions = getStrategyDescriptions();

        List<String> strategyNames = new ArrayList<>(strategyDescriptions.keySet());
        Collections.sort(strategyNames);

        for (String name : strategyNames) {
            System.out.println("\n" + name + ":");
            System.out.println(strategyDescriptions.get(name));
        }

        System.out.println("\nNaciśnij ENTER, aby kontynuować...");
        scanner.nextLine();
    }

    private static Map<String, String> getStrategyDescriptions() {
        Map<String, String> descriptions = new HashMap<>();

        descriptions.put("Tit for Tat",
                "Zaczyna od współpracy, następnie powtarza ostatni ruch przeciwnika. " +
                        "Prosta, skuteczna strategia naśladująca zachowanie przeciwnika.");

        descriptions.put("Always Cooperate",
                "Zawsze wybiera współpracę niezależnie od ruchów przeciwnika. " +
                        "Strategia potencjalnie altruistyczna, ale podatna na wykorzystanie.");

        descriptions.put("Always Defect",
                "Zawsze wybiera zdradę niezależnie od ruchów przeciwnika. " +
                        "Maksymalizuje zysk w pojedynczej grze, ale prowadzi do słabych wyników w dłuższej perspektywie.");

        descriptions.put("Pavlov",
                "Stosuje regułę \"wygraj-zostań, przegraj-zmień\". " +
                        "Kontynuuje swoją poprzednią decyzję, jeśli przyniosła dobry wynik; zmienia ją, jeśli wynik był niekorzystny.");

        descriptions.put("Tit for Two Tats",
                "Bardziej wybaczająca wersja Tit for Tat. " +
                        "Zdradza dopiero po dwóch kolejnych zdradach przeciwnika, co czyni ją odporniejszą na pojedyncze błędy.");

        descriptions.put("Grudger",
                "Zaczyna od współpracy, ale jeśli przeciwnik choć raz zdradzi, zawsze zdradza w przyszłości. " +
                        "Strategia z zerową tolerancją na zdradę.");

        descriptions.put("Random",
                "Podejmuje decyzje o współpracy lub zdradzie losowo. " +
                        "Strategia nieprzewidywalna, co może być zarówno zaletą, jak i wadą.");

        descriptions.put("Win-Stay, Lose-Shift",
                "Kontynuuje swój ostatni ruch, jeśli przyniósł korzystny wynik; zmienia go, jeśli wynik był niekorzystny. " +
                        "Podobna do Pavlova, ale z nieco innym sposobem oceny korzystności wyniku.");

        descriptions.put("Grim Trigger",
                "Współpracuje, dopóki przeciwnik nie zdradzi, a wtedy bezwarunkowo zdradza do końca gry. " +
                        "Bardzo surowa odmiana strategii Grudger.");

        descriptions.put("Generous Tit for Tat",
                "Ulepszona wersja Tit for Tat z elementem losowego wybaczania. " +
                        "Z pewnym prawdopodobieństwem (domyślnie 10%) wybacza zdradę przeciwnika i dalej współpracuje.");

        return descriptions;
    }


}