package jeuDesFourmis.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jeuDesFourmis.theController.TheController;
import jeuDesFourmis.theModel.Fourmiliere;
import jeuDesFourmis.theView.TheVue;

public class MainApp extends Application
{
    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Le Jeu des Fourmis");

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