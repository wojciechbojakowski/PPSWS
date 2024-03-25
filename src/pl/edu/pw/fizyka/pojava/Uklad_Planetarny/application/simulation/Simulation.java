package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.simulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.util.Duration;

@SuppressWarnings("serial")
public class Simulation extends JFXPanel {

    public void createScene() {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, true);
        scene.setFill(Color.LIGHTBLUE);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);

        Box box = new Box(200, 200, 200);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.RED);
        box.setMaterial(material);
        root.getChildren().addAll(box);

        scene.setCamera(camera);

        this.setScene(scene);

        // Create a Timeline to rotate the box
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> {
                    // Define rotation animation
                    box.setRotate(box.getRotate() + 1);
                }),
                new KeyFrame(Duration.seconds(0.01))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
