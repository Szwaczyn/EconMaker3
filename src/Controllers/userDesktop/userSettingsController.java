package Controllers.userDesktop;

import Controllers.ClassController;
import Controllers.userDesktop.userDesktopSettings.userSettingsBankAccountController;
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
                .build();

        window.changeWindow();
    }

    public void initialize()
    {
        try {
            System.out.println(usr.getLogin());
        } catch (Exception e){
            System.out.println(e);
        }

    }
}
