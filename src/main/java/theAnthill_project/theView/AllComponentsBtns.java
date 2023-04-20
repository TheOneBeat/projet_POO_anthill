package theAnthill_project.theView;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class AllComponentsBtns extends VBox
{
    private Component taillePlateauJeu;
    private Component capacityCase;
    private SliderContainer SimulationVitesse;
    private Component NbGrains;
    private Component NbFourmis;
    private Component NbMurs;

    private UltimateBtns btns;

    public AllComponentsBtns()
    {
        super();
        taillePlateauJeu = new Component("taille du plateau","25");
        capacityCase = new Component("capacité Case","10");
        SimulationVitesse = new SliderContainer("Vitesse Simulation");
        NbGrains = new Component("Nombre de Grains","10");
        NbFourmis = new Component("Nombre de Fourmis","4");
        NbMurs = new Component("Nombre de Murs","14");

        //Le bouton pause au tt debut du jeu...

        btns = new UltimateBtns();

        this.setSpacing(4);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setMinHeight(250);
        this.setMinWidth(250);

        this.getChildren().addAll(taillePlateauJeu,capacityCase,
                SimulationVitesse,NbGrains,NbFourmis,NbMurs,btns);
    }

    public Component getNbFourmis() {
        return NbFourmis;
    }

    public int getNumberFourmis(){return NbFourmis.getContainerValue();}

    public Component getNbGrains() {
        return NbGrains;
    }

    public int getNumberGrains(){return NbGrains.getContainerValue();}

    public Component getNbMurs() {
        return NbMurs;
    }

    public int getNumberMurs(){return NbMurs.getContainerValue();}

    public Component getCapacityCase() {
        return capacityCase;
    }

    public Component getTaillePlateauJeu() {
        return taillePlateauJeu;
    }

    //EventListener pour modifier la taille du plateau
    public void addTaillePlateauJeuChangeListener(ChangeListener<? super String> listener)
    {
        taillePlateauJeu.addChangeListener(listener);
    }

    //EventListener pour modifier la capacité du plateau
    public void addCapacityChangeListener(ChangeListener<? super String> listener)
    {
        capacityCase.addChangeListener(listener);
    }

    //EventListener pour modifier le nombre de fourmis
    public void addFourmisChangeListener(ChangeListener<? super String> listener)
    {
        NbFourmis.addChangeListener(listener);
    }

    //EventListener pour modifier le nombre de murs
    public void addMursChangeListener(ChangeListener<? super String> listener)
    {
        NbMurs.addChangeListener(listener);
    }

    //EventListener pour modifier le nombre de murs
    public void addGrainsChangeListener(ChangeListener<? super String> listener)
    {
        NbGrains.addChangeListener(listener);
    }

    public UltimateBtns getUltimateBtns() {
        return btns;
    }
    public Double getSimulationVitesse()
    {
        return SimulationVitesse.getSlideProperty().getValue();
    }

    public SliderContainer getSlider(){return SimulationVitesse;}
    public void updateAllComponents(int taille,int cap,double simulation,
                                    int nbgrains,int nbfourmis,int nbmurs)
    {
       taillePlateauJeu.updateValue(taille);
       capacityCase.updateValue(cap);
       SimulationVitesse.changeValue(simulation);
       NbMurs.updateValue(nbmurs);
       NbFourmis.updateValue(nbfourmis);
       NbGrains.updateValue(nbgrains);
    }

    public void setDisableAllComponents(boolean b)
    {
        taillePlateauJeu.setDisable(b);
        capacityCase.setDisable(b);
        NbMurs.setDisable(b);
        NbGrains.setDisable(b);
        NbFourmis.setDisable(b);
        SimulationVitesse.setDisable(b);
    }
}