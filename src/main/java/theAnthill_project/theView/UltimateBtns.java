package theAnthill_project.theView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.util.Objects;

public class UltimateBtns extends HBox
{

    private Button Play_Pause;
    private Button Loop;
    private Button Reset;

    private int size = 15;

    public static int index_Pause_Play;

    public UltimateBtns()
    {
        super();
        index_Pause_Play = -1;

        //Reset...
        Reset = new Button();
        Image imageReset = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/theAnthill_project/Images/reset.png")),size, size, true, true);
        ImageView imageViewReset = new ImageView(imageReset);
        Reset.setGraphic(imageViewReset);
        Reset.setAlignment(Pos.CENTER);

        Reset.setMaxWidth(50);
        Reset.setMinWidth(50);

        Reset.setMinHeight(25);
        Reset.setMaxHeight(25);

        //Play

        Play_Pause = new Button();

        Play_Pause.setMaxWidth(50);
        Play_Pause.setMinWidth(50);

        Play_Pause.setMinHeight(25);
        Play_Pause.setMaxHeight(25);
        Play_Pause.setAlignment(Pos.CENTER);

        this.changeImagePlay();

        //Loop

        Loop = new Button();
        Image imageLoop = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/theAnthill_project/Images/zoom-in.png")),size, size, true, true);
        ImageView imageViewLoop = new ImageView(imageLoop);
        Loop.setGraphic(imageViewLoop);
        Loop.setAlignment(Pos.CENTER);

        Loop.setMaxWidth(50);
        Loop.setMinWidth(50);

        Loop.setMinHeight(25);
        Loop.setMaxHeight(25);


        this.setMinWidth(170);
        this.setMaxWidth(170);
        this.setSpacing(5);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(Reset,Play_Pause,Loop);
    }

    public Button getReset() {
        return Reset;
    }

    public Button getLoop() {
        return Loop;
    }

    public Button getPause_Play() {
        return Play_Pause;
    }

    public void changeImagePlay()
    {
        index_Pause_Play++;
        Image imagePause = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/theAnthill_project/Images/pause.png")),size, size, true, true);
        ImageView imageViewPause = new ImageView(imagePause);

        Image imagePlay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/theAnthill_project/Images/play.png")),size, size, true, true);
        ImageView imageViewPlay = new ImageView(imagePlay);

        switch(index_Pause_Play)
        {
            case 0:
            {
                Play_Pause.setGraphic(imageViewPlay);
                setDisableUltimateBtns(true);
                break;
            }
            case 1:
            {
                Play_Pause.setGraphic(imageViewPause);
                setDisableUltimateBtns(false);
            }
        }
    }

    public void setDisableUltimateBtns(boolean b)
    {
        Reset.setDisable(b);
    }
}
