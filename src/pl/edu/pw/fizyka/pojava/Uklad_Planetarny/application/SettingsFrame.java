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
public class SettingsFrame extends JFrame {
	private JLabel text;
	private JLabel text2;
	
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
					Wyswietl.changeLocale("PL");
				}else if(languageSelect.getSelectedItem()=="English"){
					Wyswietl.changeLocale("ENG");
				}else{
					System.out.println("Ajaj");
				}
				text.setText(Wyswietl.bundle.getString("developmentText"));
				text2.setText(Wyswietl.bundle.getString("authorText"));
				that.repaint();
			}
        	
        });
        languageSelect.setSelectedIndex(0);
        languageSelect.setEditable(true);
        panelMain.add(languageSelect);
        
        JButton saveSettingsButton = new JButton(Wyswietl.bundle.getString("saveText"));
        
        saveSettingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.setText(Wyswietl.bundle.getString("developmentText"));
				text2.setText(Wyswietl.bundle.getString("authorText"));
				that.repaint();
			}
        });
        panelMain.add(saveSettingsButton);
        
        JButton saveFileButton = new JButton(Wyswietl.bundle.getString("saveToFile"));
        saveFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
        });
        panelMain.add(saveFileButton);
        
        
        
        this.text = new JLabel(Wyswietl.bundle.getString("developmentText"));
        panelMain.add(this.text);
        this.text2 = new JLabel(Wyswietl.bundle.getString("authorText"));
        panelMain.add(this.text2);
        
	}
        
        

	public SettingsFrame() throws HeadlessException {
		initFrame();
		this.setVisible(true);
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