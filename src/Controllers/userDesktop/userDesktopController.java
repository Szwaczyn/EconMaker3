package Controllers.userDesktop;

import hoodStuff.LanguageEngine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created $(DATE)
 */
public class userDesktopController
{
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

    public void initialize()
    {
        setUpLanguage();
    }

    /**
     *  Private methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private void setUpLanguage()
    {
        buttonReview.setText(translation.setUpLanguage(6));
        buttonIncome.setText(translation.setUpLanguage(40));
        buttonExpenditiures.setText(translation.setUpLanguage(41));
        buttonBoudget.setText(translation.setUpLanguage(42));
        buttonSettings.setText(translation.setUpLanguage(4));
        buttonLogOut.setText(translation.setUpLanguage(44));
    }
}
