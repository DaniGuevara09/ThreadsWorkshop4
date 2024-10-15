package co.edu.uptc.project1priorityqueues.view;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.File;

public class Confirmation {
    private Stage stage;
    private Scene scene;

    private HBox root;
    private Label label;

    private Image icon;
    private ImageView imageView;

    private static double screenWidth;
    private static double screenHeight;

    public Confirmation() {
        root = new HBox();
        stage = new Stage();
        label = new Label();
        scene = new Scene(root, 800, 300);

        icon = new Image("file:src/utilities/confi.png");
        imageView = new ImageView(icon);

        screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
    }

    public void scene(String turn) {
        label.setText("You turn is " + turn); // Set the message to be displayed
        scene.getStylesheets()
                .add(new File("src/main/java/co/edu/uptc/project1priorityqueues/view/Style.css").toURI().toString());

        imageView.setFitWidth(120);
        imageView.setFitHeight(120);

        root.setId("root2");
        root.setSpacing(40);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(imageView, label);


        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        stage.setX((screenWidth - stage.getWidth()) / 2);
        stage.setY((screenHeight - stage.getHeight()) / 2);

        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> {
            stage.close();
        });

        pause.play();
    }
}