package stamps.vues;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;
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
            try {
                collection.importerCollection(chemin);
            }
            catch (Exception e){
                // Creation de la boite d'erreur
                Alert boiteErreur = new Alert(Alert.AlertType.ERROR);
                boiteErreur.setHeaderText("Fichier JSON invalide");
                boiteErreur.show();

                // Creation du chronometre
                PauseTransition pause = new PauseTransition(Duration.seconds(10));
                pause.setOnFinished(event-> boiteErreur.close());
                pause.play();
            }
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
