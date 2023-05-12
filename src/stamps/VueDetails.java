package stamps;

import donnees.Processeur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import mvc.Observateur;
import donnees.CollectionProcesseurs;

import java.io.File;
import java.net.MalformedURLException;

public class VueDetails implements Observateur {

    private CollectionProcesseurs collection;
    private FileChooser fileChooser;
    private Image image;

    @FXML
    private AnchorPane vueDetails;

    @FXML
    private Button boutonHome, boutonPrecedent, boutonSuivant, boutonEnregister, boutonAnnulerEdition, boutonModifierImage;

    @FXML
    private Label labelMarque, labelModele, labelFrequence, labelCoeurs, labelThreads, labelSocket, labelAnnee, labelCache;

    @FXML
    private TextField champMarqueEdition, champModeleEdition, champFrequenceEdition, champCoeursEdition, champThreadsEdition, champSocketEdition, champAnneeEdition, champCacheEdition;

    @FXML
    private HBox vueDetailsConsultation, vueDetailsEdition;

    @FXML
    private ImageView vignetteProcesseur, vignetteProcesseur1;

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
        collection.notifierObservateurs();
    }

    /**
     * Passe au processeur précédent
     */
    public void processeurPredecent(){
        collection.precedent();
        collection.notifierObservateurs();
    }

    /**
     * Annule les modifications en cours
     */
    public void annulerEdition(){
        image = collection.getImage(collection.getProcesseurEnVueDetails());
        collection.modeConsultation();
        collection.terminerModifications();
        collection.notifierObservateurs();
    }

    /**
     * Enregistre les modifications en cours. Si aucune valeur n'est précisée, la valeur par défaut est 0 pour les valeurs numériques et "" pour les valeurs textuelles
     */
    public void enregistrer(){
        Processeur proc = collection.getProcesseurEnVueDetails();
        collection.ajouterImage(image, collection.getProcesseurEnVueDetails());
        collection.ajouterPetiteImage(image, collection.getProcesseurEnVueDetails());

        proc.setMarque(champMarqueEdition.getText());
        proc.setModele(champModeleEdition.getText());
        proc.setFrequence(champFrequenceEdition.getText().equals("") ? (float) 0.0 : Float.parseFloat(champFrequenceEdition.getText()));
        proc.setNbCoeurs(champCoeursEdition.getText().equals("") ? 0 : Integer.parseInt(champCoeursEdition.getText()));
        proc.setNbThreads(champThreadsEdition.getText().equals("") ? 0 : Integer.parseInt(champThreadsEdition.getText()));
        proc.setSocket(champSocketEdition.getText());
        proc.setAnnee(champAnneeEdition.getText().equals("") ? 0 : Integer.parseInt(champAnneeEdition.getText()));
        proc.setCache(champCacheEdition.getText().equals("") ? 0 : Integer.parseInt(champCacheEdition.getText()));
        collection.modeConsultation();
        collection.terminerModifications();

        collection.notifierObservateurs();
    }

    /**
     * Ouvre une fenêtre et permet de choisir une image. Si un problème survient, l'image n'est pas modifiée
     */
    public void choisirImage() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));   // Filtrage des extensions
        File file = fileChooser.showOpenDialog(boutonModifierImage.getScene().getWindow());
        if (file != null) {
            try {
                String chemin = file.toURI().toURL().toString();
                image = new Image(chemin, collection.getTailleImage(), collection.getTailleImage(), true, true);
                collection.commencerModifications();
            }
            catch (MalformedURLException ignored) {}    // En cas d'erreur l'image n'est pas modifiée
        }
        collection.notifierObservateurs();
    }

    @Override
    public void reagir() {
        vueDetails.setVisible(collection.estEnVueDetails());

        if(collection.estEnVueDetails()) {

            // S'il n'y a pas de modifications en cours, on va chercher l'image dans la collection
            if (!collection.estEnCoursDeModification()){
                image = collection.getImage(collection.getProcesseurEnVueDetails());
            }

            vueDetailsConsultation.setVisible(collection.estEnModeConsultation());
            vueDetailsEdition.setVisible(!collection.estEnModeConsultation());

            Processeur proc = collection.getProcesseurEnVueDetails();

            // Mode consultation
            if(collection.estEnModeConsultation()) {
                vignetteProcesseur.setImage(image);
                vignetteProcesseur.setPreserveRatio(true);
                vignetteProcesseur.setFitWidth(collection.getTailleImage());
                labelMarque.setText(proc.getMarque().equals("") ? "N/A" : proc.getMarque());
                labelModele.setText(proc.getModele().equals("") ? "N/A" : proc.getModele());
                labelFrequence.setText(proc.getFrequence() == 0 ? "N/A" : proc.getFrequence() + " GHz");
                labelSocket.setText(proc.getSocket().equals("") ? "N/A" : proc.getSocket());
                labelCoeurs.setText(proc.getNbCoeurs() == 0 ? "N/A" : Integer.toString(proc.getNbCoeurs()));
                labelThreads.setText(proc.getNbThreads() == 0 ? "N/A" : Integer.toString(proc.getNbThreads()));
                labelCache.setText(proc.getCache() == 0 ? "N/A" : proc.getCache() + " Mo");
                labelAnnee.setText(proc.getAnnee() == 0 ? "N/A" : Integer.toString(proc.getAnnee()));
            }
            // Mode edition
            else {
                vignetteProcesseur1.setImage(image);
                vignetteProcesseur1.setPreserveRatio(true);
                vignetteProcesseur1.setFitWidth(collection.getTailleImage());
                champMarqueEdition.setText(proc.getMarque());
                champModeleEdition.setText(proc.getModele());
                champFrequenceEdition.setText(proc.getFrequence() == 0.0 ? "" : Float.toString(proc.getFrequence()));
                champCoeursEdition.setText(proc.getNbCoeurs() == 0 ? "" : Integer.toString(proc.getNbCoeurs()));
                champThreadsEdition.setText(proc.getNbThreads() == 0 ? "" : Integer.toString(proc.getNbThreads()));
                champSocketEdition.setText(proc.getSocket());
                champAnneeEdition.setText(proc.getAnnee() == 0 ? "" : Integer.toString(proc.getAnnee()));
                champCacheEdition.setText(proc.getCache() == 0 ? "" : Integer.toString(proc.getCache()));
            }
        }
    }
}
