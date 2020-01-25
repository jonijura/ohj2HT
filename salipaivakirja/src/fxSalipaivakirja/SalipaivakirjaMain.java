package fxSalipaivakirja;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


/**
 * @author jonijura
 * @version 17.1.2020
 *
 */
public class SalipaivakirjaMain extends Application {
	@Override
	public void start(Stage primaryStage) {
	    try {
	        FXMLLoader ldr = new FXMLLoader(getClass().getResource("SalipaivakirjaGUIView.fxml")); // korjaa tiedostonimi
	        final Pane root = (Pane)ldr.load();
	        final SalipaivakirjaGUIController salipaivakirjaCtrl = (SalipaivakirjaGUIController)ldr.getController(); // korjaa nimet
	        
	        final Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("salipaivakirja.css").toExternalForm()); // korjaa tiedostonimi
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Salipaivakirja"); // korjaa title
	        
	        // Platform.setImplicitExit(false); // jos tämän laittaa, pitää itse sulkea
	        
	        primaryStage.setOnCloseRequest((event) -> {
	            // Kutsutaan voikoSulkea-metodia
	            if ( !salipaivakirjaCtrl.voikoSulkea() ) event.consume(); // korjaa nimi
	        });
	        
	        primaryStage.show();
	    } catch(Exception e) {
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
