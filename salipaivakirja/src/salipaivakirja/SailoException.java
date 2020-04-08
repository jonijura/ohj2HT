/**
 * 
 */
package salipaivakirja;

/**
 * luokka tiedostojen sailomisessa ilmeneville poikkeuksille
 * @author Joona Räty -jonijura jonijura@student.jyu.fi
 * @version 17.2.2020
 *
 */
public class SailoException extends Exception{
    private static final long serialVersionUID = 1L;


    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
     * käytettävä viesti
     * @param viesti Poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}
