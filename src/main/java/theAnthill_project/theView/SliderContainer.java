package theAnthill_project.theView;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class SliderContainer extends HBox {
    private Slider slide;
    private Label l;

    public SliderContainer(String s)
    {
        super();
        slide = new Slider(0,10,0.5);
        slide.setMaxWidth(100);
        l = new Label(s);

        l.setMaxWidth(150);
        l.setMinWidth(150);

        this.setSpacing(10);
        this.getChildren().addAll(l,slide);

        this.setMaxWidth(190);
        this.setMinWidth(190);

        l.textProperty().bind(Bindings.format("vitesse simulation : %.1f", slide.valueProperty()));
        l.setAlignment(Pos.CENTER_LEFT);
    }
    public void changeValue(double i)
    {
        slide.setValue(i);
    }

    public DoubleProperty getSlideProperty()
    {
        return slide.valueProperty();
    }
}
