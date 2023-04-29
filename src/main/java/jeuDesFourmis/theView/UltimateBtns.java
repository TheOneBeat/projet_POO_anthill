package jeuDesFourmis.theView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.util.Objects;

public class UltimateBtns extends HBox
{

    private final Button Play_Pause;
    private final Button Loop;
    private final Button Reset;

    private final int size = 15;

    public static int index_Pause_Play;

    public UltimateBtns()
    {
        super();
        index_Pause_Play = 0;

        //Reset...
        Reset = new Button("Reset");

        Reset.setMaxWidth(50);
        Reset.setMinWidth(50);

        Reset.setMinHeight(25);
        Reset.setMaxHeight(25);

        //Play

        Play_Pause = new Button("Play");

        Play_Pause.setMaxWidth(50);
        Play_Pause.setMinWidth(50);

        Play_Pause.setMinHeight(25);
        Play_Pause.setMaxHeight(25);
        Play_Pause.setAlignment(Pos.CENTER);


        //Loop

        Loop = new Button("Loupe");
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
        switch (index_Pause_Play) {
            case 0 -> {
                Play_Pause.setText("Play");
                setDisableUltimateBtns(true);
            }
            case 1 -> {
                Play_Pause.setText("Pause");
                setDisableUltimateBtns(false);
            }
        }
    }

    public void setDisableUltimateBtns(boolean b)
    {
        Reset.setDisable(b);
    }
}
