package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
	
	
	private static void initTestPlanets() {
		Planet planeta1 = new Planet();
		Wyswietl.planets.add(planeta1);
		
		Planet planeta2 = new Planet();
		Wyswietl.planets.add(planeta2);
		
		Planet planeta3 = new Planet();
		Wyswietl.planets.add(planeta3);
	}

	public Wyswietl() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Wyswietl.initTestPlanets();
		selectedLocale = new Locale("pl","PL");
		bundle = ResourceBundle.getBundle("pl.edu.pw.fizyka.pojava.Uklad_Planetarny.Language/Messages", Wyswietl.selectedLocale);
		MainMenu glowne = new MainMenu();
		glowne.setVisible(true);
		System.out.println("Hello World");
	}

}
