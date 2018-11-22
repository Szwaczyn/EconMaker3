package builder;

import Controllers.ClassController;
import Controllers.MainController;
import hoodStuff.ChangeWindow;
import hoodStuff.UserData;

/**
 * Created $(DATE)
 */
public class ChangeWindowBuilder
{
    private String path;
    private ClassController target;
    private MainController mainControllerVar;
    private UserData userSession;
    private String login;

    /**
     * Add Method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public ChangeWindowBuilder addLogin(String login)
    {
        this.login = login;
        return this;
    }

    public ChangeWindowBuilder addPath(String path)
    {
        this.path = path;
        return this;
    }

    public ChangeWindowBuilder addClassController(ClassController target)
    {
        this.target = target;
        return this;
    }

    public ChangeWindowBuilder addMainController(MainController mainControllerVar)
    {
        this.mainControllerVar = mainControllerVar;
        return this;
    }

    public ChangeWindowBuilder addUserSession(UserData user)
    {
        this.userSession = user;
        return this;
    }

    /**
     * Get Method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public String getPath()
    {
        return this.path;
    }

    public ClassController getClassController()
    {
        return this.target;
    }

    public MainController getMainControllerVar() {
        return mainControllerVar;
    }

    public UserData getUserSession() { return  userSession; }

    public String getLogin() { return login; }

    /**
     * Build Method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public ChangeWindow build()
    {
        return new ChangeWindow(this);
    }
}
