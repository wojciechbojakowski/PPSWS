package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.util.*;

public class Functions {
	
	public void positionChange(List<Planet> planets, double t){
		for(int i=0; i<planets.size();i++) {
				planets.get(i).setPositionX(planets.get(i).getPositionX()+planets.get(i).getVelocityX()*t);
				planets.get(i).setPositionY(planets.get(i).getPositionY()+planets.get(i).getVelocityY()*t);
				planets.get(i).setPositionZ(planets.get(i).getPositionZ()+planets.get(i).getVelocityZ()*t);
		}
	}
	
	public Functions() {
		// TODO Auto-generated constructor stub
	}

}
