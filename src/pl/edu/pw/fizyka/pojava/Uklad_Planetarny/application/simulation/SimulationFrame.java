package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.simulation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalTime;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import javafx.application.Platform;
import pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.EditObjectFrame;
import pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.Functions;
import pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.MainMenu;
import pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.Planet;
import pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.Wyswietl;

@SuppressWarnings("serial")
public class SimulationFrame extends JFrame {
	private Simulation jfxPanel;
	private JButton stopButton;
	private JButton addButton;
	private JButton menuButton;
	private JTextField timeLabel;
	String timeText;
	private JPanel up;
	private JPanel down;
	private Component box;
	private SimulationFrame tak;
	private MainMenu that;
	
	public void updateTextTime() {
		timeText = Functions.getTime();
		timeLabel.setText(timeText);
		timeLabel.repaint();
		up.revalidate();
		up.repaint();
	}
	
	private void createUpPanel() {
		up.setLayout(new BoxLayout(up,BoxLayout.X_AXIS));
		box = Box.createHorizontalStrut(tak.getWidth()-200);
		menuButton = new JButton(Wyswietl.bundle.getString("menuText"));
		timeText = Functions.getTime();
        timeLabel = new JTextField(timeText);
        timeLabel.setEnabled(false);
        //timeLabel.setToolTipText(Wyswietl.bundle.getString("timeText"));
        up.add(menuButton);
        
        box = Box.createHorizontalStrut(this.getWidth()-200);
        up.add(box);
        up.add(timeLabel);
		up.revalidate();
		up.repaint();
	}
	
	private void changeWidthBox() {
		up.removeAll();
		this.createUpPanel();
		tak.getContentPane().revalidate();
		tak.getContentPane().repaint();
	}
	
	private void initFrameSimulation() {
		tak = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
		setTitle(Wyswietl.bundle.getString("appTitleText"));
		this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                //System.out.println("Size Changed");
                tak.changeWidthBox();
            }
        });
	}

	public SimulationFrame() throws HeadlessException {
		this.initFrameSimulation();
		
        jfxPanel = new Simulation();
        stopButton = new JButton(Wyswietl.bundle.getString("stopText"));
        addButton = new JButton(Wyswietl.bundle.getString("addText"));
        
        //zatrzymywanie obliczeń
        ActionListener stopListener = new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
					protected Void doInBackground() throws Exception {
						if(MainMenu.getD() == 1) {
							MainMenu.setD(2);
						}
						else if(MainMenu.getD() == 2){
							MainMenu.setD(1);
							MainMenu.getButtonStart().doClick();
						}					
						return null;
					}
				};worker.execute();
        	}
		};
		stopButton.addActionListener(stopListener);
		
		
		
		ActionListener stopNameListener = new ActionListener() {//zmiana nazwy przycisku "zatrzymaj/wznów"
			@Override 
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
					protected Void doInBackground() throws Exception {
						if(stopButton.getText() == Wyswietl.bundle.getString("stopText")) {			
							System.out.println("Sukces!!!!!");
							stopButton.setText(Wyswietl.bundle.getString("resumeText"));
						}
						else if(stopButton.getText() == Wyswietl.bundle.getString("resumeText")){
							System.out.println("Kolejny sukces!!!!!");
							stopButton.setText(Wyswietl.bundle.getString("stopText"));
						}	
						//System.out.println("EDT: "+SwingUtilities.isEventDispatchThread());
						return null;
					}
				};worker.execute();
        	}
		};
		stopButton.addActionListener(stopNameListener);




		ActionListener addListener = new ActionListener() {//listener do edycji
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Planet nplanet = new Planet();
				nplanet.setName("Nowa Planeta");
				@SuppressWarnings("unused")
				EditObjectFrame edit = new EditObjectFrame(nplanet,jfxPanel);
			}
		};
		addButton.addActionListener(addListener);
        //tu się kończy moja ingerencja
        
        
        up = new JPanel();
        down = new JPanel();
        
        Timer timer = new Timer(10, null);
        timer.setRepeats(true);
        timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateTextTime();
			}
        	
        });
        
        timer.start();
        
        
        this.add(jfxPanel,BorderLayout.CENTER);
        down.add(stopButton);
        down.add(addButton,FlowLayout.LEFT);
        
        this.createUpPanel();
        this.add(up,BorderLayout.PAGE_START);
        this.add(down,BorderLayout.PAGE_END);
        
        //Platform.runLater(jfxPanel::createScene);
        //Start JAVAFX PANEL AND GENERATE WHAT HAPPENS NEXT
        Platform.runLater(new Runnable() {
			@Override
			public void run() {
				jfxPanel.initFX();
			}
        });
        this.setVisible(true);
        }
	
	public static void startAnimation() {
		Platform.startup(()->{});

        SwingUtilities.invokeLater(() -> {
            @SuppressWarnings("unused")
			SimulationFrame main = new SimulationFrame();
        });
	}

}

