package co.edu.uptc.project1priorityqueues.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class NewTurn {
    private Stage primaryStage;
    private Scene primaryScene;
    private Scene scene;
    private BorderPane root;

    private Image image;
    private ImageView imageView;
    private VBox vBoxInfo;

    private HBox hBoxDisability;
    private Button btnDisability;
    private HBox hLblDisability;
    private Label lblDisability;

    private HBox hBoxPregnant;
    private Button btnPregnant;
    private HBox hLblPregnant;
    private Label lblPregnant;

    private HBox hBoxAge;
    private Label lblAge;
    private ComboBox comboAge;

    private Label title;
    private Button btnConfirm;

    private static double screenWidth;
    private static double screenHeight;

    public NewTurn() {
        primaryStage = new Stage();
        root = new BorderPane();
        screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        scene = new Scene(root, screenWidth, screenHeight);

        image = new Image(new File("src/utilities/image.jpg").toURI().toString());
        imageView = new ImageView(image);
        vBoxInfo = new VBox();

        hBoxDisability = new HBox();
        btnDisability = new Button("");
        hLblDisability = new HBox();
        lblDisability = new Label("Disability");

        hBoxPregnant = new HBox();
        btnPregnant = new Button("");
        hLblPregnant = new HBox();
        lblPregnant = new Label("Pregnant");

        hBoxAge = new HBox();
        lblAge = new Label("Age Range");
        comboAge = new ComboBox();

        title = new Label("New Turn");
        btnConfirm = new Button("Confirm");
    }

    public void scene(Stage primaryStage) {
        this.primaryStage = primaryStage;
        scene.getStylesheets()
                .add(new File("src/main/java/co/edu/uptc/project1priorityqueues/view/Style.css").toURI().toString());

        info();
        actions();

        root.setId("root");
        title.setId("title");
        title.setPrefWidth(screenWidth);
        title.setPrefHeight(120);
        title.setAlignment(Pos.CENTER);

        vBoxInfo.setPrefWidth(screenWidth/2);
        vBoxInfo.setAlignment(Pos.CENTER);

        imageView.setPreserveRatio(true);
        imageView.setFitWidth(screenWidth/2);

        root.setTop(title);
        root.setRight(imageView);
        root.setLeft(vBoxInfo);
    }

    public void info(){
        hBoxDisability.getChildren().addAll(btnDisability, hLblDisability);
        hBoxInfo();

        hBoxPregnant.getChildren().addAll(btnPregnant, hLblPregnant);
        hBoxAge.getChildren().addAll(lblAge, comboAge);

        vBoxInfo.getChildren().addAll(hBoxDisability, hBoxPregnant, hBoxAge, btnConfirm);
    }

    public void hBoxInfo(){
        btnDisability.setId("btnCircle");
        btnDisability.setMinSize(100, 100);
        btnDisability.setMaxSize(100, 100);
    }

    public void actions(){
        btnConfirm.setOnAction(e -> {
            Main main = new Main();
            try {
                primaryStage.close();
                main.start(primaryStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    public Scene getScene() {
        return scene;
    }
}
