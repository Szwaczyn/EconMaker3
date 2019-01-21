package Controllers.userDesktop.userDesktopBoudget;

import Controllers.ClassController;
import Controllers.userDesktop.userBoudgetController;
import builder.ChangeWindowBuilder;
import builder.UserFileBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.LanguageEngine;
import hoodStuff.SortingFile;
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

    @FXML
    public void actionUp()
    {
        if(1 < positionStart)
        {

            if(positionStart != 1){
                positionStart -= 1;
                positionStop -= 1;
            }

            setLogOfBoudgetCondition();
        }
    }

    @FXML
    public void actionDown()
    {
        int lineInFile = getAmountOfLog();
        if(lineInFile > positionStop)
        {

            if(positionStop!= getAmountOfLog()){
                positionStart += 1;
                positionStop += 1;
            }

            setLogOfBoudgetCondition();
        }
    }

    /**
     *  Initialize controllers - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    LanguageEngine translation = new LanguageEngine();

    public void initialize()
    {
        setUpLanguage();
        clearAlert();

        titleList = new ArrayList<Label>();
        titleList.add(title1);
        titleList.add(title2);
        titleList.add(title3);

        valueList = new ArrayList<Label>();
        valueList.add(value1);
        valueList.add(value2);
        valueList.add(value3);

        dateList = new ArrayList<Label>();
        dateList.add(date1);
        dateList.add(date2);
        dateList.add(date3);


        if(this.userSession != null)
        {
            fillChoiceBoxBoudgetOfExpenditiure();
            if(!choiceBoxBoudget.getItems().isEmpty()){
                choiceBoxBoudget.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> setConditionOfBoudget(newValue.toString()));

                clearPosition(0);
                clearPosition(1);
                clearPosition(2);

                positionStart = 0;
                if(getAmountOfLog() >= 1) positionStart = 1;
                positionStop = getAmountOfLog();
                if(getAmountOfLog() > 3) positionStop = 3;

                setConditionOfBoudget(choiceBoxBoudget.getValue().toString());
            }
            else
            {
                setAlert(translation.setUpLanguage(113));
                labelNameOfBoudget.setVisible(false);
            }
        }
    }

    private int getAmountOfLog()
    {
        UserFile file = new UserFileBuilder()
                .addPath(this.userSession.getProfilPath())
                .addFileName(this.userSession.getFileNameOfBoudget(choiceBoxBoudget.getValue().toString()))
                .build();

        return (file.size() -1) / 3;
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
            clearPosition(0);
            clearPosition(1);
            clearPosition(2);
            date1.setText(translation.setUpLanguage(114));
        }
        else{
            clearPosition(0);
            clearPosition(1);
            clearPosition(2);
            positionStart = 1;
            positionStop = getAmountOfLog();
            if(getAmountOfLog() > 3) positionStop = 3;
            setLogOfBoudgetCondition();
        }
    }

    private void setLogOfBoudgetCondition()
    {
        UserFile file = new UserFileBuilder()
                .addPath(this.userSession.getProfilPath())
                .addFileName(this.userSession.getFileNameOfBoudget(choiceBoxBoudget.getValue().toString()))
                .build();

        amountOfPosition = (file.size() - 1) / 3;
        int positionOnDisplayList = 0;

        for(int i = positionStart; i <= positionStop; i++)
        {
            loadUpPosition(i, positionOnDisplayList);
            positionOnDisplayList += 1;
        }


    }

    private void loadUpPosition(int positionForDisplay, int positionOnDisplayList)
    {
        UserFile file = new UserFileBuilder()
                .addPath(this.userSession.getProfilPath())
                .addFileName(this.userSession.getFileNameOfBoudget(choiceBoxBoudget.getValue().toString()))
                .build();

        int position = (positionForDisplay - 1) * 3 + 1;

        titleList.get(positionOnDisplayList).setText(file.readLine(position + 1));
        valueList.get(positionOnDisplayList).setText("-" + file.readLine(position + 2) + " zl");
        dateList.get(positionOnDisplayList).setText(file.readLine(position + 3));
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

    private void clearPosition(int idOfPosition)
    {
        titleList.get(idOfPosition).setText("");
        dateList.get(idOfPosition).setText("");
        valueList.get(idOfPosition).setText("");
    }

    String[] tabBoudget;
    int idOfBoudget;
    int positionStart;
    int positionStop;
    int amountOfPosition;

    ArrayList<Label> titleList;
    ArrayList<Label> valueList;
    ArrayList<Label> dateList;

    @FXML
    Button buttonReturn = new Button();
    @FXML
    Button buttonUp = new Button();
    @FXML
    Button buttonDown = new Button();

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
