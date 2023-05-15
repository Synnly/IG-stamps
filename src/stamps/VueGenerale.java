package stamps;

import donnees.CollectionProcesseurs;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import mvc.Observateur;

import java.io.File;
import java.net.MalformedURLException;

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
