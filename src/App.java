import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Arrays;
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
         * 
         * FileWriter fw = new FileWriter(wordleOrd, true);
         * 
         * File words = new
         * File("C:\\Users\\simon.palmgrenjohan\\Documents\\svenskaord.txt");
         * Scanner scanner = new Scanner(words);
         * while(scanner.hasNext()){
         * String word = scanner.nextLine();
         * int i = word.indexOf("/");
         * if(i != -1){
         * word = word.substring(0, i);
         * }
         * if(word.length() == 5){
         * if(word.equals(word.toLowerCase())){
         * System.out.println(word);
         * fw.write(word + "\n");
         * }
         * }
         * }
         * scanner.close();
         */

        // Läser in filen wordleord och skapar ett antal nya variabler och scanners.
        File ordLista = new File("wordleord.txt");
        Scanner scanner = new Scanner(ordLista, "Cp850");
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
        scanner = new Scanner(ordLista, "Cp850");

        // Läser in ett slumpmässigt ord ur ordlistan.
        int i;
        for (i = 0; i < rand; i++) {
            ord = scanner.nextLine();
            ordArray = ord.toCharArray();
        }

        // Själva spelet.
        while (!rätt) {

            gissningArray = new char[] { 'o', 'o', 'o', 'o', 'o' };
            System.out.println(Arrays.toString(gissningArray));

            // Skapar en variabel och en scanner.
            String gissning = "";
            Scanner in = new Scanner(new InputStreamReader(System.in, "Cp850"));

            // Läser in gissningen.
            if (in.hasNextLine()) {
                gissning = in.nextLine();
            } else {
                System.out.println("error");
                break;
            }

            // Om gissningen är ordet vinner man spelet.
            if (gissning.equals(ord) && gissningar < 5) {
                gissningArray = new char[] { 'I', 'I', 'I', 'I', 'I' };
                in.close();
                rätt = true;
                gissningar++;
            } else if (gissningar < 5) { // Om gissningen ej är ordet.
                for (i = 0; i < ordArray.length; i++) {
                    if (gissning.charAt(i) == ordArray[i]) { // Går igenom bokstäverna i gissningen och kollar om de är
                                                             // på samma plats som i ordet.
                        gissningArray[i] = 'I';
                        j++;
                    }
                }
                for (i = 0; i < ord.length(); i++) {
                    char guessChar = ord.charAt(i);
                    if (gissning.contains(String.valueOf(guessChar)) && gissningArray[i] != 'I') {
                        for (j = 0; j < ord.length(); j++) {
                            if (gissning.charAt(j) == guessChar) {
                                gissningArray[j] = '-';
                            }
                        }
                        j = 0;
                        System.out.println(Arrays.toString(gissningArray));
                    }
                }
                gissningar++;
            } else if (gissningar >= 5) {
                rätt = false;
            }
        }

        scanner.close();
        if (rätt) {
            System.out.println("Grattis! Du vann!");
            System.out.println("Försök: " + gissningar);
        } else {
            System.out.println("Du gissade fel.)");
            System.out.println("Orden var: " + ord);
        }
    }
}