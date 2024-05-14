package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.simulation.SimulationFrame;

/**
 * @author Michał
 */
@SuppressWarnings("serial")
public class MainMenu extends JFrame{
	Color backgroundColor = new Color(9, 28, 124);
	Color buttonColor = new Color(0, 102, 153);
	private ImageIcon icon;
	JPanel panelLeft;
	//początek bzdur
	public static List<Planet> planets = new ArrayList<Planet>();
	static int d = 0; //chwilowa zmienna - odpala funkcje liczącze
	double time = 0.000001;
	static JButton buttonStart;
	//koniec bzdur
	public MainMenu() {
		try {
			icon = new ImageIcon("../resources/death-star-ico.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setSize(1600,1000);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(1,3));
		this.setTitle("PPSWS");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		panelLeft = new JPanel();
		panelLeft.setBackground(backgroundColor);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		this.add(panelLeft);
		
		JPanel panelCenter = new JPanel(new GridLayout(6,1,20,20));
		panelCenter.setBackground(backgroundColor);
		this.add(panelCenter);
		
		JPanel panelRight = new JPanel();
		panelRight.setBackground(backgroundColor);
		this.add(panelRight);
		
		JLabel title = new JLabel("PPSWS", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 100));
		title.setForeground(Color.white);
		panelCenter.add(title);
		
		buttonStart = new JButton(Wyswietl.bundle.getString("startText"));
		buttonStart.setFont(new Font("Arial", Font.PLAIN, 60));
		buttonStart.setBackground(buttonColor);
		buttonStart.setForeground(Color.white);
		panelCenter.add(buttonStart);
		
		JButton buttonAdd = new JButton(Wyswietl.bundle.getString("addObjText"));
		buttonAdd.setFont(new Font("Arial", Font.PLAIN, 60));
		buttonAdd.setBackground(buttonColor);
		buttonAdd.setForeground(Color.white);
		panelCenter.add(buttonAdd);
		
		JButton buttonEdit = new JButton(Wyswietl.bundle.getString("editText"));
		buttonEdit.setFont(new Font("Arial", Font.PLAIN, 60));
		buttonEdit.setBackground(buttonColor);
		buttonEdit.setForeground(Color.white);
		panelCenter.add(buttonEdit);

		JButton buttonSettings = new JButton(Wyswietl.bundle.getString("settingsText"));
		buttonSettings.setFont(new Font("Arial", Font.PLAIN, 60));
		buttonSettings.setBackground(buttonColor);
		buttonSettings.setForeground(Color.white);
		panelCenter.add(buttonSettings);
		
		showPlanets(panelLeft);
		
		MainMenu that = this;
		
		/**
		 * @author Wojciech + Michał
		 */
		ActionListener editListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("unused")
				EditObjectFrame edit = new EditObjectFrame(that);
				//that.setVisible(false);
			}
		};
		buttonEdit.addActionListener(editListener);
		
		/**
		 * @author Wojciech + Michał
		 */
		ActionListener addListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Planet nplanet = new Planet();
				nplanet.setName("Nowa Planeta");
				EditObjectFrame edit = new EditObjectFrame(that,nplanet);
				//EditObjectFrame edit = new EditObjectFrame(that,nplanet);
				//that.setVisible(false);
			}
		};
		buttonAdd.addActionListener(addListener);
		
		/**
		 * @author Wojciech Bojakowski
		 * @since 25.03.2024r.
		 */
		ActionListener startListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SimulationFrame.startAnimation();
				that.setVisible(false);
			}
		};
		buttonStart.addActionListener(startListener);
		
		ActionListener calculationListener = new ActionListener() {//odpalanie funkcji z obliczeniami
			@Override 
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
					protected Void doInBackground() throws Exception {
						System.out.println("coś działa");
						d = 1;
						TimeUnit.SECONDS.sleep(5);
						while(d == 1){
							Functions.positionChange(planets, time);
							Functions.accelerationChange(planets, time);
							Functions.velocityChange(planets, time);
						}
						return null;
						}
				};worker.execute();
			}
		};
		buttonStart.addActionListener(calculationListener);
		
		ActionListener settingsListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SettingsFrame sf = new SettingsFrame();
			}
		};
		buttonSettings.addActionListener(settingsListener);
	}
	
	void showPlanets(JPanel panelLeft) {
		panelLeft.removeAll();
		JLabel objects = new JLabel();
		objects.setText(Wyswietl.bundle.getString("listObjText"));
		objects.setFont(new Font("Arial", Font.PLAIN, 40));
		objects.setForeground(Color.white);
		panelLeft.add(objects);
		
		System.out.println("JEstem");
		for(Planet p : planets) {
			JLabel k = new JLabel(p.getName(),icon,SwingConstants.HORIZONTAL);
			k.setForeground(Color.white);
			panelLeft.add(k);
			System.out.println(p.getName());
		}
		this.makeVisible();
	}

	public void makeVisible() {
		this.revalidate();
		this.setVisible(true);
	}

	public static int getD() {
		return d;
	}
	public static void setD(int d) {
		MainMenu.d = d;
	}
	public static List<Planet> getPlanets() {
		return planets;
	}
	public static void setPlanets(List<Planet> planets) {
		MainMenu.planets = planets;
	}
	public static void addPlanet(Planet planet) {
		planets.add(planet);
	}
	public static void deletePlanet(int i) {
		planets.remove(i);
	}
	public static JButton getButtonStart() {
		return buttonStart;
	}	
}
