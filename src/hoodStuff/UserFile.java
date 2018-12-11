package hoodStuff;

import builder.UserFileBuilder;

import java.io.*;

/**
 * Created $(DATE)
 */
public class UserFile
{
    private String fileName;
    private String path;

    /**
     *  Public method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void writeDown(String line){

        line += " \n";
        try {
            FileWriter filelocal = new FileWriter(this.path + this.fileName, true);
            BufferedWriter writer = new BufferedWriter(filelocal);
            writer.write(line);
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void createFile(){
        File file = new File(path + fileName);

        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
                System.out.println("Nie mozna utworzyć pliku");
            }
        }
    }

    public void show()
    {
        System.out.println(fileName + "  " + path);
    }

    public String readLine(int numberOfline)
    {
        numberOfline--;
        String bufor = "";
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(this.path + this.fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            bufor = getLine(numberOfline, reader);
        } catch (Exception e) {
            //TODO Make error system
            System.out.println("nie można wczytać modułu: Class FileConnection: Method readSettings : " + e + this.fileName);
        }

        return bufor.trim();
    }

    public int size()
    {
        BufferedReader readFile = getReader();
        int numberOfActualLine = 0;
        try {
            while((readFile.readLine()) != null)
            {
                numberOfActualLine++;
            }
        } catch (Exception e) {
            //TODO Make System Error
            System.out.println(e + " Main Class: FileConnection: readFile: Try readSettings Line");
        }

        try {
            readFile.close();
        } catch (IOException e) {
            // TODO SYstem error
            System.out.println("Class: FileConnection: Method: ReadFIle try close file wrong");
        }

        return numberOfActualLine;
    }

    public void removeLine(int numberOfLine)
    {
        int size = size();

        String[] bufor = new String[size];
        for(int i = 0; i <= size - 1; i += 1)
        {
            bufor[i] = readLine(i + 1);
        }

        File overWriteFile = new File(this.path + this.fileName);
        overWriteFile.delete();
        createFile();

        for(int i = 0; i <= size - 1; i += 1)
        {
            if((i + 1) != numberOfLine)
            {
                writeDown(bufor[i]);
            }
        }
    }

    public int searchLine(String word)
    {
        int size = size();
        int result = -1;

        for(int i = 1; i <= size; i += 1)
        {
            if(readLine(i).trim().toLowerCase().equals(word.trim().toLowerCase()))
            {
                result = i;
            }
        }

        return result;
    }


    /**
     *  Seters method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public void setPath(String path) { this.path = path; }

    /**
     *  Private method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private String getLine(int line, BufferedReader readFile)
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
            System.out.println(e + " Main Class: FileConnection: readFile: Try readSettings Line");
        }

        try {
            readFile.close();
        } catch (IOException e) {
            // TODO SYstem error
            System.out.println("Class: FileConnection: Method: ReadFIle try close file wrong");
        }

        return result;
    }

    private BufferedReader getReader()
    {
        BufferedReader reader = null;
        try {
            FileInputStream fin = new FileInputStream(this.path + this.fileName);
            reader = new BufferedReader(new InputStreamReader(fin));
        } catch (Exception e) {
            //TODO Make error system
            System.out.println("nie można wczytać modułu: Class FileConnection: Method readSettings : " + e + this.fileName);
        }

        return reader;
    }

    private void overWrite(String line, int nr){}

    /**
     *   Constructors - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public UserFile(UserFileBuilder writerBuilder)
    {
        this.fileName = writerBuilder.getFileName();
        this.path = writerBuilder.getPath();
    }
}
