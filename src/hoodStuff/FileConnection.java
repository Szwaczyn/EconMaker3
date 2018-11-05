package hoodStuff;

import java.io.*;
import java.nio.Buffer;

/**
 * Created $(DATE)
 *
 *  setFileName
 *      Set the name of file, have to give with extension
 *
 *  readFile(int line, String file)
 *      Get the line which have number in variable line
 *      The variable file get the path of file with method setFileStreaming(String)
 *
 *  setFileStreaming(String file)
 *      Get the source of file path.
 *          user - you get the econmaker.usr
 *          settings - you get the path of settings file
 *          default - return string "wrong password"
 *
 *  writeDownThisFile(String line)
 *      write in file wich was set in this object
 *      the line will be write at down of the file
 */
//TODO Extend this class to thread
public class FileConnection
{
    private String fileName;
    private String path;

    /**
     *  Other methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

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

    public void writeDownThisFile(String line)
    {
        line += " \n";
        try {
            FileWriter filelocal = new FileWriter(this.path + this.fileName, true);
            BufferedWriter writer = new BufferedWriter(filelocal);
            writer.write(line);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Have to be set the file name of this object
    public String readThisFile(int line)
    {
        line--;
        String bufor = "";
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(this.path + this.fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            bufor = readLine(line, reader);
        } catch (Exception e) {
            //TODO Make error system
            System.out.println("nie można wczytać modułu: Class FileConnection: Method read : " + e + this.fileName);
        }

        return bufor;
    }

    public int amountOfLineInThisFile()
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

    /**
     *  Seters Method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void setFileName(String fileName)
    {
        this.fileName = setFileStreaming(fileName);
    }

    public void setFileName(String path, String fileName)
    {
        this.fileName = fileName;
        this.path = path;
    }

    /**
     *  Private Method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private FileInputStream getThisFileInputStreamReader()
    {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(this.path + this.fileName);
        } catch (Exception e) {
            //TODO System error
            System.out.println("Błąd podczas liczenia linijek");
        }
        return fin;
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

        try {
            readFile.close();
        } catch (IOException e) {
            // TODO SYstem error
            System.out.println("Class: FileConnection: Method: ReadFIle try close file wrong");
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
                path = "src/settings/econmaker.user";
            }break;

            default:
            {
                path = "Wrong path";
            }
        }

        return path;
    }

    /**
     *  Constructors - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public FileConnection() {}

    public FileConnection(String fileName)
    {
        this.fileName = fileName;
    }

    public FileConnection(String fileName, String path)
    {
        this.fileName = fileName;
        this.path = path;
    }
}
