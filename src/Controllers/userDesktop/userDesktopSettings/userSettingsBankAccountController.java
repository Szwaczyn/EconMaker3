package Controllers.userDesktop.userDesktopSettings;

import Controllers.ClassController;
import Controllers.userDesktop.userReviewController;
import builder.ChangeWindowBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.LanguageEngine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.TextBoundsType;

/**
 * Created $(DATE)
 */
public class userSettingsBankAccountController extends ClassController
{
    LanguageEngine translation = new LanguageEngine();

    /**
     *  Action controllers - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    @FXML
    public void actionReturn()
    {
        userReviewController target = new userReviewController();

        ChangeWindow win = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userSettings.fxml")
                .addClassController(target)
                .addMainController(getController())
                .build();

        win.changeWindow();
    }

    /**
     *  private method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */



    /**
     *  initialize - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        setUpLanguage();
    }

    private void setUpLanguage()
    {
        labelAlert.setVisible(false);

        radioCreateAccount.setText(translation.setUpLanguage(52));
        labelNameOfNewAccount.setText(translation.setUpLanguage(53));
        labelConditionOfNewAccount.setText(translation.setUpLanguage(54));
        buttonCreateAccount.setText(translation.setUpLanguage(55));
        buttonClearNewAccount.setText(translation.setUpLanguage(27));

        radioDeleteAccount.setText(translation.setUpLanguage(56));
        labelNameOfDeleteAccount.setText(translation.setUpLanguage(57));
        labelPasswordDeleteAccount.setText(translation.setUpLanguage(58));
        buttonDeleteAccount.setText(translation.setUpLanguage(59));
        buttonClearDeleteAccount.setText(translation.setUpLanguage(27));

        buttonReturn.setText(translation.setUpLanguage(6));
    }

    /**
     *  Controllers - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    @FXML
    Label labelNameOfNewAccount = new Label();
    @FXML
    Label labelConditionOfNewAccount = new Label();
    @FXML
    Label labelNameOfDeleteAccount = new Label();
    @FXML
    Label labelPasswordDeleteAccount = new Label();
    @FXML
    Label labelAlert = new Label();

    @FXML
    RadioButton radioCreateAccount = new RadioButton();
    @FXML
    RadioButton radioDeleteAccount = new RadioButton();

    @FXML
    TextField textNameOfNewAccount = new TextField();
    @FXML
    TextField textConditionOfNewAccount = new TextField();
    @FXML
    TextField textNameOfDeleteAccount = new TextField();
    @FXML
    TextField textPasswordDeleteAccount = new TextField();

    @FXML
    Button buttonCreateAccount = new Button();
    @FXML
    Button buttonClearNewAccount = new Button();
    @FXML
    Button buttonDeleteAccount = new Button();
    @FXML
    Button buttonClearDeleteAccount = new Button();
    @FXML
    Button buttonReturn = new Button();
}
