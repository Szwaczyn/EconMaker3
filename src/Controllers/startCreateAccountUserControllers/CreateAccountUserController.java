package Controllers.startCreateAccountUserControllers;

import Controllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created $(DATE)
 */
public class CreateAccountUserController
{
    @FXML
    Button buttonCreate = new Button();
    @FXML
    Button buttonClear = new Button();
    @FXML
    Button position_1 = new Button();

    @FXML
    Label labelLogin = new Label();
    @FXML
    Label labelPassword = new Label();
    @FXML
    Label labelQuestion = new Label();
    @FXML
    Label labelAnswer = new Label();
    @FXML
    Label labelTitle = new Label();

    @FXML
    TextField textLogin = new TextField();
    @FXML
    TextField textQuestion = new TextField();
    @FXML
    TextField textAnswer = new TextField();

    @FXML
    PasswordField textPassword = new PasswordField();



    private MainController mainControllerVar;

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }

    @FXML
    public void returnToMenu()
    {
        mainControllerVar.inicializujMenu();
    }
}
