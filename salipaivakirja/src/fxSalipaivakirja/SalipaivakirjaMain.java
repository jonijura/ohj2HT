package fxSalipaivakirja;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import salipaivakirja.Spvk;

/**
 * Rekisteri saliharjoituksille, johon voit tallentaa harjoituksien sisallon seka
 * hakea aiempia harjoituksia ja liikkeiden harjoitus historiaa.
 * 
 * Toteuttamatta kuvaajien piirtaminen.
 * @author Joona R�ty -jonijura jonijura@student.jyu.fi
 * @version 17.1.2020
 *
 */
public class SalipaivakirjaMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(
                    getClass().getResource("SalipaivakirjaGUIView.fxml"));
            final Pane root = (Pane) ldr.load();
            final SalipaivakirjaGUIController salipaivakirjaCtrl = (SalipaivakirjaGUIController) ldr
                    .getController();

            final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass()
                    .getResource("salipaivakirja.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Salipaivakirja");

            Spvk spvk = new Spvk();
            salipaivakirjaCtrl.setSpvk(spvk);

            primaryStage.setOnCloseRequest((event) -> {
                if (!salipaivakirjaCtrl.voikoSulkea())
                    event.consume();
            });

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}
