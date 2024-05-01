
package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.util.*;

public class Functions {
	double G=1;
	public void positionChange(List<Planet> planets, double t){
		for(int i=0; i<planets.size(); i++) {
				planets.get(i).setPositionX(planets.get(i).getPositionX()+planets.get(i).getVelocityX()*t);
				planets.get(i).setPositionY(planets.get(i).getPositionY()+planets.get(i).getVelocityY()*t);
				planets.get(i).setPositionZ(planets.get(i).getPositionZ()+planets.get(i).getVelocityZ()*t);
		}
	}
	public void velocityChange(List<Planet> planets, double t) {
		for(int i=0; i<planets.size(); i++) {
			planets.get(i).setVelocityX(planets.get(i).getVelocityX()+planets.get(i).getAccelerationX()*t);
			planets.get(i).setVelocityY(planets.get(i).getVelocityY()+planets.get(i).getAccelerationY()*t);
			planets.get(i).setVelocityZ(planets.get(i).getVelocityZ()+planets.get(i).getAccelerationZ()*t);
		}
	}
	public void accelerationChange(List<Planet> planets, double t) {
		for(int i=0; i<planets.size(); i++) {
			for(int j=0; j<planets.size(); j++) {
				if(j==i)continue;
				if(planets.get(i).getPositionX()-planets.get(j).getPositionX()>0) {
					planets.get(i).setAccelerationX(planets.get(i).getAccelerationX()+planets.get(j).getMass()*G/Math.pow(Math.abs(planets.get(i).getPositionX()-planets.get(j).getPositionX()),2));
				}
				else {
					planets.get(i).setAccelerationX(planets.get(i).getAccelerationX()-planets.get(j).getMass()*G/Math.pow(Math.abs(planets.get(i).getPositionX()-planets.get(j).getPositionX()),2));
				}
			}
			for(int j=0; j<planets.size(); j++) {
				if(j==i)continue;
				if(planets.get(i).getPositionY()-planets.get(j).getPositionY()>0) {
					planets.get(i).setAccelerationY(planets.get(i).getAccelerationY()+planets.get(j).getMass()*G/Math.pow(Math.abs(planets.get(i).getPositionY()-planets.get(j).getPositionY()),2));
				}
				else {
					planets.get(i).setAccelerationY(planets.get(i).getAccelerationY()-planets.get(j).getMass()*G/Math.pow(Math.abs(planets.get(i).getPositionY()-planets.get(j).getPositionY()),2));
				}
			}
			for(int j=0; j<planets.size(); j++) {
				if(j==i)continue;
				if(planets.get(i).getPositionZ()-planets.get(j).getPositionZ()>0) {
					planets.get(i).setAccelerationZ(planets.get(i).getAccelerationZ()+planets.get(j).getMass()*G/Math.pow(Math.abs(planets.get(i).getPositionZ()-planets.get(j).getPositionZ()),2));
				}
				else {
					planets.get(i).setAccelerationZ(planets.get(i).getAccelerationZ()-planets.get(j).getMass()*G/Math.pow(Math.abs(planets.get(i).getPositionZ()-planets.get(j).getPositionZ()),2));
				}
			}
		}
	}
	public Functions() {
		// TODO Auto-generated constructor stub
	}

}
