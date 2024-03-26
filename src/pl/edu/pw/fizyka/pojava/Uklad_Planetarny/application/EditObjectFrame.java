package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;

@SuppressWarnings("serial")
public class EditObjectFrame extends JFrame {
	private Planet selectedPlanet;
	private JPanel panelCenter;
	
	JTextField massText;
	
	private void close() {
		this.setVisible(false);
		this.dispose();
	}
	private void saveChanges() {
		selectedPlanet.setMass(10);
	}
	
	private void initMenu() {
		JPanel selectPanel = new JPanel();
		DefaultListModel<String> listaElementy = new DefaultListModel<String>();
		JList<String> lista = new JList<String>(listaElementy);
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
		JLabel massLabel = new JLabel(Wyswietl.bundle.getString("massText"));
		massPanel.add(massLabel);
		massText = new JTextField(Double.toString(selectedPlanet.getMass()));
		massPanel.add(massText);
		panelCenter.add(massPanel);
		
		JPanel positionPanel = new JPanel();
		JLabel pXLabel = new JLabel(Wyswietl.bundle.getString("pXText"));
		positionPanel.add(pXLabel);
		JTextField xPositionText = new JTextField(Double.toString(selectedPlanet.getPositionX()));
		positionPanel.add(xPositionText);
		JLabel pYLabel = new JLabel(Wyswietl.bundle.getString("pYText"));
		positionPanel.add(pYLabel);
		JTextField yPositionText = new JTextField(Double.toString(selectedPlanet.getPositionY()));
		positionPanel.add(yPositionText);
		JLabel pZLabel = new JLabel(Wyswietl.bundle.getString("pZText"));
		positionPanel.add(pZLabel);
		JTextField zPositionText = new JTextField(Double.toString(selectedPlanet.getPositionZ()));
		positionPanel.add(zPositionText);
		panelCenter.add(positionPanel);
		
		JPanel velocityPanel = new JPanel();
		JLabel vXLabel = new JLabel(Wyswietl.bundle.getString("vXText"));
		velocityPanel.add(vXLabel);
		JTextField xVelocityText = new JTextField(Double.toString(selectedPlanet.getVelocityX()));
		velocityPanel.add(xVelocityText);
		JLabel vYLabel = new JLabel(Wyswietl.bundle.getString("vYText"));
		velocityPanel.add(vYLabel);
		JTextField yVelocityText = new JTextField(Double.toString(selectedPlanet.getVelocityY()));
		velocityPanel.add(yVelocityText);
		JLabel vZLabel = new JLabel(Wyswietl.bundle.getString("vZText"));
		velocityPanel.add(vZLabel);
		JTextField zVelocityText = new JTextField(Double.toString(selectedPlanet.getVelocityZ()));
		velocityPanel.add(zVelocityText);
		panelCenter.add(velocityPanel);
		
		JPanel accelerationPanel = new JPanel();
		JLabel aXLabel = new JLabel(Wyswietl.bundle.getString("aXText"));
		accelerationPanel.add(aXLabel);
		JTextField xAccelerationText = new JTextField(Double.toString(selectedPlanet.getAccelerationX()));
		accelerationPanel.add(xAccelerationText);
		JLabel aYLabel = new JLabel(Wyswietl.bundle.getString("aYText"));
		accelerationPanel.add(aYLabel);
		JTextField yAccelerationText = new JTextField(Double.toString(selectedPlanet.getAccelerationY()));
		accelerationPanel.add(yAccelerationText);
		JLabel aZLabel = new JLabel(Wyswietl.bundle.getString("aZText"));
		accelerationPanel.add(aZLabel);
		JTextField zAccelerationText = new JTextField(Double.toString(selectedPlanet.getAccelerationZ()));
		accelerationPanel.add(zAccelerationText);
		panelCenter.add(accelerationPanel);
		
		JPanel endPanel = new JPanel();
		JButton quitButton = new JButton("quit");
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		JButton saveButton = new JButton("Save changes");
		
		endPanel.add(saveButton);
		endPanel.add(quitButton);
		panelCenter.add(endPanel);
		
	}
	
	private void initFrame() {
		setTitle("Add Object - PPSWS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        
        this.setLayout(new GridLayout(1,3,0,0));
        
        JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.BLUE);
		this.add(panelLeft);
		
		//panelCenter = new JPanel(new GridLayout(7,1,20,20));
		panelCenter=new JPanel();
		panelCenter.setLayout(new BoxLayout(panelCenter,BoxLayout.Y_AXIS));
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
		Wyswietl.planets.add(planet);
		this.selectedPlanet=planet;
		initFrame();
	}
}
