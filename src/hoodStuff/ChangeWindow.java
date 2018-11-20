package hoodStuff;

import Controllers.ClassController;
import Controllers.MainController;
import Controllers.startLoginUserController.LoginUserController;
import Controllers.userDesktop.userReviewController;
import builder.ChangeWindowBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * Created $(DATE)
 */
public class ChangeWindow
{
    private String path;
    private ClassController target;
    private MainController mainControllerVar;

    public void changeWindow()
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(this.path));
        try {
            Pane pane = loader.load();
            this.target = loader.getController();
            this.target.setMainController(this.mainControllerVar);
            this.mainControllerVar.setScreen(pane);
        } catch(Exception e) {
            //TODO Make System Error
            System.out.println("Nie można załadować okna loginUser.fxml - " + e);
        }
    }

    public ChangeWindow(ChangeWindowBuilder build)
    {
        this.path = build.getPath();
        this.target = build.getClassController();
        this.mainControllerVar = build.getMainControllerVar();
    }
}
