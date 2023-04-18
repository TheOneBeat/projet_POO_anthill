package theAnthill_project.theView;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Component extends HBox
{
    private Label name;
    private TextField container;

    public Component(String nom,String value)
    {
        super();
        name = new Label(nom);

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

    public StringProperty getContainerProperty()
    {
        return container.textProperty();
    }

    public TextField getContainer()
    {
        return container;
    }

    public int getContainerValue()
    {
        return Integer.parseInt(container.getText());
    }

    public void addChangeListener(ChangeListener<? super String> listener) {
        container.textProperty().addListener(listener);
    }

    public void updateValue(int i)
    {
        container.setText(String.valueOf(i));
    }

}
