package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class startMenuController
{

    private MainController mainControllerVar;

    Image button = new Image("/resources/images/button.png");
    Image buttonUnderCursor = new Image("/resources/images/buttonUnderCursor.png");

    @FXML
    ImageView position_1 = new ImageView();
    @FXML
    ImageView position_2 = new ImageView();
    @FXML
    ImageView position_3 = new ImageView();
    @FXML
    ImageView position_4 = new ImageView();
    @FXML
    ImageView position_5 = new ImageView();


    @FXML
    public void chooisePositionMenu()
    {
        if(position_1.getImage() == buttonUnderCursor)
        {
            System.out.println("Pozycja 1");
        }
        else if(position_2.getImage() == buttonUnderCursor)
        {
            System.out.println("Pozycja 2");
        }
        else if(position_3.getImage() == buttonUnderCursor)
        {
            System.out.println("Pozycja 3");
        }
        else if(position_4.getImage() == buttonUnderCursor)
        {
            System.out.println("Pozycja 4");
        }
        else if(position_5.getImage() == buttonUnderCursor)
        {
            System.out.println("Pozycja 5");
        }
    }

    @FXML
    public void drawChooseMenu_position_1()
    {
        position_1.setImage(buttonUnderCursor);
    }
    @FXML
    public void drawChooseMenu_position_2()
    {
        position_2.setImage(buttonUnderCursor);
    }
    @FXML
    public void drawChooseMenu_position_3()
    {
        position_3.setImage(buttonUnderCursor);
    }
    @FXML
    public void drawChooseMenu_position_4()
    {
        position_4.setImage(buttonUnderCursor);
    }
    @FXML
    public void drawChooseMenu_position_5()
    {
        position_5.setImage(buttonUnderCursor);
    }

    @FXML
    public void leaveCoursorButton()
    {
        position_1.setImage(button);
        position_2.setImage(button);
        position_3.setImage(button);
        position_4.setImage(button);
        position_5.setImage(button);
    }

    public void setMainController(MainController msc)
    {
        this.mainControllerVar = msc;
    }
}
