package Controllers.userDesktop;

import Controllers.ClassController;
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
public class userReviewController extends ClassController
{
    @FXML
    public void actionReturn()
    {
        userDesktopController target = new userDesktopController();

        ChangeWindow window = new ChangeWindowBuilder()
                .addPath("/layoutFXML/userDesktop/userDesktop.fxml")
                .addMainController(getController())
                .addClassController(target)
                .addUserSession(this.userSession)
                .build();

        window.changeWindow();
    }

    @FXML
    public void actionUp(){}

    @FXML
    public void actionDown() {}

    /**
     *  Initialize controllers - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

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
            if(!choiceBoxAccount.getItems().isEmpty()){
                choiceBoxAccount.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> setConditionOfAccount(newValue.toString()));

                clearPosition(0);
                clearPosition(1);
                clearPosition(2);

                positionStart = 0;
                if(getAmountOfLog() >= 1) positionStart = 1;
                positionStop = getAmountOfLog();
                if(getAmountOfLog() > 3) positionStop = 3;

                setConditionOfAccount(choiceBoxAccount.getValue().toString());
            }
            else
            {
                setAlert(translation.setUpLanguage(113));
                labelNameOfAccount.setVisible(false);
            }
        }
    }

    private void setConditionOfAccount(String setBoudget)
    {
        idOfBoudget = getIdOfPosition(setBoudget, tabAccount);
        if(!choiceBoxAccount.getItems().isEmpty()) labelValueOfAccount.setText(translation.setUpLanguage(99) + tabAccount[idOfBoudget + 1] +
                " zł");
        labelNameOfAccount.setText(choiceBoxAccount.getValue().toString());
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
                .addFileName(this.userSession.getFileNameOfBoudget(choiceBoxAccount.getValue().toString()))
                .build();

        sortFile(file);

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
                .addFileName(this.userSession.getFileNameOfBoudget(choiceBoxAccount.getValue().toString()))
                .build();

        int position = (positionForDisplay - 1) * 3 + 1;

        titleList.get(positionOnDisplayList).setText(file.readLine(position + 1));
        valueList.get(positionOnDisplayList).setText("-" + file.readLine(position + 2) + " zl");
        dateList.get(positionOnDisplayList).setText(file.readLine(position + 3));
    }

    private void sortFile(UserFile file)
    {
        String[] toOrderBufor = new String[file.size()];

        for(int i = 0; i <= file.size() - 1; i++)
        {
            toOrderBufor[i] = file.readLine(i + 1);
        }

        try{
            toOrderBufor = SortingFile.getSortedTabOfDate(toOrderBufor, 1, 3, 3);
        } catch(Exception e) {
            System.out.println(e);
        }

        file.deleteFile();

        for(int i = 0; i <= toOrderBufor.length - 1; i++)
        {
            file.writeDown(toOrderBufor[i]);
        }
    }

    private void fillChoiceBoxBoudgetOfExpenditiure()
    {
        choiceBoxAccount.getItems().clear();
        String[] items = lookForExistAccount();
        int sizeOfItem = items.length;

        for(int i = 0; i <= sizeOfItem - 1; i += 2)
        {
            choiceBoxAccount.getItems().add(items[i]);
        }

        choiceBoxAccount.getSelectionModel().selectFirst();
    }

    private String[] lookForExistAccount()
    {
        UserFile file = new UserFileBuilder()
                .addFileName(userSession.getLogin() + ".dll")
                .addPath(userSession.getProfilePath())
                .build();

        if(!file.isExist())
        {
            file.createFile();
        }

        int size = file.size();
        size -= 2;

        String[] positionInMenu = new String[size];
        int iterator = 0;

        tabAccount = positionInMenu;

        for(int i = 3; i <= size + 2; i ++)
        {
            positionInMenu[iterator] = file.readLine(i);
            iterator += 1;
        }

        return positionInMenu;
    }

    private void setUpLanguage()
    {
        labelChoiceAccount.setText(translation.setUpLanguage(115));
        labelNameOfAccount.setText(translation.setUpLanguage(116));
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

    private boolean isLogFileEmpty()
    {
        boolean result;

        UserFile file = new UserFileBuilder()
                .addFileName(this.userSession.getFileNameAccount(choiceBoxAccount.getValue().toString()))
                .addPath(this.userSession.getProfilPath())
                .build();

        if(file.isExist())
        {
            if(file.size() == 0) {
                result = true;
            }
            else result = false;
        }
        else
        {
            file.createFile();
            result = true;
        }

        return result;
    }

    private void clearAlert() { labelAlert.setText(""); labelAlert.setVisible(false); }

    private void setAlert(String text) { labelAlert.setText(text); labelAlert.setVisible(true); }

    private void clearPosition(int idOfPosition)
    {
        titleList.get(idOfPosition).setText("");
        dateList.get(idOfPosition).setText("");
        valueList.get(idOfPosition).setText("");
    }

    private int getAmountOfLog()
    {
        UserFile file = new UserFileBuilder()
                .addPath(this.userSession.getProfilPath())
                .addFileName(this.userSession.getFileNameAccount(choiceBoxAccount.getValue().toString()))
                .build();

        return (file.size() -1) / 3;
    }

    LanguageEngine translation = new LanguageEngine();
    String[] tabAccount;

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
    Label labelChoiceAccount = new Label();
    @FXML
    Label labelNameOfAccount = new Label();
    @FXML
    Label labelValueOfAccount = new Label();
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
    ChoiceBox choiceBoxAccount = new ChoiceBox();
}
