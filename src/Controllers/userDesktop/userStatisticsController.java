package Controllers.userDesktop;

import Controllers.ClassController;
import builder.ChangeWindowBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.LanguageEngine;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created $(DATE)
 */
public class userStatisticsController extends ClassController
{
    @FXML
    public void actionReturn()
    {
        userDesktopController target = new userDesktopController();

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userDesktop.fxml")
                .addMainController(getController())
                .addClassController(target)
                .addUserSession(this.userSession)
                .build();

        window.changeWindow();
    }

    /**
     *  Initialize - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        setUpLanguage();

        if(this.userSession != null)
        {
            System.out.println("ok");
        }
    }

    private void setUpLanguage()
    {
        labelExpenditiures.setText(translation.setUpLanguage(41));
        labelIncome.setText(translation.setUpLanguage(40));
    }

    LanguageEngine translation = new LanguageEngine();

    @FXML
    Label labelIncome = new Label();
    @FXML
    Label labelExpenditiures = new Label();
    @FXML
    Label labelMonth = new Label();
    @FXML
    Label labelValueIncome = new Label();
    @FXML
    Label labelValueExpenditiures = new Label();

    @FXML
    Pane blockIncome = new Pane();
    @FXML
    Pane blockExpenditiures = new Pane();
}
