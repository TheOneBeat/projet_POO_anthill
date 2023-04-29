package jeuDesFourmis.theView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import jeuDesFourmis.theModel.Fourmiliere;
import java.util.Objects;
import java.util.Random;

public class TheVue extends BorderPane
{


    /**
     * @param l : la taille de la grille
     * @param cells : la grille
     * @param myFourmiliere : la fourmiliere
     * @param components : les composants de la vue
     * @param Quit : le bouton quitter
     * @param Info : les labels d'informations
     * @param nbIter : le nombre d'itérations
     */
    private FlowPane GameVisual;
    private int l;
    private Label[][] cells;
    private Fourmiliere myFourmiliere;
    private AllComponentsBtns components;
    private Button Quit;
    private LabelsInfo Info;

    private int nbIter;
    public TheVue(Fourmiliere anthill)
    {
        super();
        initVue(anthill);
    }

    public Button getQuit() {
        return Quit;
    }

    public AllComponentsBtns getComponents() {
        return components;
    }

    /**
     * permet d'afficher la grille
     *
     */

    public void setGrilleVisible()
    {
        for (int i=0;i<l;i++)
        {
            for(int j=0;j<l;j++)
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

    /**
     * permet de cacher la grille
     *
     */

    public void setGrilleNotVisible()
    {
        for (int i=0;i<l;i++)
        {
            for(int j=0;j<l;j++) {
                cells[i][j].setBorder(Border.EMPTY);
            }
        }
    }

    /**
     * permet de mettre la bordure au flowPane GameVisual
     *
     */
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

    /**
     * permet de retirer la bordure au flowPane GameVisual
     *
     */

    public void removeBorderToGameVisual()
    {
        GameVisual.setBorder(Border.EMPTY);
    }


    /**
     * permet d'initialiser la vue avec la fourmiliere
     *
     */
    public void initVue(Fourmiliere anthill)
    {
        myFourmiliere = anthill;
        GameVisual = new FlowPane();
        //initialisation de la taille du gameTaille...

        l = myFourmiliere.getLargeur();

        GameVisual.setMinSize(200,200);
        GameVisual.setHgap(0);
        GameVisual.setVgap(0);
        GameVisual.setPrefWrapLength(l*10);

        components = new AllComponentsBtns();

        Quit = new Button("Quit");
        Info = new LabelsInfo();

        // Initialisation de la table cells...
        // && Ajout de chaque cell au flowPane GameVisual...

        cells = new Label[l][l];

        rechargeTabCells();
        addBorderToGameVisual();
        finalCodeAdd();
    }

    /**
     * permet d'ajouter les cells au flowPane GameVisual
     *
     */

    public void rechargeTabCells() {
        for (int i=0;i<l;i++)
        {
            for(int j=0;j<l;j++)
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
    }


    /**
     * permet de mettre à jour la vue avec la nouvelle largeur
     *
     */

    public void updateGameVisual(int newLargeur)
    {
        removeBorderToGameVisual();

        //System.out.printf("entrée dans la fonction updateGameVisual %n");
        GameVisual.getChildren().clear();

        this.getChildren().clear();

        l = myFourmiliere.getLargeur();
        //System.out.printf("stockage de la nouvelle largeur du model %n");

        GameVisual.setMinSize(200,200);
        GameVisual.setHgap(0);
        GameVisual.setVgap(0);
        GameVisual.setPrefWrapLength(l*10);

        components.updateAllComponents(newLargeur,
                components.getCapacityCase().getContainerValue(),
                components.getSimulationVitesse(),components.getNbGrains().getContainerValue(),
                components.getNbFourmis().getContainerValue(),components.getNbMurs().getContainerValue());

        // Initialisation de la table cells...
        // && Ajout de chaque cell au flowPane GameVisual...

        cells = new Label[l][l];
        rechargeTabCells();
        addBorderToGameVisual();

        finalCodeAdd();
    }

    /**
     * permet de mettre à jour la vue avec un nouveau nombre de fourmis
     *
     */

    public void updateGameVisualNbFourmi(int nbAnts)
    {
        initialValues();
        components.updateAllComponents(components.getTaillePlateauJeu().getContainerValue(),
                components.getCapacityCase().getContainerValue(),
                components.getSimulationVitesse(),components.getNbGrains().getContainerValue(),
                nbAnts,components.getNbMurs().getContainerValue());

        // Initialisation de la table cells...
        // && Ajout de chaque cell au flowPane GameVisual...

        

        cells = new Label[l][l];
        rechargeTabCells();

        finalCodeAdd();
    }

    /**
     * permet d'initialiser les attributs de la vue largeur, GameVisual...
     *
     */
    public void initialValues() {
        removeBorderToGameVisual();
        GameVisual.getChildren().clear();
        this.getChildren().clear();

        l = myFourmiliere.getLargeur();

        GameVisual.setMinSize(200,200);
        GameVisual.setHgap(0);
        GameVisual.setVgap(0);
        GameVisual.setPrefWrapLength(l*10);
    }


    /**
     * permet de mettre à jour la vue avec un nouveau nombre de murs
     *
     */
    public void updateGameVisualNbMurs(int nbMurs)
    {
        initialValues();

        components.updateAllComponents(components.getTaillePlateauJeu().getContainerValue(),
                components.getCapacityCase().getContainerValue(),
                components.getSimulationVitesse(),components.getNbGrains().getContainerValue(),
                components.getNbFourmis().getContainerValue(),nbMurs);

        // Initialisation de la table cells...
        // && Ajout de chaque cell au flowPane GameVisual...

        cells = new Label[l][l];
        rechargeTabCells();
        addBorderToGameVisual();

        finalCodeAdd();
    }

    /**
     * permet de mettre à jour la vue avec un nouveau nombre de grains
     *
     */

    public void updateGameVisualNbGrains(int nbGrains)
    {
        initialValues();

        components.updateAllComponents(components.getTaillePlateauJeu().getContainerValue(),
                components.getCapacityCase().getContainerValue(),
                components.getSimulationVitesse(),nbGrains,
                components.getNbFourmis().getContainerValue(),
                components.getNumberMurs());

        // Initialisation de la table cells...
        // && Ajout de chaque cell au flowPane GameVisual...

        cells = new Label[l][l];
        rechargeTabCells();
        addBorderToGameVisual();
        finalCodeAdd();
    }

    /**
     * permet d'aligner et éventuellement tous les composants de la vue'
     *
     */
    public void finalCodeAdd()
    {
        Info = new LabelsInfo();
        setTop(Info);
        setAlignment(Info,Pos.TOP_CENTER);
        setLeft(GameVisual);
        setAlignment(GameVisual, Pos.CENTER_LEFT);
        setRight(components);
        setAlignment(components,Pos.CENTER_RIGHT);
        setBottom(Quit);
        setAlignment(Quit,Pos.BOTTOM_RIGHT);
        this.setPadding(new Insets(10));
        //Initialiser le terrain avec les paramètres de l'utilisateur...
        this.updateContainerFourmiliere();
        changeGrilleVisibility();
        //mise à jour du terrain...
        this.changeCellBackgroundOnContainer();
    }


    /**
     * permet de mettre à jour la visibilité de la grille
     *
     */
    public void changeGrilleVisibility()
    {
        switch (UltimateBtns.index_Pause_Play) {
            case 0 -> this.setGrilleVisible();
            case 1 ->
                this.setGrilleNotVisible();
        }
    }

    /**
     * permet de mettre à jour le contenu des cellules
     *
     */

    public void changeCellBackgroundOnContainer()
    {
        nbIter++;
        int nbAnts=0,nbMurs=0,nbGrains=0;
        for (int i = 0; i < l; i++)
        {
            for (int j = 0; j < l; j++)
            {
                if (Objects.equals(myFourmiliere.getCellContenu(i, j), "X"))
                {
                    cells[i][j].setStyle("-fx-background-color: green;-fx-font-size: 5;" +
                            "-fx-alignment: center;");
                    nbAnts++;
                }

                else if (Objects.equals(myFourmiliere.getCellContenu(i, j), "O"))
                {
                    cells[i][j].setStyle("-fx-background-color: red;-fx-font-size: 5;" +
                            "-fx-alignment: center;");
                    nbMurs++;
                }

                else if (myFourmiliere.getCellContenu(i, j).contains("."))
                {
                    int nbCount = myFourmiliere.getCellContenu(i, j).length();
                    double opacity = (nbCount * 0.1);
                    cells[i][j].setStyle("-fx-background-color: blue;-fx-font-size: 5;" +
                            "-fx-alignment: center;-fx-opacity: " + opacity + ";");
                    nbGrains+=nbCount;
                }
                else
                    cells[i][j].setStyle("-fx-background-color: white;-fx-font-size: 5;" +
                            "-fx-alignment: center;");
            }
        }
        Info.updateInfo(nbAnts,nbMurs,nbGrains,nbIter);
    }

    /**
     * permet de retourner le contenu d'une cellule
     * @return un label
     * @param x l'abscisse de la cellule
     * @param y l'ordonnée de la cellule
     */
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


    /**
     * permet de mettre à jour  la fourmilière au tout debut lorsque l'utilisateur définie les paramètres du jeu... Et
     * donc n'est appelé qu'une seule fois
     *
     */

    public void updateContainerFourmiliere()
    {
        nbIter=0;
        int min = 0, max = myFourmiliere.getLargeur() - 1;
        Random random = new Random();
        int fourmisCount = 0, grainsCount = 0, mursCount = 0;
        int maxAttempts = 10000; // Limite le nombre d'essais pour placer les éléments.
        int attempts = 0;

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
                }
                else if (grainsCount < components.getNumberGrains())
                {
                    int nbCountGrains = random.nextInt(myFourmiliere.getQMax() + 1);
                    myFourmiliere.setValueContenu(x, y, ".".repeat(nbCountGrains));
                    grainsCount++;
                }
                else if (mursCount < components.getNumberMurs())
                {
                    myFourmiliere.setValueContenu(x, y, "O");
                    mursCount++;
                }
            }

            attempts++; // Incrémente le compteur d'essais.
        }
    }

