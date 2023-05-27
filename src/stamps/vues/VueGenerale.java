package stamps.vues;

import stamps.donnees.CollectionProcesseurs;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import stamps.mvc.Observateur;

import java.io.File;

public class VueGenerale implements Observateur {

    private CollectionProcesseurs collection;
    private DirectoryChooser dirChooser;

    @FXML
    private MenuItem menuEdition, menuConsultation;

    @FXML
    private AnchorPane vueGenerale;

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

    public void exporter(){
        dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Sélectionner l'emplacement du fichier");
        File dir = dirChooser.showDialog(vueGenerale.getScene().getWindow());
        String chemin;
        if (dir != null) {
            chemin = dir.getAbsolutePath();
            collection.exporterCollection(chemin);
        }
    }

    public void importer(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier JSON", "*.json"));
        fileChooser.setTitle("Sélectionner le fichier");
        File file = fileChooser.showOpenDialog(vueGenerale.getScene().getWindow());
        String chemin;
        if (file != null) {
            chemin = file.getAbsolutePath();
            collection.importerCollection(chemin);
        }
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
