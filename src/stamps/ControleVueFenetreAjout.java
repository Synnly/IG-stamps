package stamps;

import donnees.CollectionProcesseurs;
import donnees.Processeur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import mvc.Observateur;

public class ControleVueFenetreAjout implements Observateur {

    private CollectionProcesseurs collec;

    @FXML
    private Pane fenetreAjout;

    @FXML
    private TextField champMarque, champModele;

    @FXML
    private Button boutonAnnuler, boutonAjouter;

    public ControleVueFenetreAjout(CollectionProcesseurs collec) {
        collec.ajouterObservateur(this);
        this.collec = collec;
    }

    /**
     * Ajoute un processeur à la collection
     */
    public void ajouter() {
        Processeur p = new Processeur(champMarque.getText(), champModele.getText());
        collec.ajouterProcesseur(p);
        champMarque.clear();
        champModele.clear();
        collec.masquerFenetreAjout();
        collec.notifierObservateurs();
    }

    /**
     * Annule le processus d'ajout d'un processeur
     */
    public void annuler() {
        champMarque.clear();
        champModele.clear();
        collec.masquerFenetreAjout();
        collec.notifierObservateurs();
    }

    @Override
    public void reagir() {fenetreAjout.setVisible(collec.fenetreAjoutEstVisible());}
}
