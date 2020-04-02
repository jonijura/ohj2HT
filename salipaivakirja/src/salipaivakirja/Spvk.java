/**
 * 
 */
package salipaivakirja;

import java.util.Stack;

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
    public Harjoitus annaHarjoitus(int i) throws SailoException {
        return harjoitukset.anna(i);
    }


    /**
     * @param i paikka, josta harjsis haetaan
     * @return harjsis
     * @throws SailoException jos ei loydy
     */
    public HarjoituksenSisalto annaHarjoituksenSisalto(int i)
            throws SailoException {
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
    public String annaLiikkeenNimi(int liike_id) throws SailoException {
        return liikkeet.annaLiike(liike_id).getLiikkeenNimi();
    }


    /**
     * @param harj_id harjoituksen id
     * @return harjoituksen sisalto taulukkoon sopivana tiedostona
     * @throws SailoException jos ongelmia
     */
    public String harjsisTiedostona(int harj_id) throws SailoException {
        StringBuilder sb = new StringBuilder("\n");
        for (HarjoituksenSisalto hs : harjsis) {
            if (hs.getHarj_id() == harj_id) {

                String liikkeenNimi = annaLiikkeenNimi(hs.getLiike_id());
                sb.append(liikkeenNimi);
                sb.append(hs.tiedostona());
                sb.append("\n");
            }
        }
        return sb.toString();
    }


    /**
     * @param harj_id harjoitus id
     * @return lista harjsis joilla haluttu id
     */
    public Stack<HarjoituksenSisalto> getharjsis(int harj_id) {
        var palautus = new Stack<HarjoituksenSisalto>();
        for (HarjoituksenSisalto hs : harjsis) {
            if (hs.getHarj_id() == harj_id) {
                palautus.add(hs);
            }
        }
        return palautus;
    }


    /**
     * @param harj_id harjoitus id
     * @return harjoituksen sisallon lkm talla id:lla
     */
    public int getharjsislkm(int harj_id) {
        int lkm = 0;
        for (HarjoituksenSisalto hs : harjsis) {
            if (hs.getHarj_id() == harj_id) {
                lkm++;
            }
        }
        return lkm;
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
        for (HarjoituksenSisalto hs : harjsis) {
            if (hs.getLiike_id() == liike_id) {

                String pvm = harjoitukset
                        .annaHarjoitus(hs.getHarj_id()).getpvm();
                sb.append(pvm);
                sb.append(hs.tiedostona());
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
    public boolean onkoUusiLiike(String s){
        return liikkeet.onkoUusi(s);
    }
    


    /**
     * tarkistetaan onko liike kirjoitettu oikein ja loytyyko sita viela
     * tiedostosta
     * @param s liikkeen nimi
     * @return virhe
     */
    public String TarkistaLiike(String s) {
        if(s.contains("|"))return "Liikkeen nimessä vääriä merkkejä";
        if(liikkeet.onkoUusi(s))return "UusiLiike: \""+s+"\"";
        return null;
    }
    
    

    /**
     * poistaa harjoitusid:ta vastaavan harjoituksen
     * seka siihen liittyvan harjoiteksensisallon
     * @param id harjid
     * @throws SailoException jos ongelmia
     */
    public void poistaHarjoitus(int id) throws SailoException {
        harjoitukset.poista(id);
        for(HarjoituksenSisalto hs : harjsis)if(hs.getHarj_id()==id)harjsis.poista(hs.get_id());
    }


    /**
     * tallennetaan
     * @throws SailoException jos ei tallennu
     */
    public void tallenna() throws SailoException {
        liikkeet.tallenna("liikkeet.dat");
        harjoitukset.tallenna("harjoitukset.dat");
        harjsis.tallenna("harjsis.dat");

    }


    /**
     * luetaan tiedostot
     * @throws SailoException jos ei loydy
     */
    public void lueTiedosto() throws SailoException {
        liikkeet.lueTiedosto("../"+"liikkeet.dat");
        harjoitukset.lueTiedosto("../"+"harjoitukset.dat");
        harjsis.lueTiedosto("../"+"harjsis.dat");
    }


    /**
     * @param list lista johon liikkeiden nimet kerataan
     */
    public void lisaaLiikkeet(ObservableList<String> list) {
        for (Liike l : liikkeet)
            list.add(l.getLiikkeenNimi());
    }
    
    

    /**
     * @return viimeisimpänä lisatyn harjoituksen harjId
     */
    public int viimeisinHarjoitusID() {
        try {
            return harjoitukset.anna(harjoitukset.getlkm()-1).getharj_id();
        } catch (SailoException e) {
            //ei tule tapahtumaan
        }
        return 1;
    }




    /**
     * @param id haettu id
     * @return harjoitus, jolla haettu id
     * @throws SailoException jos ei loydy
     */
    public Harjoitus annaHarjoitusID(int id) throws SailoException {
        return harjoitukset.annaHarjoitus(id);
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

            System.out.println('\n'
                    + "==============Harjoituksen Sisalto testi==========");
            System.out.println(spvk.harjsisTiedostona(1));

            System.out.println(
                    "==============Liikkeen harjoitushistoria testi==========");
            for (int i = 0; i < koko; i++)
                spvk.annaHarjoituksenSisalto(i).MuutaLiike_id(1);
            System.out.println(spvk.liikeHistoriaTiedostona(1));
        } catch (SailoException e) {
            e.getMessage();
        }
    }




}
