/*Individuell inlämningsuppgift - Hänga gubbe
star
unfold_more
Gör spelet “Hänga Gubbe”.
Spelet ska välja ett random ord från en lista av 25 ord, där varje ord måste vara minst 7 och max 15 bokstäver långt.

Skriv ut ordet med underscore så att den som gissar vet hur många bokstäver det ska vara.

Guess the secret word: _ _ _ _ _ _ _
Attempts left - 7
Wrong letters -
Guess:
Varje gång man gissar på en bokstav som finns i ordet, fyll i den på alla ställen som bokstaven förekommer.
Om det hemliga ordet är SECRETS, och användaren gissar på E, då ska programmet skriva ut!

Correct guess!
Guess the secret word: _ E _ _ E _ _
Attempts left - 7
Wrong letters -
Guess:
Om användaren gissar fel

Wrong guess!
Guess the secret word: _ E _ _ E _ _
Attempts left - 6
Wrong letters - P
Guess:
Om användaren gissar på en bokstav som redan används

Already guessed E!
Guess the secret word: _ E C _ E _ _
Attempts left - 4
Wrong letters - P, F, A
Guess:
Man ska ha 7 försök att gissa fel bokstav, sen förlorar man spelet.

Kontrollera att användaren enbart skriver in 1 bokstav.

Användaren ska kunna skriva in både små och stora bokstäver.

Vid inlämning ska filen döpas efter ditt namn “Linus Lindroth - Hänga gubbe” */



//Importera Scanner-klassen från java.util-paketet för att kunna läsa användarinmatning från terminalen.
import java.util.Scanner;

public class SkeletonMedKommentar {
        public static void main(String[] args) {
            //Skapa en instans av Scanner för att ta emot användarinmatning.
            Scanner scanner = new Scanner(System.in);

            //Skriver ut en välkomstmeddelande som förklarar spelet "Hänga Gubben."
            System.out.println("Nu ska du få spela ett spel som kallas för Hänga gubben.\n"
                    + "Du har 7 försök på dig att gissa rätt bokstäver till det hemliga ordet.\n"
                    + "\nNu börjar spelet! " + "\nLycka till!\n");

            //Skapar en array med ord ("words") som används i spelet.
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

            //Väljer ett slumpmässigt ord från listan och omvandlar det till små bokstäver (toLowerCase).
            // Detta är det hemliga ordet som spelaren måste gissa.
            String randomWord = selectRandomWord(words).toLowerCase();

            //Skapar en tom teckenarray ("theWord") med samma längd som det hemliga ordet
            // och sätter antalet försök till 7.
            char[] theWord = new char[randomWord.length()];
            int attempts = 7;

            //Skapar en variabel "gameWin" och sätter den till falskt.
            // Denna variabel används för att kontrollera om spelaren har vunnit.
            boolean gameWin = false;

            //Skapar två tomma strängar: "correctGuessedLetters" för korrekta gissade bokstäver
            // och "incorrectGuessedLetters" för felaktiga gissningar.
            String correctGuessedLetters = "";
            String incorrectGuessedLetters = "";

            //Fyller "theWord"-arrayen med understreck ("_")
            // för att visa spelaren hur många bokstäver som finns i det hemliga ordet.
            for (int i = 0; i < randomWord.length(); i++) {    //en metod
                theWord[i] = '_';
            }
            //Startar en while-loop som fortsätter så länge antalet försök är större än 0
            // och spelet inte har vunnits ("gameWin" är falskt).
            while (attempts > 0 && !gameWin) {

                //Skriver ut det aktuella tillståndet i spelet, inklusive det delvis gissade ordet,
                // antalet försök och vilka bokstäver som har gissats tidigare.v
                System.out.println("Gissa ordet: " + new String(theWord) + "\n");
                System.out.println("Försök kvar: " + attempts);
                System.out.println("Bokstäver du har gissat på: " + correctGuessedLetters + incorrectGuessedLetters);
                System.out.print("Gissa på en bokstav: \n");

                //Läser in användarens gissning och omvandlar den till små bokstäver.
                String guessInput = scanner.next().toLowerCase();

                //Kontrollerar om användarens inmatning är en giltig gissning (en enda bokstav) och om det är en bokstav.
                if (guessInput.length() == 1 && Character.isLetter(guessInput.charAt(0))) {
                    char guess = guessInput.charAt(0);

                    //Om gissningen är giltig och inte tidigare gissats,
                    // kontrolleras om bokstaven finns i det hemliga ordet.
                    // Om det är fallet, uppdateras "theWord"-arrayen med den korrekta gissningen,
                    // annars minskar antalet försök och bokstaven läggs till i "incorrectGuessedLetters."
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

                            //Om användaren har gissat rätt på hela ordet, ändras "gameWin" till sant och while-loopen avslutas.
                        } else {
                            correctGuessedLetters += guess;
                        }
                        if (new String(theWord).equals(randomWord)) {
                            gameWin = true;
                        }

                        //Om användaren har gissat på en bokstav som redan har gissats tidigare, meddelas användaren om detta.
                    } else {
                        System.out.println("\nDu har redan gissat på denna bokstav.");
                    }
                    //Om användaren anger något ogiltigt (till exempel flera bokstäver samtidigt), meddelas användaren om detta.
                } else {
                    System.out.println("\nOBS! Endast 1 bokstav åt gången. " + "");
                }
            }
            //Efter while-loopen är avslutad, skrivs ett avslutningsmeddelande ut beroende på om användaren har vunnit eller förlorat spelet.
            if (gameWin) {
                System.out.println("Grattis! Du gissade rätt! Ordet är: " + randomWord);
            } else {
                System.out.println("Tyvärr så har du inga försök kvar. Ordet var: " + randomWord);
            }
        }
        //Slutligen finns det en hjälpmetod med namnet "selectRandomWord,"
        // som väljer ett slumpmässigt ord från listan av ord och returnerar det.
        public static String selectRandomWord(String[] words) {
            int randomIndex = (int) (Math.random() * words.length);
            return words[randomIndex];
        }
}

