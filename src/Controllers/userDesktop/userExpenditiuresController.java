package Controllers.userDesktop;

import Controllers.ClassController;
import builder.ChangeWindowBuilder;
import builder.UserFileBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.LanguageEngine;
import hoodStuff.UserFile;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created $(DATE)
 */
public class userExpenditiuresController extends ClassController
{
    /**
     *  Action - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

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
    public void setCategory()
    {
        setDisableSectionOfCategory( !checkBoxCategoryOfExpenditiure.isSelected() );
    }

    @FXML
    public void setBoudget()
    {
        setDisableSectionOfBoudget( !checkBoxBoudgetOfExpenditiure.isSelected() );
    }

    @FXML
    public void cleatTextField()
    {
        textNameOfExpenditiure.setText("");
        textValuieOfExpenditiure.setText("");
        checkBoxCategoryOfExpenditiure.setSelected(false);
        setCategory();
        checkBoxBoudgetOfExpenditiure.setSelected(false);
        setBoudget();
        clearAlert();
    }

    /**
     *  Initialize - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        setUpLanguage();

        if(this.userSession != null)
        {
            try {
                choiceBoxAccount.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> setCondition(newValue.toString()));
                setMenuAccount();
                choiceBoxAccount.getSelectionModel().selectFirst();
                setCondition(choiceBoxAccount.getValue().toString());
            } catch (Exception e) {
                setAlert(translation.setUpLanguage(104));
            }

            setDisableSectionOfExpenditiure(choiceBoxAccount.getItems().isEmpty());
        }

        setBoudget();
        setCategory();
    }

    private void setDisableSectionOfExpenditiure(boolean set)
    {
        textNameOfExpenditiure.setDisable(set);
        textValuieOfExpenditiure.setDisable(set);
        checkBoxCategoryOfExpenditiure.setDisable(set);
        choiceBoxCategoryOfExpenditiure.setDisable(set);
        datePickerOfExpenditiure.setDisable(set);
        checkBoxBoudgetOfExpenditiure.setDisable(set);
        choiceBoxSetBoudgetOfExpenditiure.setDisable(set);
        buttonSaveExpenditiure.setDisable(set);
        buttonClearExpenditiure.setDisable(set);
    }

    private void setDisableSectionOfCategory(boolean set)
    {
        choiceBoxCategoryOfExpenditiure.setDisable(set);
    }

    private void setDisableSectionOfBoudget(boolean set) { choiceBoxSetBoudgetOfExpenditiure.setDisable(set); }

    private void setUpLanguage()
    {
        buttonReturn.setText(translation.setUpLanguage(6));
        buttonSaveExpenditiure.setText(translation.setUpLanguage(9));
        buttonClearExpenditiure.setText(translation.setUpLanguage(27));
        checkBoxCategoryOfExpenditiure.setText(translation.setUpLanguage(88));
        checkBoxBoudgetOfExpenditiure.setText(translation.setUpLanguage(42));
        labelSetBoudgetOfExpenditiure.setText(translation.setUpLanguage(110));
        labelAccount.setText(translation.setUpLanguage(65));
        labelNameOfExpenditiure.setText(translation.setUpLanguage(107));
        labelValueOfExpenditiure.setText(translation.setUpLanguage(108));
        labelDataOfExpenditiure.setText(translation.setUpLanguage(109));
        labelCurrentCondition.setText(translation.setUpLanguage(99));

        labelAlert.setText("");
        labelAlert.setVisible(false);
    }

    private void setCondition(String setAccount)
    {
        idOfAccount = getIdOfPosition(setAccount, tab);
        labelCondition.setText(tab[idOfAccount + 1] + " zł");
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

    private void setMenuAccount()
    {
        choiceBoxAccount.getItems().clear();
        String[] items = lookForExistAccount();
        int sizeOfItem = items.length;

        for(int i = 0; i <= sizeOfItem - 1; i += 2)
        {
            choiceBoxAccount.getItems().add(items[i]);
        }
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

        tab = positionInMenu;

        for(int i = 3; i <= size + 2; i ++)
        {
            positionInMenu[iterator] = file.readLine(i);
            iterator += 1;
        }

        return positionInMenu;
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

    String[] tab = null;
    int idOfAccount;

    /**
     *  Controls - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    LanguageEngine translation = new LanguageEngine();

    @FXML
    Button buttonReturn = new Button();
    @FXML
    Button buttonSaveExpenditiure = new Button();
    @FXML
    Button buttonClearExpenditiure = new Button();

    @FXML
    Label labelAccount = new Label();
    @FXML
    Label labelNameOfExpenditiure = new Label();
    @FXML
    Label labelValueOfExpenditiure = new Label();
    @FXML
    Label labelDataOfExpenditiure = new Label();
    @FXML
    Label labelSetBoudgetOfExpenditiure = new Label();
    @FXML
    Label labelCurrentCondition = new Label();
    @FXML
    Label labelCondition = new Label();
    @FXML
    Label labelAlert = new Label();

    @FXML
    CheckBox checkBoxCategoryOfExpenditiure = new CheckBox();
    @FXML
    CheckBox checkBoxBoudgetOfExpenditiure = new CheckBox();

    @FXML
    TextField textNameOfExpenditiure = new TextField();
    @FXML
    TextField textValuieOfExpenditiure = new TextField();

    @FXML
    ChoiceBox choiceBoxAccount = new ChoiceBox();
    @FXML
    ChoiceBox choiceBoxCategoryOfExpenditiure = new ChoiceBox();
    @FXML
    ChoiceBox choiceBoxSetBoudgetOfExpenditiure = new ChoiceBox();

    @FXML
    DatePicker datePickerOfExpenditiure = new DatePicker();
}
