package stamps.vues;

import stamps.donnees.CollectionProcesseurs;
import stamps.donnees.Processeur;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import stamps.mvc.Observateur;

public class VueVignettes implements Observateur {

    private CollectionProcesseurs collection;

    @FXML
    private FlowPane panneauVignettes;

    @FXML
    private ScrollPane scrollPane;

    public VueVignettes(CollectionProcesseurs collec){
        collec.ajouterObservateur(this);
        collection = collec;
    }

    @FXML
    public void initialize(){
        scrollPane.viewportBoundsProperty().addListener((observable, oldValue, newValue) -> {
            panneauVignettes.setPrefWidth(newValue.getWidth());
        });
    }

    @Override
    public void reagir() {
        panneauVignettes.getChildren().clear();

        Image image;
        ImageView vueImage;
        VBox vignette;
        Label etiquetteMarque, etiquetteModele;

        for (Processeur p : collection){
            vueImage = new ImageView(collection.getPetiteImage(p));
            vueImage.setPreserveRatio(true);
            vueImage.setFitWidth(100.0);

            ContextMenu menu = new ContextMenu();
            MenuItem supprimer = new MenuItem("Supprimer");
            supprimer.setOnAction(e->{
                collection.supprimerProcesseur(p);
                collection.notifierObservateurs();
            });
            menu.getItems().add(supprimer);

            etiquetteMarque = new Label(p.getMarque());
            etiquetteMarque.setTextOverrun(OverrunStyle.CENTER_ELLIPSIS);
            etiquetteMarque.setEllipsisString("...");
            etiquetteMarque.setMaxWidth(100.0);
            etiquetteMarque.setContextMenu(menu);
            etiquetteMarque.setAlignment(javafx.geometry.Pos.CENTER);

            etiquetteModele = new Label(p.getModele());
            etiquetteModele.setTextOverrun(OverrunStyle.CENTER_ELLIPSIS);
            etiquetteModele.setEllipsisString("...");
            etiquetteModele.setMaxWidth(100.0);
            etiquetteModele.setContextMenu(menu);
            etiquetteModele.setAlignment(javafx.geometry.Pos.CENTER);

            vignette = new VBox();
            vignette.setPadding(new Insets(5, 5, 5, 5));
            vignette.setSpacing(5);
            vignette.setStyle("fillWidth:false; maxWidth:100.0; spacing:5.0; -fx-background-color: #e6e6e6; -fx-background-radius: 4");
            vignette.setOnMouseClicked(e-> {
                if (collection.estEnModeConsultation()){
                    collection.passerEnVueDetails();
                    collection.inspecterProcesseur(p);
                    collection.notifierObservateurs();
                }
            });
            vignette.setAlignment(javafx.geometry.Pos.CENTER);
            vignette.getChildren().addAll(etiquetteMarque, vueImage, etiquetteModele);

            panneauVignettes.getChildren().add(vignette);
        }
    }
}
