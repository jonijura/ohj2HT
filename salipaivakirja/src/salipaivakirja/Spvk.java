/**
 * 
 */
package salipaivakirja;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

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
     * @throws SailoException jos ei loydy
     */
    public Liike annaLiike(int i) throws SailoException {
        return liikkeet.anna(i);
    }


    /**
     * @param i paikka, josta harjoitusta haetaan
     * @return harjoitus
     * @throws SailoException jos ei loydy
     */
    public Harjoitus annaHarjoitus(int i) throws SailoException{
        return harjoitukset.anna(i);
    }


    /**
     * @param i paikka, josta harjsis haetaan
     * @return harjsis
     * @throws SailoException jos ei loydy
     */ 
    public HarjoituksenSisalto annaHarjoituksenSisalto(int i) throws SailoException {
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
     * @throws SailoException jos ei loydy
     */
    public String annaLiikkeenNimi(int liike_id) throws SailoException{
        return liikkeet.annaLiike(liike_id).getLiikkeenNimi();
    }


    /**
     * @param harj_id harjoituksen id
     * @return harjoituksen sisalto taulukkoon sopivana tiedostona
     * @throws SailoException jos ongelmia
     */
    public String harjsisTiedostona(int harj_id) throws SailoException {
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
     * @param harj_id harjoitus id
     * @return lista harjsis joilla haluttu id
     * @throws SailoException jos ongelmia
     */
    public List<HarjoituksenSisalto> harjsis(int harj_id) throws SailoException{
        var palautus = new ArrayList<HarjoituksenSisalto>();
        for(int i=0; i<harjsis.getlkm(); i++) {
            if (harjsis.anna(i).getHarj_id() == harj_id) {
                palautus.add(harjsis.anna(i));
            }
        }
        return palautus;
    }


    /**
     * @param liike_id haettava liike
     * @return liikkeen historia tiedostona
     * @throws SailoException jos ongelmia
     * @example
     * <pre name="test">
     *  #THROWS SailoException
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
    public String liikeHistoriaTiedostona(int liike_id) throws SailoException {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < harjsis.getlkm(); i++) {
            if (harjsis.anna(i).getLiike_id() == liike_id) {

                String pvm = harjoitukset.annaHarjoitus(harjsis.anna(i).getHarj_id())
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
     * @throws SailoException jos ongelmia
     */
    public boolean onkoUusiLiike(String s) throws SailoException {
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
        try {

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
        } catch(SailoException e) {
            e.getMessage();
        }
    }


    /**
     * tallennetaan
     * @throws SailoException jos ei tallennu
     */
    public void tallenna() throws SailoException {
        liikkeet.tallenna();
        harjoitukset.tallenna();
        harjsis.tallenna();
        
    }


    /**
     * luetaan tiedostot
     * @throws SailoException jos ei loydy
     */
    public void lueTiedosto() throws SailoException {
        liikkeet.lueTiedosto();
        harjoitukset.lueTiedosto();
        harjsis.lueTiedosto();
    }


    /**
     * @param list lista johon liikkeiden nimet kerataan
     * @throws SailoException jos ongelmia
     */
    public void lisaaLiikkeet(ObservableList<String> list) throws SailoException {
        for(int i=0;i<liikkeet.getlkm();i++)list.add(liikkeet.anna(i).getLiikkeenNimi());
    }

}
