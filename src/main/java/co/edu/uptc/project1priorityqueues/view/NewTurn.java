package co.edu.uptc.project1priorityqueues.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.File;

public class NewTurn {
    private Stage primaryStage;
    private Scene scene;
    private BorderPane root;

    private Image image;
    private Image icon1;
    private Image icon2;
    private ImageView imageView;
    private ImageView iconView1;
    private ImageView iconView2;

    private VBox vBoxInfo;

    private HBox hBoxDisability;
    private Circle cirDisability;
    private HBox hLblDisability;
    private Label lblDisability;

    private HBox hBoxPregnant;
    private Circle cirPregnant;
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
        icon1 = new Image("file:src/utilities/disability.png");
        icon2 = new Image("file:src/utilities/pregnant.png");
        imageView = new ImageView(image);
        iconView1 = new ImageView(icon1);
        iconView2 = new ImageView(icon2);
        vBoxInfo = new VBox();

        hBoxDisability = new HBox();
        cirDisability = new Circle(65);
        hLblDisability = new HBox();
        lblDisability = new Label("Disability");

        hBoxPregnant = new HBox();
        cirPregnant = new Circle(65);
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
        vBoxInfo.setSpacing(90);
        VBox.setMargin(btnConfirm, new Insets(0, (screenWidth / 2 - 200) / 2 , 0, (screenWidth / 2 - 200) / 2));
        BorderPane.setMargin(vBoxInfo, new Insets(90, 0, 0, 0));

        imageView.setPreserveRatio(true);
        imageView.setFitWidth(screenWidth/2);

        BorderPane.setAlignment(vBoxInfo, Pos.CENTER);

        root.setTop(title);
        root.setRight(imageView);
        root.setLeft(vBoxInfo);
    }

    public void info(){
        hBoxInfo();
        hLblDisability.getChildren().addAll(iconView1, lblDisability);
        hLblDisability.setAlignment(Pos.CENTER);
        hLblDisability.setSpacing(60);
        hBoxDisability.getChildren().addAll(cirDisability, hLblDisability);

        hLblPregnant.getChildren().addAll(iconView2, lblPregnant);
        hLblPregnant.setAlignment(Pos.CENTER);
        hLblPregnant.setSpacing(60);
        hBoxPregnant.getChildren().addAll(cirPregnant, hLblPregnant);
        hBoxAge.getChildren().addAll(lblAge, comboAge);

        vBoxInfo.getChildren().addAll(hBoxDisability, hBoxPregnant, hBoxAge, btnConfirm);
    }

    public void hBoxInfo(){
        iconView1.setFitWidth(90);
        iconView1.setFitHeight(90);
        iconView2.setFitWidth(90);
        iconView2.setFitHeight(90);

        cirDisability.setId("circle");
        cirPregnant.setId("circle");
        hLblDisability.setId("hBox");
        hLblPregnant.setId("hBox");
        lblAge.setId("hBoxAge");
        comboAge.setId("hBoxAge");

        // 900 - 650
        // 400 -

        hBoxDisability.setSpacing(60);
        hBoxPregnant.setSpacing(60);
        hBoxAge.setSpacing(60);

        hBoxDisability.setAlignment(Pos.CENTER);
        hBoxPregnant.setAlignment(Pos.CENTER);
        hBoxAge.setAlignment(Pos.CENTER);
        lblAge.setAlignment(Pos.CENTER);
        setCombo();
    }

    public void setCombo(){
        comboAge.setValue("18 - 59");
        comboAge.getItems().addAll("18 - 59", "≥ 60");

        comboAge.setCellFactory(param -> {
            ListCell<String> cell = new ListCell<>();
            cell.setAlignment(Pos.CENTER);
            cell.itemProperty().addListener((obs, oldItem, newItem) -> {
                if (newItem != null) cell.setText(newItem);
                else cell.setText(null);
            });
            return cell;
        });

        comboAge.setButtonCell(new ListCell<>() {{
            setAlignment(Pos.CENTER);
            itemProperty().addListener((obs, oldItem, newItem) -> setText(newItem != null ? (String) newItem : ""));
        }});
    }

    public void actions(){
        btnConfirm.setOnAction(e -> {
            Main main = new Main();
            try {
                primaryStage.close();
                main.start(primaryStage);
                Confirmation config = new Confirmation();
                config.scene("A 12");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Buttons
        cirDisability.setOnMouseClicked(event -> {
            if (cirDisability.getFill().equals(Color.WHITE)) {
                cirDisability.setFill(Color.web("#0960ae"));
            } else {
                cirDisability.setFill(Color.WHITE);
            }
        });

        cirPregnant.setOnMouseClicked(event -> {
            if (cirPregnant.getFill().equals(Color.WHITE)) {
                cirPregnant.setFill(Color.web("#0960ae"));
            } else {
                cirPregnant.setFill(Color.WHITE);
            }
        });
    }
    public Scene getScene() {
        return scene;
    }
}