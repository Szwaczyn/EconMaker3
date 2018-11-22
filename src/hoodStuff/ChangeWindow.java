package hoodStuff;

import Controllers.ClassController;
import Controllers.MainController;
import builder.ChangeWindowBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.nio.file.attribute.UserDefinedFileAttributeView;

/**
 * Created $(DATE)
 */
public class ChangeWindow
{
    private String path;
    private ClassController target;
    private MainController mainControllerVar;
    private UserData userSession;
    private String login;

    public void changeWindow()
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(this.path));
        try {
            Pane pane = loader.load();
            this.target = loader.getController();
            this.target.setMainController(this.mainControllerVar);
            target.setTempLogin(login);
            this.mainControllerVar.setScreen(pane);
        } catch(Exception e) {
            //TODO Make System Error
            System.out.println("Nie można załadować okna " + e);
        }
    }

    public ChangeWindow(ChangeWindowBuilder build)
    {
        this.path = build.getPath();
        this.target = build.getClassController();
        this.mainControllerVar = build.getMainControllerVar();
        this.userSession = build.getUserSession();
        this.login = build.getLogin();
    }
}
