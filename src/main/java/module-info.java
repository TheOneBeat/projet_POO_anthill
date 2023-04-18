module anthill_project {
    requires javafx.controls;
    requires javafx.fxml;


    exports theAnthill_project;
    opens theAnthill_project to javafx.fxml;

    opens tryTest.tryTest to javafx.fxml;
    exports tryTest.tryTest;


}