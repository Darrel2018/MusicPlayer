package functions;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class Functions {
	
	// Returns a Color object.
	public Color setColor(int r, int g, int b){
		Color color = new Color(r, g, b);
		return color;
	}
	
	// Creates side-Panel-Buttons.
	public JPanel createSidePanelButton(int x, int y, int sidePanelWidth, String iconPathLeft, String text){
		
		JPanel panel = new JPanel();
		
		panel.setLayout(null);
		
		panel.setBounds(x, y, sidePanelWidth, 50);
		panel.setBackground(setColor(132, 132, 132));
		panel.setBorder(BorderFactory.createLineBorder(setColor(255, 255, 255)));
		
		panel.add(createSideButtonIcon(0, 0, iconPathLeft));
		panel.add(createTextLabel(70, 0, setColor(255, 255, 255), new Font("Segoe UI", 0, 12), text, 78));
		panel.add(createSideButtonIcon(170, 0, "res\\images\\next.png"));
		
		return panel;
	}
	
	// Creates text-Labels.
	public JLabel createTextLabel(int x, int y, Color color, Font font, String text, int textLabelWidth){
		
		JLabel label = new JLabel();
		
		label.setBounds(x, y, textLabelWidth, 50);
		label.setText(text);
		label.setForeground(color);
		label.setFont(font);
		
		return label;
	}
	
	// Creates side-Button-Icons.
	private JLabel createSideButtonIcon(int x, int y, String path){
		
		JLabel iconLabel = new JLabel();
		
		iconLabel.setIcon(new ImageIcon(path));
		iconLabel.setBounds(x, y, 50, 50);
		
		return iconLabel;
	}
	
	// Creates side-Panel-Header.
	public JPanel createSidePanelHeader(int x, int y, int width, int height){
		
		JPanel panel = new JPanel();
		JSeparator sep = new JSeparator();
		
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBackground(setColor(132, 132, 132));
		
		sep.setBounds(0, height-2, width, 10);
		sep.setForeground(setColor(255, 255, 255));
		sep.setBackground(setColor(255, 255, 255));
		
		panel.add(sep);
		panel.add(createTextLabel(17, 0, setColor(255, 255, 255), new Font("Segoe UI", 0, 30), "Music Player", 170));
		
		return panel;
	}
	
	// adds listeners to buttons
	public void addButtonListeners(JPanel panel, String buttonName){
		
		panel.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e){
				System.out.println("Pressed " + buttonName);
			}
			
			public void mouseReleased(MouseEvent e){
				getButtonFunc(buttonName);
			}
		});
	}
	
	// checks what button was pressed, then executes its following code.
	private void getButtonFunc(String button){
		
		if(button.equals("Select Music")){
			System.out.println("func1");
		}
		else if(button.equals("Add Music")){
			System.out.println("func2");
		}
		else if(button.equals("Remove Music")){
			System.out.println("func3");
		}
		else if(button.equals("Exit")){
			System.out.println("func4");
		}
		else {
			System.err.println("ERROR: Cannot find button func...");
		}
	}
}
