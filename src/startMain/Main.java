package startMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/layoutFXML/main.fxml"));
        primaryStage.setTitle("Econ Maker 3 v 2.1");

        Image icon = new Image("/resources/icons/book_XL.png");
        primaryStage.getIcons().add(icon);

        primaryStage.setScene(new Scene(root, 1200, 748));
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
