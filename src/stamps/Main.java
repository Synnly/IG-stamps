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
        VueGlobale cvg = new VueGlobale(collec);
        VueVignettes cvv = new VueVignettes(collec);
        VueDetails cvd = new VueDetails(collec);
        VueFenetreAjout vfa = new VueFenetreAjout(collec);

        loader.setControllerFactory(iC->{
            if (iC.equals(VueGlobale.class)) return cvg;
            else if (iC.equals(VueFenetreAjout.class)) return vfa;
            else if (iC.equals(VueVignettes.class)) return cvv;
            else if (iC.equals(VueDetails.class)) return cvd;
            else if (iC.equals(VueGenerale.class)) return vge;
            else return null;
        });

        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(900);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        collec.notifierObservateurs();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
