package co.edu.uptc.project1priorityqueues.view;

import co.edu.uptc.project1priorityqueues.model.Patient;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private Scene scene;
    private VBox root;

    private Label titleLabel;
    private TableView<Patient> table;
    private Button addTurnBtn;

    private static double screenWidth;
    private static double screenHeight;

    public Main() {
        primaryStage = new Stage();
        root = new VBox();
        scene = new Scene(root, screenWidth, screenHeight);
        titleLabel = new Label("Medicamentos don Copito");
        addTurnBtn = new Button("Add Patient");
        table = new TableView<>();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void main(String[] args) {

    }
}
