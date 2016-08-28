/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fejvagyiras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Kovács Andor
 */
public class Fejvagyiras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

//        1. Szimuláljon egy pénzfeldobást, ahol azonos esélye van a fejnek és az írásnak is! Az eredményt írassa ki a képernyőre a mintának megfelelően!
        System.out.println("1. feladat");
        System.out.println("A penzfeldobás eredménye: " + penzfeldobas());

//        2. Kérjen be a felhasználótól egy tippet, majd szimuláljon egy pénzfeldobást! Írassa ki a képernyőre a felhasználó tippjét és a dobás eredményét is, majd tájékoztassa a felhasználót az eredményről következő formában: „Ön eltalálta.” vagy „Ön nem találta el.”! 
        System.out.println("2. feladat");
        String tipp = getInput("Tippeljen! (F/I)= ");
        String dobas = penzfeldobas();
        System.out.println("A tipp '" + tipp.toUpperCase() + "', a dobás eredménye '" + dobas + "' volt.");
        if (tipp.toUpperCase().equals(dobas)) {
            System.out.println("Ön eltalálta.");
        } else {
            System.out.println("Ön nem találta el.");
        }

//        A kiserlet.txt állományban egy pénzfeldobás-sorozat eredményét találja. Mivel a sorozat hossza tetszőleges lehet, ezért az összes adat memóriában történő egyidejű eltárolása nélkül oldja meg a következő feladatokat! Feltételezheti, hogy egymilliónál több adata nem lesz. 
        BufferedReader bufferedReader = new BufferedReader(new FileReader("kiserlet.txt"));
        String sor;

        int dobasok = 0;
        int fej = 0;
        int iras = 0;
        while ((sor = bufferedReader.readLine()) != null) {
            dobasok++;
            if (sor.toUpperCase().equals("F")) {
                fej++;
            } else {
                iras++;
            }
        }
        bufferedReader.close();
//        3. Állapítsa meg, hány dobásból állt a kísérlet, és a választ a mintának megfelelően írassa ki a képernyőre!

        System.out.println("3. feladat");
        System.out.println("A kísérlet " + dobasok + " dobásból állt.");

//        4. Milyen relatív gyakorisággal dobtunk a kísérlet során fejet? (A fej relatív gyakorisága a fejet eredményező dobások és az összes dobás hányadosa.) A relatív gyakoriságot a mintának megfelelően két tizedesjegy pontossággal, százalék formátumban írassa ki a képernyőre! 
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        String fejRelativGyakorisag = decimalFormat.format((double) fej / dobasok * 100);
        System.out.println("4. feladat");
        System.out.println("A kísérlet során a fej relatív gyakorisága " + fejRelativGyakorisag + "% volt.");

//        5. Hányszor fordult elő ebben a kísérletben, hogy egymás után pontosan két fejet dobtunk? A választ a mintának megfelelően írassa ki a képernyőre! (Feltételezheti, hogy a kísérlet legalább 3 dobásból állt.) Például az IFFFFIIFFIFFFIFF sorozatban kétszer fordult elő, hogy egymás után pontosan két fejet dobtunk. 
        bufferedReader = new BufferedReader(new FileReader("kiserlet.txt"));
        int dobas2 = 0;
        String sor2 = "";
        String sor3 = "";
        int hanyszorFordultElo = 0;
        boolean elsoFej = false;
        boolean masodikFej = false;

        while ((sor = bufferedReader.readLine()) != null) {
            dobas2++;
            sor2 = sor2 + sor;
            sor3 = sor3 + sor;
            if (dobas2 > 2) {
                if (sor2.contains("IFFI")) {
                    hanyszorFordultElo++;
                    int elsoIndex = sor2.length() - 4;
                    int masodikIndex = sor2.length();
                    String helyettesitendo = sor2.substring(elsoIndex, masodikIndex);
                    sor2 = sor2.replace(helyettesitendo, "");
                }
            }

        }

        System.out.println("5. feladat");
        System.out.println("A kísérlet során " + hanyszorFordultElo + " alkalommal dobtak pontosan két fejet egymás után.");

//        6. Milyen hosszú volt a leghosszabb, csak fejekből álló részsorozat? Írassa ki a választ a képernyőre a mintának megfelelően, és adja meg egy ilyen részsorozat első tagjának helyét is! (A minta tagjainak számozását eggyel kezdjük.) 
        int ennyiTagbolAll = 0;
        boolean keres = true;
        String keresettString = "F";

        while (keres) {
            if (sor3.contains(keresettString)) {
                ennyiTagbolAll++;
                keresettString = keresettString + "F";
            } else {
                keresettString = "";
                for (int i = 0; i < ennyiTagbolAll; i++) {
                    keresettString += "F";
                }
                keres = false;
            }
        }
        System.out.println(keresettString);
        int sorozatKezdete = sor3.indexOf(keresettString);

        System.out.println("6. feladat");
        System.out.println("A leghosszabb tisztafej sorozat " + ennyiTagbolAll + " tagból áll, kezdete a(z) " + sorozatKezdete + ". dobás.");

//        1. Állítson elő és tároljon a memóriában 1000 db négy dobásból álló sorozatot! Számolja meg, hogy hány esetben követett egy háromtagú „tisztafej” sorozatot fej, illetve hány esetben írás! Az eredményt írassa ki a dobasok.txt állományba úgy, hogy az első sorba kerüljön az eredmény, a második sorban pedig egy-egy szóközzel elválasztva, egyetlen sorban szerepeljenek a dobássorozatok!
        ArrayList<String> sorozatok = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            String negyDobasbolAlloSorozat = "";
            for (int j = 0; j < 4; j++) {
                negyDobasbolAlloSorozat += penzfeldobas();
            }
            sorozatok.add(negyDobasbolAlloSorozat);
        }

        int ffff = 0;
        int fffi = 0;
        String baszottString = "";

        for (int i = 0; i < sorozatok.size(); i++) {
            if (sorozatok.get(i).equals("FFFF")) {
                ffff++;
            }
            if (sorozatok.get(i).equals("FFFI")) {
                fffi++;
            }
            baszottString = baszottString + " " + sorozatok.get(i);
        }

        System.out.println("7. feladat");
        System.out.println("FFFF: " + ffff + ", FFFI: " + fffi);
        System.out.println(baszottString);

        PrintWriter output = new PrintWriter(new FileWriter("dobasok.txt"));
        output.println("FFFF: " + ffff + ", FFFI: " + fffi);
        output.println(baszottString);
        output.close();
    }

    private static String getInput(String prompt) {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(prompt);
        System.out.flush();

        try {
            return stdin.readLine();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private static String penzfeldobas() {
        String eredmeny;
        if (Math.random() < 0.5) {
            eredmeny = "F";
        } else {
            eredmeny = "I";
        }
        return eredmeny;
    }
}
