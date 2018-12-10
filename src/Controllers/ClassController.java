package Controllers;

import hoodStuff.UserData;

/**
 * Created $(DATE)
 */
public class ClassController
{
    public UserData userSession;

    /**
     * Windows system - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public MainController mainControllerVar;

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }

    public MainController getController()
    {
        return mainControllerVar;
    }

}
