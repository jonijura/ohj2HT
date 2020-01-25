package fxSalipaivakirja;

import javafx.application.Platform;
import javafx.fxml.FXML;

import fi.jyu.mit.fxgui.*;

/**
 * luokka käyttöliittymän tapahtumien hoitamiseksi
 * @author jonijura
 * @version 17.1.2020
 *
 */
public class SalipaivakirjaGUIController {

    /**
     * Käsitellään uuden jäsenen lisääminen
     */
    @FXML private void handleUusiMerkinta() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä");
    }
    /**
     * Käsitellään tallennuskäsky
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Käsitellään lopetuskäsky
     */
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }

    
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
        tallenna();
        return true;
    }
    
}
