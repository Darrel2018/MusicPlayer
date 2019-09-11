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
		
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		panel.setBounds(0, 0, 200, HEIGHT);
		panel.setBackground(func.setColor(32, 32, 32));
		
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
