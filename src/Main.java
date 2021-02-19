import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;
    public Scene scene;
    public Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;

        root = FXMLLoader.load(getClass().getResource("latex_tm.fxml"));
        stage.setTitle("LaTeX Table Maker");
        scene = new Scene(root, 900, 750);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
