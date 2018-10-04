package hoodStuff;

import java.io.*;

/**
 * Created $(DATE)
 */
public class LanguageEngine
{

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

    public String setUpLanguage(int numberOfLine)
    {
        String line = "";
        String returnLine = "Błąd tłumaczenia";
        FileInputStream fin = null;
        int acctualyLine = 1;

        fin = chooseFileLanguage(checkTheLanguage());

        BufferedReader reader = new BufferedReader(new InputStreamReader(fin));

        try {
            while((line = reader.readLine()) != null)
            {
                if(acctualyLine == numberOfLine)
                {
                    returnLine = line;
                    break;
                }
                acctualyLine += 1;
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

    public void changeLanguagePack(String selectedLanguage)
    {
        String[] lineSettings = new String[10];

        /**
         * Wczytanie informacja z pliku settings.dll do buforu
         */
        lineSettings = readSettingsFile();

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

    public String[] readSettingsFile()
    {
        String line = "";
        String[] lineSettings = new String[10];
        FileInputStream fin = null;

        try {
            fin = new FileInputStream("src/settings/settings.dll");
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
        return lineSettings;
    }

    /**
     * Zwraca wartośc odpowiednią dla ustawionego języka w pliku settings.dll
     *-1 - Błąd
     * 0 - Polski
     * 1 - Angielski
     */
    private int checkTheLanguage()
    {
        /**
         * Language:
         * -1 - Błąd
         *  0 - Polski
         *  1 - Angielski
         */
        int language = 0;

        String line = "";
        FileInputStream fin = null;

        try {
            fin = new FileInputStream("src/settings/settings.dll");
        } catch (FileNotFoundException e) {
            System.out.println("Nie odnaleźono pliku settings.dll");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(fin));

        try {
            while((line = reader.readLine()) != null)
            {
                if(line.equals("LANGUAGE=PolLanguagePack.txt"))
                {
                    language = 0;
                }
                else if(line.equals("LANGUAGE=EngLanguagePack.txt"))
                {
                    language = 1;
                }
            }
        } catch (IOException e) {
            language = -1;
        }

        try {
            reader.close();
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return language;
    }
}
