/**
 * 
 */
package Salipaivakirja;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Joona1
 * @version 18.2.2020
 *
 */
public class Harjoitus {

    
    private String pvm = "";
    private int harj_id = 0;
    private static int seuraavaNumero = 1;
    
    
    /**
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        Harjoitus harj = new Harjoitus("2.3.2020",true);
        Harjoitus harj2 = new Harjoitus("5.1.2020",true);
        Harjoitus harj3 = new Harjoitus("12.1.2020",true);
        harj.tulosta(System.out);
        harj2.tulosta(System.out);
        harj3.tulosta(System.out);
    }
    
    /**
     * @return harj_id
     */
    public int getharj_id() {
        return harj_id;
    }
    
    /**
     * liikkeen muodostaja + rekisterointi
     * @param s liikkeen nimi
     * @param b rekisteroidaanko liike
     */
    public Harjoitus(String s, boolean b) {
        this.pvm = s;
        if(b)this.rekisteroi();
    }
    
    /**
     * rekisteroidaan uusi liike
     * @return liike_id
     */
    public int rekisteroi() {
        harj_id = seuraavaNumero;
        seuraavaNumero++;
        return harj_id;
    }
    
    /**
     * tarvitaan tulevaisuudessa tekstilaatikkoon tulostamiseen
     * @param os outputstream tulostus
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

    /**
     * @param out minne tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(pvm + " | " + harj_id);
    }

    /**
     * @return pvm
     */
    public String getpvm() {
        return pvm;
    }
    

}
