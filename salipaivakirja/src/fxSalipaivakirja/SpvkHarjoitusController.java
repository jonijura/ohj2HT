package fxSalipaivakirja;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import salipaivakirja.Harjoitus;

/**
 * Kontrolleri harjoituksen lisaamiselle (/muokkaukselle)
 * @author Joona1
 * @version 10.2.2020
 *
 */
public class SpvkHarjoitusController
        implements ModalControllerInterface<Harjoitus>, Initializable {

    @FXML
    private GridPane pohja;

    @FXML
    private TextField liike1t, liike2t, liike3t, liike4t, liike5t, liike6t,
            liike7t, liike8t, liike9t;
    @FXML
    private TextField liike1n;
    @FXML
    private TextField liike2n;
    @FXML
    private TextField liike3n;
    @FXML
    private TextField liike4n;
    @FXML
    private TextField liike5n;
    @FXML
    private TextField liike6n;
    @FXML
    private TextField liike7n;
    @FXML
    private TextField liike8n;
    @FXML
    private TextField liike9n;
    @FXML
    private TextField pvm;

    @FXML
    private void handleOK() {
        boolean vastaus = Dialogs.showQuestionDialog("Lisätäänkö liike?",
                "Lisätäänkö liike: Leuanveto", "Kyllä", "Ei");
        if (vastaus) {
            lisaaLiike();
        }
        ModalController.closeStage(pohja);
    }


    @FXML
    private void handleCancel() {
        ModalController.closeStage(pohja);
    }

    // ======================================================= //
    private Harjoitus harjKohdalla;
    private TextField[][] editHarjSis;

    @Override
    public Harjoitus getResult() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void handleShown() {
        // TODO Auto-generated method stub

    }


    @Override
    public void setDefault(Harjoitus harj) {
        harjKohdalla = harj;
        // naytaHarjoitus(harjKohdalla);
    }


    private void naytaHarjoitus(Harjoitus harj) {
        if (harj == null) {
            Dialogs.showMessageDialog("Jasenta ei ole valittu");
            return;
        }
        pvm.setText(harj.getpvm());
        // spvk.getharjsis(harj);

        int i = 0;
        for (TextField[] tx : editHarjSis)
            for (TextField t : tx) {
                if (t == null)
                    break;
                t.setText(Integer.toString(i));
                i++;
            }
    }


    private void lisaaLiike() {
        // TODO Auto-generated method stub
    }


    /**
     * muokataan harjoitusmerkintaa
     * @param modalitystage minka paalle dialogi laitetaan
     * @param harj dialogin harjoitus
     */
    public static void muokkaaMerkintaa(Stage modalitystage, Harjoitus harj) {
        ModalController.showModal(
                SalipaivakirjaGUIController.class
                        .getResource("HarjLisays2.fxml"),//"HarjoituksenLisaysView.fxml"
                "Harjoitus", modalitystage, harj, null);

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        /*
        editHarjSis = new TextField[][] { { liike1n, liike1t },
                { liike2n, liike2t }, { liike3n, liike3t },
                { liike4n, liike4t }, { liike5n, liike5t },
                { liike6n, liike6t }, { liike7n, liike7t },
                { liike8n, liike8t }, { liike9n, liike9t } };
                */

        int i = Mjonot.erotaInt(Dialogs.showInputDialog("montako", "emt"), 4);

        editHarjSis = luoKentat(i);
        /*
         * for (TextField edit : edits) if ( edit != null )
         * edit.setOnKeyReleased( e ->
         * kasitteleMuutosJaseneen((TextField)(e.getSource())));
         * panelJasen.setFitToHeight(true);
         */

    }


    private TextField[][] luoKentat(int koko) {
        pohja.getChildren().clear();

        TextField[][] edits = new TextField[koko+1][4];

        pohja.add(new Label("Liikkeen Nimi"), 1, 0);
        pohja.add(new Label("Sarjoja"), 2, 0);
        pohja.add(new Label("Toistoja"), 3, 0);
        pohja.add(new Label("Paino"), 4, 0);

        for (int i = 1; i <= koko; i++) {
            Label label = new Label("Liike: " + i);
            pohja.add(label, 0, i);
            for (int j = 1; j <= 4; j++) {
                TextField edit = new TextField();
                edits[i-1][j-1] = edit;
                edit.setId("e"+j);
                pohja.add(edit, j, i);
            }
        }
        pohja.add(new Label("pvm"), 1, koko+1);
        TextField editpvm = new TextField();
        edits[koko][0] = editpvm;
        editpvm.setId("pvm");
        pohja.add(editpvm, 2, koko+1);
        //pohja.add(new Button("Ok"), 3, koko+1);
        //pohja.add(new Button("Cancel"), 4, koko+1);

        return edits;
    }
}
