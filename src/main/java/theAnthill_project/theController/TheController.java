package theAnthill_project.theController;

import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.StringProperty;
import theAnthill_project.theModel.Fourmiliere;
import theAnthill_project.theView.TheVue;

import java.util.Objects;
public class TheController
{
    private TheVue vue;
    private Fourmiliere f;
    public TheController(TheVue myVue, Fourmiliere anthill)
    {
        vue = myVue;
        f = anthill;
        OnclickLabel();


        //à chaque clique sur le btn play, l'image change...
        // avec le fait d'afficher les grilles ou pas...
        vue.getComponents().getUltimateBtns().getPause_Play().
                setOnAction(e->{
                    vue.getComponents().getUltimateBtns().changeImagePlay();
                    vue.changeGrilleVisibility();
                });

        //Les events...
        //event de sortie du jeu après le clique sur le bouton quit...


        vue.getQuit().setOnAction(e-> Platform.exit());

        //Le bouton Reset
        vue.getComponents().getUltimateBtns().getReset()
                .setOnAction(e->{
                    changeLargeur(f.getLargeur());
                });

        //Modification de taillePlateau...

        vue.getComponents().addTaillePlateauJeuChangeListener((observable, oldValue, newValue) -> {
            try {
                int newLargeur = Integer.parseInt(newValue);
                if (newLargeur>50)
                {
                    changeLargeur(50);
                }
                else
                {
                    changeLargeur(newLargeur);
                }

            } catch (NumberFormatException e) {
                // Gérer l'exception si la valeur n'est pas un nombre entier
                changeLargeur(20);
            }
        });

        //Modification de la capacité de grains pour chaque cellule...

        vue.getComponents().addCapacityChangeListener((observable, oldValue, newValue) -> {
            try {
                int newLargeur = Integer.parseInt(newValue);
                changeCapacityG(newLargeur);
            } catch (NumberFormatException e) {
                // Gérer l'exception si la valeur n'est pas un nombre entier
                changeCapacityG(2);
            }
        });
    }

    public void changeLargeur(int newLargeur)
    {
        //Update de la fourmiliere
        f.updateFourmiliere(newLargeur);
        //Update de la vue
        vue.updateGameVisual(newLargeur);
        OnclickLabel();
    }

    public void changeCapacityG(int cap)
    {
        //Update de la fourmiliere
        //f.setQMax(cap);ça marche mais ne réinitialise pas le terrain...
        f.updateCapFourmiliere(f.getLargeur(),cap); //reinitialise le terrain...
        vue.updateGameVisual(f.getLargeur());
        OnclickLabel();
    }

    public void OnclickLabel()
    {
        for(int i=0;i<f.getLargeur();i++)
        {
            for(int j=0;j<f.getLargeur();j++)
            {
                int finalI = i;
                int finalJ = j;
                vue.getCellsAt(i,j).setOnMouseClicked(e->
                {
                    if (e.isAltDown())
                    {
                        System.out.println("okok");
                        f.setValueContenu(finalI,finalJ,".".concat(f.getCellContenu(finalI,finalJ)));
                        //System.out.println("la position du grain "+ finalJ+" "+finalI);
                        vue.changeCellBackgroundOnContainer();
                    }
                    else if (e.isShiftDown())
                    {
                        if (Objects.equals(f.getCellContenu(finalI, finalJ), ""))
                        {
                            f.setValueContenu(finalI,finalJ,"X");
                            //System.out.println("la position "+ finalJ+" "+finalI);
                            vue.changeCellBackgroundOnContainer();
                        }
                    }
                    else
                    {
                        if (Objects.equals(f.getCellContenu(finalI, finalJ), ""))
                        {
                            f.setValueContenu(finalI,finalJ,"O");
                            //System.out.println("la position "+ finalJ+" "+finalI);
                            vue.changeCellBackgroundOnContainer();
                        }
                        else if (Objects.equals(f.getCellContenu(finalI, finalJ), "O"))
                        {
                            f.setValueContenu(finalI,finalJ,"");
                            //System.out.println("la position "+ finalJ+" "+finalI);
                            vue.changeCellBackgroundOnContainer();
                        }
                    }
                });
            }
        }
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