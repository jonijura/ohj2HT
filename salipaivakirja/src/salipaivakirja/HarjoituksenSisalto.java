/**
 * 
 */
package salipaivakirja;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.Rng;

/**
 * @author Joona1
 * @version 17.2.2020
 *
 */
public class HarjoituksenSisalto {

    private static final int TKOKO = 6;
    private int sisalto[] = new int[TKOKO]; // id|harj_id|liike_id|sarjoja|toistoja|paino
    private static int seuraavaNumero = 1;

    /**
     * ottaa harjoituksen sisallon kokonaislukutaulukosta, jossa on 5 alkiota
     * alkiot ovat harj_id|liike_id|sarjoja|toistoja|paino
     * @param t harjsis taulukkona
     * @example
     * <pre name="test">
     * int[] t = {2,1,3,12,65};
     * int[] t2 = {2,1,3,12,20,1};
     * HarjoituksenSisalto hs = new HarjoituksenSisalto(t);
     * HarjoituksenSisalto hs2 = new HarjoituksenSisalto(t2);
     * hs.getLiike_id()===1;
     * hs.getHarj_id()===2;
     * hs.tiedostona()==="|3|12|65";
     * hs2.tiedostona()==="|0|0|0";
     * hs2.get_id()-hs.get_id()===1;
     * </pre>
     */
    public HarjoituksenSisalto(int[] t) {
        if (t.length == 5) {
            for (int i = 0; i < t.length; i++) {
                this.sisalto[i + 1] = t[i];
            }
        }
        this.sisalto[0] = seuraavaNumero;
        seuraavaNumero++;
    }


    /**
     * muodostetaan harjsis tiedostossa olevasta merkkijonosta
     * @param s merkkijono
     */
    public HarjoituksenSisalto(String s) {
        parse(s);
    }


    /**
     * @param s merkkijono
     * @example
     * <pre name="test">
     * var hs = new HarjoituksenSisalto("7|1|2|3|4|5");
     * hs.tiedostona()==="|3|4|5";
     * </pre>
     */
    public void parse(String s) {
        var sb = new StringBuilder(s);
        sb.append("z");
        sisalto[0]=Mjonot.erota(sb, '|', -1);
        if(sisalto[0]>=seuraavaNumero)seuraavaNumero=sisalto[0]+1;
        for (int i = 1; i < TKOKO; i++) {
            this.sisalto[i] = Mjonot.erota(sb, '|', -1);
        }
    }


    /**
     * muodostaja harjoituksen sisallolle kun
     * harj_id on annettu, muut arvotaan
     * @param harj_id harjoitus id
     */
    public HarjoituksenSisalto(int harj_id) {
        if (harj_id < 1)
            throw new IndexOutOfBoundsException("harj_id ei voi olla alle 1!");
        this.sisalto[1] = harj_id;
        this.sisalto[0] = seuraavaNumero;
        seuraavaNumero++;
        this.sisalto[2] = Rng.rand(1, 7); // ei saa olla suurempi kuin
                                          // liikkeiden maara ks spvkGUI!
        this.sisalto[3] = Rng.rand(2, 5);
        this.sisalto[4] = Rng.rand(4, 12);
        this.sisalto[5] = 5 * Rng.rand(4, 24);
    }


    /**
     * muodostaja harjoituksen sisallolle, sisalto
     * arvotaan
     */
    public HarjoituksenSisalto() {
        this.sisalto[1] = 1;
        this.sisalto[0] = seuraavaNumero;
        seuraavaNumero++;
        this.sisalto[2] = Rng.rand(1, 5);
        this.sisalto[3] = Rng.rand(2, 5);
        this.sisalto[4] = Rng.rand(4, 12);
        this.sisalto[5] = 5 * Rng.rand(4, 24);
    }


    /**
     * @param out minne tulostetaan
     */
    public void tulosta(PrintStream out) {
        var tulostus = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            tulostus.append(sisalto[i]);
            tulostus.append("|");
        }
        tulostus.deleteCharAt(tulostus.length() - 1);
        out.println(tulostus);
    }


    /**
     * @return liike id
     */
    public int getLiike_id() {
        return sisalto[2];
    }


    /**
     * @return liike id
     */
    public int getHarj_id() {
        return sisalto[1];
    }


    /**
     * @return id
     */
    public int get_id() {
        return sisalto[0];
    }


    /**
     * muutetaan harjsis liikeid (spvk testipääohjelmaa varten)
     * @param i liikeid
     */
    public void MuutaLiike_id(int i) {
        sisalto[2] = i;
    }


    /**
     * @return harjsis tiedot  harjoituksen tiedot |sarjoja|toistoja|paino
     * taulukkoon sopivassa muodossa
     */
    public String tiedostona() {
        StringBuilder tiedosto = new StringBuilder();
        tiedosto.append("|");
        for (int i = 3; i < 6; i++) {
            tiedosto.append(sisalto[i]);
            tiedosto.append("|");
        }
        tiedosto.deleteCharAt(tiedosto.length() - 1);
        return tiedosto.toString();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i : sisalto) {
            sb.append(i);
            sb.append("|");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    /**
     * @return paino
     */
    public int getPaino() {
        return sisalto[5];
    }


    /**
     * @return sarjoja
     */
    public int getSarjoja() {
        return sisalto[3];
    }


    /**
     * @return toistoja
     */
    public int getToistoja() {
        return sisalto[4];
    }


    /**
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        HarjoituksenSisalto hsis = new HarjoituksenSisalto();
        hsis.tulosta(System.out);
        HarjoituksenSisalto hsis2 = new HarjoituksenSisalto();
        hsis2.tulosta(System.out);
        HarjoituksenSisalto hsis3 = new HarjoituksenSisalto();
        hsis3.tulosta(System.out);
        System.out.println(hsis3.tiedostona());
    }

}
