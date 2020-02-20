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
    
    private int sisalto[] = new int[6];
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
    }

    /**
     * @param out minne tulostetaan
     */
    public void tulosta(PrintStream out) {
        StringBuffer tulostus = new StringBuffer("harjsis: ");
        for(int i=0;i<6;i++) {
            tulostus.append(sisalto[i]);
            tulostus.append(" | ");
        }
        out.println(tulostus);
    }
    
    /**
     * muodostaja harjoituksen sisallolle
     */
    public HarjoituksenSisalto() {
        this.sisalto[0]=1;
        this.sisalto[1]=seuraavaNumero;
        seuraavaNumero++;
        this.sisalto[2]=1;
        this.sisalto[3]=Liike.rand(2,5);
        this.sisalto[4]=Liike.rand(4,12);
        this.sisalto[5]=5*Liike.rand(4,24);
    }
    
}
