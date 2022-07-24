package fxSalipaivakirja;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import kanta.Pvm;
import kanta.RekisteroituMerkkijono;
import salipaivakirja.HarjoituksenSisalto;
import salipaivakirja.Harjoitus;
import salipaivakirja.Liike;
import salipaivakirja.SailoException;
import salipaivakirja.Spvk;

/**
 * Luokka käyttöliittymän tapahtumien hoitamiseksi.
 * Toteuttamatta kuvaajien piirtaminen.
 * 
 * @author Joona Räty -jonijura jonijura@student.jyu.fi
 * @version 17.1.2020
 *
 */
public class SalipaivakirjaGUIController implements Initializable {

    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private ListChooser<RekisteroituMerkkijono> treeniValikko;

    @FXML
    private ChoiceBox<String> harjoitusKerratValikko;

    @FXML
    private ChoiceBox<String> maksimiPainoValikko;

    @FXML
    private StringGrid<HarjoituksenSisalto> stringGridTreeni;

    @FXML
    private StringGrid<HarjoituksenSisalto> stringGridLiike;

    @FXML
    private TextField hakuKentta;

    @FXML
    private BarChart<String, Integer> harjoitusKerratKuvaaja;

    @FXML
    private BarChart<String, Integer> maksimiPainoKuvaaja;

    @FXML
    private void etsi() {
        hae(0);
    }


    @FXML
    private void handlePoistaLiike() {
        poistaLiike();
    }


    @FXML
    private void handlePoistaHarjoitus() {
        poistaHarjoitus();
    }


    /**
     * Käsitellään uuden merkinn�n lisääminen
     */
    @FXML
    private void handleUusiMerkinta() {
        uusiMerkinta();
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
        muokkaaMerkintaa();
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

    private Harjoitus harjKohdalla;

    private Liike liikeKohdalla;

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
     * tallennetaan tiedosto ennen sulkemista
     * @return true jos saa sulkaa sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }


    /**
     * luodaan uusi merkinta
     */
    private void uusiMerkinta() {
        SpvkHarjoitusController.uusiMerkinta(null, spvk);
        haeJotainRuudulle(true);
    }


    /**
     * muokataan merkintaa
     */
    private void muokkaaMerkintaa() {
        SpvkHarjoitusController.muokkaaMerkintaa(null, spvk,
                harjKohdalla.getharj_id());
        haeJotainRuudulle(true);
    }


    /**
     * haetaan naytolle joku harjoitus tai liike nakyville
     * @param b haetaanko harjoitusta, jos false haetaan liike
     */
    private void haeJotainRuudulle(boolean b) {
        String ehto = hakuKentta.getText();
        hakuKentta.clear();
        try {
            if (b)
                hae(spvk.viimeisinHarjoitusID());
            else
                hae(0);
        } catch (SailoException e) {
            Dialogs.showMessageDialog(
                    "ongelmia harjoituksen löytämisessä" + e.getMessage());
        }
        hakuKentta.setText(ehto);
        hae(0);
    }


    /**
     * poistetaan liike
     */
    private void poistaLiike() {
        try {
            if (spvk.liikeHistoriaTiedostona(liikeKohdalla.getID())
                    .length() == 1) {
                spvk.poistaLiike(liikeKohdalla.getID());
                haeJotainRuudulle(false);
                return;
            }

        } catch (SailoException e) {
            Dialogs.showMessageDialog(
                    "ongelmia liikkeen poistossa " + e.getMessage());
        }
        Dialogs.showMessageDialog(
                "Liiketta ei voi poistaa, koska se sisältyy ainakin yhteen harjoitukseen.");
    }


