package Controllers.startCreateAccountUserControllers;

import Controllers.MainController;
import hoodStuff.BugTracker;
import hoodStuff.LanguageEngine;
import hoodStuff.sqlConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created $(DATE)
 */
public class CreateAccountUserController
{
    LanguageEngine translation = new LanguageEngine();
    sqlConnection sqlConnect = new sqlConnection();

    @FXML
    Button buttonCreate = new Button();
    @FXML
    Button buttonClear = new Button();
    @FXML
    Button position_1 = new Button();

    @FXML
    Label labelLogin = new Label();
    @FXML
    Label labelPassword = new Label();
    @FXML
    Label labelQuestion = new Label();
    @FXML
    Label labelAnswer = new Label();
    @FXML
    Label labelTitle = new Label();

    @FXML
    TextField textLogin = new TextField();
    @FXML
    TextField textQuestion = new TextField();
    @FXML
    TextField textAnswer = new TextField();

    @FXML
    PasswordField textPassword = new PasswordField();

    @FXML
    public void createUser()
    {
        if(checkTheCorrectness(textLogin.getText(), textPassword.getText(), textQuestion.getText(), textAnswer.getText()))
        {
            procedureSendUserToDatabase();
        }
        else
        {
            //TODO System error
            System.out.println("Nie mozna dodać użytkownika");
        }

    }

    private void procedureSendUserToDatabase()
    {
        System.out.println("Dodawanie użytkownika");
    }

    private boolean checkTheCorrectness(String login, String password, String question, String answer)
    {
        boolean correctnss = false;
        BugTracker bug = new BugTracker(correctnss);

        if(login.equals("lo"))
        {
            correctnss = true;
            bug.show();
        }
        bug.show();
        return correctnss;
    }

    @FXML
    public void clearTextField()
    {
        textLogin.setText("");
        textPassword.setText("");
        textQuestion.setText("");
        textAnswer.setText("");
    }

    public void initialize()
    {
        buttonCreate.setText(translation.setUpLanguage(26));
        buttonClear.setText(translation.setUpLanguage(27));
        position_1.setText(translation.setUpLanguage(6));
        labelLogin.setText(translation.setUpLanguage(22));
        labelPassword.setText(translation.setUpLanguage(23));
        labelQuestion.setText(translation.setUpLanguage(24));
        labelAnswer.setText(translation.setUpLanguage(25));
        labelTitle.setText(translation.setUpLanguage(28));

        textLogin.setText("");
    }

    private MainController mainControllerVar;

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }

    @FXML
    public void returnToMenu()
    {
        mainControllerVar.inicializujMenu();
    }
}
