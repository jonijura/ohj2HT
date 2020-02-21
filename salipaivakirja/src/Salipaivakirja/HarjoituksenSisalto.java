/**
 * 
 */
package Salipaivakirja;

import java.io.PrintStream;

/**
 * @author Joona1
 * @version 17.2.2020
 *
 */
public class HarjoituksenSisalto {

    private int sisalto[] = new int[6]; // id|harj_id|liike_id|sarjoja|toistoja|paino
    private static int seuraavaNumero = 1;

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


    /**
     * @param out minne tulostetaan
     */
    public void tulosta(PrintStream out) {
        StringBuilder tulostus = new StringBuilder("harjsis: ");
        for (int i = 0; i < 6; i++) {
            tulostus.append(sisalto[i]);
            tulostus.append(" | ");
        }
        out.println(tulostus);
    }


    /**
     * muodostaja harjoituksen sisallolle kun
     * harj_id on annettu, muut arvotaan
     * @param harj_id harjoitus id
     */
    public HarjoituksenSisalto(int harj_id) {
        this.sisalto[0] = harj_id;
        this.sisalto[1] = seuraavaNumero;
        seuraavaNumero++;
        this.sisalto[2] = Liike.rand(1, 5); //ei saa olla suurempi kuin liikkeiden maara!
        this.sisalto[3] = Liike.rand(2, 5);
        this.sisalto[4] = Liike.rand(4, 12);
        this.sisalto[5] = 5 * Liike.rand(4, 24);
    }


    /**
     * muodostaja harjoituksen sisallolle, sisalto
     * arvotaan
     */
    public HarjoituksenSisalto() {
        this.sisalto[0] = 0;
        this.sisalto[1] = seuraavaNumero;
        seuraavaNumero++;
        this.sisalto[2] = Liike.rand(1, 5); //ei saa olla suurempi kuin liikkeiden maara!
        this.sisalto[3] = Liike.rand(2, 5);
        this.sisalto[4] = Liike.rand(4, 12);
        this.sisalto[5] = 5 * Liike.rand(4, 24);
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
        return sisalto[0];
    }


    /**
     * @return harjsis tiedot harjoituksen tiedot taulukkoon sopivassa muodossa
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

}