    /**
     * kysytaan kayttajalta varmistus ja mahdollisesti poistetaan harjoitus sisaltoineen
     */
    private void poistaHarjoitus() {
        if (!Dialogs.showQuestionDialog("Poista harjoitus",
                "haluatko poistaa harjoituksen " + harjKohdalla.getpvm(),
                "kyllä", "ei"))
            return;
        try {
            spvk.poistaHarjoitus(harjKohdalla.getID());
        } catch (SailoException e) {
            Dialogs.showMessageDialog(
                    "harjoituksen poistossa ongelmia: " + e.getMessage());
        }
        haeJotainRuudulle(true);
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
        harjoitusKerratValikko.setOnAction(e -> naytaHarjoituskerrat());
        maksimiPainoValikko.setOnAction(e -> naytaMaksimiPaino());

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

        spvk.lisaaLiikkeet(list);

        harjoitusKerratValikko.setItems(list);
        maksimiPainoValikko.setItems(list);
        // nayttaa jotain tietoja ruudulle
        hae(0);
        treeniValikko.setSelectedIndex(spvk.getLiikkeidenlkm());
    }


    private void naytaHarjoituskerrat() {
        String[] dataS = { "" };
        try {
            String sisalto = spvk.liikkeenHarjLkmVuosittain(
                    harjoitusKerratValikko.getValue());
            if (sisalto.length() == 0)
                return;
            dataS = sisalto.split("\n");
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
            return;
        }
        var mp = new HashMap<Integer, Integer>();
        for (String d : dataS) {
            var vuosi = Pvm.getVuosi(d.split("\\|")[0]);
            if (mp.containsKey(vuosi))
                mp.replace(vuosi, mp.get(vuosi) + 1);
            else
                mp.put(vuosi, 1);
        }
        harjoitusKerratKuvaaja.getData().clear();
        XYChart.Series<String, Integer> data = new Series<String, Integer>();
        for (var et : mp.entrySet()) {
            data.getData().add(new Data<String, Integer>(et.getKey().toString(),
                    et.getValue()));
        }
        harjoitusKerratKuvaaja.getData().add(data);
    }


    private void naytaMaksimiPaino() {
        // maksimiPainoKuvaaja.setTitle(maksimiPainoValikko.getValue());
    }


    /**
     * nayttaa treenin tiedot naytolla
     */
    private void naytaTreeni() {
        var harj = treeniValikko.getSelectedObject();
        if (harj == null) {
            Dialogs.showMessageDialog(
                    "null viite harjoitukseen kohdasta naytaTreeni");
            return;
        }

        if (!Character.isDigit(harj.getString().charAt(0)))
            return; // katsotaan alkaako objektin merkkijono numerolla ja jos ei
                    // niin kyseessa on liike eika treeni.
        harjKohdalla = (Harjoitus) harj;
        stringGridTreeni.clear();
        int harj_id = harj.getID();

        try {
            stringGridTreeni
                    .setRivit(harj.getString() + "|sarjoja|toistoja|paino"
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
            Dialogs.showMessageDialog(
                    "null viite liikkeeseen kohdasta naytaLiike");
            return;
        }

        if (Character.isDigit(liike.getString().charAt(0)))
            return; // katsotaan alkaako objektin merkkijono numerolla ja jos ei
                    // niin kyseessa on liike eika treeni.
        liikeKohdalla = (Liike) liike;
        stringGridLiike.clear();
        int liike_id = liike.getID();
        try {
            stringGridLiike.setRivit(
                    spvk.annaLiikkeenNimi(liike_id) + "|sarjoja|toistoja|paino"
                            + "\n" + spvk.liikeHistoriaTiedostona(liike_id));
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }


    /**
     * Hakee harjoituksen tietoja ruudulle liikkeesta tai harjoituksesta
     * @param id liikkeen tai harjoituksen id
     */
    private void hae(int id) {

        String hakuehto = hakuKentta.getText();
        ArrayList<RekisteroituMerkkijono> rml = spvk.etsi(hakuehto);
        treeniValikko.clear();
        int paikka = 0, index = 0;
        for (RekisteroituMerkkijono rm : rml) {
            treeniValikko.add(rm.getString(), rm);
            if (rm.getID() == id)
                index = paikka;
            paikka++;
        }
        treeniValikko.setSelectedIndex(index);
    }

}
