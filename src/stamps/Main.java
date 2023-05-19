package stamps;

import donnees.CollectionProcesseurs;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        CollectionProcesseurs collec = new CollectionProcesseurs();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vueGenerale.fxml"));
        VueGenerale vge = new VueGenerale(collec);
        VueGlobale vg = new VueGlobale(collec);
        VueVignettes vv = new VueVignettes(collec);
        VueDetails vd = new VueDetails(collec);
        VueDetailsEdition vde = new VueDetailsEdition(collec);
        VueDetailsConsultation vdc = new VueDetailsConsultation(collec);
        VueFenetreAjout vfa = new VueFenetreAjout(collec);

        loader.setControllerFactory(iC->{
            if (iC.equals(VueGlobale.class)) return vg;
            else if (iC.equals(VueFenetreAjout.class)) return vfa;
            else if (iC.equals(VueVignettes.class)) return vv;
            else if (iC.equals(VueDetails.class)) return vd;
            else if (iC.equals(VueGenerale.class)) return vge;
            else if (iC.equals(VueDetailsEdition.class)) return vde;
            else if (iC.equals(VueDetailsConsultation.class)) return vdc;
            else return null;
        });

        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setMinHeight(720);
        primaryStage.setMinWidth(720);
        primaryStage.setHeight(720);
        primaryStage.setWidth(720);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        collec.notifierObservateurs();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
