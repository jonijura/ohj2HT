package fxSalipaivakirja;
	
import javafx.application.Application;
import javafx.stage.Stage;
import salipaivakirja.Spvk;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


/**
 * @author Joona R�ty -jonijura
 * @version 17.1.2020
 *
 */
public class SalipaivakirjaMain extends Application {
	@Override 
	public void start(Stage primaryStage) {
	    try {
	        FXMLLoader ldr = new FXMLLoader(getClass().getResource("SalipaivakirjaGUIView.fxml"));
	        final Pane root = (Pane)ldr.load();
	        final SalipaivakirjaGUIController salipaivakirjaCtrl = (SalipaivakirjaGUIController)ldr.getController();
	        
	        final Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("salipaivakirja.css").toExternalForm());
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Salipaivakirja");
	        
	        // Platform.setImplicitExit(false); // jos tämän laittaa, pitää itse sulkea
	        
	        /*
	           FXMLLoader ldr2 = new FXMLLoader(getClass().getResource("HarjLisays2.fxml"));
	            final Pane root2 = (Pane)ldr2.load();      
	            final Scene scene2 = new Scene(root2);
	            scene2.getStylesheets().add(getClass().getResource("salipaivakirja.css").toExternalForm());
            */
	        
	        Spvk spvk = new Spvk();
	        salipaivakirjaCtrl.setSpvk(spvk);
	        
	        primaryStage.setOnCloseRequest((event) -> {
	            // Kutsutaan voikoSulkea-metodia
	            if ( !salipaivakirjaCtrl.voikoSulkea() ) event.consume();
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
