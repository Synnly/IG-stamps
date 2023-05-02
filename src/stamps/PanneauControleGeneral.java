package stamps;

import donnees.CollectionProcesseurs;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PanneauControleGeneral {

    private CollectionProcesseurs collection;

    @FXML
    private Button ajouter;

    @FXML
    private Button trier;

    public PanneauControleGeneral(CollectionProcesseurs collec){
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
        System.out.println("Ajout demandé");
    }

    /**
     * Trie la collection en fonction du précédent sens de tri
     */
    public void trier(){
        System.out.println("Tri demandé");
    }
}