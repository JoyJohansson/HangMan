import java.util.Scanner;

public class SecretWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayMessage("Nu ska du få spela ett spel som kallas för Hänga gubben.");
        displayMessage("Du har 7 försök på dig att gissa rätt bokstäver till det hemliga ordet.");
        displayMessage("Nu börjar spelet!\nLycka till!\n");

        String[] words = getWordList();

        String randomWord = selectRandomWord(words).toLowerCase();

        char[] theWordInUnderscore = new char[randomWord.length()];
        int attempts = 7;
        boolean gameWin = false;
        String correctGuessedLetters = "";
        String incorrectGuessedLetters = "";

        for (int i = 0; i < randomWord.length(); i++) {
            theWordInUnderscore[i] = '_';
        }

        while (attempts > 0 && !gameWin) {
            displayMessage("Gissa ordet: " + new String(theWordInUnderscore));
            displayMessage("Försök kvar: " + attempts);
            displayMessage("Bokstäver du har gissat på: " + correctGuessedLetters + incorrectGuessedLetters);
            displayMessage("Gissa på en bokstav:");

            String guessInput = scanner.next().toLowerCase();

            if (guessInput.length() == 1 && Character.isLetter(guessInput.charAt(0))) {
                char guess = guessInput.charAt(0);
                if (!correctGuessedLetters.contains(String.valueOf(guess))
                        && !incorrectGuessedLetters.contains(String.valueOf(guess))) {
                    boolean letterGuessed = false;
                    for (int i = 0; i < randomWord.length(); i++) {
                        if (randomWord.charAt(i) == guess) {
                            theWordInUnderscore[i] = guess;
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
                    if (new String(theWordInUnderscore).equals(randomWord)) {
                        gameWin = true;
                    }
                } else {
                    displayMessage("\nDu har redan gissat på denna bokstav.");
                }
            } else {
                displayMessage("\nOBS! Endast 1 bokstav åt gången.");
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
}