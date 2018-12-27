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

    public void showUser()
    {
        System.out.println(userSession);
    }

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
