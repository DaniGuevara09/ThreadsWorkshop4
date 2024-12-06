module co.edu.uptc.project1priorityqueues {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens co.edu.uptc.project1priorityqueues.model to javafx.base;
    exports co.edu.uptc.project1priorityqueues.view;
}