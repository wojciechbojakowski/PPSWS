package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.simulation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.AmbientLight;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.MainMenu;
import pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.Planet;
import javafx.beans.property.*;
import javafx.concurrent.Task;

@SuppressWarnings("serial")
public class Simulation extends JFXPanel{
		private double anchorX, anchorY;
		  private double anchorAngleX = 0;
		  private double anchorAngleY = 0;
		  private final DoubleProperty angleX = new SimpleDoubleProperty(0);
		  private final DoubleProperty angleY = new SimpleDoubleProperty(0);
		  
		  SmartGroup root2;

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
    
    	/**
    	 * Generuje sfery pokryte tekstura
    	 * @param Planet planet
    	 * @return Sphere sphere
    	 * @author Krasnoludki
    	 */
    private Sphere renderPlanet(Planet planet) {
    	Sphere sp = new Sphere(50);
    	sp.setTranslateX(planet.getPositionX());
    	sp.setTranslateY(planet.getPositionY());
    	sp.setTranslateZ(planet.getPositionZ());
    	//System.out.println(planet.getPositionX()+","+planet.getPositionY()+", "+planet.getPositionZ());
    	//TEKSTURE + TODO LIGHT
    	PhongMaterial earthMaterial = new PhongMaterial();
    	Image texture= new Image(getClass().getResourceAsStream("../../resources/earth.jpg"));
    	if(planet.getTexturePath()!=null) {
    		Image test = readImage(new File(planet.getTexturePath()));
    		if(test!=null)texture=test;
    	}
    	earthMaterial.setDiffuseMap(texture);
    	//earthMaterial.setBumpMap(new Image(getClass().getResourceAsStream("../../resources/ilum.jpg")));
    	sp.setMaterial(earthMaterial);
    	planet.setSphere(sp); //Dodawanie do planety wskaznika na sfere w animacji.
		return sp;
    }
    
    //Tworzenie slonca
    private final PointLight pointLight = new PointLight();
    private Node[] prepareLightSource() {
  	  AmbientLight ambientLight = new AmbientLight();
  	  ambientLight.setColor(Color.LIGHTSTEELBLUE);
  	  //return ambientLight;
  	  
  	  pointLight.setColor(Color.GHOSTWHITE);
  	  pointLight.getTransforms().add(new Translate(0,-50,100));
  	  pointLight.setRotationAxis(Rotate.X_AXIS);
  	  //invisible point of light so add sphere.
  	  
  	  Sphere sphere = new Sphere(50);
  	  sphere.getTransforms().setAll(pointLight.getTransforms());
  	  sphere.rotateProperty().bind(pointLight.rotateProperty());
  	  sphere.rotationAxisProperty().bind(pointLight.rotationAxisProperty());
  	  return new Node[] {pointLight,sphere,ambientLight};
    }
    
    	/**
    	 * Generuje przestezeń z Sferami i tłem
    	 * @author Krasnoludki
    	 */
    private Scene createScene2() {
        SmartGroup  root  =  new SmartGroup();
        root2=root;
        Scene  scene  =  new  Scene(root, Color.DARKBLUE);
        
        //root.getChildren().addAll(prepareLightSource());
        //Sphere sphere = new Sphere(50);
        for(Planet p : MainMenu.planets) {
        	addPlanetToScene(p);
			/*
			 * root.getChildren().add(renderPlanet(p)); AnimationTimer timer = new
			 * AnimationTimer() {
			 * 
			 * @Override public void handle(long now) {
			 * p.getSphere().setTranslateX(p.getPositionX());
			 * p.getSphere().setTranslateY(p.getPositionY());
			 * p.getSphere().setTranslateZ(p.getPositionZ());
			 * //System.out.println(p.getPositionX()+","+p.getPositionY()+", "+p.
			 * getPositionZ()); } }; timer.start();
			 */
        }
        //root.getChildren().add(sphere);
        
        return (scene);
    }
    
