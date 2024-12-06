package co.edu.uptc.project1priorityqueues.view;

import co.edu.uptc.project1priorityqueues.logic.PatientController;
import co.edu.uptc.project1priorityqueues.logic.ThreadsPatient;
import co.edu.uptc.project1priorityqueues.model.Patient;
import com.sun.source.tree.Tree;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.util.*;

public class Main extends Application {

    private PatientController controller = new PatientController(new PatientController.PatientComparator());

    private Stage primaryStage;
    private Scene scene;
    private BorderPane root;

    private Label titleLabel;
    private StackPane stackPane;
    private HBox centerHBox;

    private VBox turnVBox1;
    private Label turnLabel1;
    private Label turnTitleLabel1;
    private TableView<Patient> table1;

    private VBox turnVBox2;
    private Label turnLabel2;
    private Label turnTitleLabel2;
    private TableView<Patient> table2;

    private VBox turnVBox3;
    private Label turnLabel3;
    private Label turnTitleLabel3;
    private TableView<Patient> table3;

    private VBox turnVBox4;
    private Label turnLabel4;
    private Label turnTitleLabel4;
    private TableView<Patient> table4;

    private Button addTurnBtn;

    private Timeline timeline1;
    private Timeline timeline2;
    private Timeline timeline3;

    private static double screenWidth;
    private static double screenHeight;

    private final Map<Integer, Integer> timerStates = new HashMap<>();

    private int timerId;
    private int currentTime;
    private int timerId2;
    private int currentTime2;
    private int timerId3;
    private int currentTime3;

