package tryTest.tryTest;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.Objects;

public class MyMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        TextField t = new TextField("0");

        Slider s = new Slider(0,100,1);

        s.valueProperty().bind(new StringBindings(t.textProperty()));

        FlowPane root = new FlowPane(t,s);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Trial");
        primaryStage.show();

    }
}

class StringBindings extends DoubleBinding
{
    private final StringProperty s;

    public StringBindings(StringProperty t)
    {
        s=t;
        bind(s);
    }

    @Override
    protected double computeValue() {
        double result;
        if (Objects.equals(s.get(), "") || Double.parseDouble(s.get())<0)
            result=0;
        else
            result = Double.parseDouble(s.get());
        return result;
    }
}