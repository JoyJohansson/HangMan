import java.util.Arrays;
import java.util.Scanner;

public class CompleteHangMan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayMessage("Nu ska du få spela ett spel som kallas för Hänga gubben.");
        displayMessage("Du har 7 försök på dig att gissa rätt bokstäver till det hemliga ordet.");
        displayMessage("Nu börjar spelet!\nLycka till!\n");

        String[] words = getWordList();
        String randomWord = selectRandomWord(words).toLowerCase();

        playHangmanGame(randomWord, scanner);

        scanner.close();
    }

    public static void playHangmanGame(String randomWord, Scanner scanner) {
        char[] wordInUnderscore = initialiseWordInUnderscore(randomWord.length());
        int attempts = 7;
        boolean gameWin = false;
        String correctGuessedLetters = "";
        String incorrectGuessedLetters = "";

        while (attempts > 0 && !gameWin) {
            displayGameState(wordInUnderscore, attempts, correctGuessedLetters, incorrectGuessedLetters);
            char guess = getValidGuess(scanner, correctGuessedLetters, incorrectGuessedLetters);

            boolean letterGuessed = false;
            for (int i = 0; i < randomWord.length(); i++) {
                if (randomWord.charAt(i) == guess) {
                    wordInUnderscore[i] = guess;
                    letterGuessed = true;
                }
            }
            if (!letterGuessed) {
                attempts--;
                incorrectGuessedLetters += guess;
                displayMessage("\nFelaktig gissning. Försök igen.");
            } else {
                correctGuessedLetters += guess;
            }
            if (isGameWon(wordInUnderscore, randomWord)) {
                gameWin = true;
            }
        }

        if (gameWin) {
            displayMessage("Grattis! Du gissade rätt! Ordet är: " + randomWord);
        } else {
            displayMessage("Tyvärr så har du inga försök kvar. Ordet var: " + randomWord);
        }
    }

    public static String[] getWordList() {
        return new String[] {"trädgårdsarbete",
                "arborist",
                "datavetenskap",
                "undervattenssim",
                "hushållsost",
                "färgkritor",
                "tangentbord",
                "urmakare",
                "statsvetare",
                "lördagsgodis",
                "chokladmuffins",
                "kastanjer",
                "tyranusarusrex",
                "guppfotografi",
                "gurkmeja",
                "födelsedagsfest",
                "mirakel",
                "stjärnfall",
                "anakonda",
                "bergskjedja",
                "atmosfären",
                "citronmaräng",
                "zebraränder",
                "encyklopedi",
                "organisation"};
    }

    public static String selectRandomWord(String[] words) {
        int randomIndex = (int) (Math.random() * words.length);
        return words[randomIndex];
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static char[] initialiseWordInUnderscore(int wordLength) {
        char[] wordInUnderscore = new char[wordLength];
        Arrays.fill(wordInUnderscore, '_');
        return wordInUnderscore;
    }

    public static void displayGameState(char[] wordInUnderscore, int attempts, String correctGuessedLetters, String incorrectGuessedLetters) {
        displayMessage("Gissa ordet: " + new String(wordInUnderscore));
        displayMessage("Försök kvar: " + attempts);
        displayMessage("Bokstäver du har gissat på: " + correctGuessedLetters + incorrectGuessedLetters);
    }

    public static boolean isGameWon(char[] wordInUnderscore, String secretWord) {
        return Arrays.equals(wordInUnderscore, secretWord.toCharArray());
    }

    public static char getValidGuess(Scanner scanner, String correctGuessedLetters, String incorrectGuessedLetters) {
        char guess;
        while (true) {
            displayMessage("Gissa på en bokstav:");
            String guessInput = scanner.next().toLowerCase();
            if (guessInput.length() == 1 && Character.isLetter(guessInput.charAt(0))) {
                guess = guessInput.charAt(0);
                if (!correctGuessedLetters.contains(String.valueOf(guess))
                        && !incorrectGuessedLetters.contains(String.valueOf(guess))) {
                    break;
                } else {
                    displayMessage("Du har redan gissat på denna bokstav.");
                }
            } else if (guessInput.length() == 1) {
                displayMessage("OBS! Endast bokstäver, inga siffror eller specialtecken.");
            } else {
                displayMessage("OBS! Endast 1 bokstav åt gången.");
            }
        }
        return guess;
    }
}