    public Main() {
        primaryStage = new Stage();
        root = new BorderPane();
        screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        stackPane = new StackPane();
        scene = new Scene(root, screenWidth, screenHeight);
        titleLabel = new Label("Don Copito Medicines");
        addTurnBtn = new Button("Add Turn");
        centerHBox = new HBox();

        turnVBox1 = new VBox();
        turnLabel1 = new Label(" - ");
        turnTitleLabel1 = new Label("Window 1");
        table1 = new TableView<>();

        turnVBox2 = new VBox();
        turnLabel2 = new Label(" - ");
        turnTitleLabel2 = new Label("Window 2");
        table2 = new TableView<>();

        turnVBox3 = new VBox();
        turnLabel3 = new Label(" - ");
        turnTitleLabel3 = new Label("Window 3");
        table3 = new TableView<>();

        turnVBox4 = new VBox();
        turnLabel4 = new Label(" - ");
        turnTitleLabel4 = new Label("Window 4");
        table4 = new TableView<>();

        turnLabel1.setAlignment(Pos.CENTER);
        turnLabel2.setAlignment(Pos.CENTER);
        turnLabel3.setAlignment(Pos.CENTER);
        turnLabel4.setAlignment(Pos.CENTER);

        timeline1 = new Timeline();
        timeline2 = new Timeline();
        timeline3 = new Timeline();

        timerId = 0;
        currentTime = 0;
        timerId2 = 0;
        currentTime2 = 0;
        timerId3 = 0;
        currentTime3 = 0;
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene.getStylesheets()
                .add(new File("src/main/java/co/edu/uptc/project1priorityqueues/view/Style.css").toURI().toString());
        table1();
        table2();
        table3();

        turn1();
        turn2();
        turn3();

        action();
        threads();

        stackPane.getChildren().addAll(titleLabel, addTurnBtn);
        StackPane.setAlignment(addTurnBtn, Pos.CENTER_RIGHT);
        StackPane.setMargin(addTurnBtn, new Insets(0, 10, 0, 0));

        root.setId("root");
        root.setTop(stackPane);
        root.setCenter(centerHBox);

        titleLabel.setId("title");
        titleLabel.setPrefWidth(screenWidth);
        titleLabel.setPrefHeight(120);
        titleLabel.setAlignment(Pos.CENTER);

        centerHBox.getChildren().addAll(turnVBox1, turnVBox2, turnVBox3, turnVBox4);
        centerHBox.setSpacing(100);
        centerHBox.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setAlignment(table1, Pos.CENTER);
        BorderPane.setAlignment(addTurnBtn, Pos.CENTER_RIGHT);

        // Up, right, down, left
        BorderPane.setMargin(titleLabel, new Insets(0, 0, 30, 0));
        BorderPane.setMargin(addTurnBtn, new Insets(30, 60, 30, 0));

        primaryStage.setTitle("Don Copito");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void table1(){
        //System.out.println("Holi: " + controller.getTurn());
        TableColumn<Patient, String> turn = new TableColumn<>();
        turn.setCellValueFactory(new PropertyValueFactory<>("turn"));

        table1.getColumns().add(turn);

        List<Patient> patientList = controller.getPatients(1);

        ObservableList<Patient> patients = FXCollections.observableArrayList(patientList);
        table1.setItems(patients);

        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table1.setRowFactory(tv -> new TableRow<>() {
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

        table1.setPrefWidth(250);
        table1.setMaxHeight(250);
        HBox.setMargin(table1, new Insets(30, 0, 30, 0));
        HBox.setMargin(turnVBox1, new Insets(30, 0, 30, 0));
    }

    public void table2(){
        //System.out.println("Holi: " + controller.getTurn());
        TableColumn<Patient, String> turn = new TableColumn<>();
        turn.setCellValueFactory(new PropertyValueFactory<>("turn"));

        table2.getColumns().add(turn);

        List<Patient> patientList = controller.getPatients(2);

        ObservableList<Patient> patients = FXCollections.observableArrayList(patientList);
        table2.setItems(patients);

        table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table2.setRowFactory(tv -> new TableRow<>() {
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

        table2.setPrefWidth(250);
        table2.setMaxHeight(250);
        HBox.setMargin(table2, new Insets(30, 0, 30, 0));
        HBox.setMargin(turnVBox2, new Insets(30, 0, 30, 0));
    }

    public void table3(){
        //System.out.println("Holi: " + controller.getTurn());
        TableColumn<Patient, String> turn = new TableColumn<>();
        turn.setCellValueFactory(new PropertyValueFactory<>("turn"));

        table3.getColumns().add(turn);

        List<Patient> patientList = controller.getPatients(3);

        ObservableList<Patient> patients = FXCollections.observableArrayList(patientList);
        table3.setItems(patients);

        table3.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table3.setRowFactory(tv -> new TableRow<>() {
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

        table3.setPrefWidth(250);
        table3.setMaxHeight(250);
        HBox.setMargin(table3, new Insets(30, 0, 30, 0));
        HBox.setMargin(turnVBox3, new Insets(30, 0, 30, 0));
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

    public void turn1(){
        turnVBox1.getChildren().addAll(turnTitleLabel1, turnLabel1, table1);
        turnTitleLabel1.setId("turnTitle");
        turnLabel1.setId("turn");

        turnTitleLabel1.setPrefWidth(250);
        turnTitleLabel1.setMinHeight(80);
        turnTitleLabel1.setAlignment(Pos.CENTER);

        turnLabel1.setPrefWidth(250);
        turnLabel1.setMinHeight(200);
        turnLabel1.setAlignment(Pos.CENTER);

        turnVBox1.setSpacing(10);
        turnVBox1.setAlignment(Pos.CENTER);

        try {
            turnLabel1.setText(controller.getTurn(1).getFirst());
        } catch (Exception _) {

        }
    }

    public void turn2(){
        turnVBox2.getChildren().addAll(turnTitleLabel2, turnLabel2, table2);
        turnTitleLabel2.setId("turnTitle");
        turnLabel2.setId("turn");

        turnTitleLabel2.setPrefWidth(250);
        turnTitleLabel2.setMinHeight(80);
        turnTitleLabel2.setAlignment(Pos.CENTER);

        turnLabel2.setPrefWidth(250);
        turnLabel2.setMinHeight(200);
        turnLabel2.setAlignment(Pos.CENTER);

        turnVBox2.setSpacing(10);
        turnVBox2.setAlignment(Pos.CENTER);

        try {
            turnLabel2.setText(controller.getTurn(2).getFirst());
        } catch (Exception _) {

        }
    }

    public void turn3(){
        turnVBox3.getChildren().addAll(turnTitleLabel3, turnLabel3, table3);
        turnTitleLabel3.setId("turnTitle");
        turnLabel3.setId("turn");

        turnTitleLabel3.setPrefWidth(250);
        turnTitleLabel3.setMinHeight(80);
        turnTitleLabel3.setAlignment(Pos.CENTER);

        turnLabel3.setPrefWidth(250);
        turnLabel3.setMinHeight(200);
        turnLabel3.setAlignment(Pos.CENTER);

        turnVBox3.setSpacing(10);
        turnVBox3.setAlignment(Pos.CENTER);

        try {
            turnLabel3.setText(controller.getTurn(3).getFirst());
        } catch (Exception _) {

        }
    }

    public void threads() {
        ThreadsPatient thPatient1 = new ThreadsPatient(controller, 1);
        ThreadsPatient thPatient2 = new ThreadsPatient(controller, 2);
        ThreadsPatient thPatient3 = new ThreadsPatient(controller, 3);

        Thread tp1 = new Thread(thPatient1);
        Thread tp2 = new Thread(thPatient2);
        Thread tp3 = new Thread(thPatient3);

        tp1.start();
        tp2.start();
        tp3.start();

        controller.setManualThreadsCount();

        controller.incrementThreadCount();
        controller.incrementThreadCount();
        controller.incrementThreadCount();

        timeline1 = createOrUpdateTimer(turnLabel1, controller.getTime1(), 1);
        timeline2 = createOrUpdateTimer(turnLabel2, controller.getTime2(), 2);
        timeline3 = createOrUpdateTimer(turnLabel3, controller.getTime3(), 3);

        timeline1.play();
        timeline2.play();
        timeline3.play();
    }

    private Timeline createOrUpdateTimer(Label label, int timeLimit, int numList) {
        Timeline timeline = new Timeline();
        int[] currentTime = {getTimerState(numList)}; // Obtiene el estado inicial del temporizador

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            try {
                // Actualiza el texto del label con el turno y el tiempo actual
                label.setText(controller.getTurn(numList).getFirst() + "\n" + currentTime[0] + " s");
                currentTime[0]++; // Incrementa el tiempo actual

                // Guarda el estado actual del temporizador
                updateTimerState(numList, currentTime[0], numList);

                // Si se alcanza el límite de tiempo
                if (currentTime[0] > timeLimit) {
                    controller.deleteFirst(numList); // Elimina el primer turno
                    updateTableView(numList); // Actualiza la tabla
                    currentTime[0] = 0; // Reinicia el contador a 0
                    updateTimerState(numList, 0, numList); // Reinicia el estado en el mapa
                    timeline.playFromStart(); // Reinicia el temporizador
                }
            } catch (Exception e) {
                // Manejo de excepciones (por ejemplo, si no hay más turnos)
                currentTime[0] = 0; // Reinicia el contador
                updateTimerState(numList, 0, numList); // Reinicia el estado en el mapa
                label.setText("-"); // Muestra vacío si no hay turnos
                timeline.stop(); // Detiene el temporizador
            }
        });

        // Configura el timeline
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE); // Ciclos indefinidos
        return timeline;
    }

    private void updateTableView(int numList) {
        List<Patient> updatedPatients = controller.getPatients(numList);
        switch (numList) {
            case 1 -> table1.getItems().setAll(updatedPatients);
            case 2 -> table2.getItems().setAll(updatedPatients);
            case 3 -> table3.getItems().setAll(updatedPatients);
        }
    }


    public void action() {
        addTurnBtn.setOnAction(e -> {
            if (controller.getPatients(1).size() == 6) {
                controller.deleteFirst(1);
            }

            NewTurn newTurn = new NewTurn();
            newTurn.scene(primaryStage, controller, timerId, currentTime, timerId2, currentTime2, timerId3, currentTime3);
            primaryStage.setScene(newTurn.getScene());

            // Actualizar la vista y los temporizadores
            table1.setItems(FXCollections.observableArrayList(controller.getPatients(1)));
            table2.setItems(FXCollections.observableArrayList(controller.getPatients(2)));
            table3.setItems(FXCollections.observableArrayList(controller.getPatients(3)));

            timeline1.playFromStart(); // Continúa desde el punto actual o se actualiza según sea necesario
            timeline2.playFromStart();
            timeline3.playFromStart();
        });
    }

    private void updateTimerState(int timerId, int currentTime, int numList) {
        switch (numList){
            case 1 -> {
                this.timerId = timerId;
                this.currentTime = currentTime;
            }
            case 2 -> {
                timerId2 = timerId;
                currentTime2 = currentTime;
            }
            case 3 -> {
                timerId3 = timerId;
                currentTime3 = currentTime;
            }
        }
        timerStates.put(timerId, currentTime);
    }

    // Obtiene el estado del temporizador
    private int getTimerState(int timerId) {
        return timerStates.getOrDefault(timerId, 0);
    }

    public void setController(PatientController controller, int timerId, int currentTime, int timerId2, int currentTime2, int timerId3, int currentTime3) {
        this.controller = controller;
        this.timerId = timerId;
        this.currentTime = currentTime;
        this.timerId2 = timerId2;
        this.currentTime2 = currentTime2;
        this.timerId3 = timerId3;
        this.currentTime3 = currentTime3;

        updateTimerState(timerId, currentTime, 1);
        updateTimerState(timerId2, currentTime2, 2);
        updateTimerState(timerId3, currentTime3, 3);

        action();
    }

    public static void main(String[] args) {
        launch(args);
    }
}