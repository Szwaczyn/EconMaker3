package Controllers.userDesktop;

import Controllers.MainController;
import hoodStuff.LanguageEngine;
import hoodStuff.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created $(DATE)
 */
public class userDesktopController
{
    private MainController mainControllerVar;

    LanguageEngine translation = new LanguageEngine();
    UserData user;

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
        System.out.println(user.getLogin());
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
        buttonSettings.setText(translation.setUpLanguage(4));
        buttonLogOut.setText(translation.setUpLanguage(44));
    }

    /**
     *  Set methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void setUser(UserData user)
    {
        this.user = user;
    }

    /**
     *  Windows System - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    @FXML
    public void returnToStartMenu()
    {
        mainControllerVar.inicializujMenu();
    }

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }
}
