package com.davenicolette.i18n.demo.app;
import java.awt.Container;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Demonstration of Java support for bidirectional text entry.
 * 
 * @author Dave Nicolette
 */
public class BidirectionalTextEntry {

	private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        //Create and set up the window.
        JFrame frame = new JFrame("Bidirectional text entry");
        Container pane = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JPanel headerPane = new JPanel();
        JLabel header = new JLabel(" Enter text using different character sets and observe how the insertion point moves ");
        headerPane.add(header);
        JPanel textBoxPane = new JPanel();
        JTextArea textBox = new JTextArea(10,80);
        textBoxPane.add(textBox);
        
        pane.add(headerPane);
        pane.add(Box.createVerticalStrut(10));
        pane.add(textBoxPane);
        pane.add(Box.createVerticalStrut(10));
        
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
