package hoodStuff;

import Controllers.ClassController;
import Controllers.MainController;
import Controllers.userDesktop.userDesktopBoudget.userBoudgetReviewController;
import Controllers.userDesktop.userExpenditiuresController;
import Controllers.userDesktop.userIncomeController;
import Controllers.userDesktop.userReviewController;
import Controllers.userDesktop.userStatisticsController;
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

    public void changeWindow()
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(this.path));
        try {
            Pane pane = loader.load();
            this.target = loader.getController();
            this.target.setMainController(this.mainControllerVar);
            this.target.userSession = userSession;
            this.mainControllerVar.setScreen(pane);

            if(this.target instanceof userIncomeController)
            {
                procedureInitialaizeIncomeController();
            }
            else if(this.target instanceof userExpenditiuresController)
            {
                procedureInitializeExpenditiureController();
            }
            else if(this.target instanceof userBoudgetReviewController)
            {
                procedureInitialaizeReviewBoudget();
            }
            else if(this.target instanceof  userReviewController)
            {
                procedureInitializeReview();
            }
            else if(this.target instanceof userStatisticsController)
            {
                procedureInitializeStatistics();
            }
            // TODO Przekazywanie tablicy budzetu this.target.
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
    }

    private void procedureInitialaizeIncomeController()
    {
        userIncomeController usr = (userIncomeController) this.target;
        usr.initialize();
    }

    private void procedureInitializeExpenditiureController()
    {
        userExpenditiuresController usr = (userExpenditiuresController) this.target;
        usr.initialize();
    }

    private void procedureInitialaizeReviewBoudget()
    {
        userBoudgetReviewController usr = (userBoudgetReviewController) this.target;
        usr.initialize();
    }

    private void procedureInitializeReview()
    {
        userReviewController usr = (userReviewController) this.target;
        usr.initialize();
    }

    private void procedureInitializeStatistics()
    {
        userStatisticsController usr = (userStatisticsController) this.target;
        usr.initialize();
    }
}
