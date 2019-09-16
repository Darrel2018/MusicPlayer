package functions;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Functions {
	
	public Color setColor(int r, int g, int b){
		Color color = new Color(r, g, b);
		return color;
	}
	
	public JPanel createSidePanelButton(int x, int y, int sidePanelWidth, String iconPathLeft, String text){
		
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		
		panel.setBounds(x, y, sidePanelWidth, 50);
		panel.setBackground(setColor(132, 132, 132));
		panel.setBorder(BorderFactory.createLineBorder(setColor(255, 255, 255)));
		
		panel.add(createSideButtonIcon(0, 0, iconPathLeft));
		panel.add(createTextLabel(70, 0, setColor(255, 255, 255), new Font("Segoe UI", 0, 12), text));
		panel.add(createSideButtonIcon(170, 0, "res\\images\\next.png"));
		
		return panel;
	}
	
	public JLabel createTextLabel(int x, int y, Color color, Font font, String text){
		
		JLabel label = new JLabel();
		
		label.setBounds(x, y, 78, 50);
		label.setText(text);
		label.setForeground(color);
		label.setFont(font);
		
		return label;
	}
	
	private JLabel createSideButtonIcon(int x, int y, String path){
		
		JLabel iconLabel = new JLabel();
		
		iconLabel.setIcon(new ImageIcon(path));
		iconLabel.setBounds(x, y, 50, 50);
		
		return iconLabel;
	}
}
