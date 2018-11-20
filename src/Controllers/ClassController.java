package Controllers;

import hoodStuff.UserData;

/**
 * Created $(DATE)
 */
public class ClassController
{
    private UserData user;

    /**
     * User - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public UserData getUser()
    {
        return this.user;
    }

    public void setUser(UserData user)
    {
        this.user = user;
    }

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
