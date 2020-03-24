/**
 * 
 */
package kanta;

/**
 * @author Joona1
 * @version 7.3.2020
 *
 */
public interface RekisteroituMerkkijono {
    

    /**
     * rekisteroi merkkijonon
     * @return id
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

}
