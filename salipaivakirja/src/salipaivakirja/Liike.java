/**
 * 
 */
package salipaivakirja;

import java.io.OutputStream;
import kanta.*;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * luokka yksittaiselle liikkeelle
 * @author Joona Räty -jonijura jonijura@student.jyu.fi
 * @version 17.2.2020
 *
 */
public class Liike implements RekisteroituMerkkijono {

    private int liike_id;
    private String liikkeenNimi = "";

    private static int seuraavaNumero = 1;

    /**
     * liikkeen muodostaja + rekisterointi
     * @param s liikkeen nimi
     * @param b rekisteroidaanko liike
     */
    public Liike(String s, boolean b) {
        this.liikkeenNimi = s;
        if (b)
            this.rekisteroi();
    }


    /**
     * @param s liike tiedostoon tallennettuna merkkijonona
     */
    public Liike(String s) {
        parse(s);
    }


    /**
     * @param s liike tiedostoon tallennettuna merkkijonona
     * @example
     * <pre name="test">
     * Liike l1 = new Liike();
     * l1.parse("15|kuperkeikka");
     * l1.getLiikkeenNimi()==="kuperkeikka";
     * l1.getLiike_id()===15;
     * Liike l2 = new Liike();
     * l2.getLiike_id()-l1.getLiike_id()===1;
     * l1.parse("");
     * l1.getLiikkeenNimi()==="VirheellinenTallennusmuoto";
     * l1.getLiike_id()===-1;
     * </pre>
     */
    @Override
    public void parse(String s) {
        var sb = new StringBuffer(s);
        liike_id = Mjonot.erotaInt(sb, -1);
        if (liike_id >= seuraavaNumero)
            seuraavaNumero = liike_id + 1;
        if (sb.length() != 0)
            this.liikkeenNimi = sb.deleteCharAt(0).toString();
        else
            this.liikkeenNimi = "VirheellinenTallennusmuoto";
    }       


    /**
     * parametriton muodostaja
     */
    public Liike() {
        this.liikkeenNimi = "kuperkeikka" + Rng.rand(1, 1000);
        rekisteroi();
    }


    /**
     * @param out minne tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(liikkeenNimi + " | " + liike_id);
    }


    /**
     * tarvitaan tulevaisuudessa tekstilaatikkoon tulostamiseen
     * @param os outputstream tulostus
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }


    /**
     * rekisteroidaan uusi liike
     * @return liike_id
     * @example
     * <pre name="test">
     *   Liike l1 = new Liike();
     *   Liike l2 = new Liike();
     *   int n1 = l1.getLiike_id();
     *   int n2 = l2.getLiike_id();
     *   n1 === n2-1;
     * </pre>
     */
    @Override
    public int rekisteroi() {
        liike_id = seuraavaNumero;
        seuraavaNumero++;
        return liike_id;
    }


    /**
     * rakennusteline
     */
    public void taytaLiikeTiedoilla() {
        liikkeenNimi = "leuanveto" + Rng.rand(100, 999);
    }


    /**
     * @return liike_id
     */
    public int getLiike_id() {
        return liike_id;
    }


    /**
     * @return liikkeen nimi
     */
    public String getLiikkeenNimi() {
        return liikkeenNimi;
    }


    @Override
    public String toString() {
        return liike_id + "|" + liikkeenNimi;
    }


    @Override
    public String getString() {
        return getLiikkeenNimi();
    }


    @Override
    public int getID() {
        return getLiike_id();
    }


    /**
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        Liike liike = new Liike();
        Liike liike2 = new Liike("4|maastaveto");

        liike.tulosta(System.out);
        liike.rekisteroi();
        liike.taytaLiikeTiedoilla();
        liike.tulosta(System.out);

        liike2.tulosta(System.out);
        liike2.rekisteroi();
        liike2.tulosta(System.out);

    }

}
