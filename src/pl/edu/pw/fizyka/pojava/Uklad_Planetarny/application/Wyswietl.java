package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;

public class Wyswietl {
	public static List<Planet> planets = new ArrayList<Planet>();
	public static Locale selectedLocale;
	public static ResourceBundle bundle;
	public static void changeLocale(String tekst) {
		if(tekst=="PL") {
			selectedLocale = new Locale("pl","PL");
			bundle=ResourceBundle.getBundle("pl.edu.pw.fizyka.pojava.Uklad_Planetarny.Language/Messages", Wyswietl.selectedLocale);
		}
		if(tekst=="ENG") {
			selectedLocale = new Locale("en","EN");
			bundle=ResourceBundle.getBundle("pl.edu.pw.fizyka.pojava.Uklad_Planetarny.Language/Messages", Wyswietl.selectedLocale);
		}
	}
	
	private static void initTestPlanets2() {//zmienione żeby dodawać do listy w mainie
		Planet planeta1 = new Planet();
		MainMenu.addPlanet(planeta1);
		
		Planet planeta2 = new Planet();
		MainMenu.addPlanet(planeta2);
		
		Planet planeta3 = new Planet();
		MainMenu.addPlanet(planeta3);
	}

	public Wyswietl() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Wyswietl.initTestPlanets2();
		selectedLocale = new Locale("pl","PL");
		bundle = ResourceBundle.getBundle("pl.edu.pw.fizyka.pojava.Uklad_Planetarny.Language/Messages", Wyswietl.selectedLocale);
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
			MainMenu main = new MainMenu();
			main.setVisible(true);
			}
			}); 
		System.out.println("Hello World");

		/*while(time != 100){
			Functions.positionChange( planets, time);
			Functions.accelerationChange(planets, time);
			Functions.velocityChange(planets, time);
			time++;
			System.out.println(planets.get(1).getPositionX());
		}*/
	}

}
