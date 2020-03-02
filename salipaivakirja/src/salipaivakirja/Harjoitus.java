/**
 * 
 */
package salipaivakirja;

import java.io.OutputStream;
import java.io.PrintStream;

import kanta.Rng;

/**
 * @author Joona1
 * @version 18.2.2020
 *
 */
public class Harjoitus {

    private String pvm = "";
    private int harj_id = 1;
    private static int seuraavaNumero = 1;

    /**
     * parametriton muodostaja, joka arpoo sisalloksi jotain jarkevaa
     */
    public Harjoitus() {
        StringBuilder sb = new StringBuilder();
        sb.append(Rng.rand(1, 28));
        sb.append('.');
        sb.append(Rng.rand(1, 12));
        sb.append('.');
        sb.append(Rng.rand(2000, 2020));
        this.pvm = sb.toString();
        this.rekisteroi();
    }


    /**
     * harjoituksen muodostaja + rekisterointi
     * @param s harjoituksen tiedot merkkijonona
     * @param b rekisteroidaanko Harjoitus
     */
    public Harjoitus(String s, boolean b) {
        this.pvm = s;
        if (b)
            this.rekisteroi();
    }


    /**
     * rekisteroidaan uusi harjoitus
     * @return harj_id
     * @example
     * <pre name="test">
     *   Harjoitus l1 = new Harjoitus();
     *   Harjoitus l2 = new Harjoitus();
     *   int n1 = l1.getharj_id();
     *   int n2 = l2.getharj_id();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        harj_id = seuraavaNumero;
        seuraavaNumero++;
        return harj_id;
    }


    /**
     * @return harj_id
     */
    public int getharj_id() {
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


    /**
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        Harjoitus harj = new Harjoitus();
        Harjoitus harj2 = new Harjoitus();
        Harjoitus harj3 = new Harjoitus();
        harj.tulosta(System.out);
        harj2.tulosta(System.out);
        harj3.tulosta(System.out);
    }

}
