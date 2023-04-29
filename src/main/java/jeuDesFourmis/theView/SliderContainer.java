package jeuDesFourmis.theView;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class SliderContainer extends HBox {
    private final Slider slide;

    public SliderContainer(String s)
    {
        super();
        slide = new Slider(0,10,0.5);
        slide.setMaxWidth(100);
        Label l = new Label(s);

        //Evènement au Scroll...
        slide.setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY();
            double sliderValue = slide.getValue();

            // Définir un facteur pour augmenter ou diminuer la sensibilité de la molette de la souris.
            double inc = 0.1;

            if (deltaY > 0) {
                slide.setValue(sliderValue + inc);
            } else if (deltaY < 0) {
                slide.setValue(sliderValue - inc);
            }
        });

        l.setMaxWidth(150);
        l.setMinWidth(150);

        this.setSpacing(10);
        this.getChildren().addAll(l,slide);

        this.setMaxWidth(190);
        this.setMinWidth(190);

        l.textProperty().bind(Bindings.format("vitesse simulation : %.1f", slide.valueProperty()));
        l.setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Change la valeur du slider
     * @param i la nouvelle valeur
     */
    public void changeValue(double i)
    {
        slide.setValue(i);
    }

    /**
     * Retourne la valueProperty du slider
     * @return la valueProperty du slider
     */
    public DoubleProperty getSlideProperty()
    {
        return slide.valueProperty();
    }
}
