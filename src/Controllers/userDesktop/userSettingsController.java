package Controllers.userDesktop;

import Controllers.ClassController;
import Controllers.userDesktop.userDesktopSettings.userSettingsBankAccountController;
import Controllers.userDesktop.userDesktopSettings.userSettingsUserAccountController;
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
        userSettingsController target = new userSettingsController();
        target.setUser(this.getUser());

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userDesktop.fxml")
                .addMainController(getController())
                .addClassController(target)
                .addLogin(getTempLogin())
                .build();

        window.changeWindow();
    }

    @FXML
    public void actionBankAccount()
    {
        userSettingsBankAccountController target = new userSettingsBankAccountController();
        target.setUser(this.getUser());

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userSettings/userSettingsBankAccount.fxml")
                .addMainController(getController())
                .addClassController(target)
                .addLogin(getTempLogin())
                .build();

        window.changeWindow();

    }

    @FXML
    public void actionUserAccount()
    {
        userSettingsUserAccountController target = new userSettingsUserAccountController();
        target.setUser(this.getUser());

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userSettings/userSettingsUserAccount.fxml")
                .addMainController(getController())
                .addClassController(target)
                .addLogin(getTempLogin())
                .build();

        window.changeWindow();
    }

    public void initialize()
    {

    }
}
