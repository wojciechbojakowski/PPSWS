package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.simulation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalTime;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javafx.application.Platform;
import pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application.Wyswietl;

@SuppressWarnings("serial")
public class SimulationFrame extends JFrame {
	private Simulation jfxPanel;
	private JButton stopButton;
	private JButton addButton;
	private JButton menuButton;
	private JLabel timeLabel;
	String timeText;
	private JPanel up;
	private JPanel down;
	private Component box;
	private SimulationFrame tak;
	
	
	private void createUpPanel() {
		up.setLayout(new BoxLayout(up,BoxLayout.X_AXIS));
		box = Box.createHorizontalStrut(tak.getWidth()-200);
		menuButton = new JButton(Wyswietl.bundle.getString("menuText"));
        timeText = LocalTime.now().toString();
        timeLabel = new JLabel(timeText);
        timeLabel.setToolTipText(Wyswietl.bundle.getString("timeText"));
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        
        up = new JPanel();
        down = new JPanel();
        
        
        
        
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

