package Controllers.userDesktop.userDesktopSettings;

import Controllers.ClassController;
import Controllers.userDesktop.userReviewController;
import builder.ChangeWindowBuilder;
import hoodStuff.ChangeWindow;
import javafx.fxml.FXML;

/**
 * Created $(DATE)
 */
public class userSettingsUserAccountController extends ClassController
{
    @FXML
    public void actionReturn()
    {
        userReviewController target = new userReviewController();

        ChangeWindow win = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userSettings.fxml")
                .addClassController(target)
                .addMainController(getController())
                .build();

        win.changeWindow();
    }
}
