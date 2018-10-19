package hoodStuff;

import java.io.*;
import java.nio.Buffer;

/**
 * Created $(DATE)
 */
//TODO Extend this class to thread
public class FileConnection
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

    public void setFileName(String fileName)
    {
        this.fileName = setFileStreaming(fileName);
    }

    /**
     *
     * @param line
     * @param file
     * @return
     *
     * Wybranie języka
     * settings - zostanie wybrany plik settings.dll
     * user     - Zostanie wybrany plik z użytkownikami econmaker.usr
     */
    public String readFile(int line, String file)
    {
        String buforLine = "Empty";

        file = setFileStreaming(file); // Method setFileStreaming change name to path of file

        FileInputStream fin = null;

        try {
            fin = new FileInputStream(file);
        } catch (Exception e) {
            //TODO Make System Error
            System.out.println(e + " FileConnection: Method readFile: Open stream");
        }

        BufferedReader readFile = new BufferedReader(new InputStreamReader(fin));

        return readLine(line, readFile);
    }


    /**
     *
     * @param line          - Wiersz w pliku
     * @param readFile      - Obiekt BuferedReader ze skojarzonym już plikiem
     * @return
     *
     * Można używać do innych funkcji jednak należy mieć na uwadzę aby bufferedReader miał skojarzony plik
     */
    private String readLine(int line, BufferedReader readFile)
    {
        int numberOfActualLine = 0;
        String buforLine;
        String result = new String("");
        try {
            while((buforLine = readFile.readLine()) != null)
            {
                if(numberOfActualLine == line)
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

    private String setFileStreaming(String file)
    {
        String path;
        switch(file)
        {
            case "settings":
            {
                path = "src/settings/settings.dll";
            } break;

            case "user":
            {
                path = "src/settings/econmaker.usr";
            }break;

            default:
            {
                path = "Wrong path";
            }
        }

        return path;
    }
}
