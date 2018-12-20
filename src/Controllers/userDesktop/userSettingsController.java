package Controllers.userDesktop;

import Controllers.ClassController;
import Controllers.userDesktop.userDesktopSettings.userSettingsBankAccountController;
import Controllers.userDesktop.userDesktopSettings.userSettingsCategoriesController;
import Controllers.userDesktop.userDesktopSettings.userSettingsUserAccountController;
import builder.ChangeWindowBuilder;
import builder.UserDataBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.UserData;
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

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userDesktop.fxml")
                .addMainController(getController())
                .addClassController(target)
                .addUserSession(this.userSession)
                .build();

        window.changeWindow();
    }

    @FXML
    public void actionBankAccount()
    {
        userSettingsBankAccountController target = new userSettingsBankAccountController();

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userSettings/userSettingsBankAccount.fxml")
                .addMainController(getController())
                .addClassController(target)
                .addUserSession(this.userSession)
                .build();

        window.changeWindow();

    }

    @FXML
    public void actionUserAccount()
    {
        userSettingsUserAccountController target = new userSettingsUserAccountController();

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userSettings/userSettingsUserAccount.fxml")
                .addMainController(getController())
                .addClassController(target)
                .addUserSession(this.userSession)
                .build();

        window.changeWindow();
    }

    @FXML
    public void actionUserCategories()
    {
        userSettingsCategoriesController target = new userSettingsCategoriesController();

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userSettings/userSettingsCategoriesController.fxml")
                .addMainController(getController())
                .addClassController(target)
                .addUserSession(this.userSession)
                .build();

        window.changeWindow();
    }

    public void initialize()
    {

    }
}
