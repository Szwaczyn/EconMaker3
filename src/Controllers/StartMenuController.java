package Controllers;

import Controllers.startCreateAccountUserControllers.CreateAccountUserController;
import Controllers.startLoginUserController.LoginUserController;
import builder.UserFileBuilder;
import hoodStuff.LanguageEngine;
import hoodStuff.SortingFile;
import hoodStuff.UserFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StartMenuController
{
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

    /**
     * Controls - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

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
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layoutFXML/startCreateAccountUser/createAccountUser.fxml"));
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
                    StartMenuSettingsController target = loader.getController();
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

    /**
     * Other method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        LanguageEngine translation = new LanguageEngine();
        position_1.setText(translation.setUpLanguage(1));
        position_2.setText(translation.setUpLanguage(2));
        position_3.setText(translation.setUpLanguage(3));
        position_4.setText(translation.setUpLanguage(4));
        position_5.setText(translation.setUpLanguage(5));

        //testSorting();
    }

    /**
     * Private method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private void testSorting()
    {
        UserFile file = new UserFileBuilder()
                .addPath(UserFile.PROFILE_PATH + "ms/")
                .addFileName("boudgetmslogTechnologia.logBoudget")
                .build();

        String[] bufor = new String[file.size()];

        for(int i = 0; i <= file.size() - 1; i++)
        {
            bufor[i] = file.readLine(i + 1);
        }

        //Before
        //show(bufor);

        try {
            bufor = SortingFile.getSortedTabOfDate(bufor, 1, 3, 3);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        show(bufor);

    }

    private void show(String[] bufor)
    {
        for(int i = 0; i <= bufor.length - 1; i++)
        {
            System.out.println(bufor[i]);
        }
    }

    private void econmakerShutDown(int code)
    {
        System.exit(code);
    }

    /**
     * Windows system - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private MainController mainControllerVar;

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }

}
