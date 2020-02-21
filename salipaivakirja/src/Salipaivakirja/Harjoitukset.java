/**
 * 
 */
package Salipaivakirja;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joona1
 * @version 18.2.2020
 *
 */
public class Harjoitukset {
    
    private List<Harjoitus> harjoitukset = new ArrayList<>();
    @SuppressWarnings("unused")
    private int lkm = 0;

    /**
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        Harjoitukset harjoitukset = new Harjoitukset();
        
        Harjoitus harj = new Harjoitus("2.3.2020",true);
        Harjoitus harj2 = new Harjoitus("5.1.2020",true);
        Harjoitus harj3 = new Harjoitus("12.1.2020",true);
        harjoitukset.lisaa(harj);
        harjoitukset.lisaa(harj2);
        harjoitukset.lisaa(harj3);
        
        for (int i = 0; i < harjoitukset.getlkm(); i++) {
            Harjoitus harjoitus = harjoitukset.anna(i);
            System.out.println("harjoituksen nro: " + i);
            harjoitus.tulosta(System.out);
        }
    }

    /**
     * TODO index out of bounds exception
     * @param i paikka, josta liiketta haetaan
     * @return paikassa oleva liike
     */
    public Harjoitus anna(int i) {
        return harjoitukset.get(i);
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
        lkm++;
    }
    
    

}
