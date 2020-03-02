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
public class HarjoituksienSisalto {

    private List<HarjoituksenSisalto> harjsis = new ArrayList<>();

    /**
     * TODO kasittele index out of bounds
     * @param i paikka josta haetaan
     * @return harjsis joka on talla paikalla
     * @throws IndexOutOfBoundsException laiton indeksi
     * <pre name="test">
     * var h = new HarjoituksienSisalto();
     * h.lisaa(new HarjoituksenSisalto());
     * h.lisaa(new HarjoituksenSisalto());
     * h.lisaa(new HarjoituksenSisalto());
     * h.lisaa(new HarjoituksenSisalto());
     * h.getlkm()===4;
     * h.anna(3).get_id()-h.anna(2).get_id()===1;
     * h.poista(3)===true;
     * h.poista(3)===false;
     * h.getlkm()===3;
     * h.anna(3); #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public HarjoituksenSisalto anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= getlkm())
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
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


    /**
     * @param id poistettavan harjoituksensisallon id
     * @return poitettiinko mitaan
     */
    public boolean poista(int id) {
        boolean b = false;
        for (int i = 0; i < getlkm(); i++) {
            if (anna(i).get_id() == id) {
                harjsis.remove(i);
                b = true;
            }
        }
        return b;
    }


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

}
