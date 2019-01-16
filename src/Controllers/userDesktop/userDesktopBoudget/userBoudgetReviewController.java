package Controllers.userDesktop.userDesktopBoudget;

import Controllers.ClassController;
import Controllers.userDesktop.userBoudgetController;
import builder.ChangeWindowBuilder;
import builder.UserFileBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.LanguageEngine;
import hoodStuff.UserFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.ArrayList;


/**
 * Created $(DATE)
 */
public class userBoudgetReviewController extends ClassController
{
    public String[] boudget;

    @FXML
    public void actionReturn()
    {
        userBoudgetController target = new userBoudgetController();

        ChangeWindow win = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userBoudget.fxml")
                .addClassController(target)
                .addMainController(mainControllerVar)
                .addUserSession(this.userSession)
                .build();

        win.changeWindow();
    }

    /**
     *  Initialize controllers - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    LanguageEngine translation = new LanguageEngine();

    public void initialize()
    {
        setUpLanguage();
        clearAlert();

        if(this.userSession != null)
        {
            fillChoiceBoxBoudgetOfExpenditiure();
            if(!choiceBoxBoudget.getItems().isEmpty()){
                choiceBoxBoudget.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> setConditionOfBoudget(newValue.toString()));
                clearPosition();
                setConditionOfBoudget(choiceBoxBoudget.getValue().toString());

                ArrayList<Label> titleList = new ArrayList<Label>();
                titleList.add(title1);
                titleList.add(title2);
                titleList.add(title3);

                ArrayList<Label> valueList = new ArrayList<Label>();
                valueList.add(value1);
                valueList.add(value2);
                valueList.add(value3);

                ArrayList<Label> dateList = new ArrayList<Label>();
                dateList.add(date1);
                dateList.add(date2);
                dateList.add(date3);
            }
            else
            {
                setAlert(translation.setUpLanguage(113));
                labelNameOfBoudget.setVisible(false);
            }
        }
    }

    private boolean isLogFileEmpty()
    {
        boolean result;

        UserFile file = new UserFileBuilder()
                .addFileName(this.userSession.getFileNameOfBoudget(choiceBoxBoudget.getValue().toString()))
                .addPath(this.userSession.getProfilPath())
                .build();

        if(file.isExist())
        {
            if(file.size() == 0) {
                file.writeDown("0");
                result = true;
            }
            else if(file.size() == 1) {
                result = true;
            }
            else result = false;
        }
        else
        {
            file.createFile();
            file.writeDown("0");
            result = true;
        }

        return result;
    }

    private void setConditionOfBoudget(String setBoudget)
    {
        idOfBoudget = getIdOfPosition(setBoudget, tabBoudget);
        if(!choiceBoxBoudget.getItems().isEmpty()) labelValueOfBoudget.setText(translation.setUpLanguage(99) + tabBoudget[idOfBoudget + 1] +
                " zł");
        labelNameOfBoudget.setText(choiceBoxBoudget.getValue().toString());
        isLogFileEmpty();

        if(isLogFileEmpty()) {
            clearPosition();
            date1.setText(translation.setUpLanguage(114));
        }
        else{
            clearPosition();
            setLogOfBoudgetCondition();
        }
    }

    private void setLogOfBoudgetCondition()
    {
        UserFile file = new UserFileBuilder()
                .addPath(this.userSession.getProfilPath())
                .addFileName(this.userSession.getFileNameOfBoudget(choiceBoxBoudget.getValue().toString()))
                .build();

        amountOfPosition = file.size() - 1;

        loadUpPosition(1);

    }

    private void loadUpPosition(int positionForDisplay)
    {
        UserFile file = new UserFileBuilder()
                .addPath(this.userSession.getProfilPath())
                .addFileName(this.userSession.getFileNameOfBoudget(choiceBoxBoudget.getValue().toString()))
                .build();

        if(file.readLine(132) == "")
        {
            System.out.println("ok");
            System.out.println("otroloolo");
        }
        else
        {
            System.out.println("nie ok");
        }
    }

    private int getIdOfPosition(String position, String[] tab)
    {
        int size = tab.length;
        int result = -1;

        for(int i = 0; i <= size - 1; i++)
        {
            if(tab[i].trim().equals(position.trim()))
            {
                result = i;
            }
        }

        return result;
    }

    private void fillChoiceBoxBoudgetOfExpenditiure()
    {
        choiceBoxBoudget.getItems().clear();
        String[] items = lookForExistBoudget();
        int sizeOfItem = items.length;

        for(int i = 0; i <= sizeOfItem - 1; i += 2)
        {
            choiceBoxBoudget.getItems().add(items[i]);
        }

        choiceBoxBoudget.getSelectionModel().selectFirst();
    }

    private String[] lookForExistBoudget()
    {
        UserFile file = new UserFileBuilder()
                .addFileName(userSession.getFileNameBoudget())
                .addPath(userSession.getProfilePath())
                .build();

        if(!file.isExist())
        {
            file.createFile();
        }

        int size = file.size();

        String[] positionInMenu = new String[size];
        int iterator = 0;

        tabBoudget = positionInMenu;

        for(int i = 1; i <= size; i ++)
        {
            positionInMenu[iterator] = file.readLine(i);
            iterator += 1;
        }

        return positionInMenu;
    }

    private void setUpLanguage()
    {
        buttonReturn.setText(translation.setUpLanguage(6));
        buttonShowBoudget.setText(translation.setUpLanguage(85));

        labelChoiceBoudget.setText(translation.setUpLanguage(86));
        labelNameOfBoudget.setText(translation.setUpLanguage(87));
        labelValueOfBoudget.setText("");
    }

    private void setAlert(String alert)
    {
        labelAlert.setText(alert);
        labelAlert.setVisible(true);
    }

    private void clearAlert()
    {
        labelAlert.setText("");
        labelAlert.setVisible(false);
    }

    private void clearPosition()
    {
        title1.setText("");
        title2.setText("");
        title3.setText("");

        date1.setText("");
        date2.setText("");
        date3.setText("");

        value1.setText("");
        value2.setText("");
        value3.setText("");

    }

    String[] tabBoudget;
    int idOfBoudget;
    int position;
    int amountOfPosition;

    @FXML
    Button buttonReturn = new Button();
    @FXML
    Button buttonShowBoudget = new Button();

    @FXML
    Label labelChoiceBoudget = new Label();
    @FXML
    Label labelNameOfBoudget = new Label();
    @FXML
    Label labelValueOfBoudget = new Label();
    @FXML
    Label labelAlert = new Label();

    @FXML
    Label title1 = new Label();
    @FXML
    Label title2 = new Label();
    @FXML
    Label title3 = new Label();
    @FXML
    Label date1 = new Label();
    @FXML
    Label date2 = new Label();
    @FXML
    Label date3 = new Label();
    @FXML
    Label value1 = new Label();
    @FXML
    Label value2 = new Label();
    @FXML
    Label value3 = new Label();

    @FXML
    ChoiceBox choiceBoxBoudget = new ChoiceBox();

    public userBoudgetReviewController()
    {
    }
    public userBoudgetReviewController(String[] boudget)
    {
        this.boudget = boudget;
        System.out.println(this);
    }
}
