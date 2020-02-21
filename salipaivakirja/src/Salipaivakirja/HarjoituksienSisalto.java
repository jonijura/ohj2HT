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
public class HarjoituksienSisalto {

    private List<HarjoituksenSisalto> harjsis = new ArrayList<>();

    /**
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        HarjoituksienSisalto harjsis = new HarjoituksienSisalto();

        HarjoituksenSisalto hsis1 = new HarjoituksenSisalto();
        HarjoituksenSisalto hsis2 = new HarjoituksenSisalto();
        HarjoituksenSisalto hsis3 = new HarjoituksenSisalto();

        harjsis.lisaa(hsis1);
        harjsis.lisaa(hsis2);
        harjsis.lisaa(hsis3);

        for (int i = 0; i < harjsis.getlkm(); i++) {
            HarjoituksenSisalto hsis = harjsis.anna(i);
            System.out.println("harjoituksen nro: " + i);
            hsis.tulosta(System.out);
        }
    }


    /**
     * TODO kasittele index out of bounds
     * @param i paikka josta haetaan
     * @return harjsis joka on talla paikalla
     */
    public HarjoituksenSisalto anna(int i) {
        return harjsis.get(i);
    }


    /**
     * @return listan koko
     */
    public int getlkm() {
        return harjsis.size();
    }


    /**
     * @param hsis lisattava harjsis
     */
    public void lisaa(HarjoituksenSisalto hsis) {
        harjsis.add(hsis);
    }

}
