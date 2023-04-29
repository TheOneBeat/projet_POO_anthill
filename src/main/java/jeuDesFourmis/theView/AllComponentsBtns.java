package jeuDesFourmis.theView;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
public class AllComponentsBtns extends VBox
{
    /**
     * Le composant pour la taille du plateau
     */
    private Component taillePlateauJeu;

    /**
     * Le composant pour la capactié d'une case
     */
    private Component capacityCase;

    /**
     * Le SliderContainer pour la vitesse de la simulation (Slider + Label)
     */

    private SliderContainer SimulationVitesse;

    /**
     * Le composant pour le nombre de grains
     */
    private Component NbGrains;

    /**
     * Le composant pour le nombre de fourmis
     */
    private Component NbFourmis;

    /**
     * Le composant pour le nombre de murs
     */
    private Component NbMurs;

    /**
     * Le composant pour les boutons Reset, Start et Loupe
     */
    private UltimateBtns btns;

    /**
     * Constructeur de la classe AllComponentsBtns qui construit le composant de droite de l'interface graphique
     */
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

    /**
     * Retourne le composant pour le nombre de fourmis
     * @return le composant pour le nombre de fourmis
     */

    public Component getNbFourmis() {
        return NbFourmis;
    }

    /**
     * Retourne la valeur du composant du nombre de fourmis
     * @return la valeur du composant du nombre de fourmis
     */
    public int getNumberFourmis(){return NbFourmis.getContainerValue();}

    /**
     * Retourne le composant pour le nombre de grains
     * @return le composant pour le nombre de grains
     */

    public Component getNbGrains() {
        return NbGrains;
    }

    /**
     * Retourne la valeur du composant du nombre de grains
     * @return la valeur du composant du nombre de grains
     */

    public int getNumberGrains(){return NbGrains.getContainerValue();}

    /**
     * Retourne le composant pour le nombre de murs
     * @return le composant pour le nombre de murs
     */

    public Component getNbMurs() {
        return NbMurs;
    }

    /**
     * Retourne la valeur du composant du nombre de murs
     * @return la valeur du composant du nombre de murs
     */
    public int getNumberMurs(){return NbMurs.getContainerValue();}

    /**
     * Retourne le composant de la capacité d'une case
     * @return le composant de la capacité d'une case
     */

    public Component getCapacityCase() {
        return capacityCase;
    }

    /**
     * Retourne le composant de la taille du plateau
     * @return le composant de la taille du plateau
     */

    public Component getTaillePlateauJeu() {
        return taillePlateauJeu;
    }


    /**
     * Permet d'ajouter un ChangeListener au composant de la taille du plateau
     *
     */
    public void addTaillePlateauJeuChangeListener(ChangeListener<? super String> listener)
    {
        taillePlateauJeu.addChangeListener(listener);
    }

    /**
     * Permet d'ajouter un ChangeListener au composant de la capacité d'une case
     *
     */
    public void addCapacityChangeListener(ChangeListener<? super String> listener)
    {
        capacityCase.addChangeListener(listener);
    }

    /**
     * Permet d'ajouter un ChangeListener au composant du nombre de grains
     *
     */
    public void addFourmisChangeListener(ChangeListener<? super String> listener)
    {
        NbFourmis.addChangeListener(listener);
    }

    /**
     * Permet d'ajouter un ChangeListener au composant du nombre de murs
     *
     */
    public void addMursChangeListener(ChangeListener<? super String> listener)
    {
        NbMurs.addChangeListener(listener);
    }

    /**
     * Permet d'ajouter un ChangeListener au composant du nombre de grains
     *
     */
    public void addGrainsChangeListener(ChangeListener<? super String> listener)
    {
        NbGrains.addChangeListener(listener);
    }

    /**
     * Retourne le composant pour les boutons Reset, Start et Loupe
     * @return le composant pour les boutons Reset, Start et Loupe
     */
    public UltimateBtns getUltimateBtns() {
        return btns;
    }

    /**
     * Retourne la valeur du Slider de la vitesse de la simulation
     * @return la valeur du Slider de la vitesse de la simulation
     */
    public Double getSimulationVitesse()
    {
        return SimulationVitesse.getSlideProperty().getValue();
    }

    /**
     * Retourne le SliderContainer pour la vitesse de la simulation (Slider + Label)
     * @return le SliderContainer pour la vitesse de la simulation (Slider + Label)
     */
    public SliderContainer getSlider(){return SimulationVitesse;}

    /**
     * Met à jour les valeurs de tous les composants
     * @param taille la taille du plateau
     * @param cap la capacité d'une case
     * @param simulation la vitesse de la simulation
     * @param nbgrains le nombre de grains
     * @param nbfourmis le nombre de fourmis
     * @param nbmurs le nombre de murs
     */
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


    /**
     * permet de desactiver tous les composants
     *
     * @param b true si on veut desactiver tous les composants, false sinon
     */
    public void setDisableAllComponents(boolean b)
    {
        taillePlateauJeu.setDisable(b);
        capacityCase.setDisable(b);
        NbMurs.setDisable(b);
        NbGrains.setDisable(b);
        NbFourmis.setDisable(b);
    }
}