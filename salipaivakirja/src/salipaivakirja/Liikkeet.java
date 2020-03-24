package salipaivakirja;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Joona1
 * @version 17.2.2020
 *
 */
public class Liikkeet implements Iterable<Liike> {

    private static final int MAX_LIIKKEITA = 5;
    private int lkm = 0;
    // private String tiedostonNimi = "";
    private Liike[] alkiot = new Liike[MAX_LIIKKEITA];

    /**
     * muodostaja
     */
    public Liikkeet() {
        //
    }


    /**
     * @return taulukon koko
     */
    private int taulukonKoko() {
        return alkiot.length;
    }


    /**
     * lisaa uuden liikkeen taulukkoon
     * @param liike lisattava liike
     */
    public void lisaa(Liike liike) {
        if (lkm >= alkiot.length)
            kasvataTaulukkoa();
        alkiot[lkm] = liike;
        lkm++;
    }


    /**
     * kun taulukosta alkaa tila loppua tuplataan sen koko
     */
    private void kasvataTaulukkoa() {
        Liike[] liikkeet = new Liike[alkiot.length * 2];
        for (int i = 0; i < lkm; i++) {
            liikkeet[i] = alkiot[i];
        }
        alkiot = liikkeet;
    }


    /**
     * @param i haettavan liikkeen paikka
     * @return haettava liike
     * @throws SailoException ei ole tata alkiota
     */
    public Liike anna(int i) throws SailoException {
        if (i < 0 || lkm <= i)
            throw new SailoException("Laiton indeksi: " + i);
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
     * @throws SailoException jos haetaan liiketta, jota ei ole
     * @example
     * <pre name="test">
     *  #THROWS SailoException
     * Liikkeet l = new Liikkeet();
     * l.lisaa(new Liike("kuperkeikka",true));
     * l.annaLiike(1).getLiikkeenNimi()==="kuperkeikka";
     * l.annaLiike(2);  #THROWS SailoException
     * l.lisaa(new Liike("karrynpyora", true));
     * l.annaLiike(2).getLiikkeenNimi()==="karrynpyora";
     * </pre>
     */
    public Liike annaLiike(int liike_id) throws SailoException{
        for (int i = 0; i < lkm; i++)
            if (alkiot[i].getLiike_id() == liike_id)
                return alkiot[i];
        throw new SailoException("ei löytynyt liikettä id="+liike_id);
    }




    /**
     * tallennetaan liikkeet.dat tiedostoon
     * @param tiedNimi tiedosto, johon tallennetaan
     * @throws SailoException jos ei aukuea
     */
    public void tallenna(String tiedNimi) throws SailoException {
        try (PrintStream fo = new PrintStream(new FileOutputStream(tiedNimi, false))) {
            for (int i = 0; i < lkm; i++) {
                fo.println(anna(i).toString());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea: " + ex.getMessage());
            throw new SailoException("Tiedosto " + tiedNimi + " ei aukea");
        }
        
    }

    
    
    /**
     * @param s liikkeen nimi
     * @return onko liiketta viela olemassa
     * @throws SailoException jos ongelmia
     * @example
     * <pre name="test">
     *  #THROWS SailoException
     * Liikkeet l = new Liikkeet();
     * l.lisaa(new Liike("kuperkeikka",true));
     * l.onkoUusi("kuperkeikka")===false;
     * l.onkoUusi(" Kuperkeikka  ")===false;
     * l.onkoUusi("kuperke ikka")===true;
     * </pre>
     */
    public boolean onkoUusi(String s) throws SailoException {
        String st = s.trim();
        for(int i=0;i<lkm;i++) {
            if(anna(i).getLiikkeenNimi().equalsIgnoreCase(st))return false;
        }
        return true;
    }


    /**
     * Lukee liikkeet tiedostosta.
     * @param tiedNimi luettavan tiedoston nimi
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * #import java.util.Iterator;
     *  Liikkeet liikkeet = new Liikkeet();
     *  Liike l1 = new Liike("leuanveto"); 
     *  Liike l2 = new Liike("kyykky"); 
     *  Liike l3 = new Liike("penkkipunnerrus"); 
     *  Liike l4 = new Liike("kuperkeikka"); 
     *  Liike l5 = new Liike("alatalja"); 
     *  String tiedNimi = "liikkeetTesti";
     *  File ftied = new File(tiedNimi);
     *  ftied.delete()===true;
     *  liikkeet.lueTiedosto(tiedNimi); #THROWS SailoException
     *  liikkeet.lisaa(l1);
     *  liikkeet.lisaa(l2);
     *  liikkeet.lisaa(l3);
     *  liikkeet.lisaa(l4);
     *  liikkeet.lisaa(l5);
     *  liikkeet.tallenna(tiedNimi);
     *  liikkeet = new Liikkeet();
     *  liikkeet.lueTiedosto(tiedNimi);
     *  Iterator<Liike> i = liikkeet.iterator();
     *  i.next().toString() === l1.toString();
     *  i.next().toString() === l2.toString();
     *  i.next().toString() === l3.toString();
     *  i.next().toString() === l4.toString();
     *  i.next().toString() === l5.toString();
     *  i.hasNext() === false;
     * </pre>
     */
    public void lueTiedosto(String tiedNimi) throws SailoException {
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)))) { // Jotta UTF8/ISO-8859 toimii
            while ( fi.hasNext() ) {
                try {
                    String s = fi.nextLine();
                    var liike = new Liike();
                    liike.parse(s);
                    lisaa(liike);
                } catch (NumberFormatException ex) {
                    // Hylätään
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea! " + ex.getMessage());
            throw new SailoException("Tiedosto " + tiedNimi + " ei aukea");
        }
       
        
    }
    
    /**
     * iteraattori liikkeille
     * @author Joona1
     * @version 24.3.2020
     *
     */
    public class LiikeIterator implements Iterator<Liike>{
        private int kohdalla = 0;
        @Override
        public boolean hasNext() {
            return kohdalla<lkm;
        }

        @Override
        public Liike next() {
            return alkiot[kohdalla++];
        }
        
    }
    
    /**
     * Palautetaan iteraattori liikkeille.
     * @return liike iteraattori
     */
    @Override
    public Iterator<Liike> iterator() {
        return new LiikeIterator();
    }

    

    /**
     * @param args ei kaytossa
     */
    public static void main(String[] args) {
        Liikkeet liikkeet = new Liikkeet();

        Liike liike1 = new Liike("Kyykky", true);
        Liike liike2 = new Liike("Penkkipunnerrus", true);
        liikkeet.lisaa(liike1);
        liikkeet.lisaa(liike2);
        System.out.println(liikkeet.taulukonKoko());

        for (int i = 0; i < 5; i++) {
            Liike liike = new Liike();
            liike.rekisteroi();
            liike.taytaLiikeTiedoilla();
            liikkeet.lisaa(liike);
        }
        System.out.println(liikkeet.taulukonKoko());

        System.out.println("==============Liikkeet testi==========");
        try {

        for (int i = 0; i < liikkeet.getlkm(); i++) {
            Liike liike = liikkeet.anna(i);
            System.out.println("liikkeen nro: " + i);
            liike.tulosta(System.out);
        }
        liikkeet.annaLiike(3).tulosta(System.out);
        }catch (SailoException e) {
            e.getMessage();
        }
    }


}
