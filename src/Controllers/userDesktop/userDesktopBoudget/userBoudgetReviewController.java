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
                setConditionOfBoudget(choiceBoxBoudget.getValue().toString());
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
        boolean result = false;

        UserFile file = new UserFileBuilder()
                .addFileName(this.userSession.getFileNameOfBoudget(choiceBoxBoudget.getValue().toString()))
                .addPath(this.userSession.getProfilPath())
                .build();

        if(file.isExist())
        {
            if(file.size() == 0) file.writeDown("0");
            result = true;
        }
        else
        {
            file.createFile();
            file.writeDown("0");
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

    String[] tabBoudget;
    int idOfBoudget;

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
