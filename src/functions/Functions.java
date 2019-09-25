package functions;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.ArrayList;

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
	public void addButtonListeners(JPanel panel, String buttonName, JPanel mPanel){
		
		panel.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e){
				System.out.println("Pressed " + buttonName);
				panel.setBackground(setColor(160, 160, 160));
			}
			
			public void mouseReleased(MouseEvent e){
				getButtonFunc(buttonName, mPanel);
				panel.setBackground(setColor(132, 132, 132));
			}
		});
	}
	
	// checks what button was pressed, then executes its following code.
	private void getButtonFunc(String button, JPanel mPanel){
		
		mPanel.removeAll();
		
		if(button.equals("Select Music")){
			selectMusic(mPanel);
		}
		else if(button.equals("Add Music")){
			System.out.println("func2");
		}
		else if(button.equals("Remove Music")){
			System.out.println("func3");
		}
		else if(button.equals("Exit")){
			System.err.println("EXITING...");
			System.exit(0);
		}
		else {
			System.err.println("ERROR: Cannot find button func...");
		}
	}
	
	private void selectMusic(JPanel mPanel){
		
		ArrayList<JPanel> objlist = new ArrayList<>();
		File file = new File("res\\music");
		
		File[] content = file.listFiles();
		for(File obj : content){
			
			if(obj.isFile()){
				System.out.println("Reading File: " + obj.getName());
				objlist.add(createMusicTab(obj.getName(), mPanel, objlist.size()));
			}
			
			if(obj.isDirectory()){
				System.out.println("Reading Directory: " + obj.getName());
			}
		}
		
		for(JPanel obj : objlist){
			mPanel.add(obj);
		}
		mPanel.repaint();
		
		// FIX THIS
		if(objlist.size() > 9){
			mPanel.addMouseWheelListener(new MouseWheelListener() {
				
				public void mouseWheelMoved(MouseWheelEvent e) {
					
					if(e.getPreciseWheelRotation() < 0){
						
						System.out.println("you moved the mouse wheel up");
						System.out.println(objlist.get(objlist.size()-1).getY() + objlist.get(objlist.size()-1).getHeight());
						if(objlist.get(objlist.size()-1).getY() + objlist.get(objlist.size()-1).getHeight() <= 490){
							for(JPanel obj : objlist){
								obj.setBounds(obj.getX(), obj.getY()+5, obj.getWidth(), obj.getHeight());
							}
						}
						
						// 450
					}
					else{
						
						System.out.println("you moved the mouse wheel down");
						System.out.println(objlist.get(objlist.size()-1).getY() + objlist.get(objlist.size()-1).getHeight());
						if(objlist.get(objlist.size()-1).getY() + objlist.get(objlist.size()-1).getHeight() >= 480){
							for(JPanel obj : objlist){
								obj.setBounds(obj.getX(), obj.getY()-5, obj.getWidth(), obj.getHeight());
							}
						}
					}
				}
			});
		}	
	}
	
	private JPanel createMusicTab(String name, JPanel mPanel, int tab){
		
		JPanel panel = new JPanel();
		
		panel.setBounds(0, 50 * tab, mPanel.getWidth(), 50);
		panel.setBackground(setColor(132, 132, 132));
		
		panel.add(createTextLabel(70, 0, setColor(255, 255, 255), new Font("Segoe UI", 0, 12), name, 78));
		
		return panel;
	}
}
