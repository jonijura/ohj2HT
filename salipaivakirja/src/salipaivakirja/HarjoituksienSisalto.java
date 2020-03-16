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
public class HarjoituksienSisalto{

    private List<HarjoituksenSisalto> harjsis = new ArrayList<>();

    /**
     * TODO kasittele index out of bounds
     * @param i paikka josta haetaan
     * @return harjsis joka on talla paikalla
     * @throws SailoException laiton indeksi
     * <pre name="test">
     * #THROWS SailoException
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
     * h.anna(3); #THROWS SailoException
     * </pre>
     */
    public HarjoituksenSisalto anna(int i) throws SailoException {
        if (i < 0 || i >= getlkm())
            throw new SailoException("Laiton indeksi: " + i);
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
     * @throws SailoException jos ongelmia
     */
    public boolean poista(int id) throws SailoException {
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
     * tallennetaan harjoituksiensisallot.dat tiedostoon
     * @throws SailoException jos ei aukea
     */
    public void tallenna() throws SailoException {
        String tiedNimi = "harjsis.dat";
        try (PrintStream fo = new PrintStream(
                new FileOutputStream(tiedNimi, false))) {
            for (int i = 0; i < getlkm(); i++) {
                anna(i).tulosta(fo);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea: " + ex.getMessage());
            throw new SailoException("Tiedosto " + tiedNimi + " ei aukea");
        }

    }
    
    
    /**
     * luetaan tiedosto harjoituksenSisalto.dat
     * @throws SailoException jos ei aukea
     */
    public void lueTiedosto() throws SailoException {
        String tiedNimi = "harjsis.dat";
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)))) {
            while ( fi.hasNext() ) {
                try {
                    String s = fi.nextLine();
                    var harjsisa = new HarjoituksenSisalto();
                    harjsisa.parse(s);
                    lisaa(harjsisa);
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
        HarjoituksienSisalto harjsis = new HarjoituksienSisalto();

        HarjoituksenSisalto hsis1 = new HarjoituksenSisalto();
        HarjoituksenSisalto hsis2 = new HarjoituksenSisalto();
        HarjoituksenSisalto hsis3 = new HarjoituksenSisalto();

        harjsis.lisaa(hsis1);
        harjsis.lisaa(hsis2);
        harjsis.lisaa(hsis3);
        try {
            for (int i = 0; i < harjsis.getlkm(); i++) {
                HarjoituksenSisalto hsis;

                hsis = harjsis.anna(i);

                System.out.println("harjoituksen nro: " + i);
                hsis.tulosta(System.out);
            }
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
