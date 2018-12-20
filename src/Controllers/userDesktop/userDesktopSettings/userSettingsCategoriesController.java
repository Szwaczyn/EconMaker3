package Controllers.userDesktop.userDesktopSettings;

import Controllers.ClassController;
import Controllers.userDesktop.userReviewController;
import builder.ChangeWindowBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.LanguageEngine;
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

    }

    @FXML
    public void actionDeleteCategory()
    {

    }

    @FXML
    public void changeTab()
    {
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
        setTab(radioNewCategory.isSelected());
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

        clearAlert();
    }

    private void setLanguage()
    {

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
