package Controllers.userDesktop;

import Controllers.ClassController;
import builder.ChangeWindowBuilder;
import hoodStuff.ChangeWindow;
import hoodStuff.LanguageEngine;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created $(DATE)
 */
public class userExpenditiuresController extends ClassController
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


    /**
     *  Initialize - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    public void initialize()
    {
        setUpLanguage();
    }

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
    }

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
