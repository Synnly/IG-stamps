package stamps.vues;

import stamps.donnees.CollectionProcesseurs;
import stamps.donnees.Processeur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import stamps.mvc.Observateur;

public class VueDetailsConsultation implements Observateur {

    private CollectionProcesseurs collection;

    @FXML
    private Label labelMarque, labelModele, labelFrequence, labelCoeurs, labelThreads, labelSocket, labelAnnee, labelCache;

    @FXML
    private HBox vueDetailsConsultation;

    @FXML
    private ImageView vignetteProcesseur;

    public VueDetailsConsultation(CollectionProcesseurs collec){
        collection = collec;
        collection.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        vueDetailsConsultation.setVisible(collection.estEnVueDetails() && collection.estEnModeConsultation());
        if (collection.estEnVueDetails() && collection.estEnModeConsultation()) {
            vueDetailsConsultation.setVisible(collection.estEnModeConsultation());
            Processeur proc = collection.getProcesseurEnVueDetails();

            vignetteProcesseur.setImage(collection.getImageProcesseurEnVueDetails());
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
    }
}
