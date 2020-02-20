package fxSalipaivakirja;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

import Salipaivakirja.Harjoitus;
import Salipaivakirja.Spvk;
import fi.jyu.mit.fxgui.*;

/**
 * luokka käyttöliittymän tapahtumien hoitamiseksi
 * 
 * @author Joona R�ty -jonijura
 * @version 17.1.2020
 *
 */
public class SalipaivakirjaGUIController implements Initializable {

    ObservableList<String> list = FXCollections.observableArrayList("Kyykky",
            "Penkki");

    @FXML
    private ListChooser<Harjoitus> treeniValikko;
    @FXML
    private ChoiceBox<String> kuvaaja1Valikko;
    @FXML
    private ChoiceBox<String> kuvaaja2Valikko;
    @FXML
    private StringGrid<String> stringGridTreeni;

    /**
     * Käsitellään uuden merkinn�n lisääminen
     */
    @FXML
    private void handleUusiMerkinta() {
        uusiMerkinta();
        // ModalController.showModal(SalipaivakirjaGUIController.class.getResource(
        // "HarjoituksenLisaysView.fxml"), "Harjoitus", null, "");
    }


    /**
     * Käsitellään vertaa
     */
    @FXML
    private void handleVertaa() {
        Dialogs.showMessageDialog("Ei osata");
    }


    /**
     * Käsitellään apua
     */
    @FXML
    private void handleApua() {
        Dialogs.showMessageDialog("En auta");
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
     * 
     * @return true jos saa sulkaa sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
    }


    /**
     * alutetaan aloitusikkuna
     */
    private void alusta() {
        treeniValikko.clear();
        treeniValikko.addSelectionListener(e -> naytaTreeni());
        kuvaaja1Valikko.setItems(list);
        kuvaaja2Valikko.setItems(list);

    }


    /**
     * nayttaa treenin tiedot naytolla
     */
    private void naytaTreeni() {
        Harjoitus harj = treeniValikko.getSelectedObject();

        if (harj == null) return;
        
        stringGridTreeni.set("ok", 1, 1);
    }


    /**
     * asetetaan kontrolleri salipaivakirjalle
     * @param spvk kaytettava salipaivakirja
     */
    public void setSpvk(Spvk spvk) {
        this.spvk = spvk;
    }


    /**
     * TODO vaikka mita
     */
    private void uusiMerkinta() {
        Harjoitus harj = new Harjoitus("2.3.2020", true);

        spvk.lisaa(harj);

        hae(harj.getharj_id());
    }


    /**
     * Hakee jäsenten tiedot listaan
     * @param jnro jäsenen numero, joka aktivoidaan haun jälkeen
     */
    private void hae(int jnro) {
        treeniValikko.clear();

        int index = 0;
        for (int i = 0; i < spvk.getHarjoitustenlkm(); i++) {
            Harjoitus harjoitus = spvk.annaHarjoitus(i);
            if (harjoitus.getharj_id() == jnro)
                index = i;
            treeniValikko.add(harjoitus.getpvm(), harjoitus);
        }
        treeniValikko.setSelectedIndex(index); // tästä tulee muutosviesti joka
                                               // näyttää jäsenen
    }

}
