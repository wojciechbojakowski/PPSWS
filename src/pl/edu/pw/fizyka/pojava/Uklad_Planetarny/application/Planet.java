package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.util.Random;

import javafx.scene.shape.Sphere;

/**
 * @author Michał
 */
public class Planet {
	String name;
	double position[] = new double[3];
	double velocity[] = new double[3];
	double acceleration[] = new double[3];
	double mass;
	double radius;
	Random r;
	private Sphere sphere;
	String texturePath;
	
	public String getTexturePath() {
		return this.texturePath;
	}
	
	public void setTexturePath(String tak) {
		this.texturePath=tak;
	}
	
	public Sphere getSphere() {
		return sphere;
	}

	public void setSphere(Sphere sphere) {
		this.sphere = sphere;
	}
	
	/*
	 * Zmienia wartości gdzie znajduje się sfera w Animacji.
	 * @author Krasnoludki
	 */
	public void updatePositionAnimation() {
		sphere.setTranslateX(getPositionX());
		sphere.setTranslateY(getPositionY());
		sphere.setTranslateZ(getPositionZ());
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getPositionX() {
		return (int)position[0];
	}
	public double getPositionY() {
		return (int)position[1];
	}
	public double getPositionZ() {
		return (int)position[2];
	}

	public void setPositionX(double position) {
		this.position[0] = (int)position;
	}
	public void setPositionY(double position) {
		this.position[1] = (int)position;
	}
	public void setPositionZ(double position) {
		this.position[2] = (int)position;
	}

	public double getVelocityX() {
		return velocity[0];
	}
	public double getVelocityY() {
		return velocity[1];
	}
	public double getVelocityZ() {
		return velocity[2];
	}

	public void setVelocityX(double velocity) {
		this.velocity[0] = velocity;
	}
	public void setVelocityY(double velocity) {
		this.velocity[1] = velocity;
	}
	public void setVelocityZ(double velocity) {
		this.velocity[2] = velocity;
	}

	public double getAccelerationX() {
		return acceleration[0];
	}
	public double getAccelerationY() {
		return acceleration[1];
	}
	public double getAccelerationZ() {
		return acceleration[2];
	}

	public void setAccelerationX(double acceleration) {
		this.acceleration[0] = acceleration;
	}
	public void setAccelerationY(double acceleration) {
		this.acceleration[1] = acceleration;
	}
	public void setAccelerationZ(double acceleration) {
		this.acceleration[2] = acceleration;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public Planet() {
		texturePath=null;//"../../resources/earth.jpg";
		r = new Random();
		name = "Dziobak";
		mass = 1;
		radius = 1;
		for(int i=0; i<3;i++) {
			//tutaj jest int bo inaczej się psuje renderowanie planet.
			position[i]=r.nextInt(1000);
		}
		//System.out.println(getPositionX()+","+getPositionY()+", "+getPositionZ()+"]");
		for(int i=0; i<3;i++) {
			acceleration[i]=r.nextInt(2);
		}
		for(int i=0; i<3;i++) {
			velocity[i]=r.nextInt(4);
		}
	}

}
