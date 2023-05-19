package stamps.vues;

import stamps.donnees.CollectionProcesseurs;
import stamps.donnees.Processeur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import stamps.mvc.Observateur;

public class VueFenetreAjout implements Observateur {

    private CollectionProcesseurs collec;

    @FXML
    private Pane fenetreAjout;

    @FXML
    private TextField champMarque, champModele;

    @FXML
    private Button boutonAnnuler, boutonAjouter;

    public VueFenetreAjout(CollectionProcesseurs collec) {
        collec.ajouterObservateur(this);
        this.collec = collec;
    }

    /**
     * Ajoute un processeur à la collection
     */
    public void ajouter() {
        Processeur p = new Processeur(champMarque.getText(), champModele.getText());

        Image image = new Image(getClass().getResourceAsStream("/cpu.png"), collec.getTailleImage(), collec.getTailleImage(), true, true);
        Image petiteImage = new Image(getClass().getResourceAsStream("/cpu.png"),100, 100, true, true);
        collec.setCheminImage("/cpu.png", p);
        collec.ajouterImage(image, p);
        collec.ajouterPetiteImage(petiteImage, p);
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
    public void reagir() {
        fenetreAjout.setVisible(collec.fenetreAjoutEstVisible());
        champMarque.requestFocus();
    }
}