    /**
     * fonction à l'origine de la deuxième fenêtre qui permet de zoomer sur une cellule
     *
     */

    public void openNewWindow(int x,int y)
    {
        Stage zoomStage = new Stage();
        zoomStage.setTitle("Zoom window");
        int length = 10;

        BorderPane root = new BorderPane();
        FlowPane zoomf = new FlowPane();

        zoomf.setMinSize(100,100);
        zoomf.setHgap(0);
        zoomf.setVgap(0);
        zoomf.setPrefWrapLength(10*length);
        zoomf.setAlignment(Pos.CENTER);

        int initX= x-5,
                finalX = x+5,
                initY = y-5,
                finalY = y+5;

        //je centre la fnêtre que je vais regarder...
        root.setCenter(zoomf);
        BorderPane.setAlignment(zoomf,Pos.CENTER);


        Scene scene = new Scene(root,210,330);

        for(int i=initX;i<finalX;i++)
        {
            for(int j=initY;j<finalY;j++)
            {
                if (!(i < 0 || i >= myFourmiliere.getLargeur() ||
                        j < 0 || j >= myFourmiliere.getLargeur()))
                {

                    cells[i][j].setMaxWidth(20);
                    cells[i][j].setMinWidth(20);
                    cells[i][j].setMinHeight(20);
                    cells[i][j].setMaxHeight(20);
                    zoomf.getChildren().add(cells[i][j]);
                }
            }
        }

        zoomStage.setOnCloseRequest(e->
        {
            for(int i=initX;i<finalX;i++)
            {
                for(int j=initY;j<finalY;j++)
                {
                    if (!(i < 0 || i >= myFourmiliere.getLargeur() ||
                            j < 0 || j >= myFourmiliere.getLargeur()))
                    {
                        cells[i][j].setMaxWidth(10);
                        cells[i][j].setMinWidth(10);
                        cells[i][j].setMinHeight(10);
                        cells[i][j].setMaxHeight(10);
                        GameVisual.getChildren().add(cells[i][j]);
                    }
                }
            }
        });
        zoomStage.setScene(scene);
        zoomStage.show();
    }

}
