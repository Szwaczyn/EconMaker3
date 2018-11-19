package hoodStuff;

import builder.UserDataBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created $(DATE)
 */
public class UserData
{
    private String file = "econmaker.user";
    private String path = "src/settings";
    private String user = "";
    private int positionLogin;


    /**
     *  Public method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void createUserProfile()
    {
        String userFileSettings = this.user + ".dll";
        String path ="src/settings/profiles/" + this.user + "/";

        createUserFolder(path);
        createUserSettings(userFileSettings, path);
    }

    /**
     *  Private method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private void createUserFolder(String path)
    {
        try {
            Files.createDirectory(Paths.get(path));
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Błąd tworzenia folderu");
        }
    }

    private void createUserSettings(String userSettings, String path)
    {
        FileConnection file = new FileConnection(userSettings, path);
        file.createFile();
        file.writeDownThisFile("0");
        file.writeDownThisFile("0");
    }

    /**
     *  Set method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void setUser(String user)
    {
        int line = getLineOfLogin(user);
        if(line != -1)
        {
            this.positionLogin = line;
            this.user = user;
        }
    }

    /**
     *  Get method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public int getLineOfLogin()
    {
        int amountLine = amountOfLineInThisFile();
        int counter = 1;
        int result = -1;
        this.user = this.user.trim();

        do{
            if(this.user.equals(getLine(counter)))
            {
                result = counter;
                break;
            }
            counter += 4;
        } while(counter <= amountLine);

        return result;
    }

    public int getLineOfLogin(String login)
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

    public String getPassword(int loginPosition)
    {
        int size = amountOfLineInThisFile();
        String result = "";

        if(checkTheCorrectness(loginPosition, size))
        {
            result = getLine(loginPosition + 1);
        }
        else
        {
            // TODO System error
            System.out.println("Wystąpił nieoczekiwany błąd ");
        }
        return result;
    }

    public String getQuestion(int loginPosition)
    {
        int size = amountOfLineInThisFile();
        String result = "";

        if(checkTheCorrectness(loginPosition, size))
        {
            result = getLine(loginPosition + 2);
        }
        else
        {
            // TODO System error
            System.out.println("Wystąpił nieoczekiwany błąd ");
        }
        return result;
    }

    public String getAnswer(int loginPosition)
    {
        int size = amountOfLineInThisFile();
        String result = "";

        if(checkTheCorrectness(loginPosition, size))
        {
            result = getLine(loginPosition + 3);
        }
        else
        {
            // TODO System error
            System.out.println("Wystąpił nieoczekiwany błąd ");
        }
        return result;
    }

    public String getAnswer()
    {
        int size = amountOfLineInThisFile();
        String result = "";

        if(checkTheCorrectness(this.getLoginPosition(), size))
        {
            result = getLine(this.getLoginPosition() + 3);
        }
        else
        {
            // TODO System error
            System.out.println("Wystąpił nieoczekiwany błąd ");
        }
        return result;
    }

    public int getLoginPosition()
    {
        return this.positionLogin;
    }

    public String getLogin() {return this.user; }

    /**
     *  Private method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private boolean checkTheCorrectness(int loginPosition, int size)
    {
        boolean result = false;

        if((1 < loginPosition) && (loginPosition < size))
        {
            result = true;
        }

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
            fin = new FileInputStream("src/settings/econmaker.user");
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
     *  Constructors - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

//    public UserData()
//    {
//        this.user = "";
//        this.positionLogin = -1;
//    }

//    public UserData(String login)
//    {
//        this.user = login;
//        this.positionLogin = getLineOfLogin();
//    }

    public UserData(UserDataBuilder userDataBuilder)
    {
        this.file = userDataBuilder.getFile();
        this.path = userDataBuilder.getPath();
        this.user = userDataBuilder.getUser();
        this.positionLogin = userDataBuilder.getPositionLogin();
    }
}
