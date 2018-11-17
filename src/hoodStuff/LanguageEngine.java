package hoodStuff;

import java.io.*;

/**
 * Created $(DATE)
 */
public class LanguageEngine extends FileConnection
{
    /**
     *  Other methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public String setUpLanguage(int numberOfLine)
    {
        SettingsConnector settings = new SettingsConnector();
        return settings.giveText(numberOfLine);
    }

    public void changeLanguagePack(String selectedLanguage)
    {
        String[] lineSettings = new String[10];
        SettingsConnector settings = new SettingsConnector();

        /**
         * Wczytanie informacja z pliku settings.dll do buforu
         */
        lineSettings[0] = settings.readSettings(1);
        lineSettings[1] = settings.readSettings(2);
        lineSettings[2] = settings.readSettings(3);
        lineSettings[3] = settings.readSettings(4);
        lineSettings[4] = settings.readSettings(5);
        lineSettings[5] = settings.readSettings(6);
        lineSettings[6] = settings.readSettings(7);

        switch (selectedLanguage)
        {
            case "Polski":{
                lineSettings[1] = "LANGUAGE=PolLanguagePack.txt";
            }break;

            case "English":{
                lineSettings[1] = "LANGUAGE=EngLanguagePack.txt";
            }break;

            case "Deutsch":{
                lineSettings[1] = "LANGUAGE=DeuLanguagePack.txt";
            }
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