/* MÅL: FÖRBÄTTRA KODEN GENOM ATT SKAPA FLERA METODER SOM MAIN-METODEN KAN KALLA PÅ.
EN METOD FÖR ATT SLUMPA FRAM ORDEN FINNS REDAN. FÖLJANDE METODER SKA IMPLANTERAS.
1.EN METOD FÖR ALLA ORDEN
2. EN METOD FÖR HÄLSNINGSMEDDELANDEN
3. EN METOD FÖR ORD TILL "UNDERSCORE"
4. EN METOD FÖR MEDELANDE I SPELET - SPELSTATUS
5. EN METOD FÖR ATT FÅ EN GILTIG GISSNING
6. EN METOD FÖR GILTIG GISSNING
7. EN METOD FÖR ATT HANTERA HELA SPELET OCH OCH TAR FÖLJANDE PARAMETRAR:
    A. RANDOM WORD
    B. SCANNER (LÄSA ANVÄNDARINMATNING)
    C. TECKENARRAY: ORD TILL UNDERSCORE
    D. ANTALET FÖRSÖK
    E. BOOLEAN: SOM INDIKERAR OM SPELET ÄR VUNNET
    F. STRÄNGAR FÖR ATT LAGRA KORREKTA OCH INKORREKTA GISSADE BOKSTÄVER.
    G. EN WHILE-LOOP FORTSÄTTER SPELET SÅ LÄNGE DET FINNS FÖRSÖK KVAR.
        INNUTI WHILE-LOOPEN:
        i. VISA DET AKTUELLA SPELETS TILLSTÅND: DETVIS DET GISSADE ORDET, ANTAL FÖRSÖK KVAR, TIDIGARE GISSADE BOKSTÄVER
        ii. ANROPAR METOD FÖR GILTIG GISSNING FRÅN SPEAREN
        iii. LOOPAR IGENOM ORDET FÖR ATT KONTROLLERA ATT BOKSTAVEN FINNS.
        iv. UPPDATERA UNDERSCORE VID KORREKT GISSADE BOKSTAV, HANTERA FELAKTIG GISSNING
        v. KONTROLLERA OM SPELET ÄR VUNNET, ANROPA METOD
     F. NÄR WHILE-LOOPEN ÄR KLAR, SKRIVER UT RESULTAT MEDDELANMDE, 2 ALTERNATIV.
 */