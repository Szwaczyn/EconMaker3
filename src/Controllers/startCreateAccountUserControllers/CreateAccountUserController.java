package Controllers.startCreateAccountUserControllers;

import Controllers.MainController;
import com.mysql.jdbc.util.BaseBugReport;
import hoodStuff.*;
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
    settingsConnector settings = new settingsConnector();

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
    Label labelAlert = new Label();

    @FXML
    TextField textLogin = new TextField();
    @FXML
    TextField textQuestion = new TextField();
    @FXML
    TextField textAnswer = new TextField();

    @FXML
    PasswordField textPassword = new PasswordField();

    @FXML
    public void createUser() {
        labelAlert.setVisible(false);
        if (checkTheCorrectness(textLogin.getText(), textPassword.getText(), textQuestion.getText(), textAnswer.getText())) {
            //TODO Check settings file and next establish connection to sql or local file

            switch (settings.read(3)) {
                case "DATABASE=Local": {
                    procedureSendUserToLocalDatabase();
                }
                break;

                case "DATABASE=SQL": {
                    procedureSendUserToSQL();
                }
                break;
            }
        } else {
            labelAlert.setText(translation.setUpLanguage(29));
            labelAlert.setVisible(true);
        }

    }

    private void procedureSendUserToLocalDatabase()
    {
        //TODO System check exist user
        saveToFileNewUser(textLogin.getText(), textPassword.getText(), textQuestion.getText(), textAnswer.getText());
        labelAlert.setVisible(true);
    }

    private void saveToFileNewUser(String user, String password, String question, String answer)
    {
        try {
            FileConnection file = new FileConnection();
            file.setFileName("src/settings/","econmaker.user");
            file.writeDownThisFile(user);
            file.writeDownThisFile(password);
            file.writeDownThisFile(question);
            file.writeDownThisFile(answer);
            labelAlert.setText(translation.setUpLanguage(37));
        } catch (Exception e) {
            System.out.println("Wystąpił błąd podczas zapisu. Kod błędu: " + e);
            labelAlert.setText(translation.setUpLanguage(38));
        }
    }

    private void procedureSendUserToSQL()
    {
        //TODO make body of this method
    }

    private boolean checkTheCorrectness(String login, String password, String question, String answer)
    {
        boolean correctness = false;

        if(!login.equals("") && !password.equals("") && !question.equals("") && !answer.equals("") && !question.equals(answer))
        {
            correctness = true;
        }

        return correctness;
    }

    @FXML
    public void clearTextField()
    {
        textLogin.setText("");
        textPassword.setText("");
        textQuestion.setText("");
        textAnswer.setText("");
        labelAlert.setText("");
        labelAlert.setVisible(false);
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
        labelAlert.setVisible(false);
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
