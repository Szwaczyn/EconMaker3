package hoodStuff;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created $(DATE)
 */
public class UserData
{
    private String file = "econmaker.user";
    private String path = "src/settings";

    /**
     *  Public method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public int getLineOfLogin(String login)
    {
        int amountLine = amountOfLineInThisFile();
        int counter = 1;
        int result = -1;

        BufferedReader reader = new BufferedReader(new InputStreamReader(getThisFileInputStreamReader()));

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

    /**
     *  Private method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

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
            System.out.println(e + " Main Class: FileConnection: readFile: Try read Line");
        }

        return result;
    }

    /**
     *  Constructors - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public UserData(){}
}
