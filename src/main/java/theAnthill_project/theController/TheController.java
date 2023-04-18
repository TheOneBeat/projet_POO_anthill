package theAnthill_project.theController;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.StringProperty;
import theAnthill_project.theModel.Fourmiliere;
import theAnthill_project.theView.AllComponentsBtns;
import theAnthill_project.theView.TheVue;

import java.util.AbstractList;
import java.util.Objects;
public class TheController
{
    private TheVue vue;
    private Fourmiliere f;
    private int size;
    public TheController(TheVue myVue, Fourmiliere anthill)
    {
        vue = myVue;
        f = anthill;
        size = f.getLargeur();
        System.out.println("la valeur de size est "+size);

        //à chaque clique sur le btn play, l'image change...
        vue.getComponents().getUltimateBtns().getPause_Play().
                setOnAction(e->vue.getComponents().getUltimateBtns().changeImagePlay());

        //Les events...

        vue.getQuit().setOnAction(e-> Platform.exit());

        //Modification de taillePlateau...

        vue.getComponents().addTaillePlateauJeuChangeListener((observable, oldValue, newValue) -> {
            try {
                int newLargeur = Integer.parseInt(newValue);
                if (newLargeur>50)
                    changeLargeur(50);
                else
                    changeLargeur(newLargeur);

            } catch (NumberFormatException e) {
                // Gérer l'exception si la valeur n'est pas un nombre entier
                changeLargeur(20);
            }
        });


    }

    public void changeLargeur(int newLargeur)
    {
        //Update de la fourmiliere
        f.updateFourmiliere(newLargeur);
        //Update de la vue
        vue.updateGameVisual(newLargeur);


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
        if (Objects.equals(s.get(), "") || Double.parseDouble(s.get())<20)
            result=20;
        else
            result = Double.parseDouble(s.get());
        return result;
    }
}