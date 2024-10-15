package co.edu.uptc.project1priorityqueues.view;

import co.edu.uptc.project1priorityqueues.model.Patient;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private Stage primaryStage;
    private Scene scene;
    private BorderPane root;

    private Label titleLabel;
    private TableView<Patient> table;
    private Button addTurnBtn;

    private static double screenWidth;
    private static double screenHeight;

    public Main() {
        primaryStage = new Stage();
        root = new BorderPane();
        screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        scene = new Scene(root, screenWidth, screenHeight);
        titleLabel = new Label("Don Copito Medicines");
        addTurnBtn = new Button("Add Turn");
        table = new TableView<>();
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene.getStylesheets()
                .add(new File("src/main/java/co/edu/uptc/project1priorityqueues/view/Style.css").toURI().toString());

        root.setId("root");
        root.setTop(titleLabel);
        root.setCenter(table);
        root.setBottom(addTurnBtn);

        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setAlignment(table, Pos.CENTER);
        BorderPane.setAlignment(addTurnBtn, Pos.CENTER_RIGHT);

        // Up, right, down, left
        BorderPane.setMargin(titleLabel, new Insets(60, 0, 30, 0));
        BorderPane.setMargin(table, new Insets(30, 60, 30, 60));
        BorderPane.setMargin(addTurnBtn, new Insets(30, 60, 30, 0));

        primaryStage.setTitle("Motorcycle Information Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void table(){
        TableColumn<Patient, String> id = new TableColumn<>("Identification number");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Patient, String> turn = new TableColumn<>("Identification number");
    }

    public static void main(String[] args) {
        launch(args);
    }
}