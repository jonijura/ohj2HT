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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import salipaivakirja.HarjoituksenSisalto;
import salipaivakirja.Harjoitus;
import salipaivakirja.SailoException;
import salipaivakirja.Spvk;

/**
 * Kontrolleri harjoituksen lisaamiselle (/muokkaukselle)
 * @author Joona1
 * @version 10.2.2020
 *
 */
public class SpvkHarjoitusController
        implements ModalControllerInterface<Spvk>, Initializable {

    @FXML
    private GridPane pohja;
    @FXML
    private Label labelVirhe;

    @FXML
    private void handleOK() {
        kasitteleOk();
    }
    
    @FXML
    private void handlePoista() {
        kasittelePoista();
    }


    @FXML
    private void handleCancel() {
        ModalController.closeStage(pohja);
    }


    @Override
    public Spvk getResult() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void handleShown() {
        // TODO Auto-generated method stub

    }


    @Override
    public void setDefault(Spvk spvk2) {
        spvk = spvk2;
        if(muokattavaHarjID>=0)naytaHarjoitus();
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();

    }

    // ======================================================= //
    private Spvk spvk;
    private TextField[][] editHarjSis;
    private static int muokattavaHarjID;
    private static int liikkeidenLkm;

    /**
     * alustetaan dialogi, jossa on kayttajan pyytama maara kenttia liikkeille
     */
    private void alusta() {
        //liikkeidenLkm = Mjonot.erotaInt(Dialogs.showInputDialog("Montako Liiketta?", "emt"), 1);
      editHarjSis = luoKentat();
      for(TextField[] edits : editHarjSis) {
          for(TextField edit:edits) {
              if(edit!=null)edit.setOnKeyReleased( e -> kasitteleMuutos((TextField)(e.getSource())));
          }
      }
    }


    private void kasitteleMuutos(TextField edit) {
        edit.getStylesheets().add(this.getClass().getResource("salipaivakirja.css").toExternalForm());
        String s = edit.getText();
        if(s.contains("a"))edit.getStyleClass().add("virhe");
        for(String st:edit.getStyleClass())
        System.out.println(st);
    }



    /**
     * Ok painettu suljetaan dialogi
     * TODO tallenna muutokset
     */
    private void kasitteleOk() {
        // if(syoteKelpaa)return;
        // SalipaivakirjaGUIController.poistaHarjsis(harjKohdalla.getharj_id());

        ModalController.closeStage(pohja);
    }
    
    
    /**
     * poista painettu, poistetaan harj tiedot
     * TODO
     */
    private void kasittelePoista() {
        ModalController.closeStage(pohja);
    }


    /**
     * TODO
     * Naytetaan harjoituksen tiedot muokkaus ikkunassa
     * @param harj Hajoitus
     */
    private void naytaHarjoitus() {
        try {
        Harjoitus harj=null;
        try {
            harj = spvk.annaHarjoitusID(muokattavaHarjID);
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
        if (harj == null) {
            Dialogs.showMessageDialog("Harjoitusta ei ole valittu");
            return;
        }
        editHarjSis[liikkeidenLkm][0].setText(harj.getpvm());

        var sisalto = spvk.getharjsis(harj.getharj_id());
        if(sisalto==null)return;
        if(sisalto.empty())Dialogs.showMessageDialog("harjoituksesta ei loytynyt sisaltoa spvk hc 121");
        
        HarjoituksenSisalto harjsis;
        for (int i=0; i < liikkeidenLkm; i++)
        {
            harjsis = sisalto.pop();
            editHarjSis[liikkeidenLkm-1-i][0].setText(spvk.annaLiikkeenNimi(harjsis.getLiike_id()));
            editHarjSis[liikkeidenLkm-1-i][1].setText(""+harjsis.getSarjoja());
            editHarjSis[liikkeidenLkm-1-i][2].setText(""+harjsis.getToistoja());
            editHarjSis[liikkeidenLkm-1-i][3].setText(""+harjsis.getPaino());
        }
        }catch(SailoException e) {
            //
        }

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
        liikkeidenLkm = spvk.getharjsislkm(muokattavaHarjID);
        ModalController.showModal(
                SalipaivakirjaGUIController.class
                        .getResource("HarjLisays2.fxml"),
                "Muokkaa merkintää", modalitystage, spvk, null);

    }
    
    /**
     * luodaan uusi merkinta
     * @param modalitystage minka paalle dialogi laitetaan
     * @param spvk salipaivakirja, jota muokataan
     */
    public static void uusiMerkinta(Stage modalitystage, Spvk spvk) {
        muokattavaHarjID=-1;
        String syote = Dialogs.showInputDialog("Montako Liiketta?", "2");
        if(syote==null)return;
        liikkeidenLkm = Math.min(10, Math.abs(Mjonot.erotaInt(syote, 1)));
        ModalController.showModal(
                SalipaivakirjaGUIController.class
                        .getResource("HarjLisays2.fxml"),
                "Muokkaa merkintää", modalitystage, spvk, null);

    }


    /**
     * luodaan kentat kayttajan syotteelle
     * @param liikkeidenLkm kenttien maara
     * @return taulukko kenttien id-eille
     */
    private TextField[][] luoKentat() {
        pohja.getChildren().clear();

        TextField[][] edits = new TextField[liikkeidenLkm + 1][4];

        pohja.add(new Label("Liikkeen Nimi"), 1, 0);
        pohja.add(new Label("Sarjoja"), 2, 0);
        pohja.add(new Label("Toistoja"), 3, 0);
        pohja.add(new Label("Paino"), 4, 0);

        for (int i = 1; i <= liikkeidenLkm; i++) {
            Label label = new Label("Liike: " + i);
            pohja.add(label, 0, i);
            for (int j = 1; j <= 4; j++) {
                TextField edit = new TextField();
                edits[i - 1][j - 1] = edit;
                edit.setId("e" + j +""+ i);
                pohja.add(edit, j, i);
            }
        }
        pohja.add(new Label("pvm"), 1, liikkeidenLkm + 1);
        TextField editpvm = new TextField();
        edits[liikkeidenLkm][0] = editpvm;
        editpvm.setId("pvm");
        pohja.add(editpvm, 2, liikkeidenLkm + 1);

        return edits;
    }
}
