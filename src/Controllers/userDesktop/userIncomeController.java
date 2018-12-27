package Controllers.userDesktop;

import Controllers.ClassController;
import builder.ChangeWindowBuilder;
import builder.UserFileBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.DataIntegration;
import hoodStuff.LanguageEngine;
import hoodStuff.UserFile;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.fxml.FXML;

/**
 * Created $(DATE)
 */
public class userIncomeController extends ClassController
{

    /**
     *  Action method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
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
    public void checkCategory()
    {
        choiceBoxCategoryOfIncome.setDisable(!checkBoxCategory.isSelected());
    }

    @FXML
    public void clearField()
    {
        textNameOfIncome.setText("");
        textValuieOdIncome.setText("");
        checkBoxCategory.setSelected(false);
    }

    @FXML
    public void addNewIncome()
    {
        String accountOfNewIncome = choiceBoxAccount.getValue().toString();
        String nameOfNewIncome = textNameOfIncome.getText();
        String valueOfNewIncome = textValuieOdIncome.getText();
        String categoryOfNewIncome;

        if(checkBoxCategory.isSelected())
        {
            categoryOfNewIncome = choiceBoxCategoryOfIncome.getValue().toString();
        }
    }

    /**
     *  Initialize method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        setLanguage();
        choiceBoxCategoryOfIncome.setDisable(true);
        setDisableSectionOfIncome(true);

        if(userSession != null)
        {
            choiceBoxAccount.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> setCondition(newValue.toString()) );
            setMenuAccount();
            choiceBoxAccount.getSelectionModel().selectFirst();
            setDisableSectionOfIncome(false);
            setCondition(choiceBoxAccount.getValue().toString());
        }
    }

    private void setDisableSectionOfIncome(boolean disable)
    {
        textNameOfIncome.setDisable(disable);
        textValuieOdIncome.setDisable(disable);
        checkBoxCategory.setDisable(disable);
        choiceBoxCategoryOfIncome.setDisable(disable);
        dataPickerOfIncome.setDisable(disable);
        buttonSaveIncome.setDisable(disable);
        buttonClearIncome.setDisable(disable);
    }

    private void setLanguage()
    {
        buttonReturn.setText(translation.setUpLanguage(6));
        buttonSaveIncome.setText(translation.setUpLanguage(9));
        buttonClearIncome.setText(translation.setUpLanguage(27));

        labelAccount.setText(translation.setUpLanguage(65));
        labelNameOfIncome.setText(translation.setUpLanguage(96));
        labelValueOfIncome.setText(translation.setUpLanguage(97));
        labelDataOfIncome.setText(translation.setUpLanguage(98));
        labelCurrentCondition.setText(translation.setUpLanguage(99));

        checkBoxCategory.setText(translation.setUpLanguage(88));
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

    private void setCondition(String setAccount)
    {
        int idOfPosition = getIdOfPosition(setAccount, tab);
        labelCondition.setText(tab[idOfPosition + 1] + " zł");
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

    LanguageEngine translation = new LanguageEngine();
    String[] tab = null;

    @FXML
    Button buttonReturn = new Button();
    @FXML
    Button buttonSaveIncome = new Button();
    @FXML
    Button buttonClearIncome = new Button();

    @FXML
    ChoiceBox choiceBoxAccount = new ChoiceBox();
    @FXML
    ChoiceBox choiceBoxCategoryOfIncome = new ChoiceBox();

    @FXML
    Label labelAccount = new Label();
    @FXML
    Label labelNameOfIncome = new Label();
    @FXML
    Label labelValueOfIncome = new Label();
    @FXML
    Label labelDataOfIncome = new Label();
    @FXML
    Label labelCurrentCondition = new Label();
    @FXML
    Label labelCondition = new Label();

    @FXML
    CheckBox checkBoxCategory = new CheckBox();

    @FXML
    TextField textNameOfIncome = new TextField();
    @FXML
    TextField textValuieOdIncome = new TextField();

    @FXML
    DatePicker dataPickerOfIncome = new DatePicker();
}
