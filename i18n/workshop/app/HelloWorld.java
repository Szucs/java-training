package com.davenicolette.i18n.workshop.app;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Hello World program - starting point for Java internationalization workshop Challenge #1.
 * 
 * @author Dave Nicolette
 */
public class HelloWorld  implements Runnable {

	JLabel greetingLabel;
	
	public HelloWorld() {
		greetingLabel = new JLabel();
	}
	
	/**
	 * This is the method where you can add logic for the Challenges.
	 */
	private void populateGUI() {
		greetingLabel.setText("Hello, World!");
	}
	
	private void createAndShowGUI() {
        populateGUI();
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Greeter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBorder(new EmptyBorder(10,30,10,30));
        backgroundPanel.setBackground(new Color(92,108,120));
        pane.add(backgroundPanel);
        
        JPanel greetingPanel = new JPanel();
        greetingPanel.setLayout(new BoxLayout(greetingPanel, BoxLayout.Y_AXIS));
        greetingPanel.add(Box.createVerticalStrut(10));
        greetingPanel.setBorder(new CompoundBorder
        	(BorderFactory.createRaisedBevelBorder(),
        	 new EmptyBorder(10,10,10,10)));
        backgroundPanel.add(greetingPanel, BorderLayout.CENTER);
        greetingPanel.add(greetingLabel);        
        greetingPanel.add(Box.createVerticalStrut(10));
        
        frame.pack();
        frame.setVisible(true);
    }
	
	public void run() {
		createAndShowGUI();
	}

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new HelloWorld());
    }
}
