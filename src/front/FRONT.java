package front;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import functions.Functions;

public class FRONT {
	
	private static int WIDTH = 800, HEIGHT = 500;
	
	private JFrame frame;
	private Functions func;
	
	public FRONT(){
		
		frame = new JFrame();
		func = new Functions();
		
		createView();
	}
	
	private void createView(){
		
		frame.setLayout(null);
		
		frame.add(sidePanel());
	}
	
	private JPanel sidePanel(){
		
		int sidePanelWidth = 200;
		
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(0, 0, sidePanelWidth, HEIGHT);
		panel.setBackground(func.setColor(192, 192, 192));
		
		panel.add(func.createSidePanelButton(0, 150, sidePanelWidth, "res\\images\\select.png", "Select Music"));
		panel.add(func.createSidePanelButton(0, 150 + (50 * 1 - 1), sidePanelWidth, "res\\images\\add.png", "Add Music"));
		panel.add(func.createSidePanelButton(0, 150 + (50 * 2 - 2), sidePanelWidth, "res\\images\\remove.png", "Remove Music"));
		panel.add(func.createSidePanelButton(0, 150 + (50 * 5 - 5), sidePanelWidth, "res\\images\\exit.png", "Exit"));
		
		return panel;
	}
	
	public static void main(String[] args){
		FRONT front = new FRONT();
		
		front.frame.setSize(new Dimension(WIDTH, HEIGHT));
		front.frame.setResizable(false);
		front.frame.setLocationRelativeTo(null);
		front.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		front.frame.setVisible(true);
	}
}
