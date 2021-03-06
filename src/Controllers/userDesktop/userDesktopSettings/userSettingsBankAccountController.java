package Controllers.userDesktop.userDesktopSettings;

import Controllers.ClassController;
import Controllers.userDesktop.userReviewController;
import builder.ChangeWindowBuilder;
import builder.UserFileBuilder;
import hoodStuff.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;

/**
 * Created $(DATE)
 */
public class userSettingsBankAccountController extends ClassController
{
    LanguageEngine translation = new LanguageEngine();

    /**
     *  Delete controllers - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    @FXML
    public void actionDeleteAccount()
    {
        if(userSession.checkPassword(textPasswordDeleteAccount.getText()))
        {
            UserFile userFile = new UserFileBuilder()
                    .addFileName(userSession.getLogin() + ".dll")
                    .addPath("src/settings/profiles/" + userSession.getLogin() + "/")
                    .build();


            int lineToDelete = userFile.searchLine(choiceBoxNameOfDeleteAccount.getValue().toString());
            userFile.removeLine(lineToDelete);
            userFile.removeLine(lineToDelete);

            String fileName = "/BASE" + choiceBoxNameOfDeleteAccount.getValue().toString() + ".base";
            File fileTodelete = new File("src/settings/profiles/" + userSession.getLogin() + fileName);
            fileTodelete.delete();

            choiceBoxNameOfDeleteAccount.getItems().remove(choiceBoxNameOfDeleteAccount.getValue());
            choiceBoxNameOfDeleteAccount.getSelectionModel().selectFirst();

            labelAlert.setText(translation.setUpLanguage(67));
            labelAlert.setVisible(true);
            textPasswordDeleteAccount.setText("");

            if(choiceBoxNameOfDeleteAccount.getItems().isEmpty())
            {
                labelAlert.setText(translation.setUpLanguage(104));
                labelAlert.setVisible(true);
                buttonDeleteAccount.setDisable(true);
            }
        }
        else
        {
            labelAlert.setText(translation.setUpLanguage(66));
            labelAlert.setVisible(true);
        }
    }

    /**
     *  Create controllers - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
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
    public void actionSetTab(ActionEvent key)
    {
        RadioButton source = (RadioButton) key.getSource();

        switch(source.getId())
        {
            case "radioCreateAccount":{
                setCreateAccountSelected(true);
                choiceBoxNameOfDeleteAccount.getItems().clear();
            }break;

            case "radioDeleteAccount":{
                setCreateAccountSelected(false);
                setMenuDeleteAccount();
                choiceBoxNameOfDeleteAccount.getSelectionModel().selectFirst();

                if(choiceBoxNameOfDeleteAccount.getItems().isEmpty())
                {
                    labelAlert.setText(translation.setUpLanguage(104));
                    labelAlert.setVisible(true);
                    buttonDeleteAccount.setDisable(true);
                }
            }
        }
    }

    @FXML
    public void actionClearTextField() { clearTextField(); }

    @FXML
    public void actionCreateAccount()
    {
        if(procedureCreateAccount(textNameOfNewAccount.getText().toLowerCase().trim(), textConditionOfNewAccount.getText()))
        {
            clearTextField();
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

    private void setMenuDeleteAccount()
    {
    String[] items = lookForExistAccount();
    int sizeOfItem = items.length;

    for(int i = 0; i <= sizeOfItem - 1; i++)
    {
        choiceBoxNameOfDeleteAccount.getItems().add(items[i]);
    }
}

    private String[] lookForExistAccount()
    {
        UserFile file = new UserFileBuilder()
                .addFileName(userSession.getLogin() + ".dll")
                .addPath(userSession.getProfilePath())
                .build();

        int size = file.size();
        size -= 2;
        size = size / 2;

        String[] positionInMenu = new String[size];
        int iterator = 0;

        for(int i = 3; i <= size * 2 + 2; i += 2)
        {
            positionInMenu[iterator] = file.readLine(i);
            iterator += 1;
        }

        return positionInMenu;
    }

    private boolean procedureCreateAccount(String nameOfNewAccount, String conditionOfAccount)
    {
        boolean isCreated = false;

        UserFile write = new UserFileBuilder()
                .addFileName(userSession.getLogin() + ".dll")
                .addPath("src/settings/profiles/" + userSession.getLogin() + "/")
                .build();

        DataIntegration integration = new DataIntegration(conditionOfAccount);

        if(!checkExisitng(write, nameOfNewAccount) && integration.isItValidCurrency())
        {
            write.writeDown(nameOfNewAccount);
            write.writeDown(integration.getValidCurrency());

            write.setFileName("BASE" + nameOfNewAccount + ".base");
            write.createFile();

            isCreated = true;
        }

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
        choiceBoxNameOfDeleteAccount.setDisable(var);
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

        labelAlert.setVisible(false);
    }


    /**
     *  private methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */


    private boolean checkExisitng(UserFile fileUser, String nameOfNewAccount)
    {
        int size = fileUser.size();
        boolean isExist = false;

        for(int i = size; i >= 1; i--)
        {
            if(fileUser.readLine(i).trim().equals(nameOfNewAccount.trim()))
            {
                isExist = true;
                break;
            }
        }

        return isExist;
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
    PasswordField textPasswordDeleteAccount = new PasswordField();

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

    @FXML
    ChoiceBox choiceBoxNameOfDeleteAccount = new ChoiceBox();

}
