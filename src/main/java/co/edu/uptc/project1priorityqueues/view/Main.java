package co.edu.uptc.project1priorityqueues.view;

import co.edu.uptc.project1priorityqueues.logic.PatientController;
import co.edu.uptc.project1priorityqueues.model.Patient;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private PatientController controller;
    private Stage primaryStage;
    private Scene scene;
    private BorderPane root;

    private Label titleLabel;
    private TableView<Patient> table;
    private Button addTurnBtn;

    private static double screenWidth;
    private static double screenHeight;

    public Main() {
        controller = new PatientController();
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
        table();

        titleLabel.setId("title");
        titleLabel.setPrefWidth(screenWidth);
        titleLabel.setPrefHeight(120);
        titleLabel.setAlignment(Pos.CENTER);

        root.setId("root");
        root.setTop(titleLabel);
        root.setCenter(table);
        root.setBottom(addTurnBtn);

        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setAlignment(table, Pos.CENTER);
        BorderPane.setAlignment(addTurnBtn, Pos.CENTER_RIGHT);

        // Up, right, down, left
        BorderPane.setMargin(titleLabel, new Insets(0, 0, 30, 0));
        BorderPane.setMargin(table, new Insets(30, 60, 30, 60));
        BorderPane.setMargin(addTurnBtn, new Insets(30, 60, 30, 0));

        primaryStage.setTitle("Don Copito");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void table(){
        TableColumn<Patient, String> id = new TableColumn<>("Identification number");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Patient, String> turn = new TableColumn<>("Turn");
        turn.setCellValueFactory(turnNum -> {
            String turnStr = controller.getTurn();
            return new javafx.beans.property.SimpleStringProperty(turnStr);
        });

        table.getColumns().addAll(id, turn);
        centerCellContent(id);
        centerCellContent(turn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // ______________________________________________________________________________________
        ObservableList<Patient> patients = FXCollections.observableArrayList(
                new Patient(1, false, false, "34 - 34"),
                new Patient(2, false, false, "34 - 34"),
                new Patient(3, false, false, "34 - 34"),
                new Patient(4, false, false, "34 - 34")
        );

        table.setItems(patients);
    }

    private <T> void centerCellContent(TableColumn<Patient, T> column) {
        column.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                    setAlignment(Pos.CENTER);
                    setPrefHeight(90);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}