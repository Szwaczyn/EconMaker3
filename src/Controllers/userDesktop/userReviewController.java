package Controllers.userDesktop;

import Controllers.ClassController;
import builder.ChangeWindowBuilder;
import hoodStuff.ChangeWindow;
import javafx.fxml.FXML;

/**
 * Created $(DATE)
 */
public class userReviewController extends ClassController
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
}
