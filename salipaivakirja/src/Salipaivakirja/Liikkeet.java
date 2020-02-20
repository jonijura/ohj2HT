/**
 * 
 */
package Salipaivakirja;

/**
 * TODO: muuta taulukon kokoa kun tila loppuu
 * @author Joona1
 * @version 17.2.2020
 *
 */
public class Liikkeet {

    private static final int MAX_LIIKKEITA = 5;
    private int lkm = 0;
    // private String tiedostonNimi = "";
    private Liike[] alkiot = new Liike[MAX_LIIKKEITA];

    /**
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        Liikkeet liikkeet = new Liikkeet();

        Liike liike1 = new Liike("Kyykky", true);
        Liike liike2 = new Liike("Penkkipunnerrus", true);
        Liike liike3 = new Liike();

        liike3.rekisteroi();
        liike3.taytaLiikeTiedoilla();

        try {
            liikkeet.lisaa(liike1);
            liikkeet.lisaa(liike2);
            liikkeet.lisaa(liike3);

            System.out.println("==============Liikkeet testi==========");

            for (int i = 0; i < liikkeet.getlkm(); i++) {
                Liike liike = liikkeet.anna(i);
                System.out.println("liikkeen nro: " + i);
                liike.tulosta(System.out);
            }
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
    }


    /**
     * muodostaja
     */
    public Liikkeet() {

    }


    /**
     * testit ks luento 13
     * lisaa uuden liikkeen taulukkoon
     * @param liike lisattava liike
     * @throws SailoException poikkeus kun taulukosta loppuu tila
     */
    public void lisaa(Liike liike) throws SailoException {
        if (lkm >= alkiot.length)
            throw new SailoException("liikaa alkioita");
        alkiot[lkm] = liike;
        lkm++;
    }


    /**
     * @param i haettavan liikkeen paikka
     * @return haettava liike
     * @throws IndexOutOfBoundsException ei ole tata alkiota
     */
    public Liike anna(int i) throws IndexOutOfBoundsException  {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }


    /**
     * @return lukumaara
     */
    public int getlkm() {
        return lkm;
    }

}
