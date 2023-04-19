package theAnthill_project.theView;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import theAnthill_project.theModel.Fourmiliere;

import java.util.Objects;
import java.util.Random;

public class TheVue extends BorderPane
{
    private FlowPane GameVisual;
    private int l;
    private Label[][] cells;
    private int doubleRatio;
    private Fourmiliere myFourmiliere;
    private AllComponentsBtns components;
    private Button Quit;
    public TheVue(Fourmiliere anthill)
    {
        super();

        initVue(anthill);
    }
    public DoubleProperty getGameVisualHeightProp()
    {
        return GameVisual.minHeightProperty();
    }

    public Button getQuit() {
        return Quit;
    }

    public AllComponentsBtns getComponents() {
        return components;
    }

    public void setGrilleVisible()
    {
        for (int i=0;i<doubleRatio;i++)
        {
            for(int j=0;j<doubleRatio;j++)
            {
                cells[i][j].setBorder(
                        new Border(
                                new BorderStroke(
                                        Paint.valueOf("BLACK"),
                                        BorderStrokeStyle.SOLID,
                                        new CornerRadii(1),
                                        BorderWidths.DEFAULT
                                )
                        )
                );
            }
        }
        this.addBorderToGameVisual();
    }

    public void setGrilleNotVisible()
    {
        for (int i=0;i<doubleRatio;i++)
        {
            for(int j=0;j<doubleRatio;j++) {
                cells[i][j].setBorder(Border.EMPTY);
            }
        }
        this.removeBorderToGameVisual();
    }
    public void addBorderToGameVisual()
    {
        GameVisual.setBorder(
                new Border(
                        new BorderStroke(
                                Paint.valueOf("RED"),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(0),
                                BorderWidths.DEFAULT
                        )
                )
        );
    }

    public void removeBorderToGameVisual()
    {
        GameVisual.setBorder(Border.EMPTY);
    }

    public Fourmiliere getMyFourmiliere() {
        return myFourmiliere;
    }

    public void initVue(Fourmiliere anthill)
    {
        myFourmiliere = anthill;
        GameVisual = new FlowPane();
        //initialisation de la taille du gameTaille...

        l = (int) myFourmiliere.getLargeur();
        doubleRatio = l;

        GameVisual.setMinSize(200,200);
        GameVisual.setHgap(0);
        GameVisual.setVgap(0);
        GameVisual.setPrefWrapLength(doubleRatio*10);

        components = new AllComponentsBtns();

        Quit = new Button("Quit");

        // Initialisation de la table cells...
        // && Ajout de chaque cell au flowPane GameVisual...

        cells = new Label[doubleRatio][doubleRatio];

        for (int i=0;i<doubleRatio;i++)
        {
            for(int j=0;j<doubleRatio;j++)
            {
                cells[i][j]=new Label("");
                cells[i][j].setMaxWidth(10);
                cells[i][j].setMinWidth(10);
                cells[i][j].setMinHeight(10);
                cells[i][j].setMaxHeight(10);
                cells[i][j].setStyle("-fx-background-color: white;-fx-font-size: 5;-fx-alignment: center;");
                GameVisual.getChildren().add(cells[i][j]);
            }
        }

        GameVisual.setStyle("-fx-background-color: white");
        GameVisual.setAlignment(Pos.CENTER);
        setLeft(GameVisual);
        setAlignment(GameVisual, Pos.CENTER_LEFT);
        setRight(components);
        setAlignment(components,Pos.CENTER_RIGHT);

        setBottom(Quit);
        setAlignment(Quit,Pos.BOTTOM_RIGHT);

        this.setPadding(new Insets(10));
        //Initialiser le terrain avec les paramètres de l'utilisateur...
        this.updateContainerFourmiliere();
        //mise à jour du terrain...
        this.changeCellBackgroundOnContainer();
    }

