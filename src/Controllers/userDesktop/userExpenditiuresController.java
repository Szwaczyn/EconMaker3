package Controllers.userDesktop;

import Controllers.ClassController;
import builder.ChangeWindowBuilder;
import builder.UserFileBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.DataIntegration;
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
        choiceBoxCategoryOfExpenditiure.getItems().clear();
        fillChoiceBoxCategoryOfExpenditiure();
        if(choiceBoxCategoryOfExpenditiure.getItems().isEmpty() && checkBoxCategoryOfExpenditiure.isSelected())
        {
            setAlert(translation.setUpLanguage(102));
            checkBoxCategoryOfExpenditiure.setSelected(false);
            choiceBoxCategoryOfExpenditiure.setDisable(true);
        }
    }

    @FXML
    public void setBoudget()
    {
        setDisableSectionOfBoudget( !checkBoxBoudgetOfExpenditiure.isSelected() );
        if(!choiceBoxSetBoudgetOfExpenditiure.getItems().isEmpty())choiceBoxSetBoudgetOfExpenditiure.getItems().clear();
        fillChoiceBoxBoudgetOfExpenditiure();
        if(choiceBoxSetBoudgetOfExpenditiure.getItems().isEmpty() && checkBoxBoudgetOfExpenditiure.isSelected())
        {
            setAlert(translation.setUpLanguage(102));
            checkBoxBoudgetOfExpenditiure.setSelected(false);
            choiceBoxSetBoudgetOfExpenditiure.setDisable(true);
        }
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

    @FXML
    public void actionAddExpenditiure()
    {
        DataIntegration integration = new DataIntegration(textValuieOfExpenditiure.getText());

        if(integration.isItValidCurrency() && integration.isValidDate(datePickerOfExpenditiure.getValue().toString()))
        {
            String name = textNameOfExpenditiure.getText();
            String value = textValuieOfExpenditiure.getText();
            String date = datePickerOfExpenditiure.getValue().toString();
            String category;
            if(checkBoxCategoryOfExpenditiure.isSelected()) category = choiceBoxCategoryOfExpenditiure.getValue().toString();

            if(checkBoxBoudgetOfExpenditiure.isSelected())
            {

            }
        }
        else
        {
            setAlert(translation.setUpLanguage(100));
        }
    }

    /**
     *  Operation - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private void saveToOperationFile(String data)
    {
        UserFile file = new UserFileBuilder()
                .addPath(this.userSession.getProfilPath())
                .addFileName(this.userSession.getFileNameAccount(choiceBoxAccount.getValue().toString()))
                .build();

        file.writeDown(data);
    }

    private void saveToBoudgetOperation(String data)
    {

    }

    private void changeLineInBoudget()
    {
        
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
            choiceBoxSetBoudgetOfExpenditiure.getSelectionModel().selectedItemProperty().addListener( (newValue) -> setConditionOfBoudget(newValue.toString()));
            setBoudget();
            setCategory();
        }
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

    private void setConditionOfBoudget(String setBoudget)
    {
        System.out.println(setBoudget);
        idOfBoudget = getIdOfPosition(setBoudget, tabBoudget);
        if(checkBoxBoudgetOfExpenditiure.isSelected()) labelSetBoudgetOfExpenditiure.setText(translation.setUpLanguage(99) + tabBoudget[idOfBoudget + 1] +
                " zł");
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

    private void fillChoiceBoxCategoryOfExpenditiure()
    {
        String[] items = lookForExistCategories();
        int sizeOfItem = items.length;

        for(int i = 0; i <= sizeOfItem - 1; i++)
        {
            choiceBoxCategoryOfExpenditiure.getItems().add(items[i]);
        }

        choiceBoxCategoryOfExpenditiure.getSelectionModel().selectFirst();
    }

    private String[] lookForExistCategories()
    {
        UserFile file = new UserFileBuilder()
                .addFileName(userSession.getFileNameCategories())
                .addPath(userSession.getProfilePath())
                .build();

        int size = file.size();

        String[] positionInMenu = new String[size];
        int iterator = 0;

        for(int i = 0; i <= size - 1; i += 1)
        {
            positionInMenu[iterator] = file.readLine(i + 1);
            iterator += 1;
        }

        return positionInMenu;
    }

    private void fillChoiceBoxBoudgetOfExpenditiure()
    {
        choiceBoxSetBoudgetOfExpenditiure.getItems().clear();
        String[] items = lookForExistBoudget();
        int sizeOfItem = items.length;

        for(int i = 0; i <= sizeOfItem - 1; i += 2)
        {
            choiceBoxSetBoudgetOfExpenditiure.getItems().add(items[i]);
        }

        choiceBoxSetBoudgetOfExpenditiure.getSelectionModel().selectFirst();
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

    String[] tab = null;
    String[] tabBoudget = null;
    int idOfAccount;
    int idOfBoudget;

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
