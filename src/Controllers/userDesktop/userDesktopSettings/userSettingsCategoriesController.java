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
import javafx.scene.control.*;

/**
 * Created $(DATE)
 */
public class userSettingsCategoriesController extends ClassController
{
    LanguageEngine translation = new LanguageEngine();

    /**
     *  Action method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    @FXML
    public void actionNewCategory()
    {
        UserFile file = new UserFileBuilder()
                .addFileName(userSession.getFileNameCategories())
                .addPath(userSession.getProfilePath())
                .build();

        if(!file.isExist())
        {
            file.createFile();
        }

        if(file.searchLine(textNewCategory.getText().trim()) == -1 && !textNewCategory.getText().isEmpty())
        {
            file.writeDown(textNewCategory.getText());
            setAlert(translation.setUpLanguage(94));

            textNewCategory.setText("");
        }
        else
        {
            setAlert(translation.setUpLanguage(93));
        }
    }

    @FXML
    public void actionDeleteCategory()
    {
        Encrypting encrypt = new EncryptBuilder()
                .addContent(passwordFieldDeleteCategory.getText().trim())
                .build();

        if(encrypt.MD5().equals(userSession.getPassword()))
        {
            UserFile file = new UserFileBuilder()
                    .addFileName(userSession.getFileNameCategories())
                    .addPath(userSession.getProfilePath())
                    .build();

            int positionToRemove = file.searchLine(choiceCategory.getValue().toString());
            file.removeLine(positionToRemove);

            setAlert(translation.setUpLanguage(95));
        }
        else
        {
            setAlert(translation.setUpLanguage(63));
        }

        passwordFieldDeleteCategory.setText("");
        choiceCategory.getItems().clear();
        fillChoiceBox();
    }

    @FXML
    public void changeTab()
    {
        UserFile file = new UserFileBuilder()
                .addFileName(userSession.getFileNameCategories())
                .addPath(userSession.getProfilePath())
                .build();

        if(!file.isExist())
        {
            file.createFile();
        }

        setTab(radioNewCategory.isSelected());
    }

    @FXML
    public void actionClearField()
    {
        passwordFieldDeleteCategory.setText("");
        textNewCategory.setText("");
        clearAlert();
    }

    @FXML
    public void initialize()
    {
        radioNewCategory.setSelected(true);
        setTab(radioNewCategory.isSelected());
        setLanguage();
    }

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

    /**
     *  Private method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private void clearAlert()
    {
        labelAlert.setVisible(false);
        labelAlert.setText("");
    }

    private void setAlert(String alert)
    {
        labelAlert.setText(alert);
        labelAlert.setVisible(true);
    }

    private void setTab(boolean isSelectedNewCategory)
    {
        buttonClearNewCategory.setDisable(!isSelectedNewCategory);
        buttonNewCategory.setDisable(!isSelectedNewCategory);
        textNewCategory.setDisable(!isSelectedNewCategory);

        choiceCategory.setDisable(isSelectedNewCategory);
        passwordFieldDeleteCategory.setDisable(isSelectedNewCategory);
        buttonDeleteCategory.setDisable(isSelectedNewCategory);
        buttonClearDeleteCategory.setDisable(isSelectedNewCategory);
        choiceCategory.setDisable(isSelectedNewCategory);

        clearAlert();
        choiceCategory.getItems().clear();

        if(!isSelectedNewCategory)
        {
            fillChoiceBox();
        }
    }

    private void setLanguage()
    {
        buttonReturn.setText(translation.setUpLanguage(6));
        buttonNewCategory.setText(translation.setUpLanguage(26));
        buttonClearDeleteCategory.setText(translation.setUpLanguage(27));
        buttonClearNewCategory.setText(translation.setUpLanguage(27));

        radioNewCategory.setText(translation.setUpLanguage(89));
        radioDeleteCategory.setText(translation.setUpLanguage(90));

        labelDeleteCategoryPassword.setText(translation.setUpLanguage(14));
        labelNewCategory.setText(translation.setUpLanguage(91));
        labelDeleteCategory.setText(translation.setUpLanguage(92));
    }

    private void fillChoiceBox()
    {
        String[] items = lookForExistCategories();
        int sizeOfItem = items.length;

        for(int i = 0; i <= sizeOfItem - 1; i++)
        {
            choiceCategory.getItems().add(items[i]);
        }

        choiceCategory.getSelectionModel().selectFirst();
    }

    private String[] lookForExistCategories()
    {
        UserFile file = new UserFileBuilder()
                .addFileName(userSession.getFileNameCategories())
                .addPath(userSession.getProfilePath())
                .build();

        int size = file.size();

        String[] positionInMenu = new String[size];
        int iterator = 0;

        for(int i = 0; i <= size - 1; i += 1)
        {
            positionInMenu[iterator] = file.readLine(i + 1);
            iterator += 1;
        }

        return positionInMenu;
    }

    @FXML
    Button buttonClearNewCategory = new Button();
    @FXML
    Button buttonNewCategory = new Button();
    @FXML
    Button buttonDeleteCategory = new Button();
    @FXML
    Button buttonClearDeleteCategory = new Button();
    @FXML
    Button buttonReturn = new Button();

    @FXML
    RadioButton radioNewCategory = new RadioButton();
    @FXML
    RadioButton radioDeleteCategory = new RadioButton();

    @FXML
    Label labelNewCategory = new Label();
    @FXML
    Label labelDeleteCategory = new Label();
    @FXML
    Label labelDeleteCategoryPassword = new Label();
    @FXML
    Label labelAlert = new Label();

    @FXML
    TextField textNewCategory = new TextField();
    @FXML
    PasswordField passwordFieldDeleteCategory = new PasswordField();

    @FXML
    ChoiceBox choiceCategory = new ChoiceBox();
}
