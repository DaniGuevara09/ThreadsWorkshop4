module co.edu.uptc.project1priorityqueues {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uptc.project1priorityqueues.model to javafx.base;
    exports co.edu.uptc.project1priorityqueues.view;
}