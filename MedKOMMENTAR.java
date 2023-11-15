//Impotera nödvändiga Java-paket och klasser:
import java.util.Arrays; //hantera teckenarrayer
import java.util.Scanner; //för att läsa användarinmatning.

public class MedKOMMENTAR {
    public static void main(String[] args) {
        //Skapa en Scanner-instans för att läsa användarinmatning.
            Scanner scanner = new Scanner(System.in);

        // Visa välkomstmeddelanden och spelregler med displayMessage-metoden.
            displayMessage("Nu ska du få spela ett spel som kallas för Hänga gubben.");
            displayMessage("Du har 7 försök på dig att gissa rätt bokstäver till det hemliga ordet.");
            displayMessage("Nu börjar spelet!\nLycka till!\n");

        // Hämta en lista med ord att använda i spelet.
            String[] words = getWordList();

        // Slumpmässigt välj ett ord från listan och omvandla det till små bokstäver.
            String randomWord = selectRandomWord(words).toLowerCase();
        // Starta spelet genom att anropa playHangmanGame-metoden.
            playHangmanGame(randomWord, scanner);
        // Stäng Scanner-instansen när spelet är klart för att undvika resursläckage.
            scanner.close();

       /* main-metoden:
        Detta är huvudmetoden i programmet och där allt börjar. Den gör följande:
        a. Skapar en instans av Scanner för att läsa användarinmatning.
        b. Använder displayMessage-metoden för att skriva ut välkomstmeddelanden och spelregler.
        c. Hämtar en lista med ord genom att anropa getWordList-metoden.
        d. Slumpmässigt väljer ett ord från listan och omvandlar det till små bokstäver.
        e. Anropar playHangmanGame-metoden för att starta spelet med det slumpmässigt valda ordet och Scanner-instansen.
        f. Stänger Scanner-instansen när spelet är klart för att undvika resursläckage.*/
        }

        public static void playHangmanGame(String randomWord, Scanner scanner) {
            // Skapa en teckenarray för det hemliga ordet med understreck som startvärden.
            char[] wordInUnderscore = initialiseWordInUnderscore(randomWord.length());

            // Antal försök och spelstatusvariabler
            int attempts = 7;
            boolean gameWin = false;
            String correctGuessedLetters = "";
            String incorrectGuessedLetters = "";

            // Huvudspelloopen, fortsätter tills inga försök återstår eller spelet är vunnet.
            while (attempts > 0 && !gameWin) {
                // Visa det aktuella spelets tillstånd med displayGameState-metoden.
                displayGameState(wordInUnderscore, attempts, correctGuessedLetters, incorrectGuessedLetters);
                // Hämta en giltig gissning från spelaren med getValidGuess-metoden.
                char guess = getValidGuess(scanner, correctGuessedLetters, incorrectGuessedLetters);
                // Kontrollera om den gissade bokstaven finns i det hemliga ordet.
                boolean letterGuessed = false;
                for (int i = 0; i < randomWord.length(); i++) {
                    if (randomWord.charAt(i) == guess) {
                        // Uppdatera det delvis gissade ordet.
                        wordInUnderscore[i] = guess;
                        letterGuessed = true;
                    }
                }
                // Hantera felaktiga gissningar och uppdatera spelstatusen.
                if (!letterGuessed) {
                    attempts--;
                    incorrectGuessedLetters += guess;
                    displayMessage("\nFelaktig gissning. Försök igen.");
                } else {
                    correctGuessedLetters += guess;
                }
                // Kontrollera om spelet är vunnet genom att jämföra det delvis gissade ordet med det hemliga ordet.
                if (isGameWon(wordInUnderscore, randomWord)) {
                    gameWin = true;
                }
            }
            // Efter att loopen är klar, skriv ut resultatmeddelande baserat på spelets utfall.
            if (gameWin) {
                displayMessage("Grattis! Du gissade rätt! Ordet är: " + randomWord);
            } else {
                displayMessage("Tyvärr så har du inga försök kvar. Ordet var: " + randomWord);
            }

        /*  playHangmanGame-metoden:
            Denna metod är ansvarig för att hantera hela spelet och tar följande parametrar:
            a. randomWord: Det slumpmässigt valda ordet som spelaren ska gissa.
            b. scanner: En Scanner-instans för att läsa användarinmatning.
            c. wordInUnderscore: En teckenarray som representerar det hemliga ordet med understreck för dolda bokstäver.
            d. attempts: Antalet försök som spelaren har kvar.
            e. gameWin: En boolesk variabel som indikerar om spelet är vunnet.
            f. correctGuessedLetters och incorrectGuessedLetters:
               Strängar för att lagra korrekt och felaktigt gissade bokstäver.
            g. I en while-loop fortsätter spelet så länge det finns försök kvar (attempts > 0)
               och spelet inte har vunnits (!gameWin).
            h. Inuti while-loopen:
                Anropar displayGameState för att visa det aktuella spelets tillstånd,
                inklusive det delvis gissade ordet, antalet försök och tidigare gissade bokstäver.
                Anropar getValidGuess för att få en giltig gissning från spelaren.
                Loopar igenom det slumpmässiga ordet och kontrollerar om bokstaven finns i det.
                Uppdaterar wordInUnderscore om bokstaven gissades korrekt och hanterar felaktiga gissningar annars.
                Kontrollerar om spelet är vunnet genom att anropa isGameWon.
            i. Efter att while-loopen är klar,
               skriver den ut ett resultatmeddelande beroende på om spelaren har vunnit eller förlorat. */
        }

