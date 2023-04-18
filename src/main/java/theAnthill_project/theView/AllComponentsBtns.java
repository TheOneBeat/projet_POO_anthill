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
        capacityCase = new Component("capacité Case","5");
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
        //this.setStyle("-fx-background-color: red");

        this.getChildren().addAll(taillePlateauJeu,capacityCase,
                SimulationVitesse,NbGrains,NbFourmis,NbMurs,btns);
    }

    public Component getNbFourmis() {
        return NbFourmis;
    }

    public Component getNbGrains() {
        return NbGrains;
    }

    public Component getNbMurs() {
        return NbMurs;
    }

    public Component getCapacityCase() {
        return capacityCase;
    }

    public Component getTaillePlateauJeu() {
        return taillePlateauJeu;
    }

    public void addTaillePlateauJeuChangeListener(ChangeListener<? super String> listener)
    {
        taillePlateauJeu.addChangeListener(listener);
    }

    public UltimateBtns getUltimateBtns() {
        return btns;
    }

    public Double getSimulationVitesse()
    {
        return SimulationVitesse.getSlideProperty().getValue();
    }

    public void updateAllComponents(int taille,int cap,int simulation,
                                    int nbgrains,int nbfourmis,int nbmurs)
    {
       taillePlateauJeu.updateValue(taille);
       capacityCase.updateValue(cap);
       SimulationVitesse.changeValue(simulation);
       NbMurs.updateValue(nbmurs);
       NbFourmis.updateValue(nbfourmis);
       NbGrains.updateValue(nbgrains);
    }
}
