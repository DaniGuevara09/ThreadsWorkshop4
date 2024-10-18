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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private PatientController controller = new PatientController(new PatientController.PatientComparator());

    private Stage primaryStage;
    private Scene scene;
    private BorderPane root;

    private Label titleLabel;
    private HBox centerHBox;
    private VBox turnVBox;
    private Label turnLabel;
    private Label turnTitleLabel;
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
        centerHBox = new HBox();
        turnVBox = new VBox();
        turnLabel = new Label("A 1");
        turnTitleLabel = new Label("Turn");
        table = new TableView<>();
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene.getStylesheets()
                .add(new File("src/main/java/co/edu/uptc/project1priorityqueues/view/Style.css").toURI().toString());
        table();
        turn();
        action();

        root.setId("root");
        root.setTop(titleLabel);
        root.setCenter(centerHBox);
        root.setBottom(addTurnBtn);

        titleLabel.setId("title");
        titleLabel.setPrefWidth(screenWidth);
        titleLabel.setPrefHeight(120);
        titleLabel.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setAlignment(table, Pos.CENTER);
        BorderPane.setAlignment(addTurnBtn, Pos.CENTER_RIGHT);

        // Up, right, down, left
        BorderPane.setMargin(titleLabel, new Insets(0, 0, 30, 0));
        BorderPane.setMargin(addTurnBtn, new Insets(30, 60, 30, 0));

        primaryStage.setTitle("Don Copito");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void table(){
        System.out.println("Holi: " + controller.getTurn());
        System.out.println("Controler 1: " + controller);
        for (int i = 0; i < controller.getTurn().size(); i++) {
            System.out.println(controller.getTurn().get(i));
        }
        TableColumn<Patient, String> turn = new TableColumn<>("Waiting List");
        turn.setCellValueFactory(new PropertyValueFactory<>("turn"));


        table.getColumns().add(turn);
        centerCellContent(turn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Patient patient, boolean empty) {
                super.updateItem(patient, empty);
                if (getIndex() == 0 && !empty) {
                    setStyle("-fx-background-color: #b8d1e7;");
                } else {
                    setStyle("");
                }
            }
        });

        // ______________________________________________________________________________________
        ObservableList<Patient> patients = FXCollections.observableArrayList(
                new Patient(false, false, "34 - 34"),
                new Patient(false, false, "34 - 34"),
                new Patient(false, false, "34 - 34"),
                new Patient(false, false, "34 - 34"),
                new Patient(false, false, "34 - 34"),
                new Patient(false, false, "34 - 34")
        );

        table.setItems(patients);
        table.setPrefWidth(600);
        table.setMaxHeight(660);
        centerHBox.setMaxHeight(696);
        HBox.setMargin(table, new Insets(30, 0, 30, 0));
        HBox.setMargin(turnVBox, new Insets(30, 0, 30, 0));
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

    public void turn(){
        turnVBox.getChildren().addAll(turnTitleLabel, turnLabel);
        turnTitleLabel.setId("turnTitle");
        turnLabel.setId("turn");

        turnTitleLabel.setPrefWidth(600);
        turnTitleLabel.setPrefHeight(90);
        turnTitleLabel.setAlignment(Pos.CENTER);

        turnLabel.setPrefWidth(600);
        turnLabel.setPrefHeight(360);
        turnLabel.setAlignment(Pos.CENTER);

        turnVBox.setSpacing(30);
        turnVBox.setAlignment(Pos.CENTER);

        centerHBox.setSpacing(180);
        centerHBox.setAlignment(Pos.CENTER);
        centerHBox.getChildren().addAll(turnVBox, table);
    }

    public void action(){
        addTurnBtn.setOnAction(e -> {
           NewTurn newTurn = new NewTurn();
           newTurn.scene(primaryStage, controller);
           primaryStage.setScene(newTurn.getScene());
        });
    }

    public void setController(PatientController controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        launch(args);
    }
}