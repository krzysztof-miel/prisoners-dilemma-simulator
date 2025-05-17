# Symulator Dylematu Więźnia

## Opis Projektu
Aplikacja symuluje interakcje w klasycznym problemie teorii gier znanym jako "Dylemat Więźnia". Umożliwia przeprowadzanie pojedynczych symulacji między strategiami lub organizowanie turniejów, w których wszystkie strategie konkurują ze sobą.

## Funkcje
- Przeprowadzanie pojedynczych symulacji między dwiema wybranymi strategiami
- Organizowanie turniejów między wszystkimi dostępnymi strategiami
- Szczegółowe opisy różnych strategii
- Zapisywanie wyników do plików tekstowych
- Analiza skuteczności strategii w różnych scenariuszach

## Dostępne Strategie
1. **Tit for Tat** - Zaczyna od współpracy, następnie powtarza ostatni ruch przeciwnika
2. **Always Cooperate** - Zawsze wybiera współpracę
3. **Always Defect** - Zawsze wybiera zdradę
4. **Pavlov** - Stosuje regułę "wygraj-zostań, przegraj-zmień"
5. **Tit for Two Tats** - Zdradza dopiero po dwóch kolejnych zdradach przeciwnika
6. **Grudger** - Współpracuje, dopóki przeciwnik nie zdradzi, potem zawsze zdradza
7. **Random** - Podejmuje decyzje losowo
8. **Win-Stay, Lose-Shift** - Kontynuuje swój ostatni ruch, jeśli przyniósł korzystny wynik
9. **Grim Trigger** - Współpracuje, dopóki przeciwnik nie zdradzi, potem zawsze zdradza
10. **Generous Tit for Tat** - Podobna do Tit for Tat, z elementem losowego wybaczania zdrad

## Zasady Punktacji
- Obaj współpracują: 3 punkty dla każdego
- Obaj zdradzają: 1 punkt dla każdego
- Współpraca vs zdrada: 0 punktów dla współpracującego, 5 punktów dla zdradzającego

## Wymagania
- Java 17 lub nowsza
- Maven

## Uruchomienie
```bash
# Sklonuj repozytorium
git clone [URL_repozytorium]

# Przejdź do katalogu projektu
cd PrisonersDilema

# Zbuduj projekt
mvn clean package

# Uruchom aplikację
java -jar target/PrisonersDilema-1.0-SNAPSHOT.jar
