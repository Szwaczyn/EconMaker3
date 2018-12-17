package Controllers.startLoginUserController;

import Controllers.MainController;
import Controllers.userDesktop.userDesktopController;
import builder.EncryptBuilder;
import builder.UserDataBuilder;
import hoodStuff.Encrypting;
import hoodStuff.LanguageEngine;
import hoodStuff.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


/**
 * Created $(DATE)
 */
public class LoginUserController
{
    private MainController mainControllerVar;

    UserData user;

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
        user = new UserDataBuilder()
                .addUser(textRescueLogin.getText())
                .build();

        int loginPosition = user.getLineOfLogin();

        if(loginPosition != -1)
        {
            setDisableLogin(true);

            setQuestion(user);
            setVisibleRescueQuestion(true);
        }
        else
        {
            labelAlert.setVisible(true);
            labelAlert.setText(translation.setUpLanguage(47));
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
        if(textRescuePassword.getText().equals(textRepeatRescuePassword.getText()))
        {
            user.rewritePassword(textRepeatRescuePassword.getText());
            labelAlert.setText(translation.setUpLanguage(48));

            revertStage();
        }
        else
        {
            labelAlert.setVisible(true);
            labelAlert.setText(translation.setUpLanguage(49));
        }
    }

    @FXML
    public void procedureUserLogin()
    {
        user = new UserDataBuilder()
                .addUser(textLogin.getText())
                .build();

        Encrypting encrypt = new EncryptBuilder()
                .addContent(textPassword.getText())
                .build();

        String inputPassword = encrypt.MD5();
        String accountPassword = user.getPassword(user.getLoginPosition());

        if(inputPassword.equals(accountPassword))
        {
            goToDesktopUser(user);
        }
        else
        {
            labelAlert.setText(translation.setUpLanguage(45));
            labelAlert.setVisible(true);
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

    private void revertStage()
    {
        setVisibleNewPassword(false);
        setVisibleRescueQuestion(false);
        setDisableLogin(false);

        textRescueLogin.setText("");
        textRescueAnswer.setText("");
        textRescuePassword.setText("");
        textRepeatRescuePassword.setText("");

        textLogin.setText("");
        textPassword.setText("");

        labelAlert.setVisible(true);
        labelAlert.setText(translation.setUpLanguage(50) + " " + user.getLogin());
    }

    private void goToDesktopUser(UserData user)
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layoutFXML/userDesktop/userDesktop.fxml"));
        try {
            Pane pane = loader.load();
            userDesktopController target = loader.getController();
            target.userSession = user;
            mainControllerVar.clearScreen();
            target.setMainController(mainControllerVar);
            mainControllerVar.setScreen(pane);
        } catch (Exception e) {
            // TODO System service error
            System.out.println("Nie można załadadować okna database.fxml");
        }
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

    private void setDisableLogin(boolean setDisable)
    {
        textLogin.setDisable(setDisable);
        textPassword.setDisable(setDisable);
        buttonLogin.setDisable(setDisable);
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
