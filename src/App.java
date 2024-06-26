import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {

        // ----------------------------- //
        // Kod för att ta enbart ord med //
        // 5 bokstäver från en ordlista. //
        // ----------------------------- //

        /*
         * File wordleOrd = new File("wordleord.txt");
         * if (wordleOrd.createNewFile()) {
         * System.out.println("File created: " + wordleOrd.getName());
         * } else {
         * System.out.println("File already exists.");
         * }
         * FileWriter fw = new FileWriter(wordleOrd, true);
         * File words = new
         * File("C:\\Users\\simon.palmgrenjohan\\Documents\\svenskaord.txt");
         * Scanner scanner = new Scanner(words);
         * while (scanner.hasNext()) {
         * String word = scanner.nextLine();
         * int i = word.indexOf("/");
         * if (i != -1) {
         * word = word.substring(0, i);
         * }
         * if (word.length() == 5) {
         * if (word.equals(word.toLowerCase())) {
         * System.out.println(word);
         * fw.write(word + "\n");
         * }
         * }
         * }
         * scanner.close();
         */

        // Läser in filen wordleord och skapar ett antal nya variabler och scanners.
        System.out.println("Välkommen till Wordle!");
        while (true) {
            Scanner scanner;
            File ordLista = new File("wordleord.txt");
            try {
                scanner = new Scanner(ordLista);
            } catch (FileNotFoundException e) {
                System.out.println("Kunde inte hitta ordlistan.");
                return;
            }
            Random random = new Random();
            Boolean rätt = false;
            int length = 0;
            int gissningar = 0;
            int j = 0;

            // Antal ord i filen. Behövs om ord tas bort eller läggs till så småning om.
            while (scanner.hasNext()) {
                length++;
                scanner.nextLine();
            }

            // Stänger scannern.
            scanner.close();

            // Skapar random variabeln.
            int rand = random.nextInt(length + 1);

            // Skapar några fler variabler och en scanner.
            String ord = ("");
            char[] ordArray = new char[5];
            char[] gissningArray = new char[] { 'o', 'o', 'o', 'o', 'o' };
            try {
                scanner = new Scanner(ordLista);
            } catch (FileNotFoundException e) {
                System.out.println("Kunde inte hitta ordlistan.");
            }
            // Läser in ett slumpmässigt ord ur ordlistan.
            int i;
            for (i = 0; i < rand; i++) {
                ord = scanner.nextLine();
                ordArray = ord.toCharArray();
            }

            System.out.println(
                    "Gissa ett ord med 5 bokstäver.\nDu har 5 försök.\nEn lista med möjliga ord bör ha följt med programmet.\nTryck på CTRL+C för att avsluta.\n");

            // Själva spelet.
            while (!rätt) {
                gissningArray = new char[] { 'o', 'o', 'o', 'o', 'o' };

                // Skapar en variabel och en scanner.
                String gissning = "";
                Scanner in = new Scanner(new InputStreamReader(System.in, "Cp850"));

                // Läser in gissningen.
                if (in.hasNextLine()) {
                    gissning = in.nextLine().toLowerCase();
                }
                in.close();
                
                // Skapar en lista med alla ord.
                List<String> wordList = new ArrayList<>();
                try (Scanner skannare = new Scanner(new File("wordleord.txt"))) {
                    while (skannare.hasNextLine()) {
                        wordList.add(skannare.nextLine());
                    }
                }

                // Kollar om gissningen finns i ordlistan.
                if (gissning.length() != 5) {
                    System.out.println("Ordet måste vara 5 bokstäver. Försök igen.");
                } else if (!wordList.contains(gissning)) {
                    System.out.println("Ordet finns inte i ordlistan. Försök igen.");
                } else { // Om gissningen finns i ordlistan.
                    // Om gissningen är ordet vinner man spelet.
                    if (gissning.equals(ord) && gissningar < 5) {
                        gissningArray = new char[] { 'I', 'I', 'I', 'I', 'I' };
                        rätt = true;
                        gissningar++;
                    } else if (gissningar < 5) { // Om gissningen ej är ordet.
                        for (i = 0; i < ordArray.length; i++) {
                            if (gissning.charAt(i) == ordArray[i]) { // Går igenom bokstäverna i gissningen och kollar
                                                                     // om de
                                                                     // är på samma plats som i ordet.
                                gissningArray[i] = 'I';
                                j++;
                            }
                        }
                        for (i = 0; i < ord.length(); i++) { // Kollar om bokstäver från gissningen finns i ordet.
                            char guessChar = ord.charAt(i);
                            if (gissning.contains(String.valueOf(guessChar)) && gissningArray[i] != 'I') {
                                for (j = 0; j < ord.length(); j++) {
                                    if (gissning.charAt(j) == guessChar) {
                                        gissningArray[j] = '-';
                                    }
                                }
                                j = 0;
                            }
                        }
                        System.out.println(Arrays.toString(gissningArray)); // Visar användaren vilka bokstäver som passar in i ordet.
                    }
                    gissningar++;

                    scanner.close();

                    // Förklarar sig sjölvt.
                    if (rätt) {
                        System.out.println("Grattis! Du vann!");
                        System.out.println("Försök: " + (gissningar - 1));
                        TimeUnit.SECONDS.sleep(5);
                        break;
                    } else if (gissningar > 4) {
                        System.out.println("Slut försök.");
                        System.out.println("Orden var: " + ord + "\n");
                        TimeUnit.SECONDS.sleep(5);
                        break;
                    }
                }
            }
        }
    }
}