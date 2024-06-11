package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
/**
 * @author Wojtek
 */
public class SettingsFrame extends JFrame {
	private JLabel text;
	private JLabel text2;
	MainMenu nie;
	
	private void initFrame() {
		setTitle("Settings - PPSWS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        
        JPanel panelMain = new JPanel();
        this.add(panelMain);
        panelMain.setLayout(new BoxLayout(panelMain,BoxLayout.Y_AXIS));
        
        SettingsFrame that = this;
        
        String [] language = {"Polski","English","Deutsch - still in developmend"};
        JComboBox<String> languageSelect = new JComboBox<String>(language);
        languageSelect.setMaximumSize(new Dimension(300,30));
        languageSelect.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(languageSelect.getSelectedItem()=="Polski") {
					Display.changeLocale("PL");
				}else if(languageSelect.getSelectedItem()=="English"){
					Display.changeLocale("ENG");
				}else{
					System.out.println("Ajaj");
				}
				text.setText(Display.bundle.getString("developmentText"));
				text2.setText(Display.bundle.getString("authorText"));
				that.repaint();
			}
        	
        });
        languageSelect.setSelectedIndex(0);
        languageSelect.setEditable(true);
        panelMain.add(languageSelect);
        
        JButton saveSettingsButton = new JButton(Display.bundle.getString("saveText"));
        JButton saveFileButton = new JButton(Display.bundle.getString("saveToFile"));
        
        saveSettingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.setText(Display.bundle.getString("developmentText"));
				text2.setText(Display.bundle.getString("authorText"));
				saveSettingsButton.setText(Display.bundle.getString("saveText")+" "+Display.bundle.getString("settingsText"));
				saveFileButton.setText(Display.bundle.getString("saveToFile"));
				nie.language();
				that.repaint();
			}
        });
        panelMain.add(saveSettingsButton);
        
        
        saveFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
        });
        panelMain.add(saveFileButton);
        
        
        
        this.text = new JLabel(Display.bundle.getString("developmentText"));
        panelMain.add(this.text);
        this.text2 = new JLabel(Display.bundle.getString("authorText"));
        panelMain.add(this.text2);
        
	}
        
        

	public SettingsFrame(MainMenu tak) throws HeadlessException {
		initFrame();
		this.setVisible(true);
		nie = tak;
	}
	
	public void save() {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Save File");
			chooser.setFileFilter(new FileNameExtensionFilter("TXT", "txt"));
			chooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
			chooser.setSelectedFile(new File("~\\untitled.txt"));
			chooser.setAcceptAllFileFilterUsed(false);
			
			int userSelection = chooser.showSaveDialog(this);
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    File fileToSave = chooser.getSelectedFile();
			    
			    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			    
			    Functions.fileName=fileToSave.getAbsolutePath();
			}
	}
}