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
        VueGlobale cvg = new VueGlobale(collec);
        VueFenetreAjout vfa = new VueFenetreAjout(collec);
        VueVignettes cvv = new VueVignettes(collec);
        VueDetails cvd = new VueDetails(collec);

        loader.setControllerFactory(iC->{
            if (iC.equals(VueGlobale.class)) return cvg;
            else if (iC.equals(VueFenetreAjout.class)) return vfa;
            else if (iC.equals(VueVignettes.class)) return cvv;
            else if (iC.equals(VueDetails.class)) return cvd;
            else return null;
        });

        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        collec.notifierObservateurs();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
