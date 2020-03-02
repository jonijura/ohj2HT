/**
 * 
 */
package salipaivakirja;

import java.util.ArrayList;
import java.util.List;

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
     * @throws IndexOutOfBoundsException laiton indeksi
     * @example
     * <pre name="test">
     * var h = new Harjoitukset();
     * h.lisaa(new Harjoitus());
     * h.lisaa(new Harjoitus());
     * h.lisaa(new Harjoitus());
     * h.lisaa(new Harjoitus());
     * h.getlkm()===4;
     * h.annaID(3).getharj_id()===3;
     * h.poista(3)===true;
     * h.poista(3)===false;
     * h.getlkm()===3;
     * h.anna(3); #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public Harjoitus anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || getlkm() <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return harjoitukset.get(i);
    }

    
    /**
     * @param id harj_id
     * @return harjoitus jolla on haluttu id
     */
    public Harjoitus annaID(int id) {
        for(int i=0;i<getlkm();i++)
            if(anna(i).getharj_id()==id) return anna(i);
        return null;
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
     */
    public boolean poista(int id) {
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
            Harjoitus harjoitus = harjoitukset.anna(i);
            System.out.println("harjoituksen nro: " + i);
            harjoitus.tulosta(System.out);
        }
    }

}
