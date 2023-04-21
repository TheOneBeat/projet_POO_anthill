package theAnthill_project.theController;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import theAnthill_project.theModel.Fourmiliere;
import theAnthill_project.theView.TheVue;
import theAnthill_project.theView.UltimateBtns;

import java.util.Objects;
import java.util.Optional;

public class TheController
{
    private TheVue vue;
    private Fourmiliere f;
    private final Service<Void> taskservice;
    private final int INITIAL_SPEED = 1000; // 1000 millisecondes = 1 seconde
    private int currentSpeed = INITIAL_SPEED;

    private BooleanProperty ignoreChangeEvent = new SimpleBooleanProperty(false);


    private int compteurLoop = 0;
    // Valeur initiale en millisecondes


    public TheController(TheVue myVue, Fourmiliere anthill)
    {
        vue = myVue;
        f = anthill;
        OnclickLabel();

        taskservice = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception
                    {
                        do {
                            f.evolue();
                            // Utilisez Platform.runLater pour appeler cette méthode sur le thread JavaFX
                            Platform.runLater(() -> {
                                vue.changeCellBackgroundOnContainer();
                            });
                            Thread.sleep(currentSpeed);
                            if (isCancelled())
                                break;
                        } while (!isCancelled());
                        return null;
                    }
                };
            }
        };

        //à chaque clique sur le btn play, l'image change...
        // avec le fait d'afficher les grilles ou pas...
        vue.getComponents().getUltimateBtns().getPause_Play().
                setOnAction(e->{
                    vue.getComponents().getUltimateBtns().changeImagePlay();
                    vue.changeGrilleVisibility();

                    if (UltimateBtns.index_Pause_Play==0)
                        execute();
                    else
                        taskservice
                                .cancel();

                });

        //Les events...
        //event de sortie du jeu après le clique sur le bouton quit...


        vue.getQuit().setOnAction(e-> Platform.exit());

        //Le bouton Reset
        vue.getComponents().getUltimateBtns().getReset()
                .setOnAction(e->{
                    if (showConfirmationDialog("Réinitialiser le plateau",
                            "Êtes-vous sûr de vouloir réinitialiser le plateau ?",
                            "Toutes les modifications en cours seront perdues."))
                    {
                        // Code pour réinitialiser le plateau
                        changeLargeur(f.getLargeur());
                    }

                });

        //Modification de taillePlateau...

        vue.getComponents().addTaillePlateauJeuChangeListener((observable, oldValue, newValue) ->
        {
            if (ignoreChangeEvent.get())
                return;

            if (showConfirmationDialog("Réinitialiser le plateau",
                    "Êtes-vous sûr de vouloir réinitialiser le plateau ?",
                    "Toutes les modifications en cours seront perdues."))
            {
                // Code pour réinitialiser le plateau
                try {
                    int newLargeur = Integer.parseInt(newValue);
                    if (newLargeur>50)
                    {
                        taskservice.reset();//ce que je viens d'ajouter
                        changeLargeur(50);
                    }
                    else
                    {
                        taskservice.reset();//ce que je viens d'ajouter
                        changeLargeur(newLargeur);
                    }

                } catch (NumberFormatException e) {
                    // Gérer l'exception si la valeur n'est pas un nombre entier
                    changeLargeur(20);
                }
            }
            else
            {
                ignoreChangeEvent.set(true);
                vue.getComponents().getTaillePlateauJeu().updateValue(Integer.parseInt(oldValue));
                ignoreChangeEvent.set(false);
            }
        });

        //Modification de la capacité de grains pour chaque cellule...

        vue.getComponents().addCapacityChangeListener((observable, oldValue, newValue) ->
        {
            if (ignoreChangeEvent.get())
                return;

            if (showConfirmationDialog("Réinitialiser la capacité de chaque cellule",
                    "Êtes-vous sûr de vouloir changer la capacité ?",
                    "Toutes les modifications en cours seront perdues."))
            {
                // Code pour réinitialiser la capactié de chaque cellule
                try {
                    int newLargeur = Integer.parseInt(newValue);
                    changeCapacityG(newLargeur);
                } catch (NumberFormatException e) {
                    // Gérer l'exception si la valeur n'est pas un nombre entier
                    changeCapacityG(2);
                }
            }
            else
            {
                ignoreChangeEvent.set(true);
                vue.getComponents().getCapacityCase().updateValue(Integer.parseInt(oldValue));
                ignoreChangeEvent.set(false);
            }
        });

        //la vitesse d'execution du service

        vue.getComponents().getSlider().getSlideProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1)
            {
                if (ignoreChangeEvent.get())
                    return;

                if (showConfirmationDialog("Réinitialiser la vitesse du jeu",
                        "Êtes-vous sûr de vouloir réinitialiser la vitesse du jeu ?",
                        "Le jeu changera de vitesse, en fonction de votre paramètre..."))
                {
                    // Code pour réinitialiser la vitesse d'exécution du service
                    double value = t1.doubleValue();
                    currentSpeed = (int)(INITIAL_SPEED/value);
                }
                else
                {
                    ignoreChangeEvent.set(true);
                    vue.getComponents().getSimulation().changeValue(number.doubleValue());
                    ignoreChangeEvent.set(false);
                }

            }
        });

        //modification du nombre de fourmis

        vue.getComponents().addFourmisChangeListener((observableValue, s, t1) ->
        {
            if (ignoreChangeEvent.get())
                return;

            if (showConfirmationDialog("Réinitialiser le nombre de fourmis",
                    "Êtes-vous sûr de vouloir réinitialiser le nombre de fourmis ?",
                    "Toutes les modifications en cours seront perdues."))
            {
                // Code pour réinitialiser le nb de fourmis
                try
                {
                    int newValue = Integer.parseInt(t1);
                    changeNbFourmis(newValue);
                }
                catch (NumberFormatException e)
                {
                    changeNbFourmis(0);
                }
            }
            else
            {
                ignoreChangeEvent.set(true);
                vue.getComponents().getNbFourmis().updateValue(Integer.parseInt(s));
                ignoreChangeEvent.set(false);
            }
        });


        //modification du nombre de murs

        vue.getComponents().addMursChangeListener((observableValue, s, t1) ->
        {
            if (ignoreChangeEvent.get())
                return;

            if (showConfirmationDialog("Réinitialiser le nombre de murs",
                    "Êtes-vous sûr de vouloir réinitialiser le nombre de murs ?",
                    "Toutes les modifications en cours seront perdues."))
            {
                // Code pour réinitialiser le nb de murs
                try
                {
                    int newValue = Integer.parseInt(t1);
                    changeNbMurs(newValue);
                }
                catch (NumberFormatException e)
                {
                    changeNbMurs(0);
                }
            }
            else
            {
                ignoreChangeEvent.set(true);
                vue.getComponents().getNbMurs().updateValue(Integer.parseInt(s));
                ignoreChangeEvent.set(false);
            }
        });

        //modification du nombre de grains au total sur le terrain...
        // indépendamment de la capacité des cellules

        vue.getComponents().addGrainsChangeListener((observableValue, s, t1) ->
        {
            if (ignoreChangeEvent.get())
                return;

            if (showConfirmationDialog("Réinitialiser le nombre de grains ",
                    "Êtes-vous sûr de vouloir réinitialisé le nombre de grains sur le plateau ?",
                    "Toutes les modifications en cours seront perdues."))
            {
                // Code pour réinitialiser le nb de Grains
                try
                {
                    int newValue = Integer.parseInt(t1);
                    changeNbGrains(newValue);
                }
                catch (NumberFormatException e)
                {
                    changeNbGrains(0);
                }
            }
            else
            {
                ignoreChangeEvent.set(true);
                vue.getComponents().getNbGrains().updateValue(Integer.parseInt(s));
                ignoreChangeEvent.set(false);
            }

        });

        //Le bouton Loop...

        vue.getComponents().getUltimateBtns().getLoop().setOnAction(e->
        {
            compteurLoop++;
        });
    }

    //Boîte de dialogue
    private boolean showConfirmationDialog(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public void changeLargeur(int newLargeur)
    {
        //Update de la fourmiliere
        f.updateFourmiliere(newLargeur);
        System.out.printf("updateFourmilière réussi de l%n");
        //Update de la vue
        vue.updateGameVisual(newLargeur);
        System.out.printf("updateGameVisual de la vue réussi de l%n");
        OnclickLabel();
        System.out.printf(" OnclickLabel du controller réussi de l%n");
    }

    public void changeCapacityG(int cap)
    {
        //Update de la fourmiliere
        //f.setQMax(cap);ça marche mais ne réinitialise pas le terrain...
        f.updateCapFourmiliere(f.getLargeur(),cap); //reinitialise le terrain...
        vue.updateGameVisual(f.getLargeur());
        OnclickLabel();
    }

    public void changeNbFourmis(int value)
    {
        f.updateFourmiliere(f.getLargeur());
        vue.updateGameVisualNbFourmi(value);
        OnclickLabel();
    }

    public void changeNbMurs(int murs)
    {
        f.updateFourmiliere(f.getLargeur());
        vue.updateGameVisualNbMurs(murs);
        OnclickLabel();
    }

    public void changeNbGrains(int grains)
    {
        f.updateFourmiliere(f.getLargeur());
        vue.updateGameVisualNbGrains(grains);
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
                        if (compteurLoop % 2 !=0)
                        {
                            vue.openNewWindow(finalI,finalJ);
                            vue.changeCellBackgroundOnContainer();
                        }
                    }


                    if (e.isShiftDown())
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

    public void execute()
    {
        System.out.println("execution enclenchée - no modif \n");
        vue.getComponents().setDisableAllComponents(true);
        vue.getComponents().getUltimateBtns().setDisableUltimateBtns(true);

        if (taskservice.isRunning()) {
            taskservice.cancel();
        }

        taskservice.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State state, Worker.State t1) {
                switch (t1)
                {
                    case FAILED, CANCELLED->
                    {
                        System.out.println("the service is cancelled");
                        vue.getComponents().setDisableAllComponents(false);
                        vue.getComponents().getUltimateBtns().setDisableUltimateBtns(false);
                        taskservice.reset();
                    }
                    case SUCCEEDED ->
                    {
                        System.out.println("the service succeeded");
                        vue.getComponents().setDisableAllComponents(false);
                        vue.getComponents().getUltimateBtns().setDisableUltimateBtns(false);
                        changeLargeur(f.getLargeur());
                        taskservice.reset();
                    }
                }
            }
        });

        // Ajouter un gestionnaire d'exceptions pour le service
        taskservice.setOnFailed(workerStateEvent -> {
            Throwable exception = taskservice.getException();
            if (exception != null) {
                exception.printStackTrace();
            } else {
                System.out.println("Le service a échoué sans exception.");
            }
        });
        taskservice.reset();
        taskservice.start();
    }
}