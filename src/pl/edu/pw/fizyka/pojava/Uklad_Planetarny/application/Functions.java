
package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


/**
 * @author Michał + Wojtek
 */

public class Functions {
	static double  G=0.001;
	static String fileName = "output.txt";
	static BufferedWriter writer = null;
	static int ogranicznik=0;
	public static int timeline=0;
	
	public static void addTime(double d) {
		timeline+=Math.ceil(d);
	}
	
	public static String getTime() {
		return String.valueOf(timeline);
	}
	
	public static void createCon() {
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			saveData(prepareFirstLines());
		} catch (IOException e) {
			writer = null;
			System.err.println("Exeption with open file to write data out");
		}
	}
	
	public static void saveData(String Data) {
		if(writer!=null) {
			if(ogranicznik!=100000) {
				ogranicznik++;
				try {
				writer.write(Data);
				writer.newLine();
			} catch (IOException e) {
				System.err.println("Exeption with writing to the file");
				e.printStackTrace();
			}
			}
			else {
				System.out.println("Saved 100000 lines");
				MainMenu.setD(0);
			}
		}
	}
	
	public static String prepareFirstLines() {
		String ret = "";
		for(Planet i : MainMenu.planets) {
			ret = ret+i.getName()+",\t        ,\t       ,\t        ,\t         ,\t        ,\t";
		}
		ret = ret+"\n";
		for(@SuppressWarnings("unused") Planet i : MainMenu.planets) {
			ret=ret+"PositionX: , PositionY: , PositionZ: , VelocityX: , VelocityY: , VelocityZ: , ";
		}
		return ret;
	}
	
	public static String prepareDate() {
		String ret ="";
		for(Planet i : MainMenu.planets) {
			ret=ret+i.getPositionX()+" , "+i.getPositionY()+" , "+i.getPositionZ()+" , "+i.getVelocityX()+" , "+i.getVelocityY()+" , "+i.getVelocityZ()+" , ";
		}
		return ret;
	}
	
	public static void positionChange(List<Planet> planets, double t){
		for(int i=0; i<planets.size(); i++) {
				planets.get(i).setPositionX(planets.get(i).getPositionX()+planets.get(i).getVelocityX()*t);
				planets.get(i).setPositionY(planets.get(i).getPositionY()+planets.get(i).getVelocityY()*t);
				planets.get(i).setPositionZ(planets.get(i).getPositionZ()+planets.get(i).getVelocityZ()*t);
				for(int j=0; j<planets.size(); j++) {
					if(j==i)continue;
					if(planets.get(i).getRadius()+planets.get(j).getRadius()>=Math.abs(planets.get(i).getPositionX()-planets.get(j).getPositionX()) && planets.get(i).getRadius()+planets.get(j).getRadius()>=Math.abs(planets.get(i).getPositionY()-planets.get(j).getPositionY()) && planets.get(i).getRadius()+planets.get(j).getRadius()>=Math.abs(planets.get(i).getPositionZ()-planets.get(j).getPositionZ())) {
						System.out.println("Zderzenie planet: "+planets.get(i).getName()+" "+planets.get(j).getName());
						if(i<j) {
							System.out.println("LICZBA PRZED: "+planets.size());
							planets.remove(j);
							System.out.println("LICZBA W POłOWIE: "+planets.size());
							planets.remove(i);
							System.out.println("LICZBA PO: "+planets.size());
						}else {
							System.out.println("LICZBA PRZED: "+planets.size());
							planets.remove(i);
							System.out.println("LICZBA W POłOWIE: "+planets.size());
							planets.remove(j);
							System.out.println("LICZBA PO: "+planets.size());
						}
					}
				}							
		}
	}
	public static void velocityChange(List<Planet> planets, double t) {
		for(int i=0; i<planets.size(); i++) {
			planets.get(i).setVelocityX(planets.get(i).getVelocityX()+planets.get(i).getAccelerationX()*t);
			planets.get(i).setVelocityY(planets.get(i).getVelocityY()+planets.get(i).getAccelerationY()*t);
			planets.get(i).setVelocityZ(planets.get(i).getVelocityZ()+planets.get(i).getAccelerationZ()*t);
		}
	}
	public static void accelerationChange(List<Planet> planets, double t) {
		for(int i=0; i<planets.size(); i++) {
			for(int j=0; j<planets.size(); j++) {
				if(i==j)continue;
				planets.get(i).setAccelerationX(planets.get(j).getMass()*G/Math.pow(Math.abs(planets.get(i).getPositionX()-planets.get(j).getPositionX()),2));
				planets.get(i).setAccelerationY(planets.get(j).getMass()*G/Math.pow(Math.abs(planets.get(i).getPositionY()-planets.get(j).getPositionY()),2));
				planets.get(i).setAccelerationZ(planets.get(j).getMass()*G/Math.pow(Math.abs(planets.get(i).getPositionZ()-planets.get(j).getPositionZ()),2));
			}
		}
	}
	public Functions() {
		// TODO Auto-generated constructor stub
	}

}
