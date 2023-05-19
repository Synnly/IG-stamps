package stamps.vues;

import stamps.donnees.CollectionProcesseurs;
import stamps.donnees.Processeur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import stamps.mvc.Observateur;

import java.io.File;
import java.net.MalformedURLException;

public class VueDetailsEdition implements Observateur {
    private CollectionProcesseurs collection;
    private FileChooser fileChooser;

    @FXML
    private ComboBox<String> comboMarque, comboModele, comboSocket, comboFrequence, comboNbCoeurs, comboNbThreads, comboAnnee, comboCache;

    @FXML
    private Button boutonEnregister, boutonAnnulerEdition, boutonModifierImage;

    @FXML
    private HBox vueDetailsEdition;

    @FXML
    private ImageView vignetteProcesseur1;

    public VueDetailsEdition(CollectionProcesseurs collec){
        collection = collec;
        collection.ajouterObservateur(this);
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
                collection.setImageProcesseurEnVueDetails(new Image(chemin, collection.getTailleImage(), collection.getTailleImage(), true, true));
                collection.setCheminImageProcesseurEnVueDetails(chemin);
                collection.commencerModifications();
            }
            catch (MalformedURLException ignored) {}    // En cas d'erreur l'image n'est pas modifiée
        }
        collection.notifierObservateurs();
    }

    /**
     * Enregistre les modifications en cours. Si aucune valeur n'est précisée, la valeur par défaut est 0 pour les valeurs numériques et "" pour les valeurs textuelles
     */
    public void enregistrer(){
        Processeur proc = collection.getProcesseurEnVueDetails();
        collection.ajouterImage(collection.getImageProcesseurEnVueDetails(), proc);
        collection.ajouterPetiteImage(collection.getImageProcesseurEnVueDetails(), proc);
        collection.setCheminImage(collection.getCheminImageProcesseurEnVueDetails(), proc);

        String stringTemp;

        stringTemp = comboMarque.getValue() == null ? "" : comboMarque.getValue();
        collection.ajouterMarque(stringTemp);
        proc.setMarque(stringTemp);

        stringTemp = comboModele.getValue() == null ? "" : comboModele.getValue();
        collection.ajouterModele(stringTemp);
        proc.setModele(stringTemp);

        try {
            collection.ajouterFrequence(Float.parseFloat(comboFrequence.getValue()));
            proc.setFrequence(Float.parseFloat(comboFrequence.getValue()));
        } catch (NumberFormatException ignored) {}

        stringTemp = comboSocket.getValue() == null ? "" : comboSocket.getValue();
        collection.ajouterSocket(stringTemp);
        proc.setSocket(stringTemp);

        try {
            collection.ajouterNbCoeurs(Integer.parseInt(comboNbCoeurs.getValue()));
            proc.setNbCoeurs(Integer.parseInt(comboNbCoeurs.getValue()));
        } catch (NumberFormatException ignored) {}

        try {
            collection.ajouterNbThreads(Integer.parseInt(comboNbThreads.getValue()));
            proc.setNbThreads(Integer.parseInt(comboNbThreads.getValue()));
        } catch (NumberFormatException ignored) {}

        try {
            collection.ajouterAnnee(Integer.parseInt(comboAnnee.getValue()));
            proc.setAnnee(Integer.parseInt(comboAnnee.getValue()));
        } catch (NumberFormatException ignored) {}

        try {
            collection.ajouterCache(Integer.parseInt(comboCache.getValue()));
            proc.setCache(Integer.parseInt(comboCache.getValue()));
        } catch (NumberFormatException ignored) {}

        collection.modeConsultation();
        collection.terminerModifications();
        collection.notifierObservateurs();
    }

    /**
     * Annule les modifications en cours
     */
    public void annulerEdition(){
        collection.setImageProcesseurEnVueDetails(collection.getImage(collection.getProcesseurEnVueDetails()));
        collection.setCheminImageProcesseurEnVueDetails("/cpu.png");
        collection.modeConsultation();
        collection.terminerModifications();
        collection.notifierObservateurs();
    }

    @Override
    public void reagir() {
        vueDetailsEdition.setVisible(collection.estEnVueDetails() && !collection.estEnModeConsultation());
        if (collection.estEnVueDetails() && !collection.estEnModeConsultation()) {
            Processeur proc = collection.getProcesseurEnVueDetails();

            vignetteProcesseur1.setImage(collection.getImageProcesseurEnVueDetails());
            vignetteProcesseur1.setPreserveRatio(true);
            vignetteProcesseur1.setFitWidth(collection.getTailleImage());

            comboMarque.setValue(proc.getMarque());
            comboMarque.getItems().clear();
            comboMarque.getItems().addAll(collection.getListeMarques());

            comboModele.setValue(proc.getModele());
            comboModele.getItems().clear();
            comboModele.getItems().addAll(collection.getListeModeles());

            comboFrequence.setValue(Float.toString(proc.getFrequence()));
            comboFrequence.getItems().clear();
            comboFrequence.getItems().addAll(collection.getlisteFrequenceAsString());

            comboSocket.setValue(proc.getSocket());
            comboSocket.getItems().clear();
            comboSocket.getItems().addAll(collection.getListeSockets());

            comboNbCoeurs.setValue(Integer.toString(proc.getNbCoeurs()));
            comboNbCoeurs.getItems().clear();
            comboNbCoeurs.getItems().addAll(collection.getlisteNbCoeursAsString());

            comboNbThreads.setValue(Integer.toString(proc.getNbThreads()));
            comboNbThreads.getItems().clear();
            comboNbThreads.getItems().addAll(collection.getlisteNbThreadsAsString());

            comboAnnee.setValue(Integer.toString(proc.getAnnee()));
            comboAnnee.getItems().clear();
            comboAnnee.getItems().addAll(collection.getlisteAnneesAsString());

            comboCache.setValue(Integer.toString(proc.getCache()));
            comboCache.getItems().clear();
            comboCache.getItems().addAll(collection.getlisteCacheAsString());
        }
    }
}
