package builder;

import hoodStuff.FileConnection;

/**
 * Created $(DATE)
 */
public class FileConnectionBuilder
{
    private String path;
    private String fileName;

    /**
     *  Add methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public FileConnectionBuilder addPath(String path)
    {
        this.path = path;
        return this;
    }

    public FileConnectionBuilder addFileName(String fileName)
    {
        this.fileName = fileName;
        return this;
    }

    /**
     *  Get methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public String getPath()
    {
        return this.path;
    }

    public String getFileName() { return this.fileName; }

    /**
     *  build - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public FileConnection build()
    {
        return new FileConnection(this);
    }
}
