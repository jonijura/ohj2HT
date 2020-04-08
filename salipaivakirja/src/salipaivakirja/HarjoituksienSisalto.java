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
import java.util.Iterator;
import java.util.Scanner;

/**
 * luokka kaikille harjoituksien sisalloille
 * @author Joona Räty -jonijura jonijura@student.jyu.fi
 * @version 18.2.2020
 *
 */
public class HarjoituksienSisalto implements Iterable<HarjoituksenSisalto>{

    private ArrayList<HarjoituksenSisalto> harjsis = new ArrayList<>();

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
     * h.anna(4); #THROWS SailoException
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
     * @example
     * <pre name="test">
     * var hs = new HarjoituksienSisalto();
     * hs.lisaa(new HarjoituksenSisalto("1|1|1|1|1|1"));
     * hs.lisaa(new HarjoituksenSisalto("2|1|1|1|1|1"));
     * hs.lisaa(new HarjoituksenSisalto("3|1|1|1|1|1"));
     * hs.getlkm()===3;
     * hs.poista(1)===true;
     * hs.getlkm()===2;
     * hs.poistaKaikki(1)===2;
     * hs.getlkm()===0;
     * </pre>
     */
    public boolean poista(int id){
        boolean b = false;
        for (int i=0;i<harjsis.size();i++) {
            if (harjsis.get(i).get_id() == id) {
                harjsis.remove(i);
                b = true;
            }
        }
        return b;
    }
    
    
    /**
     * @param id harj id
     * @return montako poistettiin
     */
    public int poistaKaikki(int id){
        int lkm = 0;
        for (int i=0;i<harjsis.size();i++) {
            if (harjsis.get(i).getHarj_id() == id) {
                harjsis.remove(i);
                lkm++;
                i--;
            }
        }
        return lkm;
    }


    /**
     * tallennetaan harjoituksiensisallot.dat tiedostoon
     * @param tiedNimi tiedosto, jonne tallennetaan
     * @throws SailoException jos ei aukea
     */
    public void tallenna(String tiedNimi) throws SailoException {
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
     * @param tiedNimi tiedosto, joka luetaan
     * @throws SailoException jos ei aukea
     */
    public void lueTiedosto(String tiedNimi) throws SailoException {
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
    

    @Override
    public Iterator<HarjoituksenSisalto> iterator() {
        return harjsis.iterator();
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
