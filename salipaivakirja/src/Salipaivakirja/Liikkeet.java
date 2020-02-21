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

        liikkeet.lisaa(liike1);
        System.out.println(liikkeet.taulukonKoko());
        liikkeet.lisaa(liike2);
        System.out.println(liikkeet.taulukonKoko());
        liikkeet.lisaa(liike3);
        System.out.println(liikkeet.taulukonKoko());

        System.out.println("==============Liikkeet testi==========");

        for (int i = 0; i < liikkeet.getlkm(); i++) {
            Liike liike = liikkeet.anna(i);
            System.out.println("liikkeen nro: " + i);
            liike.tulosta(System.out);
        }
    }


    private int taulukonKoko() {
        return alkiot.length;
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
     */
    public void lisaa(Liike liike) {
        if (lkm >= alkiot.length)kasvataTaulukkoa(); 
        alkiot[lkm] = liike;
        lkm++;
    }

    
    /**
     * kun taulukosta alkaa tila loppua
     */
    private void kasvataTaulukkoa() {
        Liike[] liikkeet = new Liike[alkiot.length + 5];
        for (int i = 0; i < lkm; i++) {
            liikkeet[i] = alkiot[i];
        }
        alkiot = liikkeet;
    }


    /**
     * @param i haettavan liikkeen paikka
     * @return haettava liike
     * @throws IndexOutOfBoundsException ei ole tata alkiota
     */
    public Liike anna(int i) throws IndexOutOfBoundsException {
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


    /**
     * etsitaan liike jolla on haluttu liike id
     * @param liike_id .
     * @return haluttu liike tai null jos ei loydy
     */
    public Liike annaLiike(int liike_id) {
        for (int i = 0; i < lkm; i++)
            if (alkiot[i].getLiike_id() == liike_id)
                return alkiot[i];
        return null;
    }

}
