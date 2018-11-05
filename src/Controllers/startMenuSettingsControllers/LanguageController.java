package Controllers.startMenuSettingsControllers;

import Controllers.MainController;
import Controllers.StartMenuSettingsController;
import hoodStuff.LanguageEngine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;

import java.io.*;

/**
 * Created $(DATE)
 */
public class LanguageController
{
    LanguageEngine translation = new LanguageEngine();

    @FXML
    Button position_1 = new Button();
    @FXML
    Button position_2 = new Button();

    @FXML
    RadioButton radioPol = new RadioButton();
    @FXML
    RadioButton radioEng = new RadioButton();

    /**
     *  Controls - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    @FXML
    public void changeLanguage(ActionEvent eventKey)
    {
        RadioButton sourceRadioButton = (RadioButton) eventKey.getSource();

        switch(sourceRadioButton.getId())
        {
            case "radioPol":{
                translation.changeLanguagePack("Polski");
            }break;

            case "radioEng":{
                translation.changeLanguagePack("English");
            }break;
        }
    }

    /**
     *  Other method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        String line = "";
        FileInputStream fin = null;

        /**
         * Ustawienie języka
         */
        position_1.setText(translation.setUpLanguage(6));
        position_2.setText(translation.setUpLanguage(9));

        try {
            fin = new FileInputStream("src/settings/settings.dll");
        } catch (FileNotFoundException e) {
            System.out.println("Nie odnaleźono pliku settings.dll");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(fin));

        try {
            while((line = reader.readLine()) != null)
            {
                if(line.equals("LANGUAGE=PolLanguagePack.txt"))
                {
                    radioPol.setSelected(true);
                    radioEng.setSelected(false);
                }
                else if(line.equals("LANGUAGE=EngLanguagePack.txt"))
                {
                    radioPol.setSelected(false);
                    radioEng.setSelected(true);
                }
            }
        } catch (IOException e) {
            System.out.println("Blad wejscia");
        }
    }

    /**
     *  Windows system - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private MainController mainControllerVar;

    @FXML
    public void returnToStartMenu()
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layoutFXML/startMenuSettings.fxml"));
        try {
            Pane pane = loader.load();
            StartMenuSettingsController target = loader.getController();
            target.setMainController(mainControllerVar);
            mainControllerVar.setScreen(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }
}
