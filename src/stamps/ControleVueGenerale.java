package stamps;

import donnees.CollectionProcesseurs;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mvc.Observateur;

public class ControleVueGenerale implements Observateur{

    private CollectionProcesseurs collection;

    @FXML
    private Button ajouter;

    @FXML
    private Button trier;

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
        System.out.println("Tri demandé");
    }

    @Override
    public void reagir() {}
}
