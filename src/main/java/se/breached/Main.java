package se.breached;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO: Increase window size
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("scene.fxml")));
        Scene scene = new Scene(root,800,600);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("styles.css")).toExternalForm());

        primaryStage.setTitle("Have you been breached?");
        primaryStage.getIcons().add(new Image("/logos/danger.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
