package com.davenicolette.i18n.workshop.app;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.davenicolette.i18n.demo.app.ResourceHelper;

/**
 * Java internationalization workshop Challenge 6 sample solution.
 * 
 * @author Dave Nicolette
 */
public class HelloWorldSolution6  implements Runnable {

	ResourceBundle greetingBundle;
	JLabel greetingLabel;
	JLabel desireLabel;
	JLabel dateLabel;
	String verb = "rent";
	String noun = "artichoke";
	
	public HelloWorldSolution6() {
		greetingLabel = new JLabel();
		desireLabel = new JLabel();
		dateLabel = new JLabel();
		setLocale(new Locale("de")); // default to German for demonstration purposes
//		setLocale(new Locale("")); // this will force the program to use the default bundle
	}

	/**
	 * Allow injection of different locales.
	 * @param locale
	 */
	public void setLocale(Locale locale) { 
		greetingBundle = ResourceHelper.getResourceBundle("HelloWorld6", locale);
		populateGUI();
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public void setNoun(String noun) {
		this.noun = noun;
	}
	
	/**
	 * Populate UI elements with localized text.
	 */
	private void populateGUI() {
		if (greetingBundle == null) {
			setLocale(Locale.getDefault());
		}
		greetingLabel.setText(greetingBundle.getString("greeting"));
		Object[] arguments = { greetingBundle.getString(verb), greetingBundle.getString(noun) };
		desireLabel.setText(MessageFormat.format(greetingBundle.getString("desire"), arguments));
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, greetingBundle.getLocale());
		String dateMessagePattern = greetingBundle.getString("today");
		String dateMessage = MessageFormat.format(dateMessagePattern, new Object[] { df.format(new Date()) } );
		dateLabel.setText(dateMessage);
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
        greetingPanel.add(Box.createVerticalStrut(2));
        greetingPanel.add(desireLabel);        
        greetingPanel.add(Box.createVerticalStrut(2));
        greetingPanel.add(dateLabel);        
        greetingPanel.add(Box.createVerticalStrut(10));
        
        frame.pack();
        frame.setVisible(true);
    }
	
	public void run() {
		createAndShowGUI();
	}

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new HelloWorldSolution6());
    }
}
