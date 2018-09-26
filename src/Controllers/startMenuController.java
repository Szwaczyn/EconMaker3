package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class startMenuController
{

    private MainController mainControllerVar;

    /**
     *
     */
    String[][] languagePack = new String[2][5];

    public void initialize()
    {
        setLanguagePack(languagePack);
    }

    @FXML
    ImageView menuBackground = new ImageView();

    @FXML
    Button position_1 = new Button();
    @FXML
    Button position_2 = new Button();
    @FXML
    Button position_3 = new Button();
    @FXML
    Button position_4 = new Button();
    @FXML
    Button position_5 = new Button();
    @FXML
    Button position_6 = new Button();

    @FXML
    VBox menuBox = new VBox();

    @FXML
    public void showMenu()
    {
        if(position_6.isVisible() == true)
        {
            position_1.setText(languagePack[0][0]);
            menuBox.setLayoutX(0);
            position_2.setText("Zaloguj");
            position_3.setText("Załóż konto");
            position_4.setText("Ustawienia");
            position_5.setText("Wyjście");
            position_6.setVisible(false);
        }
        else
        {
            menuBox.setLayoutX(-200);
            position_1.setText("=");
            position_6.setVisible(true);
        }
    }

    @FXML
    public void chooseOfMenu(ActionEvent event)
    {
        Button source = (Button) event.getSource();

        switch (source.getId())
        {
            /**
             * Pozycja "Ustawienia"
             */
            case "position_4":{
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layoutFXML/startMenuSettings.fxml"));
                try {
                    Pane pane = loader.load();
                    startMenuSettingsController target = loader.getController();
                    target.setMainController(mainControllerVar);
                    mainControllerVar.setScreen(pane);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }break;

            case "Wyjście":{
                econmakerShutDown(0);
            }break;
        }
    }

    private void econmakerShutDown(int code)
    {
        System.exit(code);
    }

    public void setLanguagePack(String[][] languagePack) {
        this.languagePack = languagePack;

        languagePack[0][0] = "Schowaj menu";
        languagePack[1][0] = "Hide menu";

    }

    public void setUpLanguage(int pointerLanguage)
    {
        position_1.setText(languagePack[pointerLanguage][0]);
    }

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }
}
