package com.davenicolette.i18n.demo.app;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Demonstration of ResourceBundle search behavior.
 * 
 * @author Dave Nicolette
 */
public class ResourceBundleSearch implements ActionListener, Runnable {
	private JFrame frame;
    
    public void actionPerformed(ActionEvent e) {
    	if (e.getActionCommand().equals("exit")) {
    		frame.dispose();
    	}
    }
    		
	private String findTheBundle(String name, String language, String country, String variant) {
		String result = "Looking for a bundle named '" + name + 
				"' for language '" + language + "'" +
				", country " + (country == "" ? "(none)" : "'" + country + "'") + 
				", variant " + (variant == "" ? "(none)" : "'" + variant + "'") + "\n";
		Locale locale = new Locale(language, (country == null ? "" : country), (variant == null ? "" : variant));
		try {
			ResourceBundle resources = ResourceBundle.getBundle("com.davenicolette.i18n.demo.resources." + name,locale);
			result += "\nhello: " + resources.getString("hello")
			       + "\ngoodbye: " + resources.getString("goodbye") + "\n\n";
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        //Create and set up the window.
        frame = new JFrame("ResourceBundle search demo");
        Container contentPane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        // Menubar for the application
        JMenuBar menuBar = new JMenuBar();

        // Conventional File menu containing just one item - Exit
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.getAccessibleContext().setAccessibleDescription(
        	"File menu");
        menuBar.add(fileMenu);

        JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        exitItem.getAccessibleContext().setAccessibleDescription("End the program");
        exitItem.setActionCommand("exit");
        exitItem.setToolTipText("End the program");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);
		
        JPanel textBoxPane = new JPanel();
        textBoxPane.setLayout(new BorderLayout());
        textBoxPane.setBackground(new Color(92,108,120));
        textBoxPane.setBorder(new CompoundBorder
        	(BorderFactory.createRaisedBevelBorder(),
        	 new EmptyBorder(10,10,10,10)));
        JTextArea outputBox = new JTextArea(10,60);
        outputBox.setEditable(false);
        textBoxPane.add(outputBox);

        // Look for ResourceBundles at different levels of detail and display the results
        
		outputBox.setText(findTheBundle("Whoopie", "en", "", ""));
		outputBox.append(findTheBundle("Whoopie", "en", "US", ""));
		outputBox.append(findTheBundle("Whoopie", "en", "US", "ValleyGirl"));
        
        frame.setJMenuBar(menuBar);
        contentPane.add(textBoxPane);

        frame.pack();
        frame.setVisible(true);
    }
		
	public void run() {
		createAndShowGUI();
	}
	
    public static void main(String[] args) {
    	javax.swing.SwingUtilities.invokeLater(new ResourceBundleSearch());
    }
}