    public void TaskAddPlanetToScene(Planet p) {
    	Task<Void> task = new Task<Void>() {
    	    protected Void call() throws Exception {
    	    	Platform.runLater(() -> {
                    addPlanetToScene(p);
                    changeScene();
                    //System.out.println("p added");
                });
    	        return null;
    	    }
    	};
    	new Thread(task).start();
    }
    
    public void changeScene() {
    	Scene scene = createScene2();
    	this.setScene(scene);
    }
    
    public void addPlanetToScene(Planet p) {
    	SmartGroup root = root2;
    	root.getChildren().add(renderPlanet(p));
    	AnimationTimer timer = new AnimationTimer() {
  	  	  @Override
  	  	  public void handle(long now) {
  	  		p.getSphere().setTranslateX(p.getPositionX());
  	  		p.getSphere().setTranslateY(p.getPositionY());
  	  		p.getSphere().setTranslateZ(p.getPositionZ());
  	  		//System.out.println(p.getPositionX()+","+p.getPositionY()+", "+p.getPositionZ());
  	  	  }
	  	};
	  	timer.start();
    }
    
    	/**
    	 * Inicjuje generowanie przestrzeni i uruchamia możliwośc sterowana kamerą
    	 * @author Krasnoludki
    	 */
    public void initFX() {
        // This method is invoked on the JavaFX thread
        Scene scene = this.createScene2();
        SmartCamera camera = new SmartCamera();
        //this.initBackground(scene);
        camera.setNearClip(1);
        camera.setFarClip(2000);
        scene.setCamera(camera);
        initCameraControl(camera,scene);
        initMouseControl(scene);
        this.setScene(scene);
        this.setFocusable(true);
    }
    	/**
    	 * Dodaje sterowanie do kamery, by sterować kamera potrzebny jest focus na simulacje.
    	 * @param camera
    	 * @param scene
    	 * @author Krasnoludki
    	 */
    private void initCameraControl(SmartCamera camera,Scene scene) {
    	//initMouseControl(root,scene);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
            	case N:
                System.out.println(MainMenu.getPlanets().toString()+" h ");
                System.out.println(root2.getChildren().toString());
                break;
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
              case Z:
            	  camera.translateYProperty().set(camera.getTranslateY() +100);
            	  break;
              case X:
            	  camera.translateYProperty().set(camera.getTranslateY() - 100);
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
    
    private void cameraRotation(SmartCamera camera, int ang, int axis) {
  	  	switch(axis) {
  	  		case 1:
  	  			camera.rotateByX(ang);
  	  			break;
  	  		case 2:
  	  			camera.rotateByY(ang);
  	  			break;
  	  		case 3:
  	  			camera.rotateByZ(ang);
  	  			break;
  	  		default:
  	  			camera.rotateByX(ang);
  	  			break;
  	  	}
    }
    
    private void initBackground(Scene scene) {
    	scene.setFill(Color.BLANCHEDALMOND);
    	Image image = new Image(getClass().getResourceAsStream("../../resources/background.jpeg"));
    	ImagePattern imgPattern = new ImagePattern(image);
    	scene.setFill(imgPattern);
    }
    
    public static Image readImage(File file) {
        try {
            BufferedImage bimg = ImageIO.read(file);
            return SwingFXUtils.toFXImage(bimg, null);
        }
        catch( IOException e ) {
            // do something, probably throw some kind of RuntimeException
        }
		return null;
    }
    
    private void initMouseControl(Scene scene){
      	Rotate xRotate;
      	Rotate yRotate;
      	
      	Camera camera = scene.getCamera();
      	camera.getTransforms().addAll(
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
      		camera.translateZProperty().set(camera.getTranslateZ()+movement);
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
    
    class SmartCamera extends PerspectiveCamera{
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
        
        void rotateByZ(int ang) {
        	r = new Rotate(ang,Rotate.Z_AXIS);
        	t = t.createConcatenation(r);
        	this.getTransforms().clear();
        	this.getTransforms().addAll(t);
        }
    }
}
