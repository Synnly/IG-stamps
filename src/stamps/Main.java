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
        loader.setLocation(getClass().getResource("vueGlobale.fxml"));
        ControleVueGenerale pdg = new ControleVueGenerale(collec);

        loader.setControllerFactory(iC->{
            if (iC.equals(ControleVueGenerale.class)) return pdg;
            else return null;
        });

        Parent root = loader.load();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
