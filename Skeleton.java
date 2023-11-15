import java.util.Scanner;

public class Skeleton {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nu ska du få spela ett spel som kallas för Hänga gubben.\n"
                    + "Du har 7 försök på dig att gissa rätt bokstäver till det hemliga ordet.\n"
                    + "\nNu börjar spelet! " + "\nLycka till!\n");

        String[] words =
                    {"trädgårdsarbete",
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


        String randomWord = selectRandomWord(words).toLowerCase();

        char[] theWord = new char[randomWord.length()];
        int attempts = 7;

        boolean gameWin = false;

        String correctGuessedLetters = "";
        String incorrectGuessedLetters = "";

        for (int i = 0; i < randomWord.length(); i++) {
                theWord[i] = '_';
        }
        while (attempts > 0 && !gameWin) {
            System.out.println("Gissa ordet: " + new String(theWord) + "\n");
            System.out.println("Försök kvar: " + attempts);
            System.out.println("Bokstäver du har gissat på: " + correctGuessedLetters + incorrectGuessedLetters);
            System.out.print("Gissa på en bokstav: \n");

            String guessInput = scanner.next().toLowerCase();

            if (guessInput.length() == 1 && Character.isLetter(guessInput.charAt(0))) {
                char guess = guessInput.charAt(0);

                if (!correctGuessedLetters.contains(String.valueOf(guess))
                        && !incorrectGuessedLetters.contains(String.valueOf(guess))) {
                    boolean letterGuessed = false;
                    for (int i = 0; i < randomWord.length(); i++) {
                        if (randomWord.charAt(i) == guess) {
                            theWord[i] = guess;
                            letterGuessed = true;
                        }
                    }
                    if (!letterGuessed) {
                        attempts--;
                        incorrectGuessedLetters += guess;
                        System.out.println("\nFelaktig gissning. Försök igen.");
                        } else {
                            correctGuessedLetters += guess;
                        }
                        if (new String(theWord).equals(randomWord)) {
                            gameWin = true;
                        }
                    } else {
                        System.out.println("\nDu har redan gissat på denna bokstav.");
                    }
                    } else if (guessInput.length() == 1) {
                        System.out.println("OBS! Endast bokstäver, inga siffror eller specialtecken.");
                    } else {
                    System.out.println("\nOBS! Endast 1 bokstav åt gången. " + "");
                }
            }
            if (gameWin) {
                System.out.println("Grattis! Du gissade rätt! Ordet är: " + randomWord);
            } else {
                System.out.println("Tyvärr så har du inga försök kvar. Ordet var: " + randomWord);
            }
        }
        public static String selectRandomWord(String[] words) {
            int randomIndex = (int) (Math.random() * words.length);
            return words[randomIndex];
    }
}
