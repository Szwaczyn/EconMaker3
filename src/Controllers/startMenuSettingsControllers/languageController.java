package Controllers.startMenuSettingsControllers;

import Controllers.MainController;
import Controllers.startMenuSettingsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;

import java.io.*;

/**
 * Created $(DATE)
 */
public class languageController
{
    private MainController mainControllerVar;

    @FXML
    RadioButton radioPol = new RadioButton();
    @FXML
    RadioButton radioEng = new RadioButton();

    public void initialize()
    {
        String line = "";
        FileInputStream fin = null;

        try {
            /**
             * !! WAŻNE !!
             * Zmienić ścieżke do pliku na adres względny
             */
            fin = new FileInputStream("C:/Users/mszwaczy/Dropbox/Projects JAVA/Econ Maker 3/src/settings/settings.dll");
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

    @FXML
    public void returnToStartMenu()
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layoutFXML/startMenuSettings.fxml"));
        try {
            Pane pane = loader.load();
            startMenuSettingsController target = loader.getController();
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
