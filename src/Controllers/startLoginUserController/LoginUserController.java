package Controllers.startLoginUserController;

import Controllers.MainController;
import builder.EncryptBuilder;
import hoodStuff.Encrypting;
import hoodStuff.FileConnection;
import hoodStuff.LanguageEngine;
import hoodStuff.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * Created $(DATE)
 */
public class LoginUserController
{
    private MainController mainControllerVar;

    UserData user = new UserData();

    LanguageEngine translation = new LanguageEngine();

    @FXML
    Label labelAlert = new Label();
    @FXML
    Label labelLogin = new Label();
    @FXML
    Label labelLoginToSystem = new Label();
    @FXML
    Label labelPassword = new Label();
    @FXML
    Label labelRescuePassword = new Label();
    @FXML
    Label labelRescueLogin = new Label();
    @FXML
    Label labelHelpQuestion = new Label();
    @FXML
    Label labelAnswerQuestion = new Label();
    @FXML
    Label labelSetRescuePassword = new Label();
    @FXML
    Label labelRepeatRescuePassword = new Label();

    @FXML
    Button buttonLogin = new Button();
    @FXML
    Button buttonRescuePassword = new Button();
    @FXML
    Button buttonRescueSendAnswer = new Button();
    @FXML
    Button buttonSetNewPassword = new Button();
    @FXML
    Button position_1 = new Button();

    @FXML
    TextField textLogin = new TextField();
    @FXML
    TextField textRescueLogin = new TextField();
    @FXML
    TextField textRescueAnswer = new TextField();

    @FXML
    PasswordField textPassword = new PasswordField();
    @FXML
    PasswordField textRescuePassword = new PasswordField();
    @FXML
    PasswordField textRepeatRescuePassword = new PasswordField();

    /**
     *  Controls - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    @FXML
    public void rescuePassword()
    {
        user.setUser(textRescueLogin.getText());
        int loginPosition = user.getLineOfLogin();
        if(loginPosition != -1)
        {
            setQuestion(user);
            setVisibleRescueQuestion(true);
        }
        else
        {
            // TODO System error. Wrong login
        }
    }

    @FXML
    public void sendRescueAnswer()
    {
        String inputAnswer = textRescueAnswer.getText().toLowerCase();

        Encrypting encrypt = new EncryptBuilder()
                .addContent(inputAnswer)
                .build();

        inputAnswer = encrypt.MD5();

         String correctAnswer = user.getAnswer();

         if(inputAnswer.equals(correctAnswer))
         {
             setVisibleNewPassword(true);
         }
         else
         {
             // TODO System error
             System.out.println("Zła odpowiedź");
         }
    }

    @FXML
    public void setNewPassword()
    {
        System.out.println("ok");
    }

    @FXML
    public void procedureUserLogin()
    {
        UserData usr = new UserData();
        Encrypting encrypt = new EncryptBuilder()
                .addContent(textPassword.getText())
                .build();
        String inputPassword = encrypt.MD5();
        String accountPassword = usr.getPassword(usr.getLineOfLogin(textLogin.getText()));

        if(inputPassword.equals(accountPassword))
        {
            // TODO redirect to user desktop
            System.out.println("Zalogowano");
        }
        else
        {
            // TODO System error
            System.out.println("Nie udało się zalogować");
        }

    }

    /**
     *  Other method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        setLanguage();
        setUnvisibleRescuePassword(true);
    }

    /**
     *  Private method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private String getUserPassword(int numberOfLine)
    {
        FileConnection file = new FileConnection("econmaker.user", "src/settings");
        return file.readThisFile(numberOfLine + 1);
    }

    private boolean compareEncryptedPassword(String userPassword, String inputPassword)
    {
        return userPassword.equals(inputPassword);
    }

    private void setLanguage()
    {
        labelAlert.setText("");

        labelLogin.setText(translation.setUpLanguage(22));
        labelRescueLogin.setText(translation.setUpLanguage(22));
        labelPassword.setText(translation.setUpLanguage(23));
        labelRescuePassword.setText(translation.setUpLanguage(30));
        labelRepeatRescuePassword.setText(translation.setUpLanguage(31));
        labelLoginToSystem.setText(translation.setUpLanguage(32));
        buttonLogin.setText(translation.setUpLanguage(2));
        labelRescuePassword.setText(translation.setUpLanguage(33));
        labelRescueLogin.setText(translation.setUpLanguage(22));
        buttonRescuePassword.setText(translation.setUpLanguage(34));
        buttonRescueSendAnswer.setText(translation.setUpLanguage(35));
        buttonSetNewPassword.setText(translation.setUpLanguage(36));
        position_1.setText(translation.setUpLanguage(6));
        labelAnswerQuestion.setText(translation.setUpLanguage(25));
        labelSetRescuePassword.setText(translation.setUpLanguage(30));
    }

    private void setVisibleRescueQuestion(boolean visibleSection)
    {
        labelHelpQuestion.setVisible(visibleSection);
        labelAnswerQuestion.setVisible(visibleSection);
        textRescueAnswer.setVisible(visibleSection);
        buttonRescueSendAnswer.setVisible(visibleSection);

        labelHelpQuestion.setDisable(!visibleSection);
        labelAnswerQuestion.setDisable(!visibleSection);
        textRescueAnswer.setDisable(!visibleSection);
        buttonRescueSendAnswer.setDisable(!visibleSection);

        buttonRescuePassword.setDisable(visibleSection);
        textRescueLogin.setDisable(visibleSection);
    }

    private void setVisibleNewPassword(boolean visibleSection)
    {
        labelSetRescuePassword.setVisible(visibleSection);
        labelRepeatRescuePassword.setVisible(visibleSection);
        textRescuePassword.setVisible(visibleSection);
        textRepeatRescuePassword.setVisible(visibleSection);
        buttonSetNewPassword.setVisible(visibleSection);

        labelSetRescuePassword.setDisable(!visibleSection);
        labelRepeatRescuePassword.setDisable(!visibleSection);
        textRescuePassword.setDisable(!visibleSection);
        textRepeatRescuePassword.setDisable(!visibleSection);

        labelHelpQuestion.setDisable(visibleSection);
        labelAnswerQuestion.setDisable(visibleSection);
        textRescueAnswer.setDisable(visibleSection);
        buttonRescueSendAnswer.setDisable(visibleSection);
    }

    private void setUnvisibleRescuePassword(boolean visible)
    {
        textRepeatRescuePassword.setText("");
        textRescueLogin.setText("");
        textRescueAnswer.setText("");

        labelHelpQuestion.setVisible(!visible);
        labelAnswerQuestion.setVisible(!visible);
        textRescueAnswer.setVisible(!visible);
        buttonRescueSendAnswer.setVisible(!visible);

        labelSetRescuePassword.setVisible(!visible);
        labelRepeatRescuePassword.setVisible(!visible);
        textRepeatRescuePassword.setVisible(!visible);
        textRescuePassword.setVisible(!visible);
        buttonSetNewPassword.setVisible(!visible);
    }

    private void setQuestion(UserData user)
    {
        String question = user.getQuestion(user.getLoginPosition());
        labelHelpQuestion.setText(translation.setUpLanguage(39) + ": " + question);
    }

    /**
     *  Windows system - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    @FXML
    public void returnToMenu()
    {
        mainControllerVar.inicializujMenu();
    }

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }
}
