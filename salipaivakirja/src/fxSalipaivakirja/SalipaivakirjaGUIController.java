package fxSalipaivakirja;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import kanta.*;
import salipaivakirja.HarjoituksenSisalto;
import salipaivakirja.Harjoitus;
import salipaivakirja.Liike;
import salipaivakirja.SailoException;
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

    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private ListChooser<RekisteroituMerkkijono> treeniValikko;
    @FXML
    private ChoiceBox<String> kuvaaja1Valikko;
    @FXML
    private ChoiceBox<String> kuvaaja2Valikko;
    @FXML
    private StringGrid<HarjoituksenSisalto> stringGridTreeni;
    @FXML
    private StringGrid<HarjoituksenSisalto> stringGridLiike;
    @FXML
    private TextField hakuKentta;

    @FXML
    private void etsi() {
        Dialogs.showMessageDialog("Ei osata");
    }


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
        //naytaLiike();
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
        SpvkHarjoitusController.muokkaaMerkintaa();
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
        try {
            spvk.tallenna();
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }


    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkaa sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        //tallenna(); TODO poista kommentit
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
        treeniValikko.addSelectionListener(e -> naytaLiike());

    }


    /**
     * asetetaan kontrolleri salipaivakirjalle
     * @param spvk kaytettava salipaivakirja
     */
    public void setSpvk(Spvk spvk) {
        this.spvk = spvk;
        try {
            spvk.lueTiedosto();
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
        try {
            spvk.lisaaLiikkeet(list);
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
        kuvaaja1Valikko.setItems(list);
        kuvaaja2Valikko.setItems(list);
        hae(5); //nayttaa jotain tietoja ruudulle :)
    }


    /**
     * nayttaa treenin tiedot naytolla
     */
    private void naytaTreeni() {
        var harj = treeniValikko.getSelectedObject();
        if (harj == null) {
            Dialogs.showMessageDialog("null viite liikkeeseen kohdasta naytaTreeni");
            return;
        }
        
        if (!Character.isDigit(harj.getString().charAt(0)))return; //katsotaan alkaako objektin merkkijono numerolla ja jos ei niin kyseessa on liike eika treeni.
        
        stringGridTreeni.clear();
        int harj_id = harj.getID();
        try {
            stringGridTreeni.setRivit(harj.getString() + "|sarjoja|toistoja|paino"
                    + spvk.harjsisTiedostona(harj_id));
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }

    }


    /**
     * nayttaa liikkeen tiedot naytolla
     */
    private void naytaLiike() {
        var liike = treeniValikko.getSelectedObject();
        if (liike == null) {
            Dialogs.showMessageDialog("null viite liikkeeseen kohdasta naytaLiike");
            return;
        }
        
        if (Character.isDigit(liike.getString().charAt(0)))return; //katsotaan alkaako objektin merkkijono numerolla ja jos ei niin kyseessa on liike eika treeni.
        
        stringGridLiike.clear();
        int liike_id = liike.getID();
        try {
            stringGridLiike.setRivit(
                    spvk.annaLiikkeenNimi(liike_id) + "|sarjoja|toistoja|paino"
                            + spvk.liikeHistoriaTiedostona(liike_id));
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }


    /**
     * generoidaan uusi merkinta ja lisataan se listalle
     */
    private void uusiMerkinta() {
        Harjoitus harj = new Harjoitus();
        spvk.lisaa(harj);
        int r = Rng.rand(2,5);
        for(int i=0;i<r;i++) {
            HarjoituksenSisalto harjsis = new HarjoituksenSisalto(harj.getharj_id());
            spvk.lisaa(harjsis);
        }
        hae(harj.getharj_id());
    }


    /**
     * lisätään liikkeitä tiedostoon
     * @param s liikkeen nimi
     */
    public void lisaaLiike(String s) {
        try {
            if (spvk.onkoUusiLiike(s)) {
                Liike liike = new Liike(s, true);
                spvk.lisaa(liike);
                list.add(s);
                kuvaaja1Valikko.setItems(list);
                kuvaaja2Valikko.setItems(list);
            }
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
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
            Harjoitus harjoitus;
            try {
                harjoitus = spvk.annaHarjoitus(i);
                if (harjoitus.getharj_id() == harj_id)
                    index = i;
                treeniValikko.add(harjoitus.getpvm(), harjoitus);
            } catch (SailoException e) {
                Dialogs.showMessageDialog(e.getMessage());
            }

        }
        treeniValikko.setSelectedIndex(index);
        
        for(int i = 0;i<spvk.getLiikkeidenlkm();i++) {
            Liike liike;
            try {
                liike = spvk.annaLiike(i);
                if (liike.getID() == harj_id)
                    index = i;
                treeniValikko.add(liike.getString(), liike);
            } catch (SailoException e) {
                Dialogs.showMessageDialog(e.getMessage());
            }
        }
        treeniValikko.setSelectedIndex(index); // tästä tulee muutosviesti joka
                                               // näyttää jäsenen
    }

}
