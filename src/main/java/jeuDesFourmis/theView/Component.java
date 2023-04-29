package jeuDesFourmis.theView;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Le component est composé d'un label et d'un textfield
 */
public class Component extends HBox
{

    private final TextField container;

    public Component(String nom,String value)
    {
        super();
        Label name = new Label(nom);

        name.setMaxWidth(150);
        name.setMinWidth(150);
        name.setAlignment(Pos.CENTER_LEFT);

        container = new TextField(value);
        container.setAlignment(Pos.CENTER);

        container.setMaxWidth(30);
        container.setMinWidth(30);

        this.setMinWidth(190);
        this.setMaxWidth(190);
        this.setMinHeight(40);
        this.setMaxHeight(40);

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(name,container);
    }

    /**
     * return 0 si le textfield est vide
     * @return la valeur du textfield
     */
    public int getContainerValue()
    {
        int result;
        if (container.getText().equals(""))
            result=0;
        else
            result = Integer.parseInt(container.getText());
        return result;
    }

    /**
     * ajoute un listener au textfield
     * @param listener
     */
    public void addChangeListener(ChangeListener<? super String> listener) {
        container.textProperty().addListener(listener);
    }


    /**
     * change la valeur du textfield
     * @param i la nouvelle valeur de type int
     */
    public void updateValue(int i)
    {
        container.setText(String.valueOf(i));
    }

}
