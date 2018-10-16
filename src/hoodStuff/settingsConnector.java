package hoodStuff;

import java.io.*;

/**
 * Created $(DATE)
 */
public class settingsConnector extends fileConnection
{
    public void changeSettings(int position, String Value)
    {
        String[] settingsContent = new String[10];

        // Save to tab content of settings dll

        for(int i = 0; i<= 9; i++)
        {
            settingsContent[i] = this.read(i+1);
        }

        settingsContent[position-1] = Value;

        saveSettings(settingsContent);
    }

    public String giveText(int numberOfLine)
    {
        String line = "";
        String returnLine = "Błąd tłumaczenia";
        FileInputStream fin = null;
        int actualLine = 1;

        fin = chooseFileLanguage(idOfLanguagePack());

        BufferedReader reader = new BufferedReader(new InputStreamReader(fin));

        try {
            while((line = reader.readLine()) != null)
            {
                if(actualLine == numberOfLine)
                {
                    returnLine = line;
                    break;
                }
                actualLine += 1;
            }
        } catch (IOException e) {
            System.out.println("Blad wejscia");
        }

        try {
            reader.close();
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnLine;
    }

    public int idOfLanguagePack()
    {
        /**
         * Language:
         * -1 - Błąd
         *  0 - Polski
         *  1 - Angielski
         */
        int language = 0;

        String lines = read(2);

        if(lines.equals("LANGUAGE=PolLanguagePack.txt"))
        {
            language = 0;
        }
        else if(lines.equals("LANGUAGE=EngLanguagePack.txt"))
        {
            language = 1;
        }
        else
        {
            language = -1;
        }

        return language;
    }

    private FileInputStream chooseFileLanguage(int language)
    {
        FileInputStream fin = null;
        try {
            if(language == 0)
            {
                fin = new FileInputStream("src/settings/languagePack/PolLanguagePack.lg");
            }
            else if(language == 1)
            {
                fin = new FileInputStream("src/settings/languagePack/EngLanguagePack.lg");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fin;
    }

    private void saveSettings(String[] newContent)
    {
        FileOutputStream fout = null;
        try {   /* Try open file */
            fout = new FileOutputStream("src/settings/settings.dll");
        } catch (Exception e) {
            //TODO Make system error
            System.out.println(e + "Error settingsConnector: Method saveSettings: Try open file");
        }

        DataOutputStream write = new DataOutputStream(fout);

        try {
            for (int i = 0; i < 9; i++) {
                write.writeBytes(newContent[i] + '\n');
            }
        } catch (Exception e) {
            //TODO System error
            System.out.println("Error settingsConnector: Method saveSettings: Try save file");
        }
    }
}
