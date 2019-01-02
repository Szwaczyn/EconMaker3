package Controllers.userDesktop;

import Controllers.ClassController;
import builder.ChangeWindowBuilder;
import hoodStuff.ChangeWindow;
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
     *  Controls - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

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
    CheckBox CheckBoxBoudgetOfExpenditiure = new CheckBox();

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
