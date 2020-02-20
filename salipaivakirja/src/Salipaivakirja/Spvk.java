/**
 * 
 */
package Salipaivakirja;

/**
 * @author Joona1
 * @version 17.2.2020
 *
 */
public class Spvk {
    
    private final Liikkeet liikkeet = new Liikkeet();
    private final Harjoitukset harjoitukset = new Harjoitukset();
    

    /**
     * testi luento 14 38:40
     * @param liike lisattava liike
     * @throws SailoException jos ei mahdu
     * @example
     * <pre name="test">
     *
     * </pre>
     */
    public void lisaa(Liike liike) throws SailoException {
        liikkeet.lisaa(liike);
    }
    
    /**
     * @param harj lisattava harjoitus
     */
    public void lisaa(Harjoitus harj) {
        harjoitukset.lisaa(harj);
    }
    
    /**
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        Spvk harjoitus = new Spvk();

        Liike liike1 = new Liike("Kyykky", true);
        Liike liike2 = new Liike("Penkkipunnerrus", true);
        Liike liike3 = new Liike();

        liike3.rekisteroi();
        liike3.taytaLiikeTiedoilla();
        
        
        try {
            harjoitus.lisaa(liike1);
            harjoitus.lisaa(liike2);
            harjoitus.lisaa(liike3);        
        } catch (SailoException e) {
            System.err.println("tila lobbu");
        }
        
        
        System.out.println("==============Harjoitus testi==========");

        for (int i = 0; i < harjoitus.getLiikkeidenlkm(); i++) {
            Liike liike = harjoitus.annaLiike(i);
            System.out.println("liikkeen nro: " + i);
            liike.tulosta(System.out);
        }
    }

    /**
     * @param i liikkeen paikka
     * @return liike
     * @throws IndexOutOfBoundsException jos alkiota eei ole
     */
    public Liike annaLiike(int i) throws IndexOutOfBoundsException{
        return liikkeet.anna(i);
    }

    /**
     * @return liikkeiden lkm
     */
    public int getLiikkeidenlkm() {
        return liikkeet.getlkm();
    }
    
    /**
     * @return harjoitusten lkm
     */
    public int getHarjoitustenlkm() {
        return harjoitukset.getlkm();
    }
    
    /**
     * @param i paikka, josta harjoitusta haetaan
     * @return harjoitus
     */
    public Harjoitus annaHarjoitus(int i) {
        return harjoitukset.anna(i);
    }
}
