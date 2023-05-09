package stamps;

import donnees.CollectionProcesseurs;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import mvc.Observateur;

public class VueGlobale implements Observateur{

    private CollectionProcesseurs collection;

    @FXML
    private BorderPane vueGlobale;

    @FXML
    private Button boutonAjouter;

    @FXML
    private Button boutonTrier;

    @FXML
    private MenuItem menuEdition, menuConsultation;

    public VueGlobale(CollectionProcesseurs collec){
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

    @Override
    public void reagir() {
        vueGlobale.setVisible(!collection.estEnVueDetails());
        if (!collection.estEnVueDetails()){
            boutonTrier.setDisable(collection.getNbProcesseurs()<2);
            menuConsultation.setDisable(collection.estEnModeConsultation());
            menuEdition.setDisable(!collection.estEnModeConsultation());
            boutonAjouter.setDisable(collection.estEnModeConsultation());
        }
    }
}
