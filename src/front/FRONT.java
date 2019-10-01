package front;

/**
 * @info This is my attempt at making a music player.
 * 
 * @author Darrel2018
 * 
 * @since 9/11/2019
 * 
 * @version 0.6
 */

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import functions.Functions;

public class FRONT {
	
	private static int WIDTH = 800, HEIGHT = 500;
	
	private JFrame frame;
	private Functions func;
	
	private JPanel mPanel;
	
	// Constructor.
	public FRONT(){
		
		frame = new JFrame();
		func = new Functions();
		
		createView();
	}
	
	// Creates GUI for frame and displays it.
	private void createView(){
		
		frame.setLayout(null);
		
		mPanel = new JPanel();
		mPanel.setBounds(200, 0, WIDTH-200, HEIGHT);
		mPanel.setBackground(func.setColor(132, 132, 132));
		
		frame.add(sidePanel());
		frame.add(mPanel);
	}
	
	// Creates sidePanel.
	private JPanel sidePanel(){
		
		int sidePanelWidth = 200;
		
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(0, 0, sidePanelWidth, HEIGHT);
		panel.setBackground(func.setColor(132, 132, 132));
		
		panel.add(func.createSidePanelHeader(0, 20, sidePanelWidth, 80));
		
		JPanel select_music = func.createSidePanelButton(0, 150, sidePanelWidth, "res\\images\\select.png", "Select Music");
		JPanel add_music = func.createSidePanelButton(0, 150 + (50 * 1 - 1), sidePanelWidth, "res\\images\\add.png", "Add Music");
		JPanel remove_music = func.createSidePanelButton(0, 150 + (50 * 2 - 2), sidePanelWidth, "res\\images\\remove.png", "Remove Music");
		JPanel exit = func.createSidePanelButton(0, 150 + (50 * 5 - 5), sidePanelWidth, "res\\images\\exit.png", "Exit");
		
		func.addButtonListeners(select_music, "Select Music", mPanel);
		func.addButtonListeners(add_music, "Add Music", mPanel);
		func.addButtonListeners(remove_music, "Remove Music", mPanel);
		func.addButtonListeners(exit, "Exit", mPanel);
		
		panel.add(select_music);
		panel.add(add_music);
		panel.add(remove_music);
		panel.add(exit);
		
		return panel;
	}
	
	//----====MAIN====----
	public static void main(String[] args){
		FRONT front = new FRONT();
		
		front.frame.setSize(new Dimension(WIDTH, HEIGHT));
		front.frame.setResizable(false);
		front.frame.setLocationRelativeTo(null);
		front.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		front.frame.setTitle("Music Player");
		
		front.frame.setVisible(true);
	}
}
