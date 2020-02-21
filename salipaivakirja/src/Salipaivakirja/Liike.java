/**
 * 
 */
package Salipaivakirja;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Joona1
 * @version 17.2.2020
 *
 */
public class Liike {

    private int liike_id;
    private String liikkeenNimi = "";

    private static int seuraavaNumero = 1;

    /**
     * @param out minne tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(liikkeenNimi + " | " + liike_id);
    }


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
     * parametriton muodostaja
     */
    public Liike() {
        //
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
     */
    public int rekisteroi() {
        liike_id = seuraavaNumero;
        seuraavaNumero++;
        return liike_id;
    }


    /**
     * arvotaan luku halutulata valilta
     * @param ala alaraja
     * @param yla ylaraja
     * @return satunnainen luku
     */
    public static int rand(int ala, int yla) {
        double n = (yla - ala) * Math.random() + ala;
        return (int) Math.round(n);
    }


    /**
     * rakennusteline
     */
    public void taytaLiikeTiedoilla() {
        liikkeenNimi = "leuanveto" + rand(100, 999);
    }


    /**
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        Liike liike = new Liike();
        Liike liike2 = new Liike();

        liike.tulosta(System.out);
        liike.rekisteroi();
        liike.taytaLiikeTiedoilla();
        liike.tulosta(System.out);

        liike2.rekisteroi();
        liike2.tulosta(System.out);
        liike2.taytaLiikeTiedoilla();
        liike2.tulosta(System.out);
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

}
