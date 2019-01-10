package Controllers.userDesktop.userDesktopBoudget;

import Controllers.ClassController;
import Controllers.userDesktop.userBoudgetController;
import builder.ChangeWindowBuilder;
import builder.EncryptBuilder;
import builder.UserFileBuilder;
import hoodStuff.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;

/**
 * Created $(DATE)
 */
public class userBoudgetCreateDeleteController extends ClassController {
    LanguageEngine translation = new LanguageEngine();

    @FXML
    public void actionCreateBoudget() {
        addBoudget(textNameOfNewBoudget.getText(), textConditionOfNewBoudget.getText());
        actionClearTextField();
    }

    @FXML
    public void actionDeleteBoudget() {
        Encrypting password = new EncryptBuilder()
                .addContent(textPasswordDeleteBoudget.getText().trim())
                .build();

        UserFile file = new UserFileBuilder()
                .addPath(UserFile.SETTINGS_PATH)
                .addFileName(UserFile.USERS_PATH)
                .build();

        if (password.MD5().equals(file.readLine(userSession.getLoginPosition() + 1).trim())) {
            UserFile boudget = new UserFileBuilder()
                    .addPath(userSession.getProfilPath())
                    .addFileName(userSession.getFileNameBoudget())
                    .build();
            if(isExitLog(choiceBoxNameOfDeleteBoudget.getValue().toString())) deleteLogOfBoudget(choiceBoxNameOfDeleteBoudget.getValue().toString());
            int lineToRemove = boudget.searchLine(choiceBoxNameOfDeleteBoudget.getValue().toString());
            boudget.removeLine(lineToRemove);
            boudget.removeLine(lineToRemove);

            choiceBoxNameOfDeleteBoudget.getItems().remove(choiceBoxNameOfDeleteBoudget.getValue());
            choiceBoxNameOfDeleteBoudget.getSelectionModel().selectFirst();

            actionClearTextField();

            setAlert(translation.setUpLanguage(84));
        } else {
            setAlert(translation.setUpLanguage(63));
        }
    }

    @FXML
    public void actionSetTab() {
        changeTab(radioCreateBoudget.isSelected());
        choiceBoxNameOfDeleteBoudget.getItems().clear();
        checkFile();
        if (radioDeleteBoudget.isSelected()) {
            setMenuDeleteBoudget();
            if (!choiceBoxNameOfDeleteBoudget.getItems().isEmpty()) choiceBoxNameOfDeleteBoudget.getSelectionModel().selectFirst();
            else {
                setDisableSectionOfDeleteBoudget(true);
                setAlert(translation.setUpLanguage(113));
            }
        }
    }

