package Controllers.userDesktop.userDesktopSettings;

import Controllers.ClassController;
import Controllers.userDesktop.userReviewController;
import builder.ChangeWindowBuilder;
import builder.EncryptBuilder;
import builder.UserFileBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.Encrypting;
import hoodStuff.LanguageEngine;
import hoodStuff.UserFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;

/**
 * Created $(DATE)
 */
public class userSettingsUserAccountController extends ClassController
{
    LanguageEngine translation = new LanguageEngine();

    /**
     *  Action - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    @FXML
    public void actionReturn()
    {
        userReviewController target = new userReviewController();

        ChangeWindow win = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userSettings.fxml")
                .addClassController(target)
                .addMainController(getController())
                .addUserSession(this.userSession)
                .build();

        win.changeWindow();
    }

    @FXML
    public void actionClearField()
    {
        passwordFieldRepeatChangePassword.setText("");
        passwordFieldChangePassword.setText("");
        passwordFieldDeleteAccount.setText("");
    }

    @FXML
    public void actionChangePassword()
    {
        if(passwordFieldChangePassword.getText().equals(passwordFieldRepeatChangePassword.getText()) && !passwordFieldChangePassword.getText().isEmpty())
        {
            UserFile file = new UserFileBuilder()
                    .addPath("src/settings/")
                    .addFileName("econmaker.user")
                    .build();

            Encrypting encrypt = new EncryptBuilder()
                    .addContent(passwordFieldChangePassword.getText())
                    .build();


                file.changeLine(encrypt.MD5(), this.userSession.getLoginPosition() + 1);
                labelAlert.setText(translation.setUpLanguage(75));
                labelAlert.setVisible(true);


        }
        else
        {
            labelAlert.setText(translation.setUpLanguage(49));
            labelAlert.setVisible(true);
        }

        // TODO find line password user
        // TODO encrypt new passsword
        // TODO change password line
    }

    @FXML
    public void actionDeleteAccount()
    {

    }

    @FXML
    public void changeTab()
    {
        selectedChangePassword(radioChangePassword.isSelected());
    }

    /**
     *  Initialize - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        setLanguage();

        selectedChangePassword(true);
        hideAlert();

    }

    private void setLanguage()
    {
        buttonClearChangePassword.setText(translation.setUpLanguage(27));
        buttonClearDeleteAccount.setText(translation.setUpLanguage(27));
        buttonChangePassword.setText(translation.setUpLanguage(72));
        buttonDeleteAccount.setText(translation.setUpLanguage(71));

        radioChangePassword.setText(translation.setUpLanguage(68));
        radioDeleteAccount.setText(translation.setUpLanguage(70));

        labelNewPassword.setText(translation.setUpLanguage(73));
        labelRepearNewPassword.setText(translation.setUpLanguage(69));
        labelDeleteAccountPassword.setText(translation.setUpLanguage(23));
    }

    private void selectedChangePassword(boolean isSelectedChangePassword)
    {
        radioChangePassword.setSelected(isSelectedChangePassword);
        radioDeleteAccount.setSelected(!isSelectedChangePassword);

        buttonDeleteAccount.setDisable(isSelectedChangePassword);
        buttonClearDeleteAccount.setDisable(isSelectedChangePassword);

        passwordFieldDeleteAccount.setDisable(isSelectedChangePassword);

        passwordFieldChangePassword.setDisable(!isSelectedChangePassword);
        passwordFieldRepeatChangePassword.setDisable(!isSelectedChangePassword);

        buttonChangePassword.setDisable(!isSelectedChangePassword);
        buttonClearChangePassword.setDisable(!isSelectedChangePassword);
    }

    private void hideAlert()
    {
        labelAlert.setVisible(false);
        labelAlert.setText("");
    }

    /**
     *  Controllers - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    @FXML
    RadioButton radioChangePassword = new RadioButton();
    @FXML
    RadioButton radioDeleteAccount = new RadioButton();

    @FXML
    PasswordField passwordFieldChangePassword = new PasswordField();
    @FXML
    PasswordField passwordFieldRepeatChangePassword = new PasswordField();
    @FXML
    PasswordField passwordFieldDeleteAccount = new PasswordField();

    @FXML
    Button buttonChangePassword = new Button();
    @FXML
    Button buttonClearChangePassword = new Button();
    @FXML
    Button buttonDeleteAccount = new Button();
    @FXML
    Button buttonClearDeleteAccount = new Button();

    @FXML
    Label labelNewPassword = new Label();
    @FXML
    Label labelRepearNewPassword = new Label();
    @FXML
    Label labelDeleteAccountPassword = new Label();
    @FXML
    Label labelAlert = new Label();

}
