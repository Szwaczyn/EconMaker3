package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainController
{
    public String user;

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
            StartMenuController smc = loader.getController();
            smc.setMainController(this);
            mainStackPane.getChildren().clear();
            mainStackPane.getChildren().add(mainPaneMenu);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setScreen(Pane pane)
    {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }

    public void clearScreen()
    {
        mainStackPane.getChildren().clear();
    }
}
