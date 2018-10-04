package Controllers.startMenuSettingsControllers;

import Controllers.MainController;
import Controllers.startMenuSettingsController;
import hoodStuff.LanguageEngine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.*;
import java.sql.*;


/**
 * Created $(DATE)
 */
public class databaseController
{
    private MainController mainControllerVar;

    /**
     * Zestaw dostępowy do bazy danych SQL
     */
    // TODO make user with restricted permissions to connect to database (Now root)
    private String userSQL = "root";
    private String passwordSQL = "";
    private String addressSQL = "";
            //"jdbc:mysql://localhost:3306/econmaker";

    LanguageEngine translation = new LanguageEngine();

    @FXML
    Button position_1 = new Button();
    @FXML
    Button testConnection = new Button();
    @FXML
    Button connect = new Button();
    @FXML
    Button saveButton = new Button();


    @FXML
    RadioButton local = new RadioButton();
    @FXML
    RadioButton sql = new RadioButton();

    @FXML
    TextField serverAdress = new TextField();
    @FXML
    TextField login = new TextField();
    @FXML
    PasswordField password = new PasswordField();
    @FXML
    TextField fileAddress = new TextField();

    @FXML
    Label labelSQLAddress = new Label();
    @FXML
    Label labelSQLLogin = new Label();
    @FXML
    Label labelSQLPassword = new Label();
    @FXML
    Label labelLocalNameFile = new Label();

    @FXML
    public void saveSettings()
    {

    }

    @FXML
    public void testOfConnectionSQL()
    {
        /**
         * Zasada przycisuku podłacz ma się opierać na wykonaniu czynnosci dla przycisku testuj polaczenie a nastepnie zapisz
         */

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(addressSQL,userSQL,passwordSQL);
        } catch (Exception e) {
            System.out.println(e + "Nie można połączyć się z bazą.");
        }
    }

    @FXML
    public void setSQLConnection(ActionEvent eventKey)
    {
        RadioButton source = (RadioButton) eventKey.getSource();

        switch(source.getId())
        {
            case "sql":{
                setChooseSQL(true);
            }break;
            case "local":{
                setChooseSQL(false);
            }break;
        }

    }

    @FXML
    public void returnToPreviousMenu()
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/layoutFXML/startMenuSettings.fxml"));
        try {
            Pane pane = loader.load();
            startMenuSettingsController target = loader.getController();
            target.setMainController(mainControllerVar);
            mainControllerVar.setScreen(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void initialize()
    {
        setUpLanguage();
        // TODO module to read settings faile and set proper position
        String[] settings = new String[10];
        settings = translation.readSettingsFile();
        isSettedLocal(settings[2]);
        /**
         * Wyłączenie możliwości edycji pól w niewybranym oknie
         */
        if(sql.isSelected())
        {
            setChooseSQL(true);
        }
        else if(local.isSelected())
        {
            setChooseSQL(false);
        }
    }

    private void readSettingsOfDatabase()
    {
        String[] lineSettings = new String[10];
        lineSettings = translation.readSettingsFile();


    }

    private void isSettedLocal(String line)
    {
        switch(line)
        {
            case "DATABASE=Local":{
                sql.setSelected(false);
                local.setSelected(true);
            }break;

            case "DATABASE=SQL":{
                sql.setSelected(true);
                local.setSelected(false);
            }break;
        }
    }

    private void setUpLanguage()
    {
        position_1.setText(translation.setUpLanguage(6));
        local.setText(translation.setUpLanguage(10));
        sql.setText(translation.setUpLanguage(11));
        labelSQLAddress.setText(translation.setUpLanguage(12));
        labelSQLLogin.setText(translation.setUpLanguage(13));
        labelSQLPassword.setText(translation.setUpLanguage(14));
        testConnection.setText(translation.setUpLanguage(15));
        connect.setText(translation.setUpLanguage(16));
        labelLocalNameFile.setText(translation.setUpLanguage(17));
        saveButton.setText(translation.setUpLanguage(9));
    }

    private void setChooseSQL(boolean sql)
    {
        if(sql)
        {
            serverAdress.setDisable(false);
            login.setDisable(false);
            password.setDisable(false);
            testConnection.setDisable(false);
            connect.setDisable(false);

            fileAddress.setDisable(true);
        }
        else if(!sql)
        {
            serverAdress.setDisable(true);
            login.setDisable(true);
            password.setDisable(true);
            testConnection.setDisable(true);
            connect.setDisable(true);

            fileAddress.setDisable(false);
        }
    }

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }
}
