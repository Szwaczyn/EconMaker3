package Controllers.userDesktop.userDesktopBoudget;

import Controllers.ClassController;
import Controllers.userDesktop.userBoudgetController;
import builder.ChangeWindowBuilder;
import builder.UserFileBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.LanguageEngine;
import hoodStuff.UserFile;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;

/**
 * Created $(DATE)
 */
public class userBoudgetCreateDeleteController extends ClassController
{
    LanguageEngine translation = new LanguageEngine();

    @FXML
    public void actionCreateBoudget()
    {
        File file = new File("src/settings/profiles/" + userSession.getLogin() + "/boudget" + userSession.getLogin() + ".dll");
        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        addBoudget(textNameOfNewBoudget.getText(), textConditionOfNewBoudget.getText());
    }

    @FXML
    public void actionDeleteBoudget()
    {
        System.out.println("Usuń");
    }

    @FXML
    public void actionSetTab()
    {
        changeTab(radioCreateBoudget.isSelected());
    }

    @FXML
    public void actionReturn()
    {
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
    public void actionClearTextField()
    {
        textPasswordDeleteBoudget.setText("");
        textConditionOfNewBoudget.setText("");
        textNameOfNewBoudget.setText("");
    }

    /**
     *  Private methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private void addBoudget(String nameOfBudget, String amountOfBoudget)
    {
        UserFile file = new UserFileBuilder()
                .addPath("src/settings/profiles/" + userSession.getLogin() + "/")
                .addFileName("/boudget" + userSession.getLogin() + ".dll")
                .build();

        file.writeDown(nameOfBudget);
        file.writeDown(amountOfBoudget);
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

        labelAlert.setText("");
        labelAlert.setVisible(false);
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
