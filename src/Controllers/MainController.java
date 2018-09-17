package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainController
{
    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize()
    {
        inicializujMenu();
    }

    public void inicializujMenu()
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layoutFXML/startMenu.fxml"));
        try {
            Pane mainPaneMenu = loader.load();
            startMenuController smc = loader.getController();
            smc.setMainController(this);
            mainStackPane.getChildren().clear();
            mainStackPane.getChildren().add(mainPaneMenu);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
