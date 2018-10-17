package Controllers.startMenuSettingsControllers;

import Controllers.MainController;
import Controllers.startMenuSettingsController;
import hoodStuff.LanguageEngine;
import hoodStuff.settingsConnector;
import hoodStuff.sqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import jdk.jfr.SettingControl;

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
    private String addressSQL = "jdbc:mysql://127.1.1.1:3306/econmaker";

    sqlConnection sqldb = null;

    LanguageEngine translation = new LanguageEngine();
    settingsConnector set = new settingsConnector();

    @FXML
    Button position_1 = new Button();
    @FXML
    Button testConnection = new Button();
    @FXML
    Button connect = new Button();
    @FXML
    Button saveButton = new Button();
    @FXML
    Button testConnectionLocalFile = new Button();

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
    Label notice = new Label();

    @FXML
    public void saveSettings()
    {
        settingsConnector test = new settingsConnector();
        //TODO save to file chooice
        if(sql.isSelected())
        {
            set.changeSettings(3,"DATABASE=SQL");
        }
        else
        {
            set.changeSettings(3,"DATABASE=Local");
        }
    }

    //TODO For tests method make separate thread

    @FXML
    public void testOfConnectionLocalFile()
    {
        String fileName = "";
        try{
            fileName = this.fileAddress.getText();
        } catch (Exception e) {
            notice.setText(translation.setUpLanguage(20));
            saveButton.setDisable(true);
        }
        if(fileName.equals(""))
        {
            notice.setText(translation.setUpLanguage(20));
            saveButton.setDisable(true);
        }
        else
        {
            notice.setText(translation.setUpLanguage(21));
            saveButton.setDisable(false);
        }
        notice.setVisible(true);
    }

    @FXML
    public void testOfConnectionSQL()
    {
        /**
         * Zasada przycisuku podłacz ma się opierać na wykonaniu czynnosci dla przycisku testuj polaczenie a nastepnie zapisz
         */

        if(sqldb.checkConnection())
        {
            notice.setText(translation.setUpLanguage(18));
        }
        else
        {
            notice.setText(translation.setUpLanguage(19));
        }
        notice.setVisible(true);
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
        readSettingsOfDatabase();
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
        sqldb =  new sqlConnection();
        setUpLanguage();
        // Line read settings database
        String databaseInSettings = set.read(3);
        isSettedLocal(databaseInSettings);
        /**
         * Wyłączenie możliwości edycji pól w niewybranym oknie
         */
        notice.setVisible(false);
        if(sql.isSelected())
        {
            setChooseSQL(true);
        }
        else if(local.isSelected())
        {
            setChooseSQL(false);
        }
        readSettingsOfDatabase();
    }

    private void readSettingsOfDatabase()
    {
        if(sql.isSelected())
        {
            password.setText("scisletajne");
            password.setDisable(true);
            login.setText(userSQL);
            login.setDisable(true);

            /**
             * Cleaning
             */

            fileAddress.setText("");
        }
        else
        {
            fileAddress.setText("econmaker.user");
            fileAddress.setDisable(true);

            /**
             * Cleaning
             */
            password.setText("");
            login.setText("");
        }
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
        testConnectionLocalFile.setText(translation.setUpLanguage(15));
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
            testConnectionLocalFile.setDisable(true);
        }
        else if(!sql)
        {
            serverAdress.setDisable(true);
            login.setDisable(true);
            password.setDisable(true);
            testConnection.setDisable(true);
            connect.setDisable(true);

            fileAddress.setDisable(false);
            testConnectionLocalFile.setDisable(false);
        }
    }

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }
}
