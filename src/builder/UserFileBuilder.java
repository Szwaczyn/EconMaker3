package builder;

import hoodStuff.UserFile;


/**
 * Created $(DATE)
 */

public class UserFileBuilder
{
    private String fileName;
    private String path;

    /**
     *  Get method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }

    /**
     *  add method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public UserFileBuilder addFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public UserFileBuilder addPath(String path) {
        this.path = path;
        return this;
    }

    public UserFile build()
    {
        return new UserFile(this);
    }


}
