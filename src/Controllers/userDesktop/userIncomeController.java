package Controllers.userDesktop;

import Controllers.ClassController;
import builder.ChangeWindowBuilder;
import builder.UserFileBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.DataIntegration;
import hoodStuff.LanguageEngine;
import hoodStuff.UserFile;
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
        choiceBoxCategoryOfIncome.getItems().clear();
        fillChoiceBoxCategoryOfIncome();
        if(choiceBoxCategoryOfIncome.getItems().isEmpty())
        {
            labelAlert.setText(translation.setUpLanguage(102));
            checkBoxCategory.setSelected(false);
            choiceBoxCategoryOfIncome.setDisable(true);
        }
    }

    @FXML
    public void clearField()
    {
        textNameOfIncome.setText("");
        textValuieOdIncome.setText("");
        checkBoxCategory.setSelected(false);
        datePickerOfIncome.getEditor().clear();
        datePickerOfIncome.setValue(null);
    }

    @FXML
    public void addNewIncome()
    {
        String accountOfNewIncome = choiceBoxAccount.getValue().toString();
        String nameOfNewIncome = textNameOfIncome.getText();
        String valueOfNewIncome = textValuieOdIncome.getText();
        String dateOfIncome = String.valueOf(datePickerOfIncome.getValue());

        DataIntegration integration = new DataIntegration(valueOfNewIncome);

        if(!integration.isItValidCurrency() || !checkDate(dateOfIncome))
        {
            labelAlert.setText(translation.setUpLanguage(100));

        }
        else
        {
            UserFile file = new UserFileBuilder()
                    .addPath(userSession.getProfilePath())
                    .addFileName("BASE"+accountOfNewIncome+".base")
                    .build();

            file.writeDown(nameOfNewIncome);
            file.writeDown("+ " + valueOfNewIncome);
            file.writeDown(dateOfIncome);
            if(checkBoxCategory.isSelected() && !choiceBoxCategoryOfIncome.getValue().toString().isEmpty())
            {
                file.writeDown(choiceBoxCategoryOfIncome.getValue().toString());
            }
            else {
                file.writeDown(" ");
            }

            file = new UserFileBuilder()
                    .addPath(userSession.getProfilePath())
                    .addFileName(userSession.getLogin() + ".dll")
                    .build();

            int lineToChange = file.searchLine(choiceBoxAccount.getValue().toString());
            double oldValue = Double.parseDouble(tab[idOfAccount + 1]);
            double amountToAdd = Double.parseDouble(valueOfNewIncome);
            oldValue += amountToAdd;
            String newValue = String.valueOf(oldValue);

            file.changeLine(integration.getValidCurrency(newValue), lineToChange);

            labelAlert.setText(translation.setUpLanguage(101));

            tab = lookForExistAccount();
            setCondition(choiceBoxAccount.getValue().toString());
            clearField();
            checkCategory();
        }
    }

    /**
     *  Initialize method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    private boolean checkDate(String date)
    {
        boolean isValidDate = true;
        String[] splitedDate = date.split("-");

        try{
            if(Integer.parseInt(splitedDate[2]) < 0 && Integer.parseInt(splitedDate[2]) > 31) isValidDate = false;
            if(Integer.parseInt(splitedDate[1]) < 0 && Integer.parseInt(splitedDate[1]) > 12) isValidDate = false;
            Integer.parseInt(splitedDate[0]);


        } catch(Exception e) {
            isValidDate = false;
        }

        return isValidDate;
    }

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

        choiceBoxCategoryOfIncome.setDisable(true);
    }

    private void setDisableSectionOfIncome(boolean disable)
    {
        textNameOfIncome.setDisable(disable);
        textValuieOdIncome.setDisable(disable);
        checkBoxCategory.setDisable(disable);
        choiceBoxCategoryOfIncome.setDisable(disable);
        datePickerOfIncome.setDisable(disable);
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

    private void fillChoiceBoxCategoryOfIncome()
    {
        String[] items = lookForExistCategories();
        int sizeOfItem = items.length;

        for(int i = 0; i <= sizeOfItem - 1; i++)
        {
            choiceBoxCategoryOfIncome.getItems().add(items[i]);
        }

        choiceBoxCategoryOfIncome.getSelectionModel().selectFirst();
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

    LanguageEngine translation = new LanguageEngine();
    String[] tab = null;
    int idOfAccount;

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
    Label labelAlert = new Label();

    @FXML
    CheckBox checkBoxCategory = new CheckBox();

    @FXML
    TextField textNameOfIncome = new TextField();
    @FXML
    TextField textValuieOdIncome = new TextField();

    @FXML
    DatePicker datePickerOfIncome = new DatePicker();
}
