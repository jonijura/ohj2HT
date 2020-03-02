/**
 * 
 */
package salipaivakirja;

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
     * @param liike lisattava liike
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
        harjsis.lisaa(harjsisa);
    }


    /**
     * @param i paikka josta liiketta haetaan
     * @return liike
     */
    public Liike annaLiike(int i) {
        return liikkeet.anna(i);
    }


    /**
     * @param i paikka, josta harjoitusta haetaan
     * @return harjoitus
     */
    public Harjoitus annaHarjoitus(int i) {
        return harjoitukset.anna(i);
    }


    /**
     * @param i paikka, josta harjsis haetaan
     * @return harjsis
     */
    public HarjoituksenSisalto annaHarjoituksenSisalto(int i) {
        return harjsis.anna(i);
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
     * @return harjoituksienSisallot lkm
     */
    public int getHarjsislkm() {
        return harjsis.getlkm();
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
     * @example
     * <pre name="test">
     * Spvk spvk = new Spvk();
     * spvk.lisaa(new Liike("kuperkeikka",true));
     * spvk.lisaa(new Liike("karrynpyora",true));
     * spvk.lisaa(new Harjoitus("3.4.2019",true));
     * spvk.lisaa(new Harjoitus("1.4.2019",true));
     * spvk.lisaa(new Harjoitus("5.4.2019",true));
     * int[] t1 = {2,1,3,12,65};
     * int[] t2 = {1,2,4,4,40};
     * int[] t3 = {1,1,5,9,120};
     * int[] t4 = {3,1,3,8,75};
     * spvk.lisaa(new HarjoituksenSisalto(t1));
     * spvk.lisaa(new HarjoituksenSisalto(t2));
     * spvk.lisaa(new HarjoituksenSisalto(t3));
     * spvk.lisaa(new HarjoituksenSisalto(t4));
     * spvk.liikeHistoriaTiedostona(1).length()===51;
     * spvk.harjsisTiedostona(1).length()===40;
     * </pre>
     */
    public String liikeHistoriaTiedostona(int liike_id) {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < harjsis.getlkm(); i++) {
            if (harjsis.anna(i).getLiike_id() == liike_id) {

                String pvm = harjoitukset.annaID(harjsis.anna(i).getHarj_id())
                        .getpvm();
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
    public boolean onkoUusiLiike(String s) {
        return liikkeet.onkoUusi(s);
    }


    /**
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        Spvk spvk = new Spvk();
        int koko = 5; // ei saa olla pienempi kuin parametrittoman
                      // HarjoituksenSisalto() arpoma liike_id!

        System.out
                .println("==============Lisays ja haku testi==========" + '\n');

        for (int i = 0; i < koko; i++) {
            spvk.lisaa(new Liike());
            spvk.lisaa(new HarjoituksenSisalto());
            spvk.lisaa(new Harjoitus());
        }

        for (int i = 0; i < koko; i++) {
            spvk.annaHarjoitus(i).tulosta(System.out);
            spvk.annaLiike(i).tulosta(System.out);
            spvk.annaHarjoituksenSisalto(i).tulosta(System.out);
        }

        System.out.println(
                '\n' + "==============Harjoituksen Sisalto testi==========");
        System.out.println(spvk.harjsisTiedostona(1));

        System.out.println(
                "==============Liikkeen harjoitushistoria testi==========");
        for (int i = 0; i < koko; i++)
            spvk.annaHarjoituksenSisalto(i).MuutaLiike_id(1);
        System.out.println(spvk.liikeHistoriaTiedostona(1));
    }

}
