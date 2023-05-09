package stamps;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import mvc.Observateur;
import donnees.CollectionProcesseurs;

public class VueDetails implements Observateur {

    private CollectionProcesseurs collection;

    @FXML
    private BorderPane vueDetails;

    public VueDetails(CollectionProcesseurs collec){
        collec.ajouterObservateur(this);
        collection = collec;
    }

    @Override
    public void reagir() {
        System.out.println(vueDetails);
        vueDetails.setVisible(collection.estEnVueDetails());
    }
}
