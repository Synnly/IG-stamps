package stamps;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import mvc.Observateur;
import donnees.CollectionProcesseurs;

import java.util.Objects;

public class VueDetails implements Observateur {

    private CollectionProcesseurs collection;

    @FXML
    private AnchorPane vueDetails;

    @FXML
    private Button boutonHome, boutonArriere, boutonAvant;

    @FXML
    private Label labelMarque, labelModele, labelFrequence, labelCoeurs, labelThreads, labelSocket, labelAnnee, labelCache;

    public VueDetails(CollectionProcesseurs collec){
        collec.ajouterObservateur(this);
        collection = collec;
    }

    public void passerEnVueGenerale(){
        collection.passerEnVueGenerale();
        collection.notifierObservateurs();
    }

    public void processeurSuivant(){
        collection.suivant();
        collection.notifierObservateurs();
    }

    public void processeurPredecent(){
        collection.precedent();
        collection.notifierObservateurs();
    }

    @Override
    public void reagir() {
        vueDetails.setVisible(collection.estEnVueDetails());
        if(collection.estEnVueDetails()) {
            vueDetails.setVisible(collection.estEnVueDetails());
            labelMarque.setText(collection.getProcesseurEnVueDetails().getMarque().equals("") ? "N/A" : collection.getProcesseurEnVueDetails().getMarque());
            labelModele.setText(collection.getProcesseurEnVueDetails().getModele().equals("") ? "N/A" : collection.getProcesseurEnVueDetails().getModele());
            labelFrequence.setText(collection.getProcesseurEnVueDetails().getFrequence() == 0 ? "N/A" : collection.getProcesseurEnVueDetails().getFrequence() + " GHz");
            labelSocket.setText(collection.getProcesseurEnVueDetails().getSocket().equals("") ? "N/A" : collection.getProcesseurEnVueDetails().getSocket());
            labelCoeurs.setText(collection.getProcesseurEnVueDetails().getNbCoeurs() == 0 ? "N/A" : Integer.toString(collection.getProcesseurEnVueDetails().getNbCoeurs()));
            labelThreads.setText(collection.getProcesseurEnVueDetails().getNbThreads() == 0 ? "N/A" : Integer.toString(collection.getProcesseurEnVueDetails().getNbThreads()));
            labelCache.setText(collection.getProcesseurEnVueDetails().getCache() == 0 ? "N/A" : collection.getProcesseurEnVueDetails().getCache() + " Mo");
            labelAnnee.setText(collection.getProcesseurEnVueDetails().getAnnee() == 0 ? "N/A" : Integer.toString(collection.getProcesseurEnVueDetails().getAnnee()));
        }
    }
}
