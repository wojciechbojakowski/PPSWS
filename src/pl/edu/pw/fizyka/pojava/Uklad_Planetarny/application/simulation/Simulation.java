package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.simulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.util.Duration;
import javafx.beans.property.*;

@SuppressWarnings("serial")
public class Simulation extends JFXPanel{
		private double anchorX, anchorY;
		  private double anchorAngleX = 0;
		  private double anchorAngleY = 0;
		  private final DoubleProperty angleX = new SimpleDoubleProperty(0);
		  private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    public void createScene() {
    	int WIDTH = this.getWidth();
    	int HEIGHT = this.getHeight();
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, true);
        scene.setFill(Color.LIGHTBLUE);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        

        Box box = new Box(200, 200, 200);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.RED);
        
        box.setMaterial(material);
        root.getChildren().addAll(box);
        box.setTranslateZ(-800);
        
        box.translateXProperty().set(WIDTH / 2);
        box.translateYProperty().set(HEIGHT / 2);
        
        Text  text  =  new  Text();
        text.setX(40);
        text.setY(100);
        text.setFont(new Font(25));
        text.setText("Welcome JavaFX!");
        root.getChildren().add(text);
        
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
    
    private Scene createScene2() {
        SmartGroup  root  =  new SmartGroup();
        Scene  scene  =  new  Scene(root, Color.ALICEBLUE);
        Sphere sphere = new Sphere(50);
        sphere.setTranslateX(300);
        sphere.setTranslateY(40);
        sphere.setTranslateZ(300);
        root.getChildren().add(sphere);
        
        initMouseControl(root,scene);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
              case W:
                //root.translateZProperty().set(root.getTranslateZ() + 100);
                break;
              case S:
                //root.translateZProperty().set(root.getTranslateZ() - 100);
                break;
              case Q:
            	  root.rotateByX(10);
            	  break;
              case E:
            	  root.rotateByX(-10);
            	  break;
              case NUMPAD6:
            	  root.rotateByY(10);
            	  break;
              case NUMPAD4:
            	  root.rotateByY(-10);
            	  break;
               default:
              	 break;
            }
          });
        return (scene);
    }
    
    public void initFX() {
        // This method is invoked on the JavaFX thread
        Scene scene = this.createScene2();
        scene.setCamera(new PerspectiveCamera());
        initCameraControl(scene.getCamera(),scene);
        this.setScene(scene);
        this.setFocusable(true);
    }
    
    private void initCameraControl(Camera camera,Scene scene) {
    	//initMouseControl(root,scene);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
              case W:
                camera.translateZProperty().set(camera.getTranslateZ() - 100);
                break;
              case S:
                camera.translateZProperty().set(camera.getTranslateZ() + 100);
                break;
              case Q:
            	  //TODO REPAIRE ROTATION CAMERA
            	  cameraRotation(camera,10,3);
            	  break;
              case E:
            	  cameraRotation(camera,-10,3);
            	  break;
              case A:
            	  camera.translateXProperty().set(camera.getTranslateX() +100);
            	  break;
              case D:
            	  camera.translateXProperty().set(camera.getTranslateX() - 100);
            	  break;
              case NUMPAD6:
            	  cameraRotation(camera,10,2);
            	  break;
              case NUMPAD4:
            	  cameraRotation(camera,-10,2);
            	  break;
              case NUMPAD8:
            	  cameraRotation(camera,10,1);
            	  break;
              case NUMPAD2:
            	  cameraRotation(camera,-10,1);
            	  break;
               default:
              	 break;
            }
          });
    }
    
    private void cameraRotation(Camera camera, int ang, int axis) {
  	  	Transform t = new Rotate(camera.getRotate());
  	  	Rotate r;
  	  	switch(axis) {
  	  		case 1:
  	  			r = new Rotate(ang, Rotate.X_AXIS);
  	  			break;
  	  		case 2:
  	  			r = new Rotate(ang, Rotate.Y_AXIS);
  	  			break;
  	  		case 3:
  	  			r = new Rotate(ang, Rotate.Z_AXIS);
  	  			break;
  	  		default:
  	  			r = new Rotate(ang, Rotate.Z_AXIS);
  	  			break;
  	  	}
        t = t.createConcatenation(r);
        camera.getTransforms().clear();
        camera.getTransforms().addAll(t);
    }
    
    private void initMouseControl(SmartGroup group, Scene scene){
      	Rotate xRotate;
      	Rotate yRotate;
      	group.getTransforms().addAll(
      			xRotate = new Rotate(0,Rotate.X_AXIS),
      			yRotate = new Rotate(0,Rotate.Y_AXIS)
      			);
      	xRotate.angleProperty().bind(angleX);
      	yRotate.angleProperty().bind(angleY);
      	
      	scene.setOnMousePressed(event -> {
      		anchorX = event.getSceneX();
      		anchorY = event.getSceneY();
      		anchorAngleX = angleX.get();
      		anchorAngleY = angleY.get();
      	});
      	
      	scene.setOnMouseDragged(event -> {
      		angleX.set(anchorAngleX-(anchorY-event.getSceneY()));
      		angleY.set(anchorAngleY+(anchorX-event.getSceneY()));
      	});
      	
      	scene.addEventHandler(ScrollEvent.SCROLL, event->{
      		double movement = event.getDeltaY();
      		group.translateZProperty().set(group.getTranslateZ()+movement);
      	});
      }
    
    class SmartGroup extends Group {
    	 
        Rotate r;
        Transform t = new Rotate();
     
        void rotateByX(int ang) {
          r = new Rotate(ang, Rotate.X_AXIS);
          t = t.createConcatenation(r);
          this.getTransforms().clear();
          this.getTransforms().addAll(t);
        }
     
        void rotateByY(int ang) {
          r = new Rotate(ang, Rotate.Y_AXIS);
          t = t.createConcatenation(r);
          this.getTransforms().clear();
          this.getTransforms().addAll(t);
        }
      }
}
