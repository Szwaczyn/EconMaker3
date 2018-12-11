package Controllers.userDesktop.userDesktopSettings;

import Controllers.ClassController;
import Controllers.userDesktop.userReviewController;
import builder.ChangeWindowBuilder;
import builder.UserDataBuilder;
import builder.UserFileBuilder;
import hoodStuff.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    public void deleteAccount()
    {
        //TODO Look fo exist account and input to menu button
        //TODO check password
        //TODO Delete from user dll
        //TODO Delete base of bank
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
                lookForExistAccount();
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

    private void lookForExistAccount()
    {
        UserFile file = new UserFileBuilder()
                .addFileName(userSession.getLogin() + ".dll")
                .addPath("src/settings/profiles/" + userSession.getLogin() + "/")
                .build();

        int size = file.size();

        MenuItem[] positionInMenu = new MenuItem[size / 2];

        for(int i = 2; i <= size / 2; i += 2)
        {
            System.out.println(file.readLine(i));
        }
    }

    private boolean procedureCreateAccount(String nameOfNewAccount, String conditionOfAccount)
    {
        boolean isCreated = false;

        System.out.println(userSession);

        UserFile write = new UserFileBuilder()
                .addFileName(userSession.getLogin() + ".dll")
                .addPath("src/settings/profiles/" + userSession.getLogin() + "/")
                .build();

        if(!checkExisitng(write, nameOfNewAccount) && checkCorrectenss(conditionOfAccount))
        {
            write.writeDown(nameOfNewAccount);
            write.writeDown(conditionOfAccount);

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
        MenuButtonNameOfAccountToDelete.setDisable(var);
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
        MenuButtonNameOfAccountToDelete.setText("");

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

    @FXML
    MenuButton MenuButtonNameOfAccountToDelete = new MenuButton();

}