    @FXML
    public void actionReturn() {
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
    public void actionClearTextField() {
        textPasswordDeleteBoudget.setText("");
        textConditionOfNewBoudget.setText("");
        textNameOfNewBoudget.setText("");
    }

    /**
     * Private methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private void deleteLogOfBoudget(String nameOfBoudget)
    {
        UserFile file = new UserFileBuilder()
                .addPath(this.userSession.getProfilePath())
                .addFileName(this.userSession.getFileNameOfBoudget(nameOfBoudget))
                .build();

        file.deleteFile();
    }

    private boolean isExitLog(String nameOfBoudget)
    {
        UserFile file = new UserFileBuilder()
                .addPath(this.userSession.getProfilePath())
                .addFileName(this.userSession.getFileNameOfBoudget(nameOfBoudget))
                .build();

        return file.isExist();
    }

    private void createLogFileBoudget(String nameOfNewBoudget)
    {
        UserFile file = new UserFileBuilder()
                .addPath(this.userSession.getProfilPath())
                .addFileName(this.userSession.getFileNameOfBoudget(nameOfNewBoudget))
                .build();

        file.createFile();

        DataIntegration integration = new DataIntegration(textConditionOfNewBoudget.getText());

        file.writeDown(integration.getValidCurrency());
    }

    private void setDisableSectionOfDeleteBoudget(boolean disable)
    {
        choiceBoxNameOfDeleteBoudget.setDisable(disable);
        textPasswordDeleteBoudget.setDisable(disable);
        buttonDeleteBoudget.setDisable(disable);
        buttonClearDeleteBoudget.setDisable(disable);
    }

    private void addBoudget(String nameOfBudget, String amountOfBoudget)
    {
        UserFile file = new UserFileBuilder()
                .addPath("src/settings/profiles/" + userSession.getLogin() + "/")
                .addFileName("/boudget" + userSession.getLogin() + ".dll")
                .build();

        DataIntegration integration = new DataIntegration(amountOfBoudget);

        if(file.searchLine(nameOfBudget) == -1 && integration.isItValidCurrency())
        {
            file.writeDown(nameOfBudget);
            file.writeDown(integration.getValidCurrency());

            createLogFileBoudget(textNameOfNewBoudget.getText());

            setAlert(translation.setUpLanguage(82));
        }
        else
        {
            setAlert(translation.setUpLanguage(83));
        }

    }

    private void setAlert(String alert)
    {
        labelAlert.setText(alert);
        labelAlert.setVisible(true);
    }

    private void clearAlert()
    {
        labelAlert.setText("");
        labelAlert.setVisible(true);
    }

    private String[] lookForExistBoudget()
    {
        UserFile file = new UserFileBuilder()
                .addFileName("boudget" + userSession.getLogin() + ".dll")
                .addPath("src/settings/profiles/" + userSession.getLogin() + "/")
                .build();

        int size = file.size();

        String[] positionInMenu = null;
        if(size > 1)
        {
            size = size / 2;

            positionInMenu = new String[size];
            int iterator = 0;

            for(int i = 1; i <= size * 2; i += 2)
            {
                positionInMenu[iterator] = file.readLine(i);
                iterator += 1;
            }
        }

        return positionInMenu;
    }

    private void setMenuDeleteBoudget()
    {
        String[] items = lookForExistBoudget();
        int sizeOfItem;
        try{
            sizeOfItem = items.length;
        } catch(Exception e) {
            sizeOfItem = -1;
        }

        for(int i = 0; i <= sizeOfItem - 1; i++)
        {
            choiceBoxNameOfDeleteBoudget.getItems().add(items[i]);
        }
    }

    /**
     *  Items methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        setUpLanguage();
        changeTab(radioCreateBoudget.isSelected());
    }

    private void setUpLanguage()
    {
        radioCreateBoudget.setText(translation.setUpLanguage(77));
        radioDeleteBoudget.setText(translation.setUpLanguage(80));

        labelNameOfNewBoudget.setText(translation.setUpLanguage(78));
        labelConditionOfNewBoudget.setText(translation.setUpLanguage(79));
        labelNameOfDeleteBoudget.setText(translation.setUpLanguage(81));
        labelPasswordDeleteBoudget.setText(translation.setUpLanguage(58));

        buttonClearDeleteBoudget.setText(translation.setUpLanguage(27));
        buttonClearNewBoudget.setText(translation.setUpLanguage(27));
        buttonCreateBoudget.setText(translation.setUpLanguage(55));
        buttonDeleteBoudget.setText(translation.setUpLanguage(59));
    }

    private void changeTab(boolean isSelectedCreateBoudget)
    {
        textNameOfNewBoudget.setDisable(!isSelectedCreateBoudget);
        textConditionOfNewBoudget.setDisable(!isSelectedCreateBoudget);
        buttonCreateBoudget.setDisable(!isSelectedCreateBoudget);
        buttonClearNewBoudget.setDisable(!isSelectedCreateBoudget);

        choiceBoxNameOfDeleteBoudget.setDisable(isSelectedCreateBoudget);
        textPasswordDeleteBoudget.setDisable(isSelectedCreateBoudget);
        buttonDeleteBoudget.setDisable(isSelectedCreateBoudget);
        buttonClearDeleteBoudget.setDisable(isSelectedCreateBoudget);

        clearAlert();
    }

    private void checkFile()
    {
        File file = new File(userSession.getProfilePath() + userSession.getFileNameBoudget());
        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    RadioButton radioCreateBoudget = new RadioButton();
    @FXML
    RadioButton radioDeleteBoudget = new RadioButton();

    @FXML
    Label labelNameOfNewBoudget = new Label();
    @FXML
    Label labelConditionOfNewBoudget = new Label();
    @FXML
    Label labelNameOfDeleteBoudget = new Label();
    @FXML
    Label labelPasswordDeleteBoudget = new Label();
    @FXML
    Label labelAlert = new Label();

    @FXML
    TextField textNameOfNewBoudget = new TextField();
    @FXML
    TextField textConditionOfNewBoudget = new TextField();

    @FXML
    PasswordField textPasswordDeleteBoudget = new PasswordField();

    @FXML
    Button buttonCreateBoudget = new Button();
    @FXML
    Button buttonClearNewBoudget = new Button();
    @FXML
    Button buttonDeleteBoudget = new Button();
    @FXML
    Button buttonClearDeleteBoudget = new Button();

    @FXML
    ChoiceBox choiceBoxNameOfDeleteBoudget = new ChoiceBox();
}
