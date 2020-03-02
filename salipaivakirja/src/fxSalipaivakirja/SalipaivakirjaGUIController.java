package fxSalipaivakirja;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import kanta.*;
import salipaivakirja.HarjoituksenSisalto;
import salipaivakirja.Harjoitus;
import salipaivakirja.Liike;
import salipaivakirja.Spvk;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;

/**
 * luokka käyttöliittymän tapahtumien hoitamiseksi
 * 
 * @author Joona R�ty -jonijura
 * @version 17.1.2020
 *
 */
public class SalipaivakirjaGUIController implements Initializable {

    private static int MONESKOHARJOITUS = 1; // esimerkkiharjoitusten
                                             // harjoitusid rakennusteline

    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private ListChooser<Harjoitus> treeniValikko;
    @FXML
    private ChoiceBox<String> kuvaaja1Valikko;
    @FXML
    private ChoiceBox<String> kuvaaja2Valikko;
    @FXML
    private StringGrid<HarjoituksenSisalto> stringGridTreeni;
    @FXML
    private StringGrid<HarjoituksenSisalto> stringGridLiike;

    /**
     * Käsitellään uuden merkinn�n lisääminen
     */
    @FXML
    private void handleUusiMerkinta() {
        uusiMerkinta();
    }


    /**
     * Käsitellään vertaa
     */
    @FXML
    private void handleVertaa() {
        Dialogs.showMessageDialog("Ei osata");
    }
    
    
    /**
     * Käsitellään vertaa
     */
    @FXML
    private void handleLiikkeenTiedot() {
        naytaLiike();
    }


    /**
     * Käsitellään apua
     */
    @FXML
    private void handleApua() {
        avustus();
    }



    /**
     * Käsitellään merkinn�n muokkaaminen
     */
    @FXML
    private void handleMuokkaaMerkintaa() {
        ModalController.showModal(SalipaivakirjaGUIController.class.getResource(
                "HarjoituksenLisaysView.fxml"), "Harjoitus", null, "");
    }


    /**
     * Käsitellään tallennuskäsky
     */
    @FXML
    private void handleTallenna() {
        tallenna();
    }


    /**
     * Käsitellään lopetuskäsky
     */
    @FXML
    private void handleLopeta() {
        tallenna();
        Platform.exit();
    }
 
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }    

    // ======================================================================
    // ======================================================================
    Spvk spvk;

    /**
     * Tietojen tallennus
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi vielä");
    }


    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkaa sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        //tallenna(); rasittava dialogi, jota ei aina jaksa sulkea TODO: poista kommentti
        return true;
    }


    /**
     * naytetaan ohjelman suunnitelma selaimessa
     */
    public void avustus() {
        boolean b = Dialogs.showQuestionDialog("Apua",
                "Haluatko avata ohjelman" + " suunnitelman selaimella?",
                "kyllä", "Ei");

        if (b) {
            Desktop desktop = Desktop.getDesktop();
            try {
                URI uri = new URI(
                        "https://tim.jyu.fi/view/kurssit/tie/ohj2/2020k/ht/jonijura");
                desktop.browse(uri);
            } catch (URISyntaxException e) {
                return;
            } catch (IOException e) {
                return;
            }
        }
    }

    
    /**
     * alutetaan aloitusikkuna
     */
    private void alusta() {
        treeniValikko.clear();
        stringGridTreeni.clear();
        stringGridLiike.clear();
        
        treeniValikko.addSelectionListener(e -> naytaTreeni());
        
        kuvaaja1Valikko.setItems(list);
        kuvaaja2Valikko.setItems(list);
    }
    

    /**
     * asetetaan kontrolleri salipaivakirjalle
     * @param spvk kaytettava salipaivakirja
     */
    public void setSpvk(Spvk spvk) {
        this.spvk = spvk;
    }
    
    
    /**
     * nayttaa treenin tiedot naytolla
     */
    private void naytaTreeni() {
        stringGridTreeni.clear();
        Harjoitus harj = treeniValikko.getSelectedObject();
        if (harj == null) {
            stringGridTreeni.setRivit("null");
            return;
        }
        int harj_id = harj.getharj_id();
        stringGridTreeni.setRivit(harj.getpvm() + "|sarjoja|toistoja|paino"
                + spvk.harjsisTiedostona(harj_id));
    }


    /**
     * nayttaa liikkeen tiedot naytolla
     */
    private void naytaLiike() {
        stringGridLiike.clear();
        int liike_id = Rng.rand(1, 5); //TODO kuinka saan valitun liikkeen liike_id:n???
        stringGridLiike.setRivit(spvk.annaLiikkeenNimi(liike_id) + "|sarjoja|toistoja|paino"
                +spvk.liikeHistoriaTiedostona(liike_id));
    }


    /**
     * generoidaan uusi merkinta ja lisataan se listalle
     */
    private void uusiMerkinta() {
        if(MONESKOHARJOITUS==1)luoEsimerkkiLiikkeita();
        Harjoitus harj = new Harjoitus();
        spvk.lisaa(harj);
        int r = Rng.rand(2,5);
        for(int i=0;i<r;i++) {
            HarjoituksenSisalto harjsis = new HarjoituksenSisalto(MONESKOHARJOITUS);
            spvk.lisaa(harjsis);
        }
        
        MONESKOHARJOITUS++;
        hae(harj.getharj_id());
    }

    
    /**
     * luodaan pari esimerkkiliiketta uusia merkintoja varten
     */
    private void luoEsimerkkiLiikkeita() {
        lisaaLiike("Kyykky");
        lisaaLiike("Penkkipunnerrus");
        lisaaLiike("Leuanveto");
        lisaaLiike("Pystypunnerrus");
        lisaaLiike("Hauiskääntö");
        lisaaLiike("Alatalja");
        lisaaLiike("Maastaveto");
    }
    
    
    /**
     * lisätään liikkeitä tiedostoon
     * @param s liikkeen nimi
     */
    private void lisaaLiike(String s) {
        if (spvk.onkoUusiLiike(s)) {
            Liike liike = new Liike(s, true);
            spvk.lisaa(liike);
            list.add(s);
            kuvaaja1Valikko.setItems(list);
            kuvaaja2Valikko.setItems(list);
        }
    }


    /**
     * Hakee harjoituksen tiedot listaan
     * @param harj_id harjoitus id joka aktivoidaan haun jälkeen
     */
    private void hae(int harj_id) {
        treeniValikko.clear();

        int index = 0;
        for (int i = 0; i < spvk.getHarjoitustenlkm(); i++) {
            Harjoitus harjoitus = spvk.annaHarjoitus(i);
            if (harjoitus.getharj_id() == harj_id)
                index = i;
            treeniValikko.add(harjoitus.getpvm(), harjoitus);
        }
        treeniValikko.setSelectedIndex(index); // tästä tulee muutosviesti joka
                                               // näyttää jäsenen
    }

}
