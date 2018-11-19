package builder;

import hoodStuff.UserData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserDataBuilder
{
    private String file = "econmaker.user";
    private String path = "src/settings";
    private String user = "";
    private int positionLogin;

    /**
     *  Add method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public UserDataBuilder addFile(String nameOfFile)
    {
        this.file = nameOfFile;
        return this;
    }

    public UserDataBuilder addPath(String pathOfFile)
    {
        this.path = pathOfFile;
        return this;
    }

    public UserDataBuilder addUser(String user)
    {
        this.user = user;
        this.positionLogin = getLineOfLogin(this.user);
        return this;
    }

    /**
     *  Get method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public String getFile(){return this.file; }

    public String getPath(){return this.path; }

    public String getUser(){return this.user; }

    public int getPositionLogin(){return this.positionLogin; }

    /**
     *  Private method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */
    private int getLineOfLogin(String login)
    {
        int amountLine = amountOfLineInThisFile();
        int counter = 1;
        int result = -1;
        login = login.trim();

        do{
            if(login.equals(getLine(counter)))
            {
                result = counter;
                break;
            }
            counter += 4;
        } while(counter <= amountLine);

        return result;
    }

    private int amountOfLineInThisFile()
    {
        int counter = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(getThisFileInputStreamReader()));

        try{
            while(reader.readLine() != null )
            {
                counter += 1;
            }
        } catch(Exception e){
            //TODO System error
            System.out.println("Error in FileConnection: Method AmountOfLineInThisFile");
        }

        try {
            reader.close();
        } catch (IOException e) {
            // TODO System error
            e.printStackTrace();
        }

        return counter;
    }

    private FileInputStream getThisFileInputStreamReader()
    {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(this.path + "/" + this.file);
        } catch (Exception e) {
            //TODO System error
            System.out.println("Błąd podczas otrzymywania ścieżki : src/settings/econmaker.user");
        }
        return fin;
    }

    private String getLine(int numberOfline)
    {
        int numberOfActualLine = 0;
        numberOfline -= 1;
        String buforLine;
        String result = new String("");
        BufferedReader reader = new BufferedReader(new InputStreamReader(getThisFileInputStreamReader()));

        try {
            while((buforLine = reader.readLine()) != null)
            {
                if(numberOfActualLine == numberOfline)
                {
                    result = buforLine;
                }
                numberOfActualLine++;
            }
        } catch (Exception e) {
            //TODO Make System Error
            System.out.println(e + " Main Class: FileConnection: readFile: Try readSettings Line");
        }

        return result.trim();
    }

    /**
     *  Build method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public UserData build()
    {
        return new UserData(this);
    }
}
