package pl.edu.pw.fizyka.pojava.Uklad_Planetarny.application;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
				
			}
        	
        });
        languageSelect.setSelectedIndex(0);
        languageSelect.setEditable(true);
        panelMain.add(languageSelect);
        
        JButton saveSettingsButton = new JButton("Save Settings");
        SettingsFrame that = this;
        saveSettingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text = new JLabel(Wyswietl.bundle.getString("developmentText"));
				text2 = new JLabel(Wyswietl.bundle.getString("authorText"));
				that.repaint();
			}
        });
        panelMain.add(saveSettingsButton);
        
        this.text = new JLabel(Wyswietl.bundle.getString("developmentText"));
        panelMain.add(this.text);
        this.text2 = new JLabel(Wyswietl.bundle.getString("authorText"));
        panelMain.add(this.text2);
        
	}

	public SettingsFrame() throws HeadlessException {
		initFrame();
		this.setVisible(true);
	}
}