package hoodStuff;

import java.io.*;

/**
 * Created $(DATE)
 */
public class fileConnection
{
    private String fileName;

    //TODO This function should be able to read each file now it is able to read only settings file
    public String read(int lineInSettings)
    {
        String line = "";
        String[] lineSettings = new String[10];
        FileInputStream fin = null;
        String s = "src/settings/settings.dll";
        try {
            fin = new FileInputStream(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int numberOfLines = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(fin));

        try {
            while((line = reader.readLine()) != null)
            {
                lineSettings[numberOfLines]  = line;
                numberOfLines++;
            }
        } catch (Exception e) {
            System.out.println(e + "Error in Class LanguageEngine: method readSettingsFile");
        }

        try {
            reader.close();
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineSettings[lineInSettings-1];
    }
}
