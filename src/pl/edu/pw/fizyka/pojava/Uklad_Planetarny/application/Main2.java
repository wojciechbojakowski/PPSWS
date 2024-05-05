package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * Main Class in PPSWS
 * @author Wojciech Bojakowski
 * @since 16.03.2024
 */

@SuppressWarnings("serial")
public class Main2 extends JFrame {
	private JFXPanel jfxPanel;
	private void initAndShowGUI() {
        setTitle("3D Animation in JavaFX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        
        JMenuBar menuBar=new JMenuBar();
        JMenu menu = new JMenu();
        JMenuItem stopButton = new JMenuItem("stop");
        JMenuItem menuButton = new JMenuItem("menu");
        menu.add(stopButton);
        menu.add(menuButton);
        menuBar.add(menu);
        this.add(menuBar);
        
        jfxPanel = new JFXPanel();
        this.add(jfxPanel);

        // Initialize JavaFX inside the JFXPanel
        Platform.runLater(this::createScene);

        setVisible(true);
    }

    private void createScene() {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600, true);
        scene.setFill(Color.LIGHTBLUE);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-100);
        scene.setCamera(camera);


        Box box = new Box(200, 200, 200);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.RED);
        box.setMaterial(material);
        root.getChildren().addAll(box);

        scene.setCamera(camera);

        jfxPanel.setScene(scene);

        // Create a Timeline to rotate the box
		/*
		 * Timeline timeline = new Timeline( new KeyFrame(Duration.seconds(0), event ->
		 * { // Define rotation animation box.setRotate(box.getRotate() + 1); }), new
		 * KeyFrame(Duration.seconds(0.01)) );
		 * timeline.setCycleCount(Timeline.INDEFINITE); timeline.play();
		 */
     
    }

    public static void main(String[] args) {
        // Initialize JavaFX (required before creating any JavaFX components)
        Platform.startup(() -> {});

        SwingUtilities.invokeLater(() -> {
            Main2 main = new Main2();
            main.initAndShowGUI();
        });
    }
}
