package builder;

import Controllers.ClassController;
import Controllers.MainController;
import hoodStuff.ChangeWindow;

/**
 * Created $(DATE)
 */
public class ChangeWindowBuilder
{
    private String path;
    private ClassController target;
    private MainController mainControllerVar;

    /**
     * Add Method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

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

    /**
     * Build Method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public ChangeWindow build()
    {
        return new ChangeWindow(this);
    }
}