    public void updateGameVisual(int newLargeur)
    {
        GameVisual.getChildren().clear();
        this.getChildren().clear();

        l = (int) myFourmiliere.getLargeur();
        doubleRatio = l;

        GameVisual.setMinSize(200,200);
        GameVisual.setHgap(0);
        GameVisual.setVgap(0);
        GameVisual.setPrefWrapLength(doubleRatio*10);

        components.updateAllComponents(newLargeur,
                components.getCapacityCase().getContainerValue(),
                components.getSimulationVitesse(),components.getNbGrains().getContainerValue(),
                components.getNbFourmis().getContainerValue(),components.getNbMurs().getContainerValue());

        // Initialisation de la table cells...
        // && Ajout de chaque cell au flowPane GameVisual...

        cells = new Label[doubleRatio][doubleRatio];

        for (int i=0;i<doubleRatio;i++)
        {
            for(int j=0;j<doubleRatio;j++)
            {
                cells[i][j]=new Label("");
                cells[i][j].setMaxWidth(10);
                cells[i][j].setMinWidth(10);
                cells[i][j].setMinHeight(10);
                cells[i][j].setMaxHeight(10);
                cells[i][j].setStyle("-fx-background-color: white;-fx-font-size: 5;-fx-alignment: center;");
                GameVisual.getChildren().add(cells[i][j]);
            }
        }

        GameVisual.setStyle("-fx-background-color: white");
        GameVisual.setAlignment(Pos.CENTER);
        setLeft(GameVisual);
        setAlignment(GameVisual, Pos.CENTER_LEFT);
        setRight(components);
        setAlignment(components,Pos.CENTER_RIGHT);

        setBottom(Quit);
        setAlignment(Quit,Pos.BOTTOM_RIGHT);

        this.setPadding(new Insets(10));

        //Initialiser le terrain avec les paramètres de l'utilisateur...
        this.updateContainerFourmiliere();
        //mise à jour du terrain...
        this.changeCellBackgroundOnContainer();
    }

    public void changeGrilleVisibility()
    {
        switch(UltimateBtns.index_Pause_Play)
        {
            case 0:
            {
                this.setGrilleNotVisible();
                this.setGrilleVisible();
                break;
            }
            case 1:
            {
                this.setGrilleNotVisible();
                UltimateBtns.index_Pause_Play=-1;
                break;
            }
        }
    }

    public void changeCellBackgroundOnContainer()
    {
        for (int i = 0; i < doubleRatio; i++)
        {
            for (int j = 0; j < doubleRatio; j++)
            {
                if (Objects.equals(myFourmiliere.getCellContenu(i, j), "X"))
                    cells[i][j].setStyle("-fx-background-color: green;-fx-font-size: 5;" +
                            "-fx-alignment: center;");

                else if (Objects.equals(myFourmiliere.getCellContenu(i, j), "O"))
                    cells[i][j].setStyle("-fx-background-color: red;-fx-font-size: 5;" +
                            "-fx-alignment: center;");

                else if (myFourmiliere.getCellContenu(i, j).contains("."))
                {
                    int nbCount = myFourmiliere.getCellContenu(i, j).length();
                    double opacity = (nbCount * 0.1);
                    cells[i][j].setStyle("-fx-background-color: blue;-fx-font-size: 5;" +
                            "-fx-alignment: center;-fx-opacity: " + opacity + ";");
                }
                else
                    cells[i][j].setStyle("-fx-background-color: white;-fx-font-size: 5;" +
                            "-fx-alignment: center;");

            }
        }
    }

    public Label getCellsAt(int x,int y) {
        return cells[x][y];
    }

    //fonction pour mettre à jour la fourmilière au tout debut
    //lorsque l'utilisateur définie les paramètres du jeu...
    public void updateContainerFourmiliere()
    {
        int min = 0, max = myFourmiliere.getLargeur() - 1;
        Random random = new Random();
        int fourmisCount = 0, grainsCount = 0, mursCount = 0;

        while (fourmisCount < components.getNumberFourmis() ||
                grainsCount < components.getNumberGrains() ||
                mursCount < components.getNumberMurs()) {

            int x = random.nextInt(max - min + 1) + min;
            int y = random.nextInt(max - min + 1) + min;

            if (myFourmiliere.getCellContenu(x, y).equals("")) {
                if (fourmisCount < components.getNumberFourmis()) {
                    myFourmiliere.setValueContenu(x, y, "X");
                    fourmisCount++;
                } else if (grainsCount < components.getNumberGrains()) {
                    int nbCountGrains = random.nextInt(myFourmiliere.getQMax() + 1);
                    myFourmiliere.setValueContenu(x, y, ".".repeat(nbCountGrains));
                    grainsCount++;
                } else if (mursCount < components.getNumberMurs()) {
                    myFourmiliere.setValueContenu(x, y, "O");
                    mursCount++;
                }
            }
        }
    }

}
