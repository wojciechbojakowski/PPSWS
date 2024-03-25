package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;

public class EditObjectFrame extends JFrame {
	private Planet selectedPlanet;
	private JPanel panelCenter;
	
	private void initMenu() {
		JPanel selectPanel = new JPanel();
		DefaultListModel listaElementy = new DefaultListModel();
		JList lista = new JList(listaElementy);
		for (int i=0; i < Wyswietl.planets.size(); i++) {
			listaElementy.addElement(Wyswietl.planets.get(i).getName());
		}
		//lista.addListSelectionListener(listaListener);
		lista.setVisibleRowCount(5);
		//JScrollPane listScrollPane = new JScrollPane(lista);
		//listScrollPane.setPreferredSize( new Dimension(300,100));
		selectPanel.add(lista);
		panelCenter.add(selectPanel);
		
		selectedPlanet = Wyswietl.planets.get(0);
		
		
		JPanel labelPanel = new JPanel();
		JLabel nameLabel = new JLabel(selectedPlanet.getName());
		labelPanel.add(nameLabel);
		panelCenter.add(labelPanel);
		
		JPanel massPanel = new JPanel();
		JTextField massText = new JTextField(Double.toString(selectedPlanet.getMass()));
		massPanel.add(massText);
		panelCenter.add(massPanel);
		
		JPanel positionPanel = new JPanel();
		JTextField xPositionText = new JTextField(Double.toString(selectedPlanet.getPositionX()));
		positionPanel.add(xPositionText);
		JTextField yPositionText = new JTextField(Double.toString(selectedPlanet.getPositionY()));
		positionPanel.add(yPositionText);
		JTextField zPositionText = new JTextField(Double.toString(selectedPlanet.getPositionZ()));
		positionPanel.add(zPositionText);
		panelCenter.add(positionPanel);
		
		JPanel velocityPanel = new JPanel();
		JTextField xVelocityText = new JTextField(Double.toString(selectedPlanet.getVelocityX()));
		velocityPanel.add(xVelocityText);
		JTextField yVelocityText = new JTextField(Double.toString(selectedPlanet.getVelocityY()));
		velocityPanel.add(yVelocityText);
		JTextField zVelocityText = new JTextField(Double.toString(selectedPlanet.getVelocityZ()));
		velocityPanel.add(zVelocityText);
		panelCenter.add(velocityPanel);
		
		JPanel accelerationPanel = new JPanel();
		JTextField xAccelerationText = new JTextField(Double.toString(selectedPlanet.getAccelerationX()));
		accelerationPanel.add(xAccelerationText);
		JTextField yAccelerationText = new JTextField(Double.toString(selectedPlanet.getAccelerationY()));
		accelerationPanel.add(yAccelerationText);
		JTextField zAccelerationText = new JTextField(Double.toString(selectedPlanet.getAccelerationZ()));
		accelerationPanel.add(zAccelerationText);
		panelCenter.add(accelerationPanel);
		
		JPanel endPanel = new JPanel();
		JButton quitButton = new JButton("quit");
		JButton saveButton = new JButton("Save changes");
		endPanel.add(saveButton);
		endPanel.add(quitButton);
		panelCenter.add(endPanel);
		
		System.out.println("Słucham?");
	}
	
	private void initFrame() {
		setTitle("Add Object - PPSWS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        
        this.setLayout(new GridLayout(1,3,0,0));
        
        JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.BLUE);
		this.add(panelLeft);
		
		panelCenter = new JPanel(new GridLayout(7,1,20,20));
		panelCenter.setBackground(Color.BLUE);
		this.add(panelCenter);
		
		JPanel panelRight = new JPanel();
		panelRight.setBackground(Color.BLUE);
		this.add(panelRight);
        
        this.initMenu();
        
        this.setVisible(true);
	}

	public EditObjectFrame() throws HeadlessException {
		
		initFrame();
	}
	
	public EditObjectFrame(Planet planet) throws HeadlessException {
		this.selectedPlanet=planet;
		initFrame();
	}
}
