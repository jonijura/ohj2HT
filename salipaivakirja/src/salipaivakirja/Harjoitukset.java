/**
 * 
 */
package salipaivakirja;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Joona1
 * @version 18.2.2020
 *
 */
public class Harjoitukset {

    private List<Harjoitus> harjoitukset = new ArrayList<>();

    /**
     * @param i paikka, josta liiketta haetaan
     * @return paikassa oleva liike
     * @throws SailoException laiton indeksi
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * var h = new Harjoitukset();
     * h.lisaa(new Harjoitus());
     * h.lisaa(new Harjoitus());
     * h.lisaa(new Harjoitus());
     * h.lisaa(new Harjoitus());
     * h.getlkm()===4;
     * h.annaHarjoitus(3).getharj_id()===3;
     * h.poista(3)===true;
     * h.poista(3)===false;
     * h.getlkm()===3;
     * h.anna(3); #THROWS SailoException
     * </pre>
     */
    public Harjoitus anna(int i) throws SailoException {
        if (i < 0 || getlkm() <= i)
            throw new SailoException("Laiton indeksi: " + i);
        return harjoitukset.get(i);
    }

    
    /**
     * @param id harj_id
     * @return harjoitus jolla on haluttu id
     * @throws SailoException jos ongelmia
     * @throws IndexOutOfBoundsException sopivaa id ei loydy
     */
    public Harjoitus annaHarjoitus(int id) throws SailoException {
        for(int i=0;i<getlkm();i++)
            if(anna(i).getharj_id()==id) return anna(i);
        throw new SailoException("Harjoitusta ei löytynyt id=" + id);
    }

    /**
     * @return harjoitusten lkm
     */
    public int getlkm() {
        return harjoitukset.size();
    }


    /**
     * @param harj lisattava harjoitus
     */
    public void lisaa(Harjoitus harj) {
        harjoitukset.add(harj);
    }


    /**
     * @param id poistettavan harjoituksen id
     * @return poitettiinko mitaan
     * @throws SailoException jos ongelmia
     */
    public boolean poista(int id) throws SailoException {
        boolean b = false;
        for (int i = 0; i < getlkm(); i++) {
            if (anna(i).getharj_id() == id) {
                harjoitukset.remove(i);
                b = true;
            }
        }
        return b;
    }

    /**
     * tallennetaan harjoitukset.dat tiedostoon
     * @throws SailoException jos ei aukea
     */
    public void tallenna() throws SailoException {
        String tiedNimi = "harj.dat";
        try (PrintStream fo = new PrintStream(new FileOutputStream(tiedNimi, false))) {
            for (int i = 0; i < getlkm(); i++) {
                fo.println(anna(i).toString());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea: " + ex.getMessage());
            throw new SailoException("Tiedosto " + tiedNimi + " ei aukea");
        }
        
    }
    


    /**
     * luetaan tiedot tiedostosta harjoitukset.dat
     * @throws SailoException jos ei aukea
     */
    public void lueTiedosto() throws SailoException {
        String tiedNimi = "harj.dat";
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)))) { // Jotta UTF8/ISO-8859 toimii
            while ( fi.hasNext() ) {
                try {
                    String s = fi.nextLine();
                    var harj = new Harjoitus();
                    harj.parse(s);
                    lisaa(harj);
                } catch (NumberFormatException ex) {
                    // Hylätään
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea! " + ex.getMessage());
            throw new SailoException("Tiedosto " + tiedNimi + " ei aukea");
        }
        
    }

    /**
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        Harjoitukset harjoitukset = new Harjoitukset();

        Harjoitus harj = new Harjoitus("2.3.2020", true);
        Harjoitus harj2 = new Harjoitus("5.1.2020", true);
        Harjoitus harj3 = new Harjoitus("12.1.2020", true);
        harjoitukset.lisaa(harj);
        harjoitukset.lisaa(harj2);
        harjoitukset.lisaa(harj3);

        for (int i = 0; i < harjoitukset.getlkm(); i++) {
            Harjoitus harjoitus;
            try {
                harjoitus = harjoitukset.anna(i);
                System.out.println("harjoituksen nro: " + i);
                harjoitus.tulosta(System.out);
            } catch (SailoException e) {
                e.printStackTrace();
            }
            
        }
    }





}
