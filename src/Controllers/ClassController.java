package Controllers;

import hoodStuff.UserData;

/**
 * Created $(DATE)
 */
public class ClassController
{
    private UserData user;
    private String tempLogin;

    /**
     * User - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void setTempLogin(String tempLogin)
    {
        this.tempLogin = tempLogin;
    }

    public String getTempLogin() {return this.tempLogin; }

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
