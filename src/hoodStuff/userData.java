package hoodStuff;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created $(DATE)
 */
public class userData
{
    private String file = "econmaker.user";
    private String path = "src/settings";

    /**
     *  Public method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public int getIdOfLogin(String login)
    {
        return 0;
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
}
