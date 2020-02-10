package fxSalipaivakirja;

import javafx.application.Platform;
import javafx.fxml.FXML;

import fi.jyu.mit.fxgui.*;

/**
 * luokka käyttöliittymän tapahtumien hoitamiseksi
 * 
 * @author Joona R�ty -jonijura
 * @version 17.1.2020
 *
 */
public class SalipaivakirjaGUIController {

	/**
	 * Käsitellään uuden merkinn�n lisääminen
	 */
	@FXML
	private void handleUusiMerkinta() {
		ModalController.showModal(SalipaivakirjaGUIController.class.getResource("HarjoituksenLisaysView.fxml"),
				"Harjoitus", null, "");
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
		ModalController.showModal(SalipaivakirjaGUIController.class.getResource("HarjoituksenLisaysView.fxml"),
				"Harjoitus", null, "");
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

}
