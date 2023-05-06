package stamps;

import donnees.CollectionProcesseurs;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import mvc.Observateur;

public class ControleVueGenerale implements Observateur{

    private CollectionProcesseurs collection;
    private boolean estEnModeConsultation = true;

    @FXML
    private Button boutonAjouter;

    @FXML
    private Button boutonTrier;

    @FXML
    private MenuItem menuEdition, menuConsultation;

    public ControleVueGenerale(CollectionProcesseurs collec){
        collec.ajouterObservateur(this);
        collection = collec;
    }

    /**
     * Quitte l'application
     */
    public void quitter(){
        Platform.exit();
    }

    /**
     * Ajoute un processeur à la collection
     */
    public void ajouter(){
        collection.afficherFenetreAjout();
        collection.notifierObservateurs();
    }

    /**
     * Trie la collection en fonction du précédent sens de tri
     */
    public void trier(){
        collection.trierProcesseurs();
        collection.notifierObservateurs();
    }

    public void modeEdition(){
        estEnModeConsultation = false;
        collection.notifierObservateurs();
    }

    public void modeConsultation(){
        estEnModeConsultation = true;
        collection.notifierObservateurs();
    }

    @Override
    public void reagir() {
        boutonTrier.setDisable(collection.getNbProcesseurs()<2);
        menuConsultation.setDisable(estEnModeConsultation);
        menuEdition.setDisable(!estEnModeConsultation);
        boutonAjouter.setDisable(estEnModeConsultation);
    }
}
