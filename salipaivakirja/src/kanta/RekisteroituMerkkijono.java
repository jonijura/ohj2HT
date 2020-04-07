/**
 * 
 */
package kanta;

import java.util.Comparator;

/**
 * @author Joona1
 * @version 7.3.2020
 *
 */
public interface RekisteroituMerkkijono {
    

    /**
     * rekisteroi merkkijonon
     * @return id
     * @example
     */
    public int rekisteroi();

    
    /**
     * palauttaa merkkijonon
     * @return merkkijono
     */
    public String getString();
    
    
    /**
     * laittaa oliolle tiedot | merkilla erotetusta tiedostosta
     * @param s tiedostorivi
     */
    public void parse(String s);


    /**
     * @return id
     */
    public int getID();
    
    /**
     * @author Joona1
     * @version 7.4.2020
     *
     */
    public static class Vertailija implements Comparator<RekisteroituMerkkijono> {

        @Override
        public int compare(RekisteroituMerkkijono o1,
                RekisteroituMerkkijono o2) {
            return o1.getString().compareToIgnoreCase(o2.getString());
        }
        
    }

}
