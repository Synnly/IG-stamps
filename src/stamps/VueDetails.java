package stamps;

import donnees.Processeur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import mvc.Observateur;
import donnees.CollectionProcesseurs;

public class VueDetails implements Observateur {

    protected CollectionProcesseurs collection;
    protected Image image;

    @FXML
    private AnchorPane vueDetails;

    @FXML
    private Button boutonHome, boutonPrecedent, boutonSuivant;

    public VueDetails(CollectionProcesseurs collec){
        collec.ajouterObservateur(this);
        collection = collec;
    }

    @FXML
    public void initialize(){
        boutonPrecedent.disableProperty().bind(collection.getIndiceProcesseurEnVueDetailsProperty().isEqualTo(0));
        boutonSuivant.disableProperty().bind(collection.getIndiceProcesseurEnVueDetailsProperty().isEqualTo(collection.getPropertyNbProcesseurs().subtract(1)));
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

            // S'il n'y a pas de modifications en cours, on va chercher l'image dans la collection
            if (!collection.estEnCoursDeModification()){
                collection.setImageProcesseurEnVueDetails(collection.getImageProcesseurEnVueDetails());
                Processeur p = collection.getProcesseurEnVueDetails();
                collection.setCheminImage(p.getCheminImage(), p);
            }
        }
    }
}
