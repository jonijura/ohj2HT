package fxSalipaivakirja;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;

/**
 * Kontrolleri harjoituksen lisaamiselle (/muokkaukselle)
 * @author Joona1
 * @version 10.2.2020
 *
 */
public class SpvkHarjoitusController implements ModalControllerInterface<String> {
    
    @FXML
    private void handleOK() {
        boolean vastaus = Dialogs.showQuestionDialog("Lisätäänkö liike?",
                "Lisätäänkö liike: Leuanveto", "Kyllä", "Ei");
        if(vastaus) {
            lisaaLiike();
        }
    }
    
    private void lisaaLiike() {
        // TODO Auto-generated method stub
        
    }

    @FXML
    private void handleCancel() {
        Dialogs.showMessageDialog("En osannut");
    }

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }
}
