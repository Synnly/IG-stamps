package stamps;

import donnees.CollectionProcesseurs;
import donnees.Processeur;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import mvc.Observateur;

public class ControleVueVignettes implements Observateur {

    private CollectionProcesseurs collection;

    @FXML
    private FlowPane panneauVignettes;

    public ControleVueVignettes(CollectionProcesseurs collec){
        collec.ajouterObservateur(this);
        collection = collec;
    }

    @Override
    public void reagir() {
        panneauVignettes.getChildren().clear();

        Image image;
        ImageView vueImage;
        VBox vignette;
        Label etiquette;

        for (Processeur p : collection){
            vueImage = new ImageView(collection.getImage(p));
            vueImage.setStyle("fillWidth:false; fitHeight:100.0; fitWidth:100.0; pickOnBounds:true; preserveRatio:true");

            etiquette = new Label(p.getMarque() + " " + p.getModele());
            etiquette.setTextOverrun(OverrunStyle.CENTER_ELLIPSIS);
            etiquette.setEllipsisString("...");
            etiquette.setMaxWidth(100.0);

            vignette = new VBox();
            vignette.setPadding(new Insets(5, 5, 5, 5));
            vignette.setSpacing(5);
            vignette.setStyle("fillWidth:false; maxWidth:100.0; spacing:5.0; -fx-background-color: #e6e6e6; -fx-background-radius: 4");
            vignette.getChildren().addAll(vueImage, etiquette);

            panneauVignettes.getChildren().add(vignette);
        }
    }
}
