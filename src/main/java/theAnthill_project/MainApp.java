package theAnthill_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import theAnthill_project.theController.TheController;
import theAnthill_project.theModel.Fourmiliere;
import theAnthill_project.theView.TheVue;

public class MainApp extends Application
{
    @Override
    public void start(Stage stage)
    {
        stage.setTitle("The anthill's game");

        Fourmiliere Model = new Fourmiliere(50,10);

        TheVue Vue = new TheVue(Model);

        TheController Controller = new TheController(Vue,Model);

        Scene scene = new Scene(Vue);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}