package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.simulation.Simulation;

import java.awt.Color;

@SuppressWarnings("serial")
/**
 * @author Wojtek
 */
public class EditObjectFrame extends JFrame {
	private Planet selectedPlanet;
	private JPanel panelCenter;
	private MainMenu that;
	JTextField massText, radiusText;
	Simulation jFXpanel;
	Planet SecondPlanet;
	
	
		
	
	
	
	private void close() {
		this.setVisible(false);
		this.dispose();
	}
	
	@SuppressWarnings("unused")
	private void saveChanges() {
		selectedPlanet.setMass(10);
	}
	
	private void updateList(DefaultListModel<String> listaElementy) {
		//listaElementy.addElement(MainMenu.planets.get(MainMenu.planets.size()-1).getName());
		that.revalidate();
		that.setVisible(true);
	}
	
	private void initMenu() {
		JPanel selectPanel = new JPanel();
		
		DefaultListModel<String> listaElementy = new DefaultListModel<String>();
		JList<String> lista = new JList<String>(listaElementy);
		for (int i=0; i < MainMenu.planets.size(); i++) {
			listaElementy.addElement(MainMenu.planets.get(i).getName());
		}
		//lista.addListSelectionListener(listaListener);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//lista.setVisibleRowCount(5);
		JScrollPane listScrollPane = new JScrollPane(lista);
		//listScrollPane.setPreferredSize( new Dimension(300,100));
		selectPanel.add(listScrollPane);
		panelCenter.add(selectPanel);
		
		
		JPanel labelPanel = new JPanel();
		JLabel nameLabel = new JLabel(Display.bundle.getString("nameText"));
		labelPanel.add(nameLabel);
		JTextField nameText = new JTextField(selectedPlanet.getName());
		labelPanel.add(nameText);
		panelCenter.add(labelPanel);
		
		JPanel massPanel = new JPanel();
		JLabel massLabel = new JLabel(Display.bundle.getString("massText"));
		massPanel.add(massLabel);
		massText = new JTextField(Double.toString(selectedPlanet.getMass()));
		massPanel.add(massText);
		panelCenter.add(massPanel);
		
		JPanel radiusPanel = new JPanel();
		JLabel radiusLabel = new JLabel(Display.bundle.getString("radiusText"));
		radiusPanel.add(radiusLabel);
		radiusText = new JTextField(Double.toString(selectedPlanet.getRadius()));
		radiusPanel.add(radiusText);
		panelCenter.add(radiusPanel);
		
		JPanel positionPanel = new JPanel();
		JLabel pXLabel = new JLabel(Display.bundle.getString("pXText"));
		positionPanel.add(pXLabel);
		JTextField xPositionText = new JTextField(Double.toString(selectedPlanet.getPositionX()));
		positionPanel.add(xPositionText);		
		JLabel pYLabel = new JLabel(Display.bundle.getString("pYText"));
		positionPanel.add(pYLabel);
		JTextField yPositionText = new JTextField(Double.toString(selectedPlanet.getPositionY()));
		positionPanel.add(yPositionText);		
		JLabel pZLabel = new JLabel(Display.bundle.getString("pZText"));
		positionPanel.add(pZLabel);
		JTextField zPositionText = new JTextField(Double.toString(selectedPlanet.getPositionZ()));
		positionPanel.add(zPositionText);
		panelCenter.add(positionPanel);
		
		JPanel velocityPanel = new JPanel();
		JLabel vXLabel = new JLabel(Display.bundle.getString("vXText"));
		velocityPanel.add(vXLabel);
		JTextField xVelocityText = new JTextField(Double.toString(selectedPlanet.getVelocityX()));
		velocityPanel.add(xVelocityText);
		JLabel vYLabel = new JLabel(Display.bundle.getString("vYText"));
		velocityPanel.add(vYLabel);
		JTextField yVelocityText = new JTextField(Double.toString(selectedPlanet.getVelocityY()));
		velocityPanel.add(yVelocityText);
		JLabel vZLabel = new JLabel(Display.bundle.getString("vZText"));
		velocityPanel.add(vZLabel);
		JTextField zVelocityText = new JTextField(Double.toString(selectedPlanet.getVelocityZ()));
		velocityPanel.add(zVelocityText);
		panelCenter.add(velocityPanel);
		
		JPanel accelerationPanel = new JPanel();
		JLabel aXLabel = new JLabel(Display.bundle.getString("aXText"));
		accelerationPanel.add(aXLabel);
		JTextField xAccelerationText = new JTextField(Double.toString(selectedPlanet.getAccelerationX()));
		accelerationPanel.add(xAccelerationText);
		JLabel aYLabel = new JLabel(Display.bundle.getString("aYText"));
		accelerationPanel.add(aYLabel);
		JTextField yAccelerationText = new JTextField(Double.toString(selectedPlanet.getAccelerationY()));
		accelerationPanel.add(yAccelerationText);
		JLabel aZLabel = new JLabel(Display.bundle.getString("aZText"));
		accelerationPanel.add(aZLabel);
		JTextField zAccelerationText = new JTextField(Double.toString(selectedPlanet.getAccelerationZ()));
		accelerationPanel.add(zAccelerationText);
		panelCenter.add(accelerationPanel);
		
		JPanel endPanel = new JPanel();
		JButton quitButton = new JButton(Display.bundle.getString("quitText"));
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		
		JButton saveButton = new JButton(Display.bundle.getString("saveText"));
		
		ActionListener saveListener = new ActionListener() {//odpalanie funkcji z obliczeniami
			@Override 
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
					protected Void doInBackground() throws Exception {
						Planet planet = selectedPlanet;
						planet.setName(nameText.getText());
						planet.setMass(Double.parseDouble(massText.getText()));
						planet.setRadius(Double.parseDouble(radiusText.getText()));
						planet.setPositionX(Double.parseDouble(xPositionText.getText()));
						planet.setPositionY(Double.parseDouble(yPositionText.getText()));
						planet.setPositionZ(Double.parseDouble(zPositionText.getText()));
						planet.setVelocityX(Double.parseDouble(xVelocityText.getText()));
						planet.setVelocityY(Double.parseDouble(yVelocityText.getText()));
						planet.setVelocityZ(Double.parseDouble(zVelocityText.getText()));
						planet.setAccelerationX(Double.parseDouble(xAccelerationText.getText()));
						planet.setAccelerationY(Double.parseDouble(yAccelerationText.getText()));
						planet.setAccelerationZ(Double.parseDouble(zAccelerationText.getText()));
						//MainMenu.addPlanet(planet);
						System.out.println("zapisuje się!");
						//System.out.println(planet.getName());
						return null;
						}
					protected void done() {
						if(jFXpanel!=null&&SecondPlanet!=null) {
							jFXpanel.TaskAddPlanetToScene(SecondPlanet);
							SecondPlanet=null;
						}else {
							that.showPlanets(that.panelLeft);
							updateList(listaElementy);
						}
						close();
					}
				};worker.execute();
				
			}
		};
		saveButton.addActionListener(saveListener);
		
		JButton deleteButton = new JButton(Display.bundle.getString("deleteText"));

		ActionListener deleteListener = new ActionListener() {//odpalanie funkcji z obliczeniami
			@Override 
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
					protected Void doInBackground() throws Exception {
							for(int i= 0; i<MainMenu.planets.size(); i++ ) {
								if(lista.isSelectedIndex(i)) {
									MainMenu.deletePlanet(i);
									System.out.println(i);
								}
							}
						System.out.println("usuwa  się!");
						
						return null;
						}
				};worker.execute();
			}
		};
		deleteButton.addActionListener(deleteListener);
		
		JButton changeTextureButton=new JButton(Display.bundle.getString("changeTexture"));
		changeTextureButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTexture(selectedPlanet);
			}
		});
		endPanel.add(changeTextureButton);
		
		endPanel.add(saveButton);
		endPanel.add(deleteButton);
		endPanel.add(quitButton);
		panelCenter.add(endPanel);
		
		lista.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList answer = (JList)e.getSource();
				if(answer.getValueIsAdjusting()) {
					int k = answer.getSelectedIndex();
					System.out.println(k);
					if(k!=-1) {
						selectedPlanet = MainMenu.planets.get(k);
						nameText.setText(selectedPlanet.getName());
						massText.setText(Double.toString(selectedPlanet.getMass()));
						radiusText.setText(Double.toString(selectedPlanet.getRadius()));
						xPositionText.setText(Double.toString(selectedPlanet.getPositionX()));
						yPositionText.setText(Double.toString(selectedPlanet.getPositionY()));
						zPositionText.setText(Double.toString(selectedPlanet.getPositionZ()));
						xVelocityText.setText(Double.toString(selectedPlanet.getVelocityX()));
						yVelocityText.setText(Double.toString(selectedPlanet.getVelocityY()));
						zVelocityText.setText(Double.toString(selectedPlanet.getVelocityZ()));
						xAccelerationText.setText(Double.toString(selectedPlanet.getAccelerationX()));
						yAccelerationText.setText(Double.toString(selectedPlanet.getAccelerationY()));
						zAccelerationText.setText(Double.toString(selectedPlanet.getAccelerationZ()));
					}
				}
			}
		});
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

	private void changeTexture(Planet p) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Save File");
		chooser.setFileFilter(new FileNameExtensionFilter("JPG", "jpg"));
		chooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
		chooser.setCurrentDirectory(new File("~"));
		chooser.setAcceptAllFileFilterUsed(false);
		
		int userSelection = chooser.showSaveDialog(this);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = chooser.getSelectedFile();
		    
		    System.out.println("Texture of"+this.getName()+" change for: " + fileToSave.getAbsolutePath());
		    
		    p.setTexturePath("/"+fileToSave.getAbsolutePath());
		}
	}
	public EditObjectFrame(MainMenu t) throws HeadlessException {
		that=t;
		selectedPlanet = MainMenu.planets.get(0);
		initFrame();
		jFXpanel=null;
		SecondPlanet=null;
	}
	
	public EditObjectFrame(MainMenu t,Planet planet) throws HeadlessException {
		that=t;
		MainMenu.planets.add(planet);
		this.selectedPlanet=planet;
		initFrame();
		jFXpanel=null;
		SecondPlanet=null;
	}

	public EditObjectFrame(Planet nplanet,Simulation panel) {
		MainMenu.planets.add(nplanet);
		this.selectedPlanet=nplanet;
		SecondPlanet=nplanet;
		jFXpanel=panel;
		initFrame();
		
	}
}
