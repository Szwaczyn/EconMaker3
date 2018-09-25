package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class startMenuController
{

    private MainController mainControllerVar;


    @FXML
    ImageView menuBackground = new ImageView();

    @FXML
    Button position_1 = new Button();
    @FXML
    Button position_2 = new Button();
    @FXML
    Button position_3 = new Button();
    @FXML
    Button position_4 = new Button();
    @FXML
    Button position_5 = new Button();

    @FXML
    VBox menuBox = new VBox();

    @FXML
    public void showMenu()
    {
        if(position_5.isVisible() == true)
        {
            position_1.setText("Schowaj menu");
            menuBox.setLayoutX(0);
            position_2.setText("Zaloguj");
            position_3.setText("Załóż konto");
            position_4.setText("Wyjście");
            position_5.setVisible(false);
        }
        else
        {
            menuBox.setLayoutX(-200);
            position_1.setText("=");
            position_5.setVisible(true);
        }
    }

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }
}
