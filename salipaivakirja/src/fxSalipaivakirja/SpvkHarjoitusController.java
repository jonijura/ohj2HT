package fxSalipaivakirja;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kanta.Pvm;
import salipaivakirja.HarjoituksenSisalto;
import salipaivakirja.Harjoitus;
import salipaivakirja.Liike;
import salipaivakirja.SailoException;
import salipaivakirja.Spvk;
import javafx.scene.control.TextArea;

/**
 * Kontrolleri harjoituksen lisaamiselle (/muokkaukselle)
 * @author Joona1
 * @version 10.2.2020
 *
 */
public class SpvkHarjoitusController
        implements ModalControllerInterface<Spvk>, Initializable {

    @FXML
    private BorderPane pohja;
    @FXML
    private TextArea textAreaHarjSis;

    @FXML
    private TextField textFieldPVM;

    @FXML
    private Label labelVaroitus;

    @FXML
    private Label labelVirhe;

    @FXML
    void HandleOK() {
        kasitteleOK();
    }


    @FXML
    void HandlePeruuta() {
        kasittelePeruuta();
    }


    @FXML
    void HandlePoista() {
        kasittelePoista();
    }


    @Override
    public Spvk getResult() {
        return null;
    }


    @Override
    public void handleShown() {
        //
    }


    @Override
    public void setDefault(Spvk spvk2) {
        spvk = spvk2;
        if (muokattavaHarjID >= 0)
            naytaHarjoitus();
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();

    }

    // ======================================================= //
    private Spvk spvk;
    private static int muokattavaHarjID;

    /**
     * alustetaan dialogi, jossa on kayttajan pyytama maara kenttia liikkeille
     */
    private void alusta() {
        textAreaHarjSis.setOnKeyReleased(e -> kasitteleMuutos((TextArea)(e.getSource())));
        textFieldPVM.setOnKeyReleased(e -> kasitteleMuutos((TextField)(e.getSource())));
        labelVirhe.getStylesheets().add(this.getClass().getResource("salipaivakirja.css").toExternalForm());
        textFieldPVM.getStylesheets().add(this.getClass().getResource("salipaivakirja.css").toExternalForm());
        labelVaroitus.getStylesheets().add(this.getClass().getResource("salipaivakirja.css").toExternalForm());
    }

    /**
     * kutsutaan kun pvm syottokenttaa on muutettu
     * @param textField muutettu kentta
     */
    private boolean kasitteleMuutos(TextField pvmTxt) {
        String virhe = Pvm.TarkistaPvm(pvmTxt.getText());
        if(virhe==null) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().clear();
            textFieldPVM.getStyleClass().clear();
            return false;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
        textFieldPVM.getStyleClass().add("virhe");
        return true;
    }


    /**
     * kutsutaan kun harjsis syottokenttaa on muutettu.
     * @param textArea muutettu kentta
     */
    private boolean kasitteleMuutos(TextArea harjSisTxt) {
        String[] sisalto = harjSisTxt.getText().split("[ \n]+");
        String virhe;
        boolean varoituksia=false;
        for(int i=0;i<sisalto.length;i++) {
            if(i%4==0) {
                virhe = spvk.TarkistaLiike(sisalto[i]);
                if(virhe!=null&&virhe.length()!=0) {
                    if(virhe.charAt(0)=='U') {
                        labelVaroitus.setText(virhe);
                        labelVaroitus.getStyleClass().add("varoitus");
                        varoituksia=true;
                    }
                    else {
                        labelVirhe.setText(virhe);
                        labelVirhe.getStyleClass().add("virhe");
                        return true;
                    }
                }
            }
            else if(!sisalto[i].equals(Integer.toString(Mjonot.erotaInt(sisalto[i], 0)))) {
                labelVirhe.getStyleClass().add("virhe");
                labelVirhe.setText("Paikassa, jossa pitäisi olla numero onkin \""+sisalto[i] +"\"");
                return true;
            } 
        }
        labelVirhe.setText(""+sisalto.length);
        labelVirhe.getStyleClass().clear();
        textAreaHarjSis.getStyleClass().clear();
        if(varoituksia)return false;
        labelVaroitus.setText(""+sisalto.length);
        labelVaroitus.getStyleClass().clear();
        return false;
    }
    
    
    /**
     * lisataan uudet harjoituksen sisallot lukemalla se tekstikentasta
     */
    private void lisaaHarjsis(int harj_id) {
        int[] harjsis = {harj_id,1,1,1,1};
        String[] sisalto = textAreaHarjSis.getText().split("[ \n]+");
        for (int i = 0; i < sisalto.length-3; i += 4) {
            if(spvk.onkoUusiLiike(sisalto[i])<0) {
                var l = new Liike(sisalto[i],false);
                int id = l.rekisteroi();
                spvk.lisaa(l);
                harjsis[1]=id;
            }
            else harjsis[1]=spvk.onkoUusiLiike(sisalto[i]);
            for(int j=1;j<4;j++) {
                harjsis[j+1]=Mjonot.erotaInt(sisalto[i+j],0);
            }
            var hs = new HarjoituksenSisalto(harjsis);
            spvk.lisaa(hs);
        }
    }



    /**
     * OK painettu suljetaan dialogi ja tallennetaan muutokset
     */
    private void kasitteleOK() {
        if(kasitteleMuutos(textAreaHarjSis)||kasitteleMuutos(textFieldPVM))Dialogs.showMessageDialog("korjaa havaitut virheet");
        else {
            try {
                spvk.poistaHarjoitus(muokattavaHarjID);
            } catch (SailoException e) {
                Dialogs.showMessageDialog("vanhan harjoituksen poistossa ongelmia: "+e.getMessage());
            }
            var harj = new Harjoitus(textFieldPVM.getText(), true);
            lisaaHarjsis(harj.getharj_id());
            spvk.lisaa(harj);
            ModalController.closeStage(pohja);
        }
    }


    /**
     * poista painettu, poistetaan harj tiedot
     */
    private void kasittelePoista() {
        try {
            spvk.poistaHarjoitus(muokattavaHarjID);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("harjoituksen poistossa ongelmia: "+e.getMessage());
        }
        ModalController.closeStage(pohja);
    }
    
    
    /**
     * peruuta painettu, suljetaan dialogi
     */
    private void kasittelePeruuta() {
        ModalController.closeStage(pohja);
    }


    /**
     * Naytetaan harjoituksen tiedot muokkaus ikkunassa
     */
    private void naytaHarjoitus() {
        StringBuilder sb=null;
        try {
            sb = new StringBuilder(spvk.harjsisTiedostona(muokattavaHarjID));
            textFieldPVM.setText(spvk.annaHarjoitusID(muokattavaHarjID).getpvm());
        } catch (SailoException e) {
            Dialogs.showMessageDialog("harjoituksenSisaltoa ei loytynyt"+e.getMessage());
        }
        if(sb==null||sb.length()==0)return;
        sb.deleteCharAt(0);
        while(sb.indexOf("|")>0)sb.replace(sb.indexOf("|"), sb.indexOf("|")+1, " ");
        textAreaHarjSis.setText(sb.toString());
        
    }


    /**
     * muokataan harjoitusmerkintaa
     * @param modalitystage minka paalle dialogi laitetaan
     * @param spvk salipaivakirja, jota muokataan
     * @param harj_id muokattavan harjoituksen id
     */
    public static void muokkaaMerkintaa(Stage modalitystage, Spvk spvk,
            int harj_id) {
        muokattavaHarjID = harj_id;
        ModalController.showModal(
                SalipaivakirjaGUIController.class
                        .getResource("HarjLisays3.fxml"),
                "Muokkaa merkintää", modalitystage, spvk, null);

    }


    /**
     * luodaan uusi merkinta
     * @param modalitystage minka paalle dialogi laitetaan
     * @param spvk salipaivakirja, jota muokataan
     */
    public static void uusiMerkinta(Stage modalitystage, Spvk spvk) {
        muokattavaHarjID = -1;
        ModalController.showModal(
                SalipaivakirjaGUIController.class
                        .getResource("HarjLisays3.fxml"),
                "Muokkaa merkintää", modalitystage, spvk, null);

    }
}
