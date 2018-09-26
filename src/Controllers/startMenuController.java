package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.*;

public class startMenuController
{

    private MainController mainControllerVar;

    @FXML
    ImageView menuBackground = new ImageView();

    @FXML
    Button position_1 = new Button();
    @FXML
    Button position_2 = new Button();
    @FXML
    Button position_3 = new Button();
    @FXML
    Button position_4 = new Button();
    @FXML
    Button position_5 = new Button();
    @FXML
    Button position_6 = new Button();

    @FXML
    VBox menuBox = new VBox();

    @FXML
    public void showMenu()
    {
        if(position_6.isVisible() == true)
        {
            menuBox.setLayoutX(0);
            position_6.setVisible(false);
        }
        else
        {
            menuBox.setLayoutX(-200);
            position_1.setText("=");
            position_6.setVisible(true);
        }
    }

    @FXML
    public void chooseOfMenu(ActionEvent event)
    {
        Button source = (Button) event.getSource();

        switch (source.getId())
        {
            /**
             * Pozycja "Ustawienia"
             */
            case "position_4":{
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layoutFXML/startMenuSettings.fxml"));
                try {
                    Pane pane = loader.load();
                    startMenuSettingsController target = loader.getController();
                    target.setMainController(mainControllerVar);
                    mainControllerVar.setScreen(pane);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }break;

            case "position_5":{
                econmakerShutDown(0);
            }break;
        }
    }

    private void econmakerShutDown(int code)
    {
        System.exit(code);
    }

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }


    public void initialize()
    {
        int language;
        language = checkTheLanguage();
        setUpLanguage(language);
    }

    private FileInputStream chooseFileLanguage(int language)
    {
        FileInputStream fin = null;
        try {
            if(language == 0)
            {
                fin = new FileInputStream("C:/Users/mszwaczy/Dropbox/Projects JAVA/Econ Maker 3/src/settings/PolLanguagePack.lg");
            }
            else if(language == 1)
            {
                fin = new FileInputStream("C:/Users/mszwaczy/Dropbox/Projects JAVA/Econ Maker 3/src/settings/EngLanguagePack.lg");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fin;
    }

    public void setUpLanguage(int language)
    {
        String line = "";
        FileInputStream fin = null;

        fin = chooseFileLanguage(language);

        BufferedReader reader = new BufferedReader(new InputStreamReader(fin));

        int i = 0;

        try {
            while((line = reader.readLine()) != null)
            {
                switch (i)
                {
                    case 0:{
                        position_1.setText(line);
                    }break;

                    case 1:{
                        position_2.setText(line);
                    }break;

                    case 2:{
                        position_3.setText(line);
                    }break;

                    case 3:{
                        position_4.setText(line);
                    }break;

                    case 4:{
                        position_5.setText(line);
                    }break;
                }
                i++;
            }
        } catch (IOException e) {
            System.out.println("Blad wejscia");
        }
    }

    /**
     * Zwraca wartośc odpowiednią dla ustawionego języka w pliku settings.dll
     * 0 - Polski
     * 1 - Angielski
     */
    private int checkTheLanguage()
    {
        /**
         * Language:
         *  0 - Polski
         *  1 - Angielski
         */
        int language = 0;

        String line = "";
        FileInputStream fin = null;

        try {
            /**
             * !! WAŻNE !!
             * Zmienić ścieżke do pliku na adres względny
             */
            fin = new FileInputStream("C:/Users/mszwaczy/Dropbox/Projects JAVA/Econ Maker 3/src/settings/settings.dll");
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
            System.out.println("Blad wejscia");
        }

        return language;
    }
}
