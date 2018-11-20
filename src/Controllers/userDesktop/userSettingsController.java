package Controllers.userDesktop;

import Controllers.ClassController;
import builder.ChangeWindowBuilder;
import hoodStuff.ChangeWindow;
import javafx.fxml.FXML;

/**
 * Created $(DATE)
 */
public class userSettingsController extends ClassController
{
    @FXML
    public void actionReturn()
    {
        userDesktopController target = new userDesktopController();

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userDesktop.fxml")
                .addMainController(getController())
                .addClassController(target)
                .build();

        window.changeWindow();
    }

    @FXML
    public void actionBankAccount()
    {
        userDesktopController target = new userDesktopController();

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userSettings/userSettingsBankAccount.fxml")
                .addMainController(getController())
                .addClassController(target)
                .build();

        window.changeWindow();
    }

    @FXML
    public void actionUserAccount()
    {
        userDesktopController target = new userDesktopController();

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userSettings/userSettingsUserAccount.fxml")
                .addMainController(getController())
                .addClassController(target)
                .build();

        window.changeWindow();
    }
}
