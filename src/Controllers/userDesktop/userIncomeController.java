package Controllers.userDesktop;

import Controllers.ClassController;
import builder.ChangeWindowBuilder;
import builder.UserFileBuilder;
import hoodStuff.ChangeWindow;
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
    public void loadContent()
    {
        if(choiceBoxAccount.getItems().isEmpty()) { setMenuAccount(); }
        System.out.println("ok");
    }

    @FXML
    public void loadEqupiment()
    {
        if(choiceBoxAccount.getSelectionModel().isEmpty()) { setDisableSectionOfIncome(false); }
    }

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

    /**
     *  Initialize method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        setLanguage();
        checkCategory();
        setDisableSectionOfIncome(true);
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

        checkBoxCategory.setText(translation.setUpLanguage(88));
    }

    private void setMenuAccount()
    {
        choiceBoxAccount.getItems().clear();
        String[] items = lookForExistAccount();
        int sizeOfItem = items.length;

        for(int i = 0; i <= sizeOfItem - 1; i++)
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

        int size = file.size();
        size -= 2;
        size = size / 2;

        String[] positionInMenu = new String[size];
        int iterator = 0;

        for(int i = 3; i <= size * 2 + 2; i += 2)
        {
            positionInMenu[iterator] = file.readLine(i);
            iterator += 1;
        }

        return positionInMenu;
    }

    LanguageEngine translation = new LanguageEngine();

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
    CheckBox checkBoxCategory = new CheckBox();

    @FXML
    TextField textNameOfIncome = new TextField();
    @FXML
    TextField textValuieOdIncome = new TextField();

    @FXML
    DatePicker dataPickerOfIncome = new DatePicker();
}
