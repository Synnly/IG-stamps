package stamps;

import donnees.CollectionProcesseurs;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import mvc.Observateur;

public class VueGenerale implements Observateur {

    private CollectionProcesseurs collection;

    @FXML
    private MenuItem menuEdition, menuConsultation;

    public VueGenerale(CollectionProcesseurs collec){
        collec.ajouterObservateur(this);
        collection = collec;
    }


    /**
     * Passe en mode édition
     */
    public void modeEdition(){
        collection.modeEdition();
        collection.notifierObservateurs();
    }

    /**
     * Passe en mode consultation
     */
    public void modeConsultation(){
        collection.modeConsultation();
        collection.notifierObservateurs();
    }

    /**
     * Quitte l'application
     */
    public void quitter(){
        Platform.exit();
    }

    @Override
    public void reagir() {
        menuConsultation.setDisable(collection.estEnModeConsultation());
        menuEdition.setDisable(!collection.estEnModeConsultation());
    }
}
