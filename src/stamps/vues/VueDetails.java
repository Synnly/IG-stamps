package stamps.vues;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import stamps.donnees.Processeur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import stamps.mvc.Observateur;
import stamps.donnees.CollectionProcesseurs;

public class VueDetails implements Observateur {

    protected CollectionProcesseurs collection;
    protected Image image;
    private TranslateTransition transition;
    private RotateTransition rotation;

    @FXML
    private AnchorPane vueDetails;

    @FXML
    private Button boutonHome, boutonPrecedent, boutonSuivant;

    @FXML
    private ImageView dino;

    @FXML
    private Pane enclosDino;

    public VueDetails(CollectionProcesseurs collec){
        collec.ajouterObservateur(this);
        collection = collec;
    }

    @FXML
    public void initialize(){
        boutonPrecedent.disableProperty().bind(collection.getIndiceProcesseurEnVueDetailsProperty().isEqualTo(0));
        boutonSuivant.disableProperty().bind(collection.getIndiceProcesseurEnVueDetailsProperty().isEqualTo(collection.getPropertyNbProcesseurs().subtract(1)));

        transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(10));
        transition.setNode(dino);
        transition.setFromX(dino.getLayoutY());
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(true);
        transition.setInterpolator(Interpolator.LINEAR);
        enclosDino.widthProperty().addListener((observable, oldValue, newValue) -> {
            transition.setToX(newValue.doubleValue() - dino.getFitWidth());
            transition.stop();
            rotation.stop();
            transition.play();
            rotation.play();
        });
        transition.play();

        rotation = new RotateTransition();
        rotation.setDuration(Duration.seconds(10));
        rotation.setNode(dino);
        rotation.setByAngle(3 * 360);
        rotation.setCycleCount(RotateTransition.INDEFINITE);
        rotation.setInterpolator(Interpolator.LINEAR);
        rotation.setAutoReverse(true);
    }

    /**
     * Retourne à la vue générale
     */
    public void passerEnVueGenerale(){
        collection.passerEnVueGenerale();
        collection.terminerModifications();
        collection.notifierObservateurs();
    }

    /**
     * Passe au processeur suivant
     */
    public void processeurSuivant(){
        collection.suivant();
        collection.terminerModifications();
        collection.modeConsultation();
        collection.notifierObservateurs();
    }

    /**
     * Passe au processeur précédent
     */
    public void processeurPredecent(){
        collection.precedent();
        collection.terminerModifications();
        collection.modeConsultation();
        collection.notifierObservateurs();
    }

    @Override
    public void reagir() {
        vueDetails.setVisible(collection.estEnVueDetails());

        if(collection.estEnVueDetails()) {

            //transition.toXProperty().bind(enclosDino.widthProperty().subtract(dino.getFitWidth()));
            transition.play();
            rotation.play();

            // S'il n'y a pas de modifications en cours, on va chercher l'image dans la collection
            if (!collection.estEnCoursDeModification()){
                collection.setImageProcesseurEnVueDetails(collection.getImageProcesseurEnVueDetails());
                Processeur p = collection.getProcesseurEnVueDetails();
                collection.setCheminImage(p.getCheminImage(), p);
            }
        }
    }
}
