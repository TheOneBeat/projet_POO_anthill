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
        System.out.printf("entrée dans la fonction updateGameVisual %n");
        GameVisual.getChildren().clear();
        this.getChildren().clear();

        l = (int) myFourmiliere.getLargeur();
        System.out.printf("stockage de la nouvelle largeur du model %n");
        doubleRatio = l;

        GameVisual.setMinSize(200,200);
        GameVisual.setHgap(0);
        GameVisual.setVgap(0);
        GameVisual.setPrefWrapLength(doubleRatio*10);

        System.out.printf("Modification du terrain %n");

        components.updateAllComponents(newLargeur,
                components.getCapacityCase().getContainerValue(),
                components.getSimulationVitesse(),components.getNbGrains().getContainerValue(),
                components.getNbFourmis().getContainerValue(),components.getNbMurs().getContainerValue());

        System.out.printf("Mise à jour des composants du terrain %n");
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
        System.out.printf("finition de updateGameVisual de la vue %n");

        //Initialiser le terrain avec les paramètres de l'utilisateur...
        this.updateContainerFourmiliere();

        System.out.printf("appel de la fonction pour remplir le terrain à initialisation du game %n");
        //
        //mise à jour du terrain...
        this.changeCellBackgroundOnContainer();
        System.out.printf("mise à jour de la vue du game %n");
    }


    public void updateGameVisualNbFourmi(int nbAnts)
    {
        System.out.printf("entrée dans la fonction updateGameVisual %n");
        GameVisual.getChildren().clear();
        this.getChildren().clear();

        l = (int) myFourmiliere.getLargeur();
        System.out.printf("stockage de la nouvelle largeur du model %n");
        doubleRatio = l;

        GameVisual.setMinSize(200,200);
        GameVisual.setHgap(0);
        GameVisual.setVgap(0);
        GameVisual.setPrefWrapLength(doubleRatio*10);

        System.out.printf("Modification du terrain %n");

        components.updateAllComponents(components.getTaillePlateauJeu().getContainerValue(),
                components.getCapacityCase().getContainerValue(),
                components.getSimulationVitesse(),components.getNbGrains().getContainerValue(),
                nbAnts,components.getNbMurs().getContainerValue());

        System.out.printf("Mise à jour des composants du terrain %n");
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
        System.out.printf("finition de updateGameVisual de la vue %n");

        //Initialiser le terrain avec les paramètres de l'utilisateur...
        this.updateContainerFourmiliere();

        System.out.printf("appel de la fonction pour remplir le terrain à initialisation du game %n");
        //
        //mise à jour du terrain...
        this.changeCellBackgroundOnContainer();
        System.out.printf("mise à jour de la vue du game %n");
    }



    public void updateGameVisualNbMurs(int nbMurs)
    {
        System.out.printf("entrée dans la fonction updateGameVisual %n");
        GameVisual.getChildren().clear();
        this.getChildren().clear();

        l = (int) myFourmiliere.getLargeur();
        System.out.printf("stockage de la nouvelle largeur du model %n");
        doubleRatio = l;

        GameVisual.setMinSize(200,200);
        GameVisual.setHgap(0);
        GameVisual.setVgap(0);
        GameVisual.setPrefWrapLength(doubleRatio*10);

        System.out.printf("Modification du terrain %n");

        components.updateAllComponents(components.getTaillePlateauJeu().getContainerValue(),
                components.getCapacityCase().getContainerValue(),
                components.getSimulationVitesse(),components.getNbGrains().getContainerValue(),
                components.getNbFourmis().getContainerValue(),nbMurs);

        System.out.printf("Mise à jour des composants du terrain %n");
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
        System.out.printf("finition de updateGameVisual de la vue %n");

        //Initialiser le terrain avec les paramètres de l'utilisateur...
        this.updateContainerFourmiliere();

        System.out.printf("appel de la fonction pour remplir le terrain à initialisation du game %n");
        //
        //mise à jour du terrain...
        this.changeCellBackgroundOnContainer();
        System.out.printf("mise à jour de la vue du game %n");
    }

    public void updateGameVisualNbGrains(int nbGrains)
    {
        System.out.printf("entrée dans la fonction updateGameVisual %n");
        GameVisual.getChildren().clear();
        this.getChildren().clear();

        l = (int) myFourmiliere.getLargeur();
        System.out.printf("stockage de la nouvelle largeur du model %n");
        doubleRatio = l;

        GameVisual.setMinSize(200,200);
        GameVisual.setHgap(0);
        GameVisual.setVgap(0);
        GameVisual.setPrefWrapLength(doubleRatio*10);

        System.out.printf("Modification du terrain %n");

        components.updateAllComponents(components.getTaillePlateauJeu().getContainerValue(),
                components.getCapacityCase().getContainerValue(),
                components.getSimulationVitesse(),nbGrains,
                components.getNbFourmis().getContainerValue(),
                components.getNumberMurs());

        System.out.printf("Mise à jour des composants du terrain %n");
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
        System.out.printf("finition de updateGameVisual de la vue %n");

        //Initialiser le terrain avec les paramètres de l'utilisateur...
        this.updateContainerFourmiliere();

        System.out.printf("appel de la fonction pour remplir le terrain à initialisation du game %n");
        //
        //mise à jour du terrain...
        this.changeCellBackgroundOnContainer();
        System.out.printf("mise à jour de la vue du game %n");
    }


    public void changeGrilleVisibility()
    {
        switch(UltimateBtns.index_Pause_Play)
        {
            case 0:
            {
                this.setGrilleNotVisible();
                break;
            }
            case 1:
            {
                this.setGrilleVisible();
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

    /*
    * Si les cases du terrain sont remplies et qu'il n'y a plus de
    * cases vides pour placer les fourmis,
    *  les grains et les murs, la boucle continuera indéfiniment.
    * Pour éviter cela, j'ai un compteur d'essais (attempts,maxAttempts) pour limiter le nombre de tentatives de
    * placement des éléments.
    *
    * */

    public void updateContainerFourmiliere()
    {
        resetFourmiliere();
        System.out.printf("entree dans la fonction updateContainerFourmiliere %n");
        int min = 0, max = myFourmiliere.getLargeur() - 1;
        Random random = new Random();
        int fourmisCount = 0, grainsCount = 0, mursCount = 0;
        int maxAttempts = 10000; // Limite le nombre d'essais pour placer les éléments.
        int attempts = 0;

        System.out.printf("entree dans le while de la fonction updateContainerFourmiliere %n");
        while ((fourmisCount < components.getNumberFourmis() ||
                grainsCount < components.getNumberGrains() ||
                mursCount < components.getNumberMurs()) && attempts < maxAttempts) {

            int x = random.nextInt(max - min + 1) + min;
            int y = random.nextInt(max - min + 1) + min;

            if (myFourmiliere.getCellContenu(x, y).equals(""))
            {
                if (fourmisCount < components.getNumberFourmis())
                {
                    myFourmiliere.setValueContenu(x, y, "X");
                    fourmisCount++;
                    System.out.printf("on met les fourmis %n");
                }
                else if (grainsCount < components.getNumberGrains())
                {
                    int nbCountGrains = random.nextInt(myFourmiliere.getQMax() + 1);
                    myFourmiliere.setValueContenu(x, y, ".".repeat(nbCountGrains));
                    grainsCount++;
                    System.out.printf("on met les grains %n");
                }
                else if (mursCount < components.getNumberMurs())
                {
                    myFourmiliere.setValueContenu(x, y, "O");
                    mursCount++;
                    System.out.printf("on met les murs %n");
                }
            }

            attempts++; // Incrémente le compteur d'essais.
        }

        if (attempts >= maxAttempts) {
            System.out.println("Le nombre maximum d'essais a été atteint. Veuillez réessayer avec un autre nombre d'éléments.");
        }
    }

    public void resetFourmiliere() {
        for (int i = 0; i < myFourmiliere.getLargeur(); i++) {
            for (int j = 0; j < myFourmiliere.getLargeur(); j++) {
                myFourmiliere.setValueContenu(i, j, "");
            }
        }
    }


}
