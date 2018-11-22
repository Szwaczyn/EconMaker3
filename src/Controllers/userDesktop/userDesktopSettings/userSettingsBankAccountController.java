package Controllers.userDesktop.userDesktopSettings;

import Controllers.ClassController;
import Controllers.userDesktop.userReviewController;
import builder.ChangeWindowBuilder;
import builder.UserFileBuilder;
import hoodStuff.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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

    @FXML
    public void actionSetTab(ActionEvent key)
    {
        RadioButton source = (RadioButton) key.getSource();

        switch(source.getId())
        {
            case "radioCreateAccount":{
                setCreateAccountSelected(true);
            }break;

            case "radioDeleteAccount":{
                setCreateAccountSelected(false);
            }
        }
    }

    @FXML
    public void actionClearTextField() { clearTextField(); }

    @FXML
    public void actionCreateAccount()
    {
        if(procedureCreateAccount(textNameOfNewAccount.getText(), textConditionOfNewAccount.getText()))
        {
            labelAlert.setText(translation.setUpLanguage(60));
            labelAlert.setVisible(true);
        }
        else
        {
            labelAlert.setText(translation.setUpLanguage(61));
            labelAlert.setVisible(true);
        }
    }

    /**
     *  private method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private boolean procedureCreateAccount(String nameOfNewAccount, String conditionOfAccount)
    {
        boolean isCreated = false;

        String login = getTempLogin().trim();

        UserFile write = new UserFileBuilder()
                .addFileName(login + ".dll")
                .addPath("src/settings/profiles/" + login + "/")
                .build();

        if(!checkExisitng(write, nameOfNewAccount) && checkCorrectenss(conditionOfAccount))
        {
            write.writeDown(nameOfNewAccount);
            write.writeDown(conditionOfAccount);

            write.setFileName("BASE" + nameOfNewAccount + ".base");
            write.createFile();

            isCreated = true;
        }

        // TODO Utworzenie pliku historii konta
        return isCreated;
    }

    /**
     *  initialize - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        setUpLanguage();
        setCreateAccountSelected(radioCreateAccount.isSelected());
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

    private void setCreateAccountSelected(boolean var)
    {
        textNameOfDeleteAccount.setDisable(var);
        textPasswordDeleteAccount.setDisable(var);

        buttonDeleteAccount.setDisable(var);
        buttonClearDeleteAccount.setDisable(var);

        textNameOfNewAccount.setDisable(!var);
        textConditionOfNewAccount.setDisable(!var);

        buttonClearNewAccount.setDisable(!var);
        buttonCreateAccount.setDisable(!var);

        clearTextField();
    }

    private void clearTextField()
    {
        textConditionOfNewAccount.setText("");
        textNameOfNewAccount.setText("");
        textPasswordDeleteAccount.setText("");
        textNameOfDeleteAccount.setText("");

        labelAlert.setVisible(false);
    }

    /**
     *  private methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private boolean checkCorrectenss(String conditionOfNewAccount)
    {
        boolean correct = true;
        try {
            int test = Integer.parseInt(conditionOfNewAccount);
        } catch(Exception e) {
            correct = false;
        }

        return correct;
    }

    private boolean checkExisitng(UserFile fileUser, String nameOfNewAccount)
    {
        int size = fileUser.size();
        boolean isExist = false;

        for(int i = size; i >= 1; i--)
        {
            if(fileUser.readLine(size).equals(nameOfNewAccount.trim()))
            {
                isExist = true;
                break;
            }
        }

        return isExist;
    }

    private void inputToUserFile(UserFile file, String nameOfNewAccount, int Condition)
    {

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
