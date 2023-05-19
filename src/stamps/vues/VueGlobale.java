package stamps.vues;

import stamps.donnees.CollectionProcesseurs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import stamps.mvc.Observateur;

public class VueGlobale implements Observateur{

    private CollectionProcesseurs collection;

    @FXML
    private AnchorPane vueGlobale;

    @FXML
    private Button boutonAjouter;

    @FXML
    private Button boutonTrier;

    @FXML
    private Label labelNbProcesseurs;

    public VueGlobale(CollectionProcesseurs collec){
        collec.ajouterObservateur(this);
        collection = collec;
    }

    @FXML
    public void initialize(){
        labelNbProcesseurs.textProperty().bind(collection.getPropertyNbProcesseurs().asString());
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

    @Override
    public void reagir() {
        vueGlobale.setVisible(!collection.estEnVueDetails());
        if (!collection.estEnVueDetails()){
            boutonTrier.setDisable(collection.getNbProcesseurs()<2);
            boutonAjouter.setDisable(collection.estEnModeConsultation());
        }
    }
}
