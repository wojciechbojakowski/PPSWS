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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;

@SuppressWarnings("serial")
public class EditObjectFrame extends JFrame {
	private Planet selectedPlanet;
	private JPanel panelCenter;
	private MainMenu that;
	
	JTextField massText;
	JTextField nameLabel;
	JTextField xPositionText;
	JTextField yPositionText;
	JTextField zPositionText;
	JTextField xVelocityText;
	JTextField yVelocityText;
	JTextField zVelocityText;
	JTextField xAccelerationText;
	JTextField yAccelerationText;
	JTextField zAccelerationText;
	
	private void close() {
		that.makeVisible();
		this.setVisible(false);
		this.dispose();
	}
	
	private void saveChanges() {
		selectedPlanet.setMass(10);
		selectedPlanet.setName(nameLabel.getText());
		float x = 0,y = 0,z = 0;
		try {
			x=Float.valueOf(xPositionText.getText());
			y=Float.valueOf(yPositionText.getText());
			z=Float.valueOf(zPositionText.getText());
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			x=0;
			y=0;
			z=0;
			System.err.println("Position Save Error");
		}finally {
			selectedPlanet.setPositionX(x);
			selectedPlanet.setPositionY(y);
			selectedPlanet.setPositionZ(z);
		}
		
		try {
			x=Float.valueOf(xVelocityText.getText());
			y=Float.valueOf(yVelocityText.getText());
			z=Float.valueOf(zVelocityText.getText());
		} catch (NumberFormatException e) {
			System.err.println("Position Save Error");
		}finally {
			selectedPlanet.setVelocityX(x);
			selectedPlanet.setVelocityY(y);
			selectedPlanet.setVelocityZ(z);
		}
		
		try {
			Float.valueOf(xAccelerationText.getText());
			Float.valueOf(yAccelerationText.getText());
			Float.valueOf(zAccelerationText.getText());
		} catch (NumberFormatException e) {
			System.err.println("Position Save Error");
		}finally {
			selectedPlanet.setAccelerationX(x);
			selectedPlanet.setAccelerationY(y);
			selectedPlanet.setAccelerationZ(z);
		}
	}
	
	private void initMenu() {
		JPanel selectPanel = new JPanel();
		DefaultListModel<String> listaElementy = new DefaultListModel<String>();
		JList<String> lista = new JList<String>(listaElementy);
		for (int i=0; i < Wyswietl.planets.size(); i++) {
			listaElementy.addElement(Wyswietl.planets.get(i).getName());
		}
		lista.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				
				if (lsm.isSelectionEmpty()) {
	                System.err.println("Selection went wrong");
	            } else {
	                // Find out which indexes are selected.
	                int minIndex = lsm.getMinSelectionIndex();
	                int maxIndex = lsm.getMaxSelectionIndex();
	                for (int i = minIndex; i <= maxIndex; i++) {
	                    if (lsm.isSelectedIndex(i)) {
	                    	System.out.println(" " + i);
	                    }
	                }
	            }
				
			}
		});
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//lista.setVisibleRowCount(5);
		JScrollPane listScrollPane = new JScrollPane(lista);
		//listScrollPane.setPreferredSize( new Dimension(300,100));
		selectPanel.add(listScrollPane);
		panelCenter.add(selectPanel);
		
		selectedPlanet = Wyswietl.planets.get(0);
		
		
		JPanel labelPanel = new JPanel();
		nameLabel = new JTextField(selectedPlanet.getName());
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
		xPositionText = new JTextField(Double.toString(selectedPlanet.getPositionX()));
		positionPanel.add(xPositionText);
		JLabel pYLabel = new JLabel(Wyswietl.bundle.getString("pYText"));
		positionPanel.add(pYLabel);
		yPositionText = new JTextField(Double.toString(selectedPlanet.getPositionY()));
		positionPanel.add(yPositionText);
		JLabel pZLabel = new JLabel(Wyswietl.bundle.getString("pZText"));
		positionPanel.add(pZLabel);
		zPositionText = new JTextField(Double.toString(selectedPlanet.getPositionZ()));
		positionPanel.add(zPositionText);
		panelCenter.add(positionPanel);
		
		JPanel velocityPanel = new JPanel();
		JLabel vXLabel = new JLabel(Wyswietl.bundle.getString("vXText"));
		velocityPanel.add(vXLabel);
		xVelocityText = new JTextField(Double.toString(selectedPlanet.getVelocityX()));
		velocityPanel.add(xVelocityText);
		JLabel vYLabel = new JLabel(Wyswietl.bundle.getString("vYText"));
		velocityPanel.add(vYLabel);
		yVelocityText = new JTextField(Double.toString(selectedPlanet.getVelocityY()));
		velocityPanel.add(yVelocityText);
		JLabel vZLabel = new JLabel(Wyswietl.bundle.getString("vZText"));
		velocityPanel.add(vZLabel);
		zVelocityText = new JTextField(Double.toString(selectedPlanet.getVelocityZ()));
		velocityPanel.add(zVelocityText);
		panelCenter.add(velocityPanel);
		
		JPanel accelerationPanel = new JPanel();
		JLabel aXLabel = new JLabel(Wyswietl.bundle.getString("aXText"));
		accelerationPanel.add(aXLabel);
		xAccelerationText = new JTextField(Double.toString(selectedPlanet.getAccelerationX()));
		accelerationPanel.add(xAccelerationText);
		JLabel aYLabel = new JLabel(Wyswietl.bundle.getString("aYText"));
		accelerationPanel.add(aYLabel);
		yAccelerationText = new JTextField(Double.toString(selectedPlanet.getAccelerationY()));
		accelerationPanel.add(yAccelerationText);
		JLabel aZLabel = new JLabel(Wyswietl.bundle.getString("aZText"));
		accelerationPanel.add(aZLabel);
		zAccelerationText = new JTextField(Double.toString(selectedPlanet.getAccelerationZ()));
		accelerationPanel.add(zAccelerationText);
		panelCenter.add(accelerationPanel);
		
		JPanel endPanel = new JPanel();
		JButton quitButton = new JButton(Wyswietl.bundle.getString("quitText"));
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		JButton saveButton = new JButton(Wyswietl.bundle.getString("saveText"));
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});
		
		JButton deleteButton = new JButton(Wyswietl.bundle.getString("deleteText"));
		
		endPanel.add(saveButton);
		endPanel.add(deleteButton);
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

	public EditObjectFrame(MainMenu t) throws HeadlessException {
		that=t;
		initFrame();
	}
	
	public EditObjectFrame(MainMenu t,Planet planet) throws HeadlessException {
		that=t;
		Wyswietl.planets.add(planet);
		this.selectedPlanet=planet;
		initFrame();
	}

	public EditObjectFrame(Planet nplanet) {
		Wyswietl.planets.add(nplanet);
		this.selectedPlanet=nplanet;
		initFrame();
	}
}
