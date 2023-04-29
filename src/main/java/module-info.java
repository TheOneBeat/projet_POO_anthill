module anthill_project {
    requires javafx.controls;
    requires javafx.fxml;


    exports jeuDesFourmis.Game;
    opens jeuDesFourmis.Game to javafx.fxml;


}