package Controllers.userDesktop;

import Controllers.ClassController;
import Controllers.userDesktop.userDesktopBoudget.userBoudgetCreateDeleteController;
import builder.ChangeWindowBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.LanguageEngine;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

/**
 * Created $(DATE)
 */
public class userBoudgetController extends ClassController
{
    LanguageEngine translation = new LanguageEngine();
    /**
     *  Action methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    @FXML
    public void actionReturn()
    {
        userDesktopController target = new userDesktopController();

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userDesktop.fxml")
                .addMainController(getController())
                .addClassController(target)
                .addUserSession(userSession)
                .build();

        window.changeWindow();
    }

    @FXML
    public void actionCreate()
    {
        userBoudgetCreateDeleteController target = new userBoudgetCreateDeleteController();

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userBoudget/userBoudgetCreateDelete.fxml")
                .addMainController(getController())
                .addClassController(target)
                .addUserSession(userSession)
                .build();

        window.changeWindow();
    }

    /**
     *  Items methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        buttonReturn.setText(translation.setUpLanguage(6));
        buttonReview.setText(translation.setUpLanguage(46));
        buttonCreate.setText(translation.setUpLanguage(76));
    }

    @FXML
    Button buttonReturn = new Button();
    @FXML
    Button buttonReview = new Button();
    @FXML
    Button buttonCreate = new Button();
}
