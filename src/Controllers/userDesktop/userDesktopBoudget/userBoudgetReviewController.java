package Controllers.userDesktop.userDesktopBoudget;

import Controllers.ClassController;
import Controllers.userDesktop.userBoudgetController;
import builder.ChangeWindowBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.LanguageEngine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;


/**
 * Created $(DATE)
 */
public class userBoudgetReviewController extends ClassController
{
    public String[] boudget;

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

    public void actionShowBoudget()
    {
        System.out.println(this);
    }

    /**
     *  Initialize controllers - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    LanguageEngine translation = new LanguageEngine();

    public void initialize()
    {
        setUpLanguage();
        clearAlert();
    }

    public void setBoudget(String[] boudget)
    {
        this.boudget = boudget;
    }

    private void setUpLanguage()
    {
        buttonReturn.setText(translation.setUpLanguage(6));
        buttonShowBoudget.setText(translation.setUpLanguage(85));

        labelChoiceBoudget.setText(translation.setUpLanguage(86));
        labelNameOfBoudget.setText(translation.setUpLanguage(87));
        labelValueOfBoudget.setText("");
    }

    private void setAlert(String alert)
    {
        labelAlert.setText(alert);
        labelAlert.setVisible(true);
    }

    private void clearAlert()
    {
        labelAlert.setText("");
        labelAlert.setVisible(false);
    }

    @FXML
    Button buttonReturn = new Button();
    @FXML
    Button buttonShowBoudget = new Button();

    @FXML
    Label labelChoiceBoudget = new Label();
    @FXML
    Label labelNameOfBoudget = new Label();
    @FXML
    Label labelValueOfBoudget = new Label();
    @FXML
    Label labelAlert = new Label();

    @FXML
    ChoiceBox choiceBoxBoudget = new ChoiceBox();

    public userBoudgetReviewController()
    {
    }
    public userBoudgetReviewController(String[] boudget)
    {
        this.boudget = boudget;
        System.out.println(this);
    }
}
