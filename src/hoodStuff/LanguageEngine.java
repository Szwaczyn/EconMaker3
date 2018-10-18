package hoodStuff;

import java.io.*;

/**
 * Created $(DATE)
 */
public class LanguageEngine extends FileConnection
{

    public String setUpLanguage(int numberOfLine)
    {
        settingsConnector settings = new settingsConnector();
        return settings.giveText(numberOfLine);
    }

    public void changeLanguagePack(String selectedLanguage)
    {
        String[] lineSettings = new String[10];
        settingsConnector settings = new settingsConnector();

        /**
         * Wczytanie informacja z pliku settings.dll do buforu
         */
        lineSettings[0] = settings.read(1);
        lineSettings[1] = settings.read(2);
        lineSettings[2] = settings.read(3);
        lineSettings[3] = settings.read(4);
        lineSettings[4] = settings.read(5);
        lineSettings[5] = settings.read(6);
        lineSettings[6] = settings.read(7);

        switch (selectedLanguage)
        {
            case "Polski":{
                lineSettings[1] = "LANGUAGE=PolLanguagePack.txt";
            }break;

            case "English":{
                lineSettings[1] = "LANGUAGE=EngLanguagePack.txt";
            }break;
        }

        /**
         * Zapis już wyedytowanego buforu do pliku settings.dll
         */

        FileOutputStream fout = null;
        try {   /* Try open file */
            fout = new FileOutputStream("src/settings/settings.dll");
        } catch (Exception e) {
            System.out.println(e + "Error LanguageEngine: Method ChooseFileLanguage: Try open file");
        }

        DataOutputStream write = new DataOutputStream(fout);

        try {
            for (int i = 0; i < 9; i++) {
                write.writeBytes(lineSettings[i] + '\n');
            }
        } catch (Exception e) {
            System.out.println("Error Class LanguageEngine Method Change LanguagePack. Zapis do pliku");
        }
    }
}
