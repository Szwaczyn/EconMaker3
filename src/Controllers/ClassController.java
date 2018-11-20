package Controllers;

/**
 * Created $(DATE)
 */
public class ClassController
{
    /**
     * Windows system - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private MainController mainControllerVar;

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }

    public MainController getController()
    {
        return mainControllerVar;
    }
}
