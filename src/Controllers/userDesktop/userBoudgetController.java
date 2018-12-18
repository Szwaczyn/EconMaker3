package Controllers.userDesktop;

import Controllers.ClassController;
import Controllers.userDesktop.userDesktopBoudget.userBoudgetCreateDeleteController;
import Controllers.userDesktop.userDesktopBoudget.userBoudgetReviewController;
import builder.ChangeWindowBuilder;
import builder.UserFileBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.LanguageEngine;
import hoodStuff.UserFile;
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
    public void actionReview()
    {
        userBoudgetReviewController target = new userBoudgetReviewController();

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userBoudget/userBoudgetReview.fxml")
                .addMainController(getController())
                .addClassController(target)
                .addUserSession(userSession)
                .addBoudget(lookForExistBoudget())
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

    private String[] lookForExistBoudget()
    {
        UserFile file = new UserFileBuilder()
                .addFileName("boudget" + userSession.getLogin() + ".dll")
                .addPath("src/settings/profiles/" + userSession.getLogin() + "/")
                .build();

        int size = file.size();
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
