package jeuDesFourmis.theView;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class LabelsInfo extends HBox
{
    /**
     * Les labels qui vont afficher les infos de la simulation
     */
    private final Label nbAnts, nbWall, nbGrain, nbIteration;
    public LabelsInfo()
    {
        super();
        nbAnts = new Label("");
        nbWall = new Label("");
        nbGrain = new Label("");
        nbIteration = new Label("");

        HBox h1 = new HBox(new Label("Nb de fourmis"),nbAnts);
        HBox h2 = new HBox(new Label("Nb de murs"),nbWall);
        HBox h3 = new HBox(new Label("Nb de grains"),nbGrain);
        HBox h4 = new HBox(new Label("Nb d'Iterations"),nbIteration);

        h1.setSpacing(5);
        h2.setSpacing(5);
        h3.setSpacing(5);
        h4.setSpacing(5);

        this.setSpacing(10);
        this.setMinWidth(450);
        this.setMaxWidth(450);
        this.getChildren().addAll(h1,h2,h3,h4);
        this.setPadding(new Insets(10));
    }


    /**
     * Met à jour les labels
     * @param nbfourmis
     * @param nbmur
     * @param nbgrain
     * @param nbIter
     */
    public void updateInfo(int nbfourmis,int nbmur,int nbgrain,int nbIter)
    {
        nbAnts.setText(String.valueOf(nbfourmis));
        nbWall.setText(String.valueOf(nbmur));
        nbGrain.setText(String.valueOf(nbgrain));
        nbIteration.setText(String.valueOf(nbIter));
    }
}
