package Controllers;

import Controllers.startCreateAccountUserControllers.CreateAccountUserController;
import Controllers.startLoginUserController.LoginUserController;
import hoodStuff.LanguageEngine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.*;

public class startMenuController
{

    private MainController mainControllerVar;

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
            menuBox.setLayoutX(0);
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
             * Pozycja "Logowanie"
             */
            case "position_2":{
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layoutFXML/startLoginUser/loginUser.fxml"));
                try {
                    Pane pane = loader.load();
                    LoginUserController target = loader.getController();
                    target.setMainController(mainControllerVar);
                    mainControllerVar.setScreen(pane);
                } catch(Exception e) {
                    //TODO Make System Error
                    System.out.println("Nie można załadować okna loginUser.fxml - " + e);
                }
            }break;
            /**
             *  Pozycja "Stwórz konto"
             */
            case "position_3":{
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layoutFXML/startCreateAccountUser/CreateAccountUser.fxml"));
                try{
                    Pane pane = loader.load();
                    CreateAccountUserController target = loader.getController();
                    target.setMainController(mainControllerVar);
                    mainControllerVar.setScreen(pane);
                } catch(Exception e) {
                    //TODO Make system error
                    System.out.println(e + " Nie można załadować pliku FXML !!");
                }
            }break;

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

            case "position_5":{
                econmakerShutDown(0);
            }break;
        }
    }

    private void econmakerShutDown(int code)
    {
        System.exit(code);
    }

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }


    public void initialize()
    {
        LanguageEngine translation = new LanguageEngine();
        position_1.setText(translation.setUpLanguage(1));
        position_2.setText(translation.setUpLanguage(2));
        position_3.setText(translation.setUpLanguage(3));
        position_4.setText(translation.setUpLanguage(4));
        position_5.setText(translation.setUpLanguage(5));
    }

}
