package theAnthill_project.theView;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LabelsInfo extends HBox
{
    private final Label nbAnts, nbWall, nbGrain;
    public LabelsInfo()
    {
        super();
        nbAnts = new Label("");
        nbWall = new Label("");
        nbGrain = new Label("");

        HBox h1 = new HBox(new Label("Nb de fourmis"),nbAnts);
        HBox h2 = new HBox(new Label("Nb de murs"),nbWall);
        HBox h3 = new HBox(new Label("Nb de grains"),nbGrain);

        h1.setSpacing(5);
        h2.setSpacing(5);
        h3.setSpacing(5);

        this.setSpacing(10);
        this.setMinWidth(300);
        this.setMaxWidth(300);
        this.getChildren().addAll(h1,h2,h3);
        this.setPadding(new Insets(10));
    }

    public void updateInfo(int nbfourmis,int nbmur,int nbgrain)
    {
        nbAnts.setText(String.valueOf(nbfourmis));
        nbWall.setText(String.valueOf(nbmur));
        nbGrain.setText(String.valueOf(nbgrain));
    }
}
