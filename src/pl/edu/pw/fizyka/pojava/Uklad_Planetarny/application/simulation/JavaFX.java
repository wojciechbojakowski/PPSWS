package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.simulation;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class JavaFX extends Application {
 
  private static final int WIDTH = 1000;
  private static final int HEIGHT = 600;
  
  private double anchorX, anchorY;
  private double anchorAngleX = 0;
  private double anchorAngleY = 0;
  private final DoubleProperty angleX = new SimpleDoubleProperty(0);
  private final DoubleProperty angleY = new SimpleDoubleProperty(0);
  
 
 
  /**
   * This method adds two integers and returns the result.
   * @param a The first integer.
   * @param b The second integer.
   * @return The sum of a and b.
   */
  @Override
  public void start(Stage primaryStage) {
    //Create box
    //Box box = PreparedBox();
 
    Sphere box = PreparedSphere();
    //Prepare transformable Group container
    SmartGroup group = new SmartGroup();
    group.getChildren().add(box);
    group.getChildren().addAll(prepareLightSource());
    //group.getChildren().add(new AmbientLight()); // light from all directions. default
    //group.getChildren().add(new PointLight());
 
    Camera camera = new PerspectiveCamera();
    Scene scene = new Scene(group, WIDTH, HEIGHT);
    scene.setFill(Color.SILVER);
    scene.setCamera(camera);
 
    //Move to center of the screen
    group.translateXProperty().set(WIDTH / 2);
    group.translateYProperty().set(HEIGHT / 2);
    group.translateZProperty().set(-800);
    
    initMouseControl(group,scene, primaryStage);
 
    //Add keyboard control.
    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
      switch (event.getCode()) {
        case W:
          group.translateZProperty().set(group.getTranslateZ() + 100);
          break;
        case S:
          group.translateZProperty().set(group.getTranslateZ() - 100);
          break;
        case Q:
          group.rotateByX(10);
          break;
        case E:
          group.rotateByX(-10);
          break;
        case NUMPAD6:
          group.rotateByY(10);
          break;
        case NUMPAD4:
          group.rotateByY(-10);
          break;
         default:
        	 break;
      }
    });
 
    primaryStage.setTitle("Cos");
    primaryStage.setScene(scene);
    primaryStage.show();
    
    AnimationTimer timer = new AnimationTimer() {
  	  @Override
  	  public void handle(long now) {
  		  pointLight.setRotate(pointLight.getRotate()+1);
  	  }
    };
    timer.start();
  }
  
  private final PointLight pointLight = new PointLight();
  
  private Node[] prepareLightSource() {
	  //AmbientLight ambientLight = new AmbientLight();
	  //ambientLight.setColor(Color.HOTPINK);
	  //return ambientLight;
	  
	  pointLight.setColor(Color.AQUA);
	  pointLight.getTransforms().add(new Translate(0,-50,100));
	  pointLight.setRotationAxis(Rotate.X_AXIS);
	  //invisible point of light so add sphere.
	  
	  Sphere sphere = new Sphere(2);
	  sphere.getTransforms().setAll(pointLight.getTransforms());
	  sphere.rotateProperty().bind(pointLight.rotateProperty());
	  sphere.rotationAxisProperty().bind(pointLight.rotationAxisProperty());
	  return new Node[] {pointLight,sphere};
  }
 
  private Sphere PreparedSphere() {
	  PhongMaterial material = new PhongMaterial(Color.LIGHTSKYBLUE);
	  material.setDiffuseMap(new Image(getClass().getResourceAsStream("../../resources/earth.jpg")));
	  //material.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("/resource/ilum.jpg")));
	  
	  //material.setBumpMap(new Image(getClass().getResourceAsStream("../../resources/ilum.jpg")));
	  
	  //material.setSpecularColor(Color.WHITE);// White - a lot reflection, Black - no reflection, it is checked how close color is to white and black
	  //material.setSpecularMap(new Image(getClass().getResourceAsStream("../../resources/ilum.jpg")));
	  
	  Sphere sphere = new Sphere(100);
	  sphere.setMaterial(material);
	  return sphere;
  }
  
  private void initMouseControl(SmartGroup group, Scene scene, Stage stage){
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
  	
  	stage.addEventHandler(ScrollEvent.SCROLL, event->{
  		double movement = event.getDeltaY();
  		group.translateZProperty().set(group.getTranslateZ()+movement);
  	});
  }
  
  public static void main(String[] args) {
    launch(args);
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