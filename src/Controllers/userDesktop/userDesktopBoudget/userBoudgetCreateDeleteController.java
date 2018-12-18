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
        if(radioDeleteBoudget.isSelected())
        {
            setMenuDeleteBoudget();
            choiceBoxNameOfDeleteBoudget.getSelectionModel().selectFirst();
        }
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

        if(file.searchLine(nameOfBudget) == -1)
        {
            file.writeDown(nameOfBudget);
            file.writeDown(amountOfBoudget);

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
        size -= 2;
        size = size / 2;

        String[] positionInMenu = new String[size];
        int iterator = 0;

        for(int i = 1; i <= size * 2; i += 2)
        {
            positionInMenu[iterator] = file.readLine(i);
            iterator += 1;
        }

        return positionInMenu;
    }

    private void setMenuDeleteBoudget()
    {
        String[] items = lookForExistBoudget();
        int sizeOfItem = items.length;

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
