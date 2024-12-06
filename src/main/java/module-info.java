module co.edu.uptc.project1priorityqueues {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires com.google.gson;

    opens co.edu.uptc.project1priorityqueues.model to javafx.base, com.google.gson;
    opens co.edu.uptc.project1priorityqueues.logic to com.google.gson;
    exports co.edu.uptc.project1priorityqueues.view;
}