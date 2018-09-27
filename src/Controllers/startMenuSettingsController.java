package Controllers;

import Controllers.startMenuSettingsControllers.languageController;
import hoodStuff.LanguageEngine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Created $(DATE)
 */
public class startMenuSettingsController
{
    private MainController mainControllerVar;

    @FXML
    Button position_1 = new Button();
    @FXML
    Button position_2 = new Button();
    @FXML
    Button position_3 = new Button();

    @FXML
    public void chooseSettings(ActionEvent event)
    {
        Button source = (Button) event.getSource();

        switch(source.getId())
        {
            case "position_2":{
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layoutFXML/startMenuSettings/language.fxml"));
                try {
                    Pane pane = loader.load();
                    languageController target = loader.getController();
                    mainControllerVar.clearScreen();
                    target.setMainController(mainControllerVar);
                    mainControllerVar.setScreen(pane);
                } catch (Exception e) {
                    /**
                     * Do zrobienia system obsługi błędów
                     */
                    System.out.println("Nie można załadadować okna language.fxml");
                }
            }break;

            case "position_3":{
                /**
                 * Do zrobienia ustawienia bazy danych
                 */
            }break;
        }
    }

    @FXML
    public void returnToStartMenu()
    {
        mainControllerVar.inicializujMenu();
    }

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }

    public void initialize()
    {
        LanguageEngine translation = new LanguageEngine();
        position_1.setText(translation.setUpLanguage(6));
        position_2.setText(translation.setUpLanguage(7));
        position_3.setText(translation.setUpLanguage(8));
    }
}