        // getWordList-metoden:
        // Denna metod returnerar en array med ord som används i spelet.
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

        //selectRandomWord-metoden:
        // Denna metod väljer slumpmässigt ett ord från en given lista av ord.
        public static String selectRandomWord(String[] words) {
            int randomIndex = (int) (Math.random() * words.length);
            return words[randomIndex];
        }

        //displayMessage-metoden:
        // En enkel hjälpmetod som skriver ut ett meddelande till konsolen.
        public static void displayMessage(String message) {
            System.out.println(message);
        }

        //initialiseWordInUnderscore-metoden:
        // Denna metod skapar och returnerar en teckenarray med understreck som representerar det hemliga ordet.
        //(Skriv ut ordet med underscore så att den som gissar vet hur många bosktäver det ska vara).
        public static char[] initialiseWordInUnderscore(int wordLength) {
            char[] wordInUnderscore = new char[wordLength];
            Arrays.fill(wordInUnderscore, '_');
            return wordInUnderscore;
        }

        //displayGameState-metoden:
        // Denna metod visar det aktuella spelets tillstånd, inklusive det delvis gissade ordet,
        // antalet försök och tidigare gissade bokstäver.
        public static void displayGameState(char[] wordInUnderscore, int attempts, String correctGuessedLetters, String incorrectGuessedLetters) {
            displayMessage("Gissa ordet: " + new String(wordInUnderscore));
            displayMessage("Försök kvar: " + attempts);
            displayMessage("Bokstäver du har gissat på: " + correctGuessedLetters + incorrectGuessedLetters);
        }

        //isGameWon-metoden:
        // Denna metod jämför det delvis gissade ordet med det hemliga ordet för att avgöra om spelet är vunnet.
        public static boolean isGameWon(char[] wordInUnderscore, String secretWord) {
            return Arrays.equals(wordInUnderscore, secretWord.toCharArray());
        }

        //getValidGuess-metoden:
        // Denna metod tar användarinmatning och returnerar en giltig gissning efter att ha kontrollerat om gissningen är en bokstav,
        // inte tidigare gissad och en enda bokstav åt gången.
        public static char getValidGuess(Scanner scanner, String correctGuessedLetters, String incorrectGuessedLetters) {
            char guess;
            while (true) {
                // Hämta en giltig gissning från spelaren.
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

