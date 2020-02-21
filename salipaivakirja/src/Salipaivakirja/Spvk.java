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
    private final HarjoituksienSisalto harjsis = new HarjoituksienSisalto();

    /**
     * testi luento 14 38:40
     * @param liike lisattava liike
     * @example
     * <pre name="test">
     *
     * </pre>
     */
    public void lisaa(Liike liike) {
        liikkeet.lisaa(liike);
    }


    /**
     * @param harj lisattava harjoitus
     */
    public void lisaa(Harjoitus harj) {
        harjoitukset.lisaa(harj);
    }


    /**
     * @param harjsisa lisattava harjoitusksensisalto
     */
    public void lisaa(HarjoituksenSisalto harjsisa) {
        this.harjsis.lisaa(harjsisa);
    }


    /**
     * TODO paremmat testit
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        Spvk harjoitus = new Spvk();

        Liike liike1 = new Liike("Kyykky", true);
        Liike liike2 = new Liike("Penkkipunnerrus", true);
        Liike liike3 = new Liike();

        liike3.rekisteroi();
        liike3.taytaLiikeTiedoilla();

        harjoitus.lisaa(liike1);
        harjoitus.lisaa(liike2);
        harjoitus.lisaa(liike3);

        System.out.println("==============Harjoitus testi==========");

        for (int i = 0; i < harjoitus.getLiikkeidenlkm(); i++) {
            Liike liike = harjoitus.annaLiike(i);
            System.out.println("liikkeen nro: " + i);
            liike.tulosta(System.out);
        }
        System.out.println(harjoitus.annaLiikkeenNimi(2));
    }


    /**
     * @param i liikkeen paikka
     * @return liike
     * @throws IndexOutOfBoundsException jos alkiota eei ole
     */
    public Liike annaLiike(int i) throws IndexOutOfBoundsException {
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


    /**
     * @param liike_id . 
     * @return liikkeen nimi
     */
    public String annaLiikkeenNimi(int liike_id) {
        return liikkeet.annaLiike(liike_id).getLiikkeenNimi();
    }


    /**
     * @param harj_id harjoituksen id
     * @return harjoituksen sisalto taulukkoon sopivana tiedostona
     */
    public String harjsisTiedostona(int harj_id) {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < harjsis.getlkm(); i++) {
            if (harjsis.anna(i).getHarj_id() == harj_id) {

                String liikkeenNimi = annaLiikkeenNimi(
                        harjsis.anna(i).getLiike_id());
                sb.append(liikkeenNimi);
                sb.append(harjsis.anna(i).tiedostona());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    
    /**
     * @param liike_id haettava liike
     * @return liikkeen historia tiedostona
     */
    public String liikeHistoriaTiedostona(int liike_id) {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < harjsis.getlkm(); i++) {
            if (harjsis.anna(i).getLiike_id() == liike_id) {

                String pvm = harjoitukset.anna(harjsis.anna(i).getHarj_id()).getpvm();
                sb.append(pvm);
                sb.append(harjsis.anna(i).tiedostona());
                sb.append("\n");
            }
        }
        return sb.toString();
    }


    /**
     * katsotaan onko liike uusi vai onko se jokin edellisista
     * @param s liikkeen nimi
     * @return onko liike uusi
     */
    public boolean onkoUusiLiike(@SuppressWarnings("unused") String s) {
        // TODO Auto-generated method stub
        return true;
    }
}
