package builder;

import hoodStuff.Encrypting;

public class EncryptBuilder
{
    private String content;

    /**
     *  Add methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public EncryptBuilder addContent(String content)
    {
        this.content = content;
        return this;
    }

    /**
     *  Get methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public String getContent()
    {
        return content;
    }

    /**
     *  Build methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public Encrypting build()
    {
        return new Encrypting(this);
    }

    /**
     *  Constructor ethods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public EncryptBuilder()
    { }
}
