package Controllers.userDesktop;

import Controllers.ClassController;
import Controllers.MainController;
import builder.ChangeWindowBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.LanguageEngine;
import hoodStuff.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Created $(DATE)
 */
public class userDesktopController extends ClassController
{
    private MainController mainControllerVar;

    LanguageEngine translation = new LanguageEngine();

    @FXML
    Button buttonReview = new Button();
    @FXML
    Button buttonIncome = new Button();
    @FXML
    Button buttonExpenditiures = new Button();
    @FXML
    Button buttonBoudget = new Button();
    @FXML
    Button buttonStatistics = new Button();
    @FXML
    Button buttonSettings = new Button();
    @FXML
    Button buttonLogOut = new Button();

    /**
     *  Public methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    @FXML
    public void review()
    {
        userReviewController target = new userReviewController();

        ChangeWindow win = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userReview.fxml")
                .addClassController(target)
                .addMainController(mainControllerVar)
                .addUserSession(this.userSession)
                .build();

        win.changeWindow();
    }

    @FXML
    public void actionIncome()
    {
        userIncomeController target = new userIncomeController();

        ChangeWindow win = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userIncome.fxml")
                .addClassController(target)
                .addMainController(mainControllerVar)
                .addUserSession(this.userSession)
                .build();

        win.changeWindow();
    }

    @FXML
    public void actionExpenditiures()
    {
        userExpenditiuresController target = new userExpenditiuresController();

        ChangeWindow win = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userExpenditiures.fxml")
                .addClassController(target)
                .addMainController(mainControllerVar)
                .addUserSession(this.userSession)
                .build();

        win.changeWindow();
    }

    @FXML
    public void actionBoudget()
    {
        userBoudgetController target = new userBoudgetController();

        ChangeWindow win = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userBoudget.fxml")
                .addClassController(target)
                .addMainController(mainControllerVar)
                .addUserSession(this.userSession)
                .build();

        win.changeWindow();
    }

    @FXML
    public void actionStatistics()
    {
        userStatisticsController target = new userStatisticsController();

        ChangeWindow win = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userStatistics.fxml")
                .addClassController(target)
                .addMainController(mainControllerVar)
                .addUserSession(this.userSession)
                .build();

        win.changeWindow();
    }

    @FXML
    public void actionSettings()
    {
        userSettingsController target = new userSettingsController();

        ChangeWindow win = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userSettings.fxml")
                .addClassController(target)
                .addMainController(mainControllerVar)
                .addUserSession(this.userSession)
                .build();

        win.changeWindow();
    }

    @FXML
    public void actionLogOut()
    {
        mainControllerVar.inicializujMenu();
    }



    public void initialize()
    {
        setUpLanguage();
    }

    /**
     *  Private methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private void setUpLanguage()
    {
        buttonReview.setText(translation.setUpLanguage(46));
        buttonIncome.setText(translation.setUpLanguage(40));
        buttonExpenditiures.setText(translation.setUpLanguage(41));
        buttonBoudget.setText(translation.setUpLanguage(42));
        buttonStatistics.setText(translation.setUpLanguage(51));
        buttonSettings.setText(translation.setUpLanguage(4));
        buttonLogOut.setText(translation.setUpLanguage(44));
    }


    /**
     *  Windows System - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }
}
