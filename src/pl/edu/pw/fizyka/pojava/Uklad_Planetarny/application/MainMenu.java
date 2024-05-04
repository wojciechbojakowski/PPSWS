package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.simulation.SimulationFrame;

/**
 * @author Michał
 */
@SuppressWarnings("serial")
public class MainMenu extends JFrame{
	Color backgroundColor = new Color(9, 28, 124);
	Color buttonColor = new Color(0, 102, 153);
	public MainMenu() {
		this.setSize(1600,1000);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(1,3));
		this.setTitle("PPSWS");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panelLeft = new JPanel();
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
		
		JButton buttonStart = new JButton("START");
		buttonStart.setFont(new Font("Arial", Font.PLAIN, 60));
		buttonStart.setBackground(buttonColor);
		buttonStart.setForeground(Color.white);
		panelCenter.add(buttonStart);
		
		JButton buttonAdd = new JButton("DODAJ OBIEKT");
		buttonAdd.setFont(new Font("Arial", Font.PLAIN, 60));
		buttonAdd.setBackground(buttonColor);
		buttonAdd.setForeground(Color.white);
		panelCenter.add(buttonAdd);
		
		JButton buttonEdit = new JButton("EDYTUJ OBIEKT");
		buttonEdit.setFont(new Font("Arial", Font.PLAIN, 60));
		buttonEdit.setBackground(buttonColor);
		buttonEdit.setForeground(Color.white);
		panelCenter.add(buttonEdit);

		JButton buttonSettings = new JButton("USTAWIENIA");
		buttonSettings.setFont(new Font("Arial", Font.PLAIN, 60));
		buttonSettings.setBackground(buttonColor);
		buttonSettings.setForeground(Color.white);
		panelCenter.add(buttonSettings);
		
		JLabel objects = new JLabel();
		objects.setText("Spis obiektów: ");
		objects.setFont(new Font("Arial", Font.PLAIN, 40));
		objects.setForeground(Color.white);
		panelLeft.add(objects);
		
		MainMenu that = this;
		
		/**
		 * @author Wojciech + Michał
		 */
		ActionListener editListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("unused")
				EditObjectFrame edit = new EditObjectFrame(that);
				that.setVisible(false);
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
				@SuppressWarnings("unused")
				EditObjectFrame edit = new EditObjectFrame(that,nplanet);
				that.setVisible(false);
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
		
		ActionListener settingsListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SettingsFrame sf = new SettingsFrame();
			}
		};
		buttonSettings.addActionListener(settingsListener);
	}
	
	public void makeVisible() {
		this.revalidate();
		this.setVisible(true);
	}

